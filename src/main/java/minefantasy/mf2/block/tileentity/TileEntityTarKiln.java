package minefantasy.mf2.block.tileentity;

import minefantasy.mf2.api.crafting.IHeatUser;
import minefantasy.mf2.api.helpers.CustomToolHelper;
import minefantasy.mf2.api.refine.SmokeMechanics;
import minefantasy.mf2.block.refining.BlockTarKiln;
import minefantasy.mf2.block.tileentity.blastfurnace.TileEntityBlastFH;
import minefantasy.mf2.item.list.ComponentListMF;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

import java.util.Random;

public class TileEntityTarKiln extends TileEntity implements IInventory, IHeatUser {
    private ItemStack[] inv = new ItemStack[1];
    public int tarOnOut = 0; //max tar on out = 8
    public float temperature;
    public float progress = 0, progressMax = 500;
    private Random rand = new Random();


    @Override
    public void updateEntity() {
        super.updateEntity();
        temperature = getTemperature();

        if (canWork()) {
            progress += (temperature / 1000F); //process speed
            if (progress >= progressMax) {
                progress = 0;
                getTar();
            }
        } else {
            progress = 0;
        }
        if (progress > 0 && rand.nextInt(3) == 0 && !isOutside()) {
            SmokeMechanics.emitSmokeIndirect(worldObj, xCoord, yCoord, zCoord, 1);
        }
        if (!worldObj.isRemote)
            BlockTarKiln.updateBlockState(getTarOnOut() > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);


    }

    public void getTar() {
        if (!canWork()) {
            return;
        }

        tarOnOut += 1;

        //consume bark
        if (CustomToolHelper.areEqual(inv[0], getBark()))
            inv[0].stackSize = inv[0].stackSize - 8;

        if (inv[0].stackSize <= 0) {
            inv[0] = null;
        }

    }


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

    public ItemStack getResultTar () {
        return new ItemStack(ComponentListMF.tar);
    }
    public ItemStack getBark () {
        return new ItemStack(ComponentListMF.bark);
    }

    public int getTarOnOut () {
            return tarOnOut;
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

        tarOnOut = nbt.getInteger("tarOnOut");

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

        nbt.setInteger("tarOnOut", tarOnOut);

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
        return "gui.tar_kiln.name";
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

    private boolean isBlastOutput() {
        if (worldObj == null)
            return false;
        TileEntity tile = worldObj.getTileEntity(xCoord, yCoord + 1, zCoord);
        return tile != null && tile instanceof TileEntityBlastFH;
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
