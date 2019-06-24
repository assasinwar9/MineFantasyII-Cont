package minefantasy.mf2.block.tileentity;

import minefantasy.mf2.block.basic.BlockDenseGravel;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;

import java.util.Random;

public class TileEntityDenseGravel extends TileEntity {
    private Random rand = new Random();
    private float progress = 0;
    private float progressMax = 100 + rand.nextInt(50);


    @Override
    public void updateEntity() {
        super.updateEntity();
        if (isFlooded() && CanSeeTheSky()) {
            progress += 1;
            if (progress >= progressMax) {
                progress = 0;
                BlockDenseGravel.setLimestone(worldObj, xCoord, yCoord, zCoord);
            }
        } else {
            progress = 0;
        }

    }

    private boolean isFlooded () {
        Block under = worldObj.getBlock(xCoord, yCoord + 1, zCoord);
        Block under2 = worldObj.getBlock(xCoord, yCoord + 2, zCoord);
        if (under == Blocks.water && under2 == Blocks.water) return true;
        else return false;
    }

    /*private boolean isAvailableArea () {
        return true;
    }*/

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
