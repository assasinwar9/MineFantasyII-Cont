package minefantasy.mf2.block.tileentity;

import minefantasy.mf2.block.basic.BlockDenseGravel;
import minefantasy.mf2.block.wizardry.BlockRunicPillar;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.Random;

public class TileEntityRunicPillar extends TileEntity {
    private Random rand = new Random();
    private float progress = 0;
    private float progressMax = 400 + rand.nextInt(50);
    private Block upperBlock, lowerBlock, currentBlock;
    public boolean isActive;

    private TileEntity tile;

    @Override
    public void updateEntity() {
        super.updateEntity();

        /*meta:
        0 = simple side tex
        1 = top side tex
        2 = top active side tex
        3 = mid side tex
        4 = mid active side tex
        5 = bottom side tex
        6 = bottom active side tex
        */
        //if(!worldObj.isRemote) {
            if (isTopBlock()) {
                BlockRunicPillar.updateBlockState(worldObj, xCoord, yCoord, zCoord, isActive ? 2 : 1);
            }
            else if (isBottomBlock()) {
                BlockRunicPillar.updateBlockState(worldObj, xCoord, yCoord, zCoord, isActive ? 6 : 5);
            }
            else BlockRunicPillar.updateBlockState(worldObj, xCoord, yCoord, zCoord, isActive ? 4 : 3);
        //}

    }

    public void activation () {
        isActive = true;
            for (int i = 1; i <=256; i++) {
                tile = worldObj.getTileEntity(xCoord, yCoord + i, zCoord);
                if (tile instanceof TileEntityRunicPillar)
                    ((TileEntityRunicPillar) tile).isActive = true;
                else break;
            }
            for (int i = 1; i <=256; i++) {
                tile = worldObj.getTileEntity(xCoord, yCoord - i, zCoord);
                if (tile instanceof TileEntityRunicPillar)
                    ((TileEntityRunicPillar) tile).isActive = true;
                else break;
        }
    }

    public void deactivation () {
        isActive = false;
            for (int i = 1; i <=256; i++) {
                tile = worldObj.getTileEntity(xCoord, yCoord + i, zCoord);
                if (tile instanceof TileEntityRunicPillar)
                    ((TileEntityRunicPillar) tile).isActive = false;
                else break;
            }
            for (int i = 1; i <=256; i++) {
                tile = worldObj.getTileEntity(xCoord, yCoord - i, zCoord);
                if (tile instanceof TileEntityRunicPillar)
                    ((TileEntityRunicPillar) tile).isActive = false;
                else break;
        }
    }



    public boolean isTopBlock () {
        currentBlock = worldObj.getBlock(xCoord, yCoord, zCoord);
        upperBlock = worldObj.getBlock(xCoord, yCoord + 1, zCoord);
        lowerBlock = worldObj.getBlock(xCoord, yCoord - 1, zCoord);
        return  (!(upperBlock instanceof BlockRunicPillar) && lowerBlock instanceof BlockRunicPillar);
    }
    public boolean isBottomBlock () {
        currentBlock = worldObj.getBlock(xCoord, yCoord, zCoord);
        upperBlock = worldObj.getBlock(xCoord, yCoord + 1, zCoord);
        lowerBlock = worldObj.getBlock(xCoord, yCoord - 1, zCoord);
        return  (upperBlock instanceof BlockRunicPillar && !(lowerBlock instanceof BlockRunicPillar));
    }


    private boolean CanSeeTheSky() {
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (!worldObj.canBlockSeeTheSky(xCoord + x, yCoord + 3, zCoord + y)) {
                    return false;
                }
            }
        }
        return true;
    }

}
