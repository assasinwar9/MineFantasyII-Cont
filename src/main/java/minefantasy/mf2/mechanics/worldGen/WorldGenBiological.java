package minefantasy.mf2.mechanics.worldGen;

import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.config.ConfigWorldGen;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.Random;

public class WorldGenBiological {
    public static void generate(Random seed, int chunkX, int chunkZ, World world, int dimension) {
        boolean debug = world.getWorldInfo().getTerrainType() == WorldType.FLAT && MineFantasyII.isDebug();

        BiomeGenBase biome = world.getBiomeGenForCoords(chunkX * 16, chunkZ * 16);
        if (debug || isBiomeInConstraint(biome, ConfigWorldGen.berryMinTemp, ConfigWorldGen.berryMaxTemp,
                ConfigWorldGen.berryMinRain, ConfigWorldGen.berryMaxRain)) {
            generatePlant(seed, chunkX, chunkZ, world, BlockListMF.berryBush, 0, ConfigWorldGen.berryRarity, 6);
        }
        if (debug || isBiomeInConstraint(biome, ConfigWorldGen.yewMinTemp, ConfigWorldGen.yewMaxTemp,
                ConfigWorldGen.yewMinRain, ConfigWorldGen.yewMaxRain)) {
            generateTree(seed, chunkX, chunkZ, world, BlockListMF.log_yew, BlockListMF.leaves_yew,
                    ConfigWorldGen.yewRarity);
        }
        if (debug || isBiomeInConstraint(biome, ConfigWorldGen.ironbarkMinTemp, ConfigWorldGen.ironbarkMaxTemp,
                ConfigWorldGen.ironbarkMinRain, ConfigWorldGen.ironbarkMaxRain)) {
            generateTree(seed, chunkX, chunkZ, world, BlockListMF.log_ironbark, BlockListMF.leaves_ironbark,
                    ConfigWorldGen.ironbarkRarity);
        }
        if (debug || isBiomeInConstraint(biome, ConfigWorldGen.ebonyMinTemp, ConfigWorldGen.ebonyMaxTemp,
                ConfigWorldGen.ebonyMinRain, ConfigWorldGen.ebonyMaxRain)) {
            generateTree(seed, chunkX, chunkZ, world, BlockListMF.log_ebony, BlockListMF.leaves_ebony,
                    ConfigWorldGen.ebonyRarity);
        }

        //herbs
        if (isBiomeInConstraint(biome, ConfigWorldGen.carxaMinTemp, ConfigWorldGen.carxaMaxTemp,
                ConfigWorldGen.carxaMinRain, ConfigWorldGen.carxaMaxRain)) {
            generatePlant(seed, chunkX, chunkZ, world, BlockListMF.herb_carxanium, 2, ConfigWorldGen.carxaRarity, 3);
        }
        if (isBiomeInConstraint(biome, ConfigWorldGen.sinisterMinTemp, ConfigWorldGen.sinisterMaxTemp,
                ConfigWorldGen.sinisterMinRain, ConfigWorldGen.sinisterMaxRain)) {
            generatePlant(seed, chunkX, chunkZ, world, BlockListMF.herb_sinister_grass, 1, ConfigWorldGen.sinisterRarity, 3);
        }
        if (isBiomeInConstraint(biome, ConfigWorldGen.endureMinTemp, ConfigWorldGen.endureMaxTemp,
                ConfigWorldGen.endureMinRain, ConfigWorldGen.endureMaxRain)) {
            generatePlant(seed, chunkX, chunkZ, world, BlockListMF.herb_endure_root, 0, ConfigWorldGen.endureRarity, 4);
        }
        if (isBiomeInConstraint(biome, ConfigWorldGen.dogsearMinTemp, ConfigWorldGen.dogsearMaxTemp,
                ConfigWorldGen.dogsearMinRain, ConfigWorldGen.dogsearMaxRain)) {
            generatePlant(seed, chunkX, chunkZ, world, BlockListMF.herb_dogsear, 1, ConfigWorldGen.dogsearRarity, 3);
        }
        if (isBiomeInConstraint(biome, ConfigWorldGen.flameflMinTemp, ConfigWorldGen.flameflMaxTemp,
                ConfigWorldGen.flameflMinRain, ConfigWorldGen.flameflMaxRain)) {
            generatePlant(seed, chunkX, chunkZ, world, BlockListMF.herb_flameflower, 1, ConfigWorldGen.flameflRarity, 2);
        }
        if (isBiomeInConstraint(biome, ConfigWorldGen.wispflMinTemp, ConfigWorldGen.wispflMaxTemp,
                ConfigWorldGen.wispflMinRain, ConfigWorldGen.wispflMaxRain)) {
            generatePlant(seed, chunkX, chunkZ, world, BlockListMF.herb_wispflock, 1, ConfigWorldGen.wispflRarity, 3);
        }
        if (isBiomeInConstraint(biome, ConfigWorldGen.moonflMinTemp, ConfigWorldGen.moonflMaxTemp,
                ConfigWorldGen.moonflMinRain, ConfigWorldGen.moonflMaxRain)) {
            generatePlant(seed, chunkX, chunkZ, world, BlockListMF.herb_moonflower, 1, ConfigWorldGen.moonflRarity, 2);
        }
        if (isBiomeInConstraint(biome, ConfigWorldGen.sadblosMinTemp, ConfigWorldGen.sadblosMaxTemp,
                ConfigWorldGen.sadblosMinRain, ConfigWorldGen.sadblosMaxRain)) {
            generatePlant(seed, chunkX, chunkZ, world, BlockListMF.herb_sadblossom, 1, ConfigWorldGen.sadblosRarity, 3);
        }
        if (isBiomeInConstraint(biome, ConfigWorldGen.boneberMinTemp, ConfigWorldGen.boneberMaxTemp,
                ConfigWorldGen.boneberMinRain, ConfigWorldGen.boneberMaxRain)) {
            generatePlant(seed, chunkX, chunkZ, world, BlockListMF.herb_boneberry, 1, ConfigWorldGen.boneberRarity, 3);
        }
        if (isBiomeInConstraint(biome, ConfigWorldGen.thornMinTemp, ConfigWorldGen.thornMaxTemp,
                ConfigWorldGen.thornMinRain, ConfigWorldGen.thornMaxRain)) {
            generatePlant(seed, chunkX, chunkZ, world, BlockListMF.herb_thorn_root, 1, ConfigWorldGen.thornRarity, 3);
        }
        if (isBiomeInConstraint(biome, ConfigWorldGen.savyireMinTemp, ConfigWorldGen.savyireMaxTemp,
                ConfigWorldGen.savyireMinRain, ConfigWorldGen.savyireMaxRain)) {
            generatePlant(seed, chunkX, chunkZ, world, BlockListMF.herb_savyire, 1, ConfigWorldGen.savyireRarity, 3);
        }
    }

