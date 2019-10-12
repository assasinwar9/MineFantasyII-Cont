package minefantasy.mf2.block.tileentity;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import minefantasy.mf2.block.tileentity.blastfurnace.TileEntityBlastFH;
import minefantasy.mf2.block.wizardry.BlockMagicChalice;
import minefantasy.mf2.item.tool.ItemSpadeMF;
import minefantasy.mf2.item.tool.crafting.ItemHammer;
import minefantasy.mf2.item.tool.crafting.ItemSpanner;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.Random;

public class TileEntityMagicChalice extends TileEntity implements IInventory {
    private ItemStack[] inv = new ItemStack[1];
    private Random rand = new Random();
    private int downMaxCraftLimit = -60;
    private int downCraftLimit = -60;
    private int upMaxCraftLimit = 60;
    private int upCraftLimit = 60;
    private float markerPos = 0;
    private int dropCount;
    private int clickCount;

    private int progress = 0;
    private int progressMax = 800;
    private Block currentBlock;

    private int level;
    private String modification;
    private TileEntity tile;
    private TileEntityMagicPedestal magicPedestal;

    private float speed = 1;
    private int tickExisted, ms;

    private int direction = 1; //+1 = right, -1 = left

    private boolean craftingPhase = false;

    @Override
    public void updateEntity() {
        super.updateEntity();
            ++tickExisted;
            if (tickExisted == 10) {
                ++downCraftLimit;
                --upCraftLimit;
            }
            if (tickExisted >= 20) {
                tickExisted = 0;
                checkStructure();
            }
        if (worldObj.isRemote) {

            //moving indicator
            ++ms;
            if (ms == 2) //exist 2 ticks or 100 ms
            {
                ms = 0;
                if (craftingPhase) {
                    markerPos += (float) direction * speed;
                }


            }
        }

            if (markerPos >= upCraftLimit || markerPos <= downCraftLimit) {
                if (worldObj.isRemote)
                    craftFinalization(true);
                progress = 0;
            }


            if (craftingPhase) {
                    ++progress;
                if (progress >= progressMax) {
                    craftingPhase = false;
                    if (worldObj.isRemote)
                        craftFinalization(false);
                    progress = 0;
                }
            }

    }


   /* private boolean isOutside() {
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (!worldObj.canBlockSeeTheSky(xCoord + x, yCoord + 1, zCoord + y)) {
                    return false;
                }
            }
        }
        return true;
    }*/

    public boolean interract (World world, int x, int y, int z, EntityPlayer user) {
        ItemStack held = user.getHeldItem();

        //debug
        if (held != null && held.getItem() instanceof ItemSpanner) {
            speed+=0.2F;
            if (!world.isRemote)
                Minecraft.getMinecraft().thePlayer.sendChatMessage("[DEBUG] Speed has been changed. Current speed = " + speed);
            return true;
        }
        if (held != null && held.getItem() instanceof ItemHammer) {
            speed-=0.2F;
            if (!world.isRemote)
                Minecraft.getMinecraft().thePlayer.sendChatMessage("[DEBUG] Speed has been changed. Current speed = " + speed);
            return true;
        }

        if (held != null && held.getItem() instanceof ItemSpadeMF) {
            if (speed < 2.0F)
                speed = 3.0F;
            else if (speed < 4.0F)
                speed = 4.0F;
            else if (speed < 5.0F)
                speed = 5.0F;
            else if (speed > 5.0F)
                speed = 1.0F;
            if (!world.isRemote)
                Minecraft.getMinecraft().thePlayer.sendChatMessage("[DEBUG] Difficulty lvl has been changed. Current speed = " + speed);
            return true;
        }

        //end of debug function

        if(!craftingPhase && magicPedestal != null && magicPedestal.validStructure) {
            if (held != null && held.getItem() == Items.emerald) {
                --held.stackSize;
                activation();
                return true;
            }
        }
        if (craftingPhase) {
            if (held != null && held.getItem() == Items.stick) {
                if (direction == 1) {
                    upCraftLimit = Math.round(markerPos);
                    markerPos -= 2;
                    ++clickCount;
                }
                if (direction == -1) {
                    downCraftLimit = Math.round(markerPos);
                    markerPos += 2;
                    ++clickCount;
                }
                direction *= -1;
                return true;
            }
        }
        return false;

    }

    public void activation () {
        craftingPhase  = true;
        direction = 1;
        upCraftLimit = upMaxCraftLimit;
        downCraftLimit = downMaxCraftLimit;
        magicPedestal.isActive = true;
    }

    private void craftFinalization (boolean ruin) {
        craftingPhase = false;
        markerPos = 0;
        clickCount = 0;
        upCraftLimit = upMaxCraftLimit;
        downCraftLimit = downMaxCraftLimit;
        magicPedestal.isActive = false;
        dropItem(worldObj, xCoord, yCoord, zCoord, ruin ? Item.getItemFromBlock(Blocks.dirt) : Items.diamond, 4, false, false);
        if (worldObj.getBlock(xCoord, yCoord, zCoord) instanceof BlockMagicChalice)
            ((BlockMagicChalice) worldObj.getBlock(xCoord, yCoord, zCoord)).spawnFinalParticle(worldObj, xCoord, yCoord, zCoord, "flame", "smoke");
        if (!worldObj.isRemote)
            Minecraft.getMinecraft().thePlayer.sendChatMessage("[DEBUG] craft finalization");
    }

    public void dropItem(World world, int x, int y, int z, Item item, int count, boolean isRandom, boolean noLoss) {
        dropCount = isRandom ? rand.nextInt(count) : count;
        if (isRandom && noLoss && dropCount == 0){
            dropCount = 1;
        }

        ItemStack itemStack = new ItemStack(item, dropCount);

        if (!world.isRemote) {
            EntityItem drop = new EntityItem(world, x + 0.5, y + 1.5, z + 0.5, itemStack);
            drop.delayBeforeCanPickup = 10;
            world.spawnEntityInWorld(drop);
        }
    }

    private void checkStructure () {
        tile = worldObj.getTileEntity(xCoord, yCoord - 1, zCoord);
        if (tile != null && tile instanceof TileEntityMagicPedestal)
            magicPedestal = (TileEntityMagicPedestal) tile;
        else magicPedestal = null;
    }

    public int getLevel () {
        if (this.getBlockType() != null && this.getBlockType() instanceof BlockMagicChalice)
            return  ((BlockMagicChalice) this.getBlockType()).getLvl();
        else return 0;
    }

    public String getModification () {
        if (this.getBlockType() != null && this.getBlockType() instanceof BlockMagicChalice)
            return  ((BlockMagicChalice) this.getBlockType()).getModification();
        else return "error";
    }


    public int getProgressBar () {
        return Math.round(progress * 115F / progressMax);
    }


    public boolean isCraftingPhase () {
        return craftingPhase;
    }

    public int getMarkerPosition () {
        return Math.round(markerPos);
    }

    public void moveMarker (int move) {
        markerPos += move;
    }

    public int getDownCraftLimit () {
        return downCraftLimit;
    }

    public int getUpCraftLimit () {
        return upCraftLimit;
    }

    @Override
    public Block getBlockType() {
        if (worldObj == null)
            return Blocks.air;

        return super.getBlockType();
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

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

    public void onInventoryChanged() {
    }

    @Override
    public String getInventoryName() {
        return "gui.crucible.name";
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

}

