package minefantasy.mf2.block.herbs;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.item.list.ComponentListMF;
import minefantasy.mf2.item.list.HerbalicListMF;
import minefantasy.mf2.item.list.ToolListMF;
import minefantasy.mf2.item.tool.ItemSpadeMF;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;

public class BlockHerbCarxanium extends BlockHerbsMF {
    private int meta, maxMeta = 2;
    private Block block;
    private String name = "carxanium";
    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    public BlockHerbCarxanium () {
        setBlockName("herb_" + name);

        GameRegistry.registerBlock(this, "herb_" + name);
    }

    @Override
    public boolean interract (World world, int x, int y, int z, EntityPlayer user) {
        ItemStack held = user.getEquipmentInSlot(0);
        meta = world.getBlockMetadata(x, y, z);

        if (held != null && held.getItem() instanceof ItemSpadeMF) {
            if (meta == 2) {
                dropItem(world, x, y, z, ComponentListMF.carxanium_root, 1, false, false);
                dropItem(world, x, y, z, ComponentListMF.carxanium_items, 3, true, true);
            }
            if (meta == 1) {
                dropItem(world, x, y, z, ComponentListMF.carxanium_root, 1, false, false);
            }
            held.damageItem(1, user);
            if (held.getItemDamage() >= held.getMaxDamage()) {
                if (world.isRemote)
                    user.renderBrokenItemStack(held);
                user.destroyCurrentEquippedItem();
            }
            world.setBlock(x, y, z, Blocks.air);
            return true;
        }
        if (meta == 2) {
            dropItem(world, x, y, z, ComponentListMF.carxanium_items, 3, true, true);
            --meta;
            world.setBlockMetadataWithNotify(x, y, z, meta, 2);
            return true;
        }
        return false;
    }

/*
    @Override
    public Block getNextGrowStage () {
        if (stage == 1)
            return BlockListMF.herb_carxanium_2;
        if (stage == 2)
            return BlockListMF.herb_carxanium_3;
        else return BlockListMF.herb_carxanium_3;
    }

    @Override
    public Block getPrevGrowStage () {
        return BlockListMF.herb_carxanium_2;
    }*/

    @Override
    public int getMaxMeta () {
        return maxMeta; // 0, 1, 2, total = 3 stages
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta)
    {
        /*
        if (meta < 0 || meta > 7)
        {
            meta = 7;
        }*/

        return icons[meta];
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister reg)
    {
        icons = new IIcon[3];

        for (int i = 0; i < icons.length; ++i)
        {
            icons[i] = reg.registerIcon(getTexture() + i);
        }
    }

    //@Override
    public String getTexture () {
        return "minefantasy2:herbs/" + name + "_stage_";
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
        return ((ground == Blocks.stone) || (ground == Blocks.stonebrick) || (ground == Blocks.cobblestone));
    }
    //for normal ground
          //  return ((ground == Blocks.dirt) || (ground == Blocks.farmland) || (ground == Blocks.grass));


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
        return ((ground == Blocks.stone) || (ground == Blocks.stonebrick) || (ground == Blocks.cobblestone));
    }


}
