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
