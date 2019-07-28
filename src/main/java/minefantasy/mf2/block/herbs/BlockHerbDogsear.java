package minefantasy.mf2.block.herbs;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import minefantasy.mf2.item.list.ComponentListMF;
import minefantasy.mf2.item.tool.ItemShearsMF;
import minefantasy.mf2.item.tool.ItemSpadeMF;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;

public class BlockHerbDogsear extends BlockHerbsMF {
    private int meta;
    private Block block;
    private String name = "dogsear";
    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    public BlockHerbDogsear() {
        setBlockName("herb_" + name);

        GameRegistry.registerBlock(this, "herb_" + name);
    }

    @Override
    public boolean interract (World world, int x, int y, int z, EntityPlayer user) {
        ItemStack held = user.getEquipmentInSlot(0);
        meta = world.getBlockMetadata(x, y, z);

        if (held != null && held.getItem() instanceof ItemShearsMF) {
            if (meta == 1) {
                dropItem(world, x, y, z, ComponentListMF.dogsear_item, 2, true, false);
            }
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
        return false;
    }

    @Override
    public void getCustomDrop (World world, int x, int y, int z, Block block) {
        meta = world.getBlockMetadata(x, y, z);
        if (meta == 1)
            dropItem(world, x, y, z, ComponentListMF.dogsear_item, 1, false, false);
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
        return 1;
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
        icons = new IIcon[2];

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
