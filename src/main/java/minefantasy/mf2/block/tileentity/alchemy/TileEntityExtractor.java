package minefantasy.mf2.block.tileentity.alchemy;

import minefantasy.mf2.api.alchemy.CraftingManagerRefFurnace;
import minefantasy.mf2.api.alchemy.IRefFurnaceRecipe;
import minefantasy.mf2.api.crafting.IHeatUser;
import minefantasy.mf2.block.alchemy.BlockRefFurnace;
import minefantasy.mf2.block.alchemy.BlockRefFurnaceExtractor;
import minefantasy.mf2.block.tileentity.TileEntityForge;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

import java.util.Random;

public class TileEntityExtractor extends TileEntity implements IInventory {
    private ItemStack[] inv = new ItemStack[3];
    private float progress, temp;
    private Random rand = new Random();
    private int i, s;
    public boolean ruined;


    @Override
    public void updateEntity() {
        super.updateEntity();

        /*
        if (!worldObj.isRemote) {
            updateRecipe();
        }

        if (!worldObj.isRemote && canWork()) {
            temp = getTemperature();
            if (temp >= recipe.minTemp() && temp < recipe.maxTemp()) {
                ++progress;
            }
            if (progress >= recipe.time()) {
                for (i = 0; i < 3; i++){
                    if (recipe.result()[i] != null)
                        dropItem(recipe.result()[i]);
                }
                progress = 0;
                consumeResources();
            }
        } else {
            progress = 0;
        }
        if (!worldObj.isRemote) {
            BlockRefFurnace.updateBlockState(progress > 0, worldObj, xCoord, yCoord, zCoord);
        }*/

    }

    public void transferResult (ItemStack[] result) {
        for (int item = 0; item < 3; item++) {
            if (result[item] != null) {
                if (this.inv[item] == null) {
                    this.setInventorySlotContents(item, result[item]);
                } else {
                    if (this.inv[item].isItemEqual(result[item]) && ItemStack.areItemStackTagsEqual(this.inv[item], result[item])) {
                        if (this.inv[item].stackSize + inv[item].stackSize <= inv[item].getMaxStackSize()) {
                            this.inv[item].stackSize += result[item].stackSize;
                        } else {
                            dropItem(result[item]);
                        }
                    } else {
                        dropItem(result[item]);
                    }
                }
            }
        }
    }

    public boolean isRuined () {
        Block block = worldObj.getBlock(xCoord, yCoord, zCoord);
        if (block instanceof BlockRefFurnaceExtractor)
            return ((BlockRefFurnaceExtractor) block).ruined;
        return false;
    }

    public void dropItem (ItemStack itemStack) {
        if (!worldObj.isRemote && itemStack != null) {
            float f = this.rand.nextFloat() * 0.8F + 0.1F;
            float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
            float f2 = this.rand.nextFloat() * 0.8F + 0.1F;
            EntityItem entityitem = new EntityItem(worldObj, xCoord + f, yCoord + f1 + 1, zCoord + f2, itemStack);

            float f3 = 0.05F;
            entityitem.motionX = (float) this.rand.nextGaussian() * f3;
            entityitem.motionY = (float) this.rand.nextGaussian() * f3 + 0.2F;
            entityitem.motionZ = (float) this.rand.nextGaussian() * f3;
            worldObj.spawnEntityInWorld(entityitem);
        }
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inv[slot];
    }

    @Override
    public int getSizeInventory() {
        return inv.length;
    }



    @Override
    public ItemStack decrStackSize(int slot, int num) {
        onInventoryChanged();
        if (this.inv[slot] != null) {
            ItemStack itemstack;

            if (this.inv[slot].stackSize <= num) {
                itemstack = this.inv[slot];
                this.inv[slot] = null;
                return itemstack;
            } else {
                itemstack = this.inv[slot].splitStack(num);

                if (this.inv[slot].stackSize == 0) {
                    this.inv[slot] = null;
                }

                return itemstack;
            }
        } else {
            return null;
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        //tarOnOut = nbt.getInteger("tarOnOut");

        NBTTagList savedItems = nbt.getTagList("Items", 10);
        this.inv = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < savedItems.tagCount(); ++i) {
            NBTTagCompound savedSlot = savedItems.getCompoundTagAt(i);
            byte slotNum = savedSlot.getByte("Slot");

            if (slotNum >= 0 && slotNum < this.inv.length) {
                this.inv[slotNum] = ItemStack.loadItemStackFromNBT(savedSlot);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        //nbt.setInteger("tarOnOut", tarOnOut);

        NBTTagList savedItems = new NBTTagList();

        for (int i = 0; i < this.inv.length; ++i) {
            if (this.inv[i] != null) {
                NBTTagCompound savedSlot = new NBTTagCompound();
                savedSlot.setByte("Slot", (byte) i);
                this.inv[i].writeToNBT(savedSlot);
                savedItems.appendTag(savedSlot);
            }
        }

        nbt.setTag("Items", savedItems);
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack item) {
        onInventoryChanged();
        inv[slot] = item;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        return inv[slot];
    }

    @Override
    public String getInventoryName() {
        return "gui.extractor.name";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer user) {
        return user.getDistance(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) < 8D;
    }

    @Override
    public void openInventory() {
    }

    @Override
    public void closeInventory() {
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack item) {
        return true;
    }

    public void onInventoryChanged() {
    }


}
