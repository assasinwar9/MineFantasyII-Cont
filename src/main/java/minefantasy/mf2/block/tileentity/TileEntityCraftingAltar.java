package minefantasy.mf2.block.tileentity;

import minefantasy.mf2.api.crafting.IHeatUser;
import minefantasy.mf2.api.helpers.CustomToolHelper;
import minefantasy.mf2.api.refine.Alloy;
import minefantasy.mf2.api.refine.AlloyRecipes;
import minefantasy.mf2.api.refine.SmokeMechanics;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.block.refining.BlockCrucible;
import minefantasy.mf2.block.tileentity.blastfurnace.TileEntityBlastFH;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEndPortalFrame;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.Random;

public class TileEntityCraftingAltar extends TileEntity implements IInventory {
    private ItemStack[] inv = new ItemStack[1];
    private Random rand = new Random();
    private int downMaxCraftLimit = -60;
    private int downCraftLimit = -60;
    private int upMaxCraftLimit = 60;
    private int upCraftLimit = 60;
    private int markerPos = 0;
    private int dropCount;
    private int clickCount;

    private int progress = 0;
    private int progressMax = 800;

    private int speed; //pixel per second
    private int tickExisted;

    private int direction = 1; //+1 = right, -1 = left

    private boolean craftingPhase = false;

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (markerPos >= upCraftLimit || markerPos <= downCraftLimit) {
            craftFinalization(true);
            progress = 0;
        }
        /*
        ++tickExisted;
        if (craftingPhase && tickExisted >= 2) {
            markerPos += direction * speed;
            tickExisted = 0;
        }*/
        if (craftingPhase) {
            speed = clickCount > 4 ? 2 : 1;
            ++progress;
            ++tickExisted;
            markerPos += direction * speed;
            if (progress >= progressMax) {
                craftFinalization(false);
                progress = 0;
            }
            if (tickExisted >= 20) {

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
       
       if(!craftingPhase) {
           if (held != null && held.getItem() == Items.emerald) {
               --held.stackSize;
               craftingPhase  = true;
               direction = 1;
               speed = 1;
               upCraftLimit = upMaxCraftLimit;
               downCraftLimit = downMaxCraftLimit;
               return true;
           }
       }
       if (craftingPhase) {
           if (held != null && held.getItem() == Items.stick) {
               if (direction == 1) {
                   upCraftLimit = markerPos;
                   markerPos -= 1;
                   ++clickCount;
               }
               if (direction == -1) {
                   downCraftLimit = markerPos;
                   markerPos += 1;
                   ++clickCount;
               }
               direction *= -1;
               return true;
           }
       }
       return false;
       
   }

   private void craftFinalization (boolean ruin) {
       craftingPhase = false;
       markerPos = 0;
       clickCount = 0;
       upCraftLimit = upMaxCraftLimit;
       downCraftLimit = downMaxCraftLimit;
       dropItem(worldObj, xCoord, yCoord, zCoord, ruin ? Item.getItemFromBlock(Blocks.dirt) : Items.diamond, 4, false, false);

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

    public int getProgressBar () {
       return (int) Math.floor(progress * 115 / progressMax);
    }


   public boolean isCraftingPhase () {
       return craftingPhase;
   }

   public int getMarkerPosition () {
       return markerPos;
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
