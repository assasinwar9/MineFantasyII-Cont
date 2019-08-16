package minefantasy.mf2.block.tileentity;

import minefantasy.mf2.api.crafting.IHeatUser;
import minefantasy.mf2.api.helpers.CustomToolHelper;
import minefantasy.mf2.api.refine.SmokeMechanics;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.block.refining.BlockTarKiln;
import minefantasy.mf2.block.tileentity.blastfurnace.TileEntityBlastFH;
import minefantasy.mf2.item.food.FoodListMF;
import minefantasy.mf2.item.list.ComponentListMF;
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
    public float progress_0, progress_1, progress_2, progress_3, progressMax = 500;
    private Random rand = new Random();


    @Override
    public void updateEntity() {
        super.updateEntity();

        isActive = progress_0 > 0 || progress_1 > 0 || progress_2 > 0 || progress_3 > 0;

        temperature = getTemperature();

        //slot 0
        if (inv[0] != null && getResult(inv[0]) != null && temperature >= 800F) {
            progress_0 += temperature / 1000F;
            if (progress_0 >= progressMax) {
                progress_0 = 0;
                setInventorySlotContents(0, new ItemStack(getResult(inv[0]), inv[0].stackSize));
            }
        }
        //slot 1
        if (inv[1] != null && getResult(inv[1]) != null && temperature >= 800F) {
            progress_1 += temperature / 1000F;
            if (progress_1 >= progressMax) {
                progress_1 = 0;
                setInventorySlotContents(1, new ItemStack(getResult(inv[1]), inv[1].stackSize));
            }
        }
        //slot 3
        if (inv[2] != null && getResult(inv[2]) != null && temperature >= 800F) {
            progress_2 += temperature / 1000F;
            if (progress_2 >= progressMax) {
                progress_2 = 0;
                setInventorySlotContents(2, new ItemStack(getResult(inv[2]), inv[2].stackSize));
            }
        }
        //slot 4
        if (inv[3] != null && getResult(inv[3]) != null && temperature >= 800F) {
            progress_3 += temperature / 1000F;
            if (progress_3 >= progressMax) {
                progress_3 = 0;
                setInventorySlotContents(3, new ItemStack(getResult(inv[3]), inv[3].stackSize));
            }
        }
        /*
        if (canWork()) {
            progress += (temperature / 1000F); //process speed
            if (progress >= progressMax) {
                progress = 0;
            }
        } else {
            progress = 0;
        }
        if (progress > 0 && rand.nextInt(3) == 0 && !isOutside()) {
            SmokeMechanics.emitSmokeIndirect(worldObj, xCoord, yCoord, zCoord, 1);
        }
        if (!worldObj.isRemote)
            BlockTarKiln.updateBlockState(getTarOnOut() > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);*/


    }

    /*
    private boolean canWork() {
        if (temperature <= 0) {
            return false;
        }
        if (inv[0] == null || !CustomToolHelper.areEqual(inv[0], getBark()))
            return false;
        if (inv[0].stackSize < 8)
            return false;
        if (tarOnOut < 8);
            return true;
    }
    */

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

    public float getTemperature() {

        TileEntity tile = worldObj.getTileEntity(xCoord, yCoord - 1, zCoord);
        if (tile != null && tile instanceof TileEntityForge) {
            return ((TileEntityForge) tile).getBlockTemperature();
        }
        return 0F;
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
        return "gui.glasscaster.name";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 4;
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
