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

import java.util.ArrayList;
import java.util.Random;

public class TileEntityMagicPedestal extends TileEntity {
    private ItemStack[] inv = new ItemStack[1];
    private Random rand = new Random();
    private int dropCount;
    private int runic_pillars;
    private TileEntity tile;
    public boolean validStructure = false;
    private int ticksExisted;

    public boolean isActive = false;

    @Override
    public void updateEntity() {
        super.updateEntity();

        ++ticksExisted;
        if (ticksExisted >= 20) {
            ticksExisted = 0;
            validStructure = checkStructure();
        }

        if (isActive && validStructure) {
            structureActivation(true);
        }
        else {
            structureActivation(false);
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

   private boolean checkStructure () {
       runic_pillars = 0;
       for (int x = 0; x <= 10; x++) {
           for (int z = 0; z <= 10; z++) {
               tile = worldObj.getTileEntity(xCoord - 5 + x, yCoord, zCoord - 5 + z);
               if (tile != null && tile instanceof TileEntityRunicPillar && ((TileEntityRunicPillar) tile).isBottomBlock())
                   ++runic_pillars;
           }
       }
       return runic_pillars >= 5; //required count of runic pillars
   }

   private void structureActivation (boolean activation) {
       for (int x = 0; x <= 10; x++) {
           for (int z = 0; z <= 10; z++) {
               tile = worldObj.getTileEntity(xCoord - 5 + x, yCoord, zCoord - 5 + z);
               if (tile != null && tile instanceof TileEntityRunicPillar && ((TileEntityRunicPillar) tile).isBottomBlock()) {
                   if (activation)
                       ((TileEntityRunicPillar) tile).activation();
                   else ((TileEntityRunicPillar) tile).deactivation();
               }
           }
       }
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


   public boolean isActive () {
       return isActive;
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


    }
