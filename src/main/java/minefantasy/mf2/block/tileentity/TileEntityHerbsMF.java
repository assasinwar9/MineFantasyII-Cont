package minefantasy.mf2.block.tileentity;

import minefantasy.mf2.block.herbs.BlockHerbsMF;
import minefantasy.mf2.block.herbs.BlockHerbsMF;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

import java.util.Random;
/*
public class TileEntityHerbsMF extends TileEntity {
    private Random rand = new Random();
    private int progressGrow = 0;
    private int progressMaxGrow = BlockHerbsMF.getGrowTime() + rand.nextInt(100);


    @Override
    public void updateEntity() {
        super.updateEntity();

        if (!worldObj.isRemote) {
            if (worldObj.getBlockLightValue(xCoord, yCoord + 1, zCoord) >= 9) {
                if (progressGrow > progressMaxGrow) {
                    BlockHerbsMF.updateBlockStage(worldObj, xCoord, yCoord, zCoord, 1);
                    progressGrow = 0;
                }
                if (worldObj.getBlockMetadata(xCoord, yCoord, zCoord) < BlockPlantsMF.getMaxGrowStage()) {
                    ++progressGrow;
                }
            }
        }

    }


    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

    }


}*/
