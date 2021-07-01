package minefantasy.mf2.mechanics.worldGen;

import minefantasy.mf2.block.list.BlockListMF;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenSaltDeposit extends WorldGenerator {
    private int lengthMax, lengthHalf, widthMax, widthHalf, width;
    private int j, k;

    public WorldGenSaltDeposit() {
        super();
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
        //middle layer
        lengthMax = MathHelper.getRandomIntegerInRange(rand, 10, 25);
        lengthHalf = lengthMax / 2;
        widthMax = MathHelper.getRandomIntegerInRange(rand, 10, 20);
        widthHalf = widthMax / 2;

        for (j = 1; j <= lengthHalf; j++) {
            width = MathHelper.getRandomIntegerInRange(rand, widthMax - 2, widthMax + 1) - j;
            for (k = 1; k <= width; k++) {
                setSaltDeposit(world,x + j, y, z + widthHalf - (width / 2) + k);
                if (rand.nextInt(10) < 4)
                    setSaltDeposit(world,x + j, y, z + widthHalf - (width / 2) + k + 1);
            }
        }
        for (j = 1; j <= lengthHalf; j++) {
            width = MathHelper.getRandomIntegerInRange(rand, widthMax - 2, widthMax + 1) - j;
            for (k = 1; k <= width; k++) {
                setSaltDeposit(world,x - j + 1, y, z + widthHalf - (width / 2) + k);
                if (rand.nextInt(10) < 4)
                    setSaltDeposit(world,x - j + 1, y, z + widthHalf - (width / 2) + k - 1);
            }
        }
        //upper layer
        lengthMax = MathHelper.getRandomIntegerInRange(rand, 7, 12);
        lengthHalf = lengthMax / 2;
        widthMax = MathHelper.getRandomIntegerInRange(rand, 8, 15);
        widthHalf = widthMax / 2;

        for (j = 1; j <= lengthHalf; j++) {
            width = MathHelper.getRandomIntegerInRange(rand, widthMax - 2, widthMax + 1) - j;
            for (k = 1; k <= width; k++) {
                setSaltDeposit(world,x + j, y + 1, z + widthHalf - (width / 2) + k + 1);
                if (rand.nextInt(10) < 4)
                    setSaltDeposit(world,x + j, y + 1, z + widthHalf - (width / 2) + k + 1);
            }
        }
        for (j = 1; j <= lengthHalf; j++) {
            width = MathHelper.getRandomIntegerInRange(rand, widthMax - 2, widthMax + 1) - j;
            for (k = 1; k <= width; k++) {
                setSaltDeposit(world,x - j + 1, y + 1, z + widthHalf - (width / 2) + k - 1);
                if (rand.nextInt(10) < 4)
                    setSaltDeposit(world,x - j + 1, y + 1, z + widthHalf - (width / 2) + k - 1);
            }
        }

        //lower layer
        lengthMax = MathHelper.getRandomIntegerInRange(rand, 8, 11);
        lengthHalf = lengthMax / 2;
        widthMax = MathHelper.getRandomIntegerInRange(rand, 6, 14);
        widthHalf = widthMax / 2;

        for (j = 1; j <= lengthHalf; j++) {
            width = MathHelper.getRandomIntegerInRange(rand, widthMax - 2, widthMax + 1) - j;
            for (k = 1; k <= width; k++) {
                setSaltDeposit(world,x + j - 2, y - 1, z + widthHalf - (width / 2) + k + 1 - 2);
                if (rand.nextInt(10) < 4)
                    setSaltDeposit(world,x + j - 2, y - 1, z + widthHalf - (width / 2) + k + 1 - 2);
            }
        }
        for (j = 1; j <= lengthHalf; j++) {
            width = MathHelper.getRandomIntegerInRange(rand, widthMax - 2, widthMax + 1) - j;
            for (k = 1; k <= width; k++) {
                setSaltDeposit(world,x - j + 1 + 2, y - 1, z + widthHalf - (width / 2) + k - 1 + 2);
                if (rand.nextInt(10) < 4)
                    setSaltDeposit(world,x - j + 1 + 2, y - 1, z + widthHalf - (width / 2) + k - 1 + 2);
            }
        }
        return true;
    }

    private void setSaltDeposit (World world, int x, int y, int z) {
        if (world.getBlock(x, y, z) != Blocks.water && !world.isAirBlock(x, y, z)) {
            world.setBlock(x, y, z, BlockListMF.salt_deposit);
        }
    }
}