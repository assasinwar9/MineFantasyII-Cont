package minefantasy.mf2.block.tileentity.decor;

import minefantasy.mf2.api.heating.IQuenchBlock;
import minefantasy.mf2.api.helpers.CustomToolHelper;
import minefantasy.mf2.container.ContainerTrough;
import minefantasy.mf2.item.ItemColormats;
import minefantasy.mf2.item.food.FoodListMF;
import minefantasy.mf2.item.list.ComponentListMF;
import minefantasy.mf2.network.NetworkUtils;
import minefantasy.mf2.network.packet.TroughPacket;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.WorldServer;

public class TileEntityTrough extends TileEntityWoodDecor implements IQuenchBlock, IInventory {
    public static int capacityScale = 8;
    public int fill;
    private int ticksExisted;
    public String liquid = "water"; //liquid type: water, black
    public boolean soakingMode = false;
    public ItemStack[] inv = new ItemStack[1];
    public final ContainerTrough container;
    public float progress = 0, progressMax = 1200; //default progress max = 1 minute

    public ItemStack soakingItems;
    public float soakTimeScale;


    public TileEntityTrough() {
        super("trough_wood");
        container = new ContainerTrough(this);
    }

    @Override
    public float quench() {
        if (fill > 0) {
            --fill;
            return 0F;
        }
        return -1F;
    }

    public boolean interact(EntityPlayer user, ItemStack held) {
        if (held != null) {
            int glass_bottle = 1, bucket = 16;
            if (fill < getCapacity())// Give
            {
                if (held.getItem() == Items.water_bucket) {
                    user.setCurrentItemOrArmor(0, new ItemStack(Items.bucket));
                    addCapacity(bucket);
                    liquid = "water";
                    soakingMode = false;
                    return true;
                }
            }

            // Take
            if (fill >= 1 && held.getItem() == Items.glass_bottle && held.getItemDamage() == 0 && !soakingMode) {
                givePlayerItem(user, held, new ItemStack(Items.potionitem));
                addCapacity(-glass_bottle);
                return true;
            }
            if (fill >= 16 && held.getItem() == Items.bucket && !soakingMode) {
                givePlayerItem(user, held, new ItemStack(Items.water_bucket));
                addCapacity(-bucket);
                return true;
            }

        }
        // take soaking item
        if (held == null && inv[0] != null && inv[0].stackSize > 0) {
            user.entityDropItem(inv[0].copy(), 0F);
            setInventorySlotContents(0, null);
            return true;
    }
        return false;
    }

   private void setPlayerItems (EntityPlayer user, ItemStack held) {
       //max items for soaking = 4
       //itemCount = held.stackSize;

       --held.stackSize;
       if (held.stackSize <= 0)
           user.setCurrentItemOrArmor(0, null);
       if (!user.worldObj.isRemote) {
           user.entityDropItem(new ItemStack(ComponentListMF.clay_pot), 0F);
       }
   }

       private void givePlayerItem(EntityPlayer user, ItemStack held, ItemStack jug) {
        if (held.stackSize == 1) {
            user.setCurrentItemOrArmor(0, jug);
            return;
        }

        --held.stackSize;
        if (!user.inventory.addItemStackToInventory(jug)) {
            if (!user.worldObj.isRemote) {
                user.entityDropItem(jug, 0F);
            }
        }
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        ++ticksExisted;
        if (ticksExisted == 20 || ticksExisted % 100 == 0) {
            syncData();
        }
        if (ticksExisted % 100 == 0 && worldObj.canLightningStrikeAt(xCoord, yCoord + 1, zCoord)) {
            addCapacity(1);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("fill", fill);
        nbt.setString("liquid", liquid);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        fill = nbt.getInteger("fill");
        liquid = nbt.getString("liquid");

    }

    private void addCapacity(int i) {
        int cap = getCapacity();
        fill = Math.min(cap, fill + i);
        syncData();
    }

    @Override
    public int getCapacity() {
        return super.getCapacity() * capacityScale;
    }

    public void syncData() {
        if (worldObj.isRemote)
            return;

        NetworkUtils.sendToWatchers(new TroughPacket(this).generatePacket(), (WorldServer) worldObj, this.xCoord, this.zCoord);
        super.sendPacketToClient();
    }

    public void onInventoryChanged() {
    }

    @Override
    public int getSizeInventory() {
        return inv.length;
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
    @Override
    public String getInventoryName() {
        return "tile.invtrough.name";
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
        return false;
    }


}
