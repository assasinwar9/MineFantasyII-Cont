package minefantasy.mf2.mechanics.worldGen;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenClayLayer extends WorldGenerator {

    public WorldGenClayLayer() {
        super();
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
        if ((world.getBlock(x, y - 1, z) == Blocks.dirt || world.getBlock(x, y - 1, z) == Blocks.grass)
                && (world.isAirBlock(x, y, z) || world.getBlock(x, y, z) == Blocks.snow_layer)) {
            if (rand.nextInt(10) < 8  && !world.isAirBlock(x, y - 4, z)
                    && world.getBlock(x, y - 4, z) != Blocks.water) {
                world.setBlock(x, y - 4, z, Blocks.clay);
            }
            if (rand.nextInt(10) < 8  && !world.isAirBlock(x, y - 5, z)
                    && world.getBlock(x, y - 5, z) != Blocks.water) {
                world.setBlock(x, y - 5, z, Blocks.clay);
            }
            if (rand.nextInt(10) < 3  && !world.isAirBlock(x, y - 6, z)
                    && world.getBlock(x, y - 6, z) != Blocks.water) {
                world.setBlock(x, y - 6, z, Blocks.clay);
            }
        }

        return true;
    }
}