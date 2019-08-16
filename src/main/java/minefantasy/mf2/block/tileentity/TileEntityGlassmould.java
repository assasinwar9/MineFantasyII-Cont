package minefantasy.mf2.block.tileentity;

import minefantasy.mf2.block.basic.BlockGlassmouldMF;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;

import java.util.Random;

public class TileEntityGlassmould extends TileEntity {
    private Random rand = new Random();
    public float progress = 0;
    private float progressMax = 400 + rand.nextInt(80);
    private Block block;


    @Override
    public void updateEntity() {
        super.updateEntity();
        block = worldObj.getBlock(xCoord, yCoord, zCoord);
        if (block instanceof BlockGlassmouldMF && ((BlockGlassmouldMF) block).readyBlock != null) {
            progress += 1;
            if (progress >= progressMax) {
                progress = 0;
                ((BlockGlassmouldMF) block).updateBlock(worldObj, xCoord, yCoord, zCoord);
            }
        } else {
            progress = 0;
        }

    }

}
