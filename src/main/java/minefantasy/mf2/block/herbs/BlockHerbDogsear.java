package minefantasy.mf2.block.herbs;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.item.list.ComponentListMF;
import minefantasy.mf2.item.tool.ItemShearsMF;
import minefantasy.mf2.item.tool.ItemSpadeMF;
import minefantasy.mf2.util.MFLogUtil;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;

import java.util.ArrayList;
import java.util.Random;

public class BlockHerbDogsear extends BlockHerbsMF {
    private int meta;
    private Block block;
    private String name = "dogsear";
    @SideOnly(Side.CLIENT)
    private IIcon[] icons;
    private Random rand = new Random();

    public BlockHerbDogsear() {
        setBlockName("herb_" + name);
        GameRegistry.registerBlock(this, "herb_" + name);
        this.setHardness(0.3F);
        this.setHarvestLevel("shovel", 0);
        // p_1,2,3 = offset, p_4,5,6 = dimension. One 0.1F = 1.6 pixels
        this.setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 0.9F, 0.8F);
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        super.updateTick(world, x, y, z, random);

        int l = world.getBlockMetadata(x, y, z);
        if (world.getBlockLightValue(x, y, z) >= getMinReqLightLvl()) {
            if (random.nextInt(100) <= getGrowChance()) {
                if (l < getMaxMeta()) {
                    ++l;
                    world.setBlockMetadataWithNotify(x, y, z, l, 2);
                }
            }
        }

        if (random.nextInt(100) <= getBreedingChance() && l == getMaxMeta()) {
            breeding(world, x, y, z);
        }
    }

    private void breeding (World world, int x, int y, int z) {
        if (existWater(world, x, y, z) && existCouple(world, x, y, z)) {
            int x2, z2;
            x2 = x - 1 + rand.nextInt(3);
            z2 = z - 1 + rand.nextInt(3);
            if (canPlaceBlockOn(world.getBlock(x2, y - 1, z2)) && world.isAirBlock(x2, y, z2)) {
                world.setBlock(x2, y, z2, this);
            }
            else {
            }
        }
    }

    private boolean existWater (World world, int x, int y, int z) {
        for (int x1 = 0; x1 < 3; x1++) {
            for (int z1 = 0; z1 < 3; z1++) {
                if (world.getBlock(x - 1 + x1, y - 1, z - 1 + z1) == Blocks.water) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean existCouple (World world, int x, int y, int z) {
        for (int x1 = 0; x1 < 3; x1++) {
            for (int z1 = 0; z1 < 3; z1++) {
                if (world.getBlock(x - 1 + x1, y, z - 1 + z1) instanceof BlockHerbDogsear
                        && world.getBlockMetadata(x - 1 + x1, y, z - 1 + z1) > 0
                        && !(x1 == 1 && z1 == 1) ) {

                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean interract (World world, int x, int y, int z, EntityPlayer user) {
        ItemStack held = user.getEquipmentInSlot(0);
        meta = world.getBlockMetadata(x, y, z);

        if (held != null && held.getItem() instanceof ItemShearsMF && meta == 1) {
            dropItem(world, x, y, z, ComponentListMF.dogsear_item, 3, true, true);

            held.damageItem(1, user);
            if (held.getItemDamage() >= held.getMaxDamage()) {
                if (world.isRemote)
                    user.renderBrokenItemStack(held);
                user.destroyCurrentEquippedItem();
            }
            --meta;
            world.setBlockMetadataWithNotify(x, y, z, meta, 2);
            return true;
        }
        else if (meta == 1) {
                dropItem(world, x, y, z, ComponentListMF.dogsear_item, 2, true, false);
                --meta;
                world.setBlockMetadataWithNotify(x, y, z, meta, 2);
                return true;
            }
        if (held != null && held.getItem() == Items.emerald && meta < getMaxMeta()) { //debug
            meta++;
            world.setBlockMetadataWithNotify(x, y, z, meta, 2);
        }
        return false;
    }

    @Override
    public int getMaxMeta () {
        return 1;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta)
    {
        return icons[meta];
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister reg)
    {
        icons = new IIcon[2];

        for (int i = 0; i < icons.length; ++i)
        {
            icons[i] = reg.registerIcon(getTexture() + i);
        }
    }

    public String getTexture () {
        return "minefantasy2:herbs/" + name + "_stage_";
    }

    private int getBreedingChance () {
        return 85;
    }

    @Override
    public int getGrowChance () {
        return 80;
    }

    @Override
    public int getRipeChance () {
        return 0;
    }

    @Override
    public boolean isRightSoil (Block ground) {
        return ((ground == Blocks.dirt) || (ground == Blocks.farmland) || (ground == Blocks.grass));
    }

    @Override
    public int getSizeFactor () {
        return 1;
    }

    @Override
    public boolean isGoodBiome(BiomeGenBase biome) {
        return ((biome.rainfall >= 0.5f) && (biome.temperature > 0.2f) && (biome.temperature <= 0.5))
                || (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.FOREST))
                || (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.DENSE))
                || (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.CONIFEROUS));
    }

    @Override
    public boolean canPlaceBlockOn(Block ground) {
        return ((ground == Blocks.dirt) || (ground == Blocks.farmland) || (ground == Blocks.grass));
    }


}
