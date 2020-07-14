package minefantasy.mf2.block.wizardry;


import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.block.tileentity.TileEntityDenseGravel;
import minefantasy.mf2.block.tileentity.TileEntityRunicPillar;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockRunicPillar extends BlockContainer {
    private String name;
    public String modification;
    private IIcon topTex, topSTex, midSTex, botSTex, topActiveSTex, midActiveSTex, botActiveSTex;
    private int l;
    private TileEntity tile;

    public BlockRunicPillar(String modification) {
        super(Material.rock);

        this.modification = modification;
        this.name = "runic_pillar_" + modification;
        GameRegistry.registerBlock(this, name);
        setBlockName(name);
        this.setResistance(4.0F);
        this.setHardness(4.0F);
        this.setHarvestLevel("pickaxe", 0);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isOpaqueCube() {
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityRunicPillar();
    }

    private static TileEntity getTile(IBlockAccess world, int x, int y, int z) {
        return world.getTileEntity(x, y, z);
    }

    public static void updateBlockState (World world, int x, int y, int z, int meta) {
        world.setBlockMetadataWithNotify(x, y, z, meta, 2);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        tile = getTile(world, x, y, z);
        if (tile instanceof TileEntityRunicPillar)
            ((TileEntityRunicPillar) tile).deactivation();

        world.removeTileEntity(x, y, z);
        super.breakBlock(world, x, y, z, block, meta);

    }
/*
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer user, int side, float xOffset,
                                    float yOffset, float zOffset) {
        tile = getTile(world, x, y, z);
        if (tile instanceof TileEntityRunicPillar) {
            ((TileEntityRunicPillar) tile).activation(world, x, y, z, true);
            return true;
        }
        else return false;
    }*/


/*
    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        tile = getTile(world, x, y, z);
        if (tile.isTopBlock(world, x, y, z)) {
            world.setBlockMetadataWithNotify(x, y, z, 1, 2);
        }
        else if (tile.isBottomBlock(world, x, y, z)) {
            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }
        else world.setBlockMetadataWithNotify(x, y, z, 3, 2);
    }*/


/*
    public void activation (World world, int x, int y, int z) {
        if (isTopBlock(world, x, y, z)) {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }
        if (isBottomBlock(world, x, y, z)) {
            world.setBlockMetadataWithNotify(x, y, z, 6, 2);
        }
        else world.setBlockMetadataWithNotify(x, y, z, 4, 2);
    }*/


    @Override
    public Item getItemDropped(int meta, Random rand, int i) {
        return Item.getItemFromBlock(this);
    }


    @Override
    public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata) {
        return false;
    }

    public String getModification () {
        return this.modification;
    }

    /*
    private boolean isActive (World world, int x, int y, int z) {
        l = world.getBlockMetadata(x, y, z);
        return l == 1;
    }*/

    /*
    private boolean isPillar (World world, int x, int y, int z) {
        upperBlock = world.getBlock(x, y + 1, z);
        lowerBlock = world.getBlock(x, y - 1, z);
        if
    }*/


    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (side == 1 || side == 0)
            return topTex;
        else if (side > 1) {
            if (meta == 0)
                return midSTex;
            if (meta == 1)
                return topSTex;
            if (meta == 2)
                return topActiveSTex;
            if (meta == 3)
                return midSTex;
            if (meta == 4)
                return midActiveSTex;
            if (meta == 5)
                return botSTex;
            if (meta == 6)
                return botActiveSTex;
            else return midSTex;
        }
        else return midSTex;
    }


    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        topTex =  reg.registerIcon("minefantasy2:zsAddon/runic_pillars/megalyth_top");
        topSTex = reg.registerIcon("minefantasy2:zsAddon/runic_pillars/megalyth_ina_high");
        midSTex = reg.registerIcon("minefantasy2:zsAddon/runic_pillars/megalyth_ina_medium");
        botSTex = reg.registerIcon("minefantasy2:zsAddon/runic_pillars/megalyth_ina_low");
        topActiveSTex = reg.registerIcon("minefantasy2:zsAddon/runic_pillars/megalyth_" + modification + "_high");
        midActiveSTex = reg.registerIcon("minefantasy2:zsAddon/runic_pillars/megalyth_" + modification + "_medium");
        botActiveSTex = reg.registerIcon("minefantasy2:zsAddon/runic_pillars/megalyth_" + modification + "_low");
    }
}

