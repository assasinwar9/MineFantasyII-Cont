package minefantasy.mf2.block.tileentity;

import minefantasy.mf2.api.crafting.IHeatUser;
import minefantasy.mf2.api.helpers.CustomToolHelper;
import minefantasy.mf2.api.refine.SmokeMechanics;
import minefantasy.mf2.block.crafting.BlockGlasscaster;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.block.refining.BlockTarKiln;
import minefantasy.mf2.block.tileentity.blastfurnace.TileEntityBlastFH;
import minefantasy.mf2.item.food.FoodListMF;
import minefantasy.mf2.item.list.ComponentListMF;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

import java.util.Random;

public class TileEntityGlasscaster extends TileEntity implements IInventory, IHeatUser {
    private ItemStack[] inv = new ItemStack[4];
    public boolean isActive;
    public float temperature;
    public float[] progress = new float[4];
    private float progressMax = 200;
    private Random rand = new Random();
    private double partOffsetX, partOffsetZ, partOffsetY, s;
    private Block block;


    @Override
    public void updateEntity() {
        super.updateEntity();

        if (progress[0] > 0 || progress[1] > 0 || progress[2] > 0 || progress[3] > 0) {
            block = worldObj.getBlock(xCoord, yCoord, zCoord);
            if (block instanceof BlockGlasscaster)
                ((BlockGlasscaster) block).spawnParticle(worldObj, xCoord, yCoord, zCoord);
        }

        temperature = getTemperature();

        for (int i = 0; i < 4; i++) {
            if (inv[i] != null && getResult(inv[i]) != null && temperature >= 800F) {
                progress[i] += temperature / 1000F;
                if (progress[i] >= (progressMax * inv[i].stackSize * getProgressScale(inv[i].stackSize))) {
                    progress[i] = 0;
                    setInventorySlotContents(i, new ItemStack(getResult(inv[i]), inv[i].stackSize));
                }
            } else
                progress[i] = 0;
        }
    }

    private Item getResult (ItemStack item) {
        if (item != null) {
            //glass casting
            if (item.getItem() == ComponentListMF.glassmould_block_filled)
                return Item.getItemFromBlock(BlockListMF.glassmould_block_melted);
            if (item.getItem() == ComponentListMF.glassmould_bottle_filled)
                return Item.getItemFromBlock(BlockListMF.glassmould_bottle_melted);
            if (item.getItem() == ComponentListMF.glassmould_panel_filled)
                return Item.getItemFromBlock(BlockListMF.glassmould_panel_melted);
            //bake ceramic and chamotte
            if (item.getItem() == ComponentListMF.clay_pot_uncooked)
                return ComponentListMF.clay_pot;
            if (item.getItem() == FoodListMF.jug_uncooked)
                return FoodListMF.jug_empty;
            if (item.getItem() == ComponentListMF.pie_tray_uncooked)
                return FoodListMF.pie_tray;
            if (item.getItem() == ComponentListMF.ingot_mould_uncooked)
                return ComponentListMF.ingot_mould;
            if (item.getItem() == ComponentListMF.mine_casing_uncooked)
                return ComponentListMF.mine_casing;
            if (item.getItem() == ComponentListMF.bomb_casing_uncooked)
                return ComponentListMF.bomb_casing;
            if (item.getItem() == ComponentListMF.fireclay_brick)
                return ComponentListMF.strong_brick;

            if (item.getItem() == ComponentListMF.glassmould_block_uncooked)
                return ComponentListMF.glassmould_block;
            if (item.getItem() == ComponentListMF.glassmould_bottle_uncooked)
                return ComponentListMF.glassmould_bottle;
            if (item.getItem() == ComponentListMF.glassmould_panel_uncooked)
                return ComponentListMF.glassmould_panel;

            //
        }
        return null;
    }

    private float getProgressScale (int stackSize) {
        if (stackSize <= 4)
            return 1;
        else if (stackSize <= 8)
            return 1.2F;
        else if (stackSize <= 16)
            return 1.5F;
        else if (stackSize <= 32)
            return 2.0F;
        else if (stackSize > 32)
            return 2.5F;
        else return 1.0F;
    }

    public float getTemperature() {

        TileEntity tile = worldObj.getTileEntity(xCoord, yCoord - 1, zCoord);
        if (tile != null && tile instanceof TileEntityForge) {
            return ((TileEntityForge) tile).getBlockTemperature();
        }
        return 0F;
    }

    /*
    private void spawnParticle () {
        if (rand.nextInt(10) < 3) {
            s = 0.5; //basic centre-position offset
            if (worldObj.getBlockMetadata(xCoord, yCoord, zCoord) == 4) {
                partOffsetX = (double) (rand.nextInt(2) + 1) / 10 - s - 0.2;
                partOffsetZ = (double) (rand.nextInt(2) + 1) / 10 + s - 0.6;
                partOffsetY = (double) (rand.nextInt(3) + 1) / 10;
            }
            if (worldObj.getBlockMetadata(xCoord, yCoord, zCoord) == 5) {
                partOffsetX = (double) (rand.nextInt(2) + 1) / 10 + s;
                partOffsetZ = (double) (rand.nextInt(2) + 1) / 10 + s - 0.6;
                partOffsetY = (double) (rand.nextInt(3) + 1) / 10;
            }
            if (worldObj.getBlockMetadata(xCoord, yCoord, zCoord) == 2) {
                partOffsetX = (double) (rand.nextInt(2) + 1) / 10 + s - 0.6;
                partOffsetZ = (double) (rand.nextInt(2) + 1) / 10 - s - 0.2;
                partOffsetY = (double) (rand.nextInt(3) + 1) / 10;
            }
            if (worldObj.getBlockMetadata(xCoord, yCoord, zCoord) == 3) {
                partOffsetX = (double) (rand.nextInt(2) + 1) / 10 + s - 0.6;
                partOffsetZ = (double) (rand.nextInt(2) + 1) / 10 + s;
                partOffsetY = (double) (rand.nextInt(3) + 1) / 10;
            }
            worldObj.spawnParticle("smoke", xCoord + 0.5D + partOffsetX, yCoord + 0.4D + partOffsetY, zCoord + 0.5D + partOffsetZ, 0.0D, 0.0D, 0.0D);
        }
    }*/

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
        return "gui.glasscaster.name";
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

    @Override
    public boolean canAccept(TileEntity tile) {
        return tile instanceof TileEntityForge;
    }


    public void onInventoryChanged() {
    }

    private boolean isOutside() {
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (!worldObj.canBlockSeeTheSky(xCoord + x, yCoord + 1, zCoord + y)) {
                    return false;
                }
            }
        }
        return true;
    }

}