    public static boolean isBiomeInConstraint(BiomeGenBase biome, float tempMin, float tempMax, float rainMin,
                                              float rainMax) {
        if (biome != null) {
            return biome.temperature >= tempMin && biome.temperature < tempMax && biome.rainfall >= rainMin
                    && biome.rainfall < rainMax;
        }
        return false;
    }

    private static void generatePlant(Random seed, int chunkX, int chunkZ, World world, Block plant, int meta,
                                      float chance, int count) {
        boolean doGen = world.getWorldInfo().getTerrainType() != WorldType.FLAT;
        if (doGen && seed.nextFloat() < chance) {
            for (int a = 0; a < count; a++) {
                int j = chunkX * 16 + seed.nextInt(16);
                int k = chunkZ * 16 + seed.nextInt(16);
                int l = world.getTopSolidOrLiquidBlock(j, k);
                (new WorldGenBush(plant, meta)).generate(world, seed, j, l, k);
            }
        }
    }

    private static void generateTree(Random seed, int chunkX, int chunkZ, World world, Block log, Block leaves,
                                     float chance) {
        boolean doGen = world.getWorldInfo().getTerrainType() != WorldType.FLAT
                || (doGen = MineFantasyII.isDebug() && world.getWorldInfo().getTerrainType() == WorldType.FLAT);
        if (doGen && seed.nextFloat() < chance) {
            int j = chunkX * 16 + seed.nextInt(16);
            int k = chunkZ * 16 + seed.nextInt(16);
            int l = world.getTopSolidOrLiquidBlock(j, k);
            (new WorldGenMFTree(false, log, leaves)).generate(world, seed, j, l, k);
        }
    }

    private static void generateOreWithNeighbour(Random seed, int chunkX, int chunkZ, World world, Block ore, Block bed,
                                                 Material neighbour, int size, int frequencyMin, int frequencyMax, float rarity, int layerMin,
                                                 int layerMax) {
        int frequency = MathHelper.getRandomIntegerInRange(seed, frequencyMin, frequencyMax);
        if (seed.nextFloat() < rarity) {
            for (int count = 0; count < frequency; count++) {
                int x = chunkX * 16 + seed.nextInt(16);
                int y = MathHelper.getRandomIntegerInRange(seed, layerMin, layerMax);
                int z = chunkZ * 16 + seed.nextInt(16);

                if (isNeibourNear(world, x, y, z, neighbour)) {
                    if ((new WorldGenMinableMF(ore, size, bed)).generate(world, seed, x, y, z)) {
                    }
                }
            }
        }
    }

    private static void generateOreWithNeighbour(Random seed, int chunkX, int chunkZ, World world, Block ore, Block bed,
                                                 Block neighbour, int size, int frequencyMin, int frequencyMax, float rarity, int layerMin, int layerMax) {
        int frequency = MathHelper.getRandomIntegerInRange(seed, frequencyMin, frequencyMax);
        if (seed.nextFloat() < rarity) {
            for (int count = 0; count < frequency; count++) {
                int x = chunkX * 16 + seed.nextInt(16);
                int y = MathHelper.getRandomIntegerInRange(seed, layerMin, layerMax);
                int z = chunkZ * 16 + seed.nextInt(16);

                if (isNeibourNear(world, x, y, z, neighbour)) {
                    if ((new WorldGenMinableMF(ore, size, bed)).generate(world, seed, x, y, z)) {
                    }
                }
            }
        }
    }

    private static boolean isNeibourNear(World world, int x, int y, int z, Block neighbour) {
        return world.getBlock(x - 1, y, z) == neighbour || world.getBlock(+1, y, z) == neighbour
                || world.getBlock(x, y - 1, z) == neighbour || world.getBlock(x, y + 1, z) == neighbour
                || world.getBlock(x, y, z - 1) == neighbour || world.getBlock(x, y, z + 1) == neighbour;
    }

    private static boolean isNeibourNear(World world, int x, int y, int z, Material neighbour) {
        return world.getBlock(x - 1, y, z).getMaterial() == neighbour
                || world.getBlock(+1, y, z).getMaterial() == neighbour
                || world.getBlock(x, y - 1, z).getMaterial() == neighbour
                || world.getBlock(x, y + 1, z).getMaterial() == neighbour
                || world.getBlock(x, y, z - 1).getMaterial() == neighbour
                || world.getBlock(x, y, z + 1).getMaterial() == neighbour;
    }
}
