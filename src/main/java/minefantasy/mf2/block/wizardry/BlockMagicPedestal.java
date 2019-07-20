package minefantasy.mf2.block.wizardry;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.block.tileentity.TileEntityMagicPedestal;
import minefantasy.mf2.item.list.CreativeTabMF;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockMagicPedestal extends BlockContainer {
    public static int mag_pedestal_RI = RenderingRegistry.getNextAvailableRenderId();
    private Random rand = new Random();
    private IIcon sideTex, topTex;

    public BlockMagicPedestal() {
        super(Material.rock);
        GameRegistry.registerBlock(this, "MF_MagicPedestal");
        setBlockName("magic_pedestal");
        this.setStepSound(Block.soundTypeStone);
        this.setHardness(8F);
        this.setResistance(8F);
        this.setCreativeTab(CreativeTabMF.tabUtil);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return BlockListMF.crucible.getIcon(side, meta);
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    private static TileEntityMagicPedestal getTile(IBlockAccess world, int x, int y, int z) {
        return (TileEntityMagicPedestal) world.getTileEntity(x, y, z);
    }

   /* public static void updateFurnaceBlockState(boolean state, World world, int x, int y, int z) {
        int l = world.getBlockMetadata(x, y, z);
        TileEntityCrucible tileentity = getTile(world, x, y, z);
        keepInventory = true;
        Block block = world.getBlock(x, y, z);

        if (block != null && block instanceof BlockMagicPedestal) {
            int blocktier = ((BlockMagicPedestal) block).tier;
            boolean auto = ((BlockMagicPedestal) block).isAuto;
            if (state) {
                world.setBlock(x, y, z, getActiveBlock(blocktier, auto));
            } else {
                world.setBlock(x, y, z, getInactiveBlock(blocktier, auto));
            }
        }

        keepInventory = false;
        world.setBlockMetadataWithNotify(x, y, z, l, 2);

        if (tileentity != null) {
            tileentity.validate();
            world.setTileEntity(x, y, z, tileentity);
        }
    }*/


    /*@Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        if (!isActive) {
            super.getSubBlocks(item, tab, list);
        }
    }*/

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityMagicPedestal();
    }

    /*@Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {

        super.breakBlock(world, x, y, z, block, meta);
    }*/

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer user, int side, float xOffset,
                                    float yOffset, float zOffset) {
        TileEntity tile = getTile(world, x, y, z);
        if (tile instanceof TileEntityMagicPedestal) {
            return ((TileEntityMagicPedestal) tile).interract(world, x, y, z, user);
        }

        return false;
    }


    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z) {
        return Item.getItemFromBlock(this);
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int fort) {
        return Item.getItemFromBlock(this);
    }

    @Override
    public int getRenderType() {
        return mag_pedestal_RI;
    }
}
