package minefantasy.mf2.block.basic;


import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.block.tileentity.TileEntityDenseGravel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockDenseGravel extends BlockContainer {
    private Object drop;

    public BlockDenseGravel(String name) {
        this(name, null, Material.rock);
    }

    public BlockDenseGravel(String name, Object drop, Material material) {
        super(material);

        GameRegistry.registerBlock(this, name);
        setBlockName(name);
        setBlockTextureName("minefantasy2:zsAddon/dense_gravel");
        this.setResistance(4.0F);
        this.setHardness(4.0F);
        this.setHarvestLevel("pickaxe", 0);
        this.drop = drop;
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isOpaqueCube() {
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityDenseGravel();
    }

    private static TileEntityDenseGravel getTile(IBlockAccess world, int x, int y, int z) {
        return (TileEntityDenseGravel) world.getTileEntity(x, y, z);
    }

    public static void removeTileEntity (World world, int x, int y, int z) {
        world.removeTileEntity(x, y, z);
    }

    public static void setLimestone (World world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);

        if (block != null && block instanceof BlockDenseGravel) {
            world.setBlock(x, y, z, BlockListMF.limestone_friable);
            removeTileEntity(world, x, y, z);
        }
    }




    @Override
    public Item getItemDropped(int meta, Random rand, int i) {
        if (drop != null) {
            if (drop instanceof Item) {
                return (Item) drop;
            }
            if (drop instanceof Block) {
                return Item.getItemFromBlock((Block) drop);
            }
        }
        return Item.getItemFromBlock(this);
    }

    @Override
    public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata) {
        return false;
    }
}

