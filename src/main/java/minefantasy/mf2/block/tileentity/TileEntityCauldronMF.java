package minefantasy.mf2.block.tileentity;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

import java.util.Random;

public class TileEntityCauldronMF extends TileEntity implements IInventory {
    public float progress = 0, progressMax = 1000;
    public float temperature;
    private ItemStack[] inv = new ItemStack[1];
    private Random rand = new Random();
    public String liquid = "water";


    public int ticksExisted;



    @Override
    public void updateEntity() {
        super.updateEntity();

        //soaking


    }



  /*  public boolean interract (EntityPlayer user, ItemStack held) {
        if (held != null) {
            if (fill < capacity)
            {
                if (held.getItem() == Items.water_bucket) {
                    user.setCurrentItemOrArmor(0, new ItemStack(Items.bucket));
                    setCap(1);
                    liquid = "water";
                    soakingMode = false;
                    return true;
                }
            }
            //of color materials
            if (held.getItem() instanceof ItemColormats && fill > 0 && liquid == "water") {
                if (held.getItem() == ComponentListMF.colormat_black) liquid = "black";
                if (held.getItem() == ComponentListMF.colormat_red) liquid = "red";
                if (held.getItem() == ComponentListMF.colormat_green) liquid = "green";
                if (held.getItem() == ComponentListMF.colormat_brown) liquid = "brown";
                if (held.getItem() == ComponentListMF.colormat_blue) liquid = "blue";
                if (held.getItem() == ComponentListMF.colormat_purple) liquid = "purple";
                if (held.getItem() == ComponentListMF.colormat_cyan) liquid = "cyan";
                if (held.getItem() == ComponentListMF.colormat_light_gray) liquid = "light_gray";
                if (held.getItem() == ComponentListMF.colormat_gray) liquid = "gray";
                if (held.getItem() == ComponentListMF.colormat_pink) liquid = "pink";
                if (held.getItem() == ComponentListMF.colormat_lime) liquid = "lime";
                if (held.getItem() == ComponentListMF.colormat_yellow) liquid = "yellow";
                if (held.getItem() == ComponentListMF.colormat_light_blue) liquid = "light_blue";
                if (held.getItem() == ComponentListMF.colormat_magenta) liquid = "magenta";
                if (held.getItem() == ComponentListMF.colormat_orange) liquid = "orange";
                if (held.getItem() == ComponentListMF.colormat_white) liquid = "white";

                --held.stackSize;

                dropItems(user, new ItemStack(ComponentListMF.clay_pot));

                soakingMode = true;
                return true;
            }
            //set liming liquid
            if (held.getItem() == ComponentListMF.limestone_item_pot && fill > 0 && liquid == "water") {
                liquid = "limestone";

                --held.stackSize;
                dropItems(user, new ItemStack(ComponentListMF.clay_pot));

                soakingMode = true;
                return true;
            }

            // Take
            if (fill > 0 && held.getItem() == Items.bucket && !soakingMode) {
                --held.stackSize;
                dropItems(user, new ItemStack(Items.water_bucket));
                setCap(-1);
                return true;
            }

        }
        return false;
    }
*/

    /*private boolean isOutside() {
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (!worldObj.canBlockSeeTheSky(xCoord + x, yCoord + 1, zCoord + y)) {
                    return false;
                }
            }
        }
        return true;
    }
*/




    @Override
    public Block getBlockType() {
        if (worldObj == null)
            return Blocks.air;

        return super.getBlockType();
    }

    /*public void syncData() {
        if (worldObj.isRemote)
            return;

        NetworkUtils.sendToWatchers(new SoakingTroughPacket(this).generatePacket(), (WorldServer) worldObj, this.xCoord, this.zCoord);
    }*/



  @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setString("liquid", liquid);


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
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        liquid = nbt.getString("liquid");


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
    public int getSizeInventory() {
        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inv[slot];
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
    public ItemStack getStackInSlotOnClosing(int slot) {
        return inv[slot];
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack item) {
        onInventoryChanged();
        inv[slot] = item;
    }

    public void onInventoryChanged() {
    }

    @Override
    public String getInventoryName() {
        return "gui.cauldronMF.name";
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

}
