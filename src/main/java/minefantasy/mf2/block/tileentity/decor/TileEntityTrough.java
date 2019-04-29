package minefantasy.mf2.block.tileentity.decor;

import minefantasy.mf2.api.heating.IQuenchBlock;
import minefantasy.mf2.api.helpers.CustomToolHelper;
import minefantasy.mf2.item.ItemColormats;
import minefantasy.mf2.item.food.FoodListMF;
import minefantasy.mf2.item.list.ComponentListMF;
import minefantasy.mf2.network.NetworkUtils;
import minefantasy.mf2.network.packet.TroughPacket;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldServer;

public class TileEntityTrough extends TileEntityWoodDecor implements IQuenchBlock {
    public static int capacityScale = 8;
    public int fill;
    private int ticksExisted;
    public String liquid; //liquid type: water, black
    public boolean soakingMode;

    public TileEntityTrough() {
        super("trough_wood");
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
                    if (held.stackSize <= 0)
                        user.setCurrentItemOrArmor(0, null);
                    givePlayerItem(user, held, new ItemStack(ComponentListMF.clay_pot));

                    soakingMode = true;
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
        return false;
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
        nbt.setBoolean("soakingMode", soakingMode);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        fill = nbt.getInteger("fill");
        liquid = nbt.getString("liquid");
        soakingMode = nbt.getBoolean("soakingMode");
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

}
