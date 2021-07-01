package minefantasy.mf2.block.wizardry;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.block.tileentity.TileEntityMagicChalice;
import minefantasy.mf2.item.list.CreativeTabMF;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class BlockMagicChalice extends BlockContainer {
    public static int mag_chalice_RI = RenderingRegistry.getNextAvailableRenderId();
    private Random rand = new Random();
    private IIcon sideTex, topTex;
    public int lvl;
    public String modification;
    private double partOffsetX, partOffsetZ, partOffsetY;

    public BlockMagicChalice(int level, String modification) {
        super(Material.rock);

        this.modification = modification;
        this.lvl = level;
        GameRegistry.registerBlock(this, "MF_MagicChalice_lvl_" + level + "_" + modification);
        setBlockName("magic_chalice_lvl_" + level + "_" + modification);
        this.setStepSound(Block.soundTypeStone);
        this.setHardness(8F);
        this.setResistance(8F);
        this.setCreativeTab(CreativeTabMF.tabUtil);

        this.setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 0.4F, 0.8F);
        //setBlockTextureName("minefantasy2:zsAddon/zs_block");
    }

    @Override
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB axis, List list, Entity player) {
        this.setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 0.4F, 0.8F);
        super.addCollisionBoxesToList(world, x, y, z, axis, list, player);
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

    private static TileEntityMagicChalice getTile(IBlockAccess world, int x, int y, int z) {
        return (TileEntityMagicChalice) world.getTileEntity(x, y, z);
    }

    public int getLvl () {
        return lvl;
    }

    public String getModification () {
        return modification;
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        TileEntityMagicChalice tile = (TileEntityMagicChalice) world.getTileEntity(x, y, z);
        if (tile != null && tile.isCraftingPhase()) {
            if (rand.nextInt(10) < 7) {
                partOffsetX = (double) (rand.nextInt(4) + 1) / 10;
                partOffsetZ = (double) (rand.nextInt(4) + 1) / 10;
                world.spawnParticle("smoke", x + 0.28D + partOffsetX, y + 0.4D, z + 0.28D + partOffsetZ, 0.0D, 0.0D, 0.0D);
                world.spawnParticle("flame", x + 0.28D + partOffsetX, y + 0.4D, z + 0.28D + partOffsetZ, 0.0D, 0.0D, 0.0D);
                world.playSound(x, y, z, "fire.fire", 1.0F + random.nextFloat(), random.nextFloat() * 0.7F + 0.3F, false);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void spawnFinalParticle (World world, int x, int y, int z, String particleFirst, String particleSecond) {
        for (int s = 0; s < 3; s++) {
            partOffsetX = (double) (rand.nextInt(5) + 1) / 10;
            partOffsetZ = (double) (rand.nextInt(5) + 1) / 10;
            partOffsetY = (double) (rand.nextInt(5) + 1) / 10;
            world.spawnParticle(particleFirst, x + 0.28D + partOffsetX, y + 0.8D + partOffsetY, z + 0.28D + partOffsetZ, 0.0D, 0.0D, 0.0D);
            world.spawnParticle(particleSecond, x + 0.28D + partOffsetX, y + 0.8D + partOffsetY, z + 0.28D + partOffsetZ, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityMagicChalice();
    }

    /*@Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {

        super.breakBlock(world, x, y, z, block, meta);
    }*/

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer user, int side, float xOffset,
                                    float yOffset, float zOffset) {
        TileEntity tile = getTile(world, x, y, z);
        if (tile instanceof TileEntityMagicChalice) {
            return ((TileEntityMagicChalice) tile).interract(world, x, y, z, user);
        }

        return false;
    }

    public String getTex () {
        return "chalice_lvl" + getLvl() + "_" + getModification();
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
        return mag_chalice_RI;
    }
}
