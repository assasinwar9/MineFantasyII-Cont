package minefantasy.mf2.block.basic;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import minefantasy.mf2.api.helpers.ToolHelper;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.block.tileentity.TileEntityCrucible;
import minefantasy.mf2.block.tileentity.TileEntityGlassmould;
import minefantasy.mf2.config.ConfigHardcore;
import minefantasy.mf2.item.list.ComponentListMF;
import minefantasy.mf2.item.list.CreativeTabMF;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class BlockGlassmouldMF extends BlockSlabMF implements ITileEntityProvider {
    private String name;
    private int l;
    private Random rand = new Random();
    @SideOnly(Side.CLIENT)
    private IIcon sideIcon, topIcon, topIconReady;
    private double partOffsetX, partOffsetZ, partOffsetY;
    public Block readyBlock;


    public BlockGlassmouldMF(String name, Block readyBlock) {
        super(name, Material.clay);

        setBlockName(name);

        this.name = name;
        this.readyBlock = readyBlock;
        this.setHardness(0.2F);
        //this.setHarvestLevel("pickaxe", 1);
        this.setCreativeTab(CreativeTabMF.tabOres);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityGlassmould();
    }

    public void updateBlock (World world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);

        if (block != null && block instanceof BlockGlassmouldMF) {
            world.setBlock(x, y, z, readyBlock);
            world.removeTileEntity(x, y, z);
        }
    }


    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        super.updateTick(world, x, y, z, random);
    }


    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile != null && tile instanceof TileEntityGlassmould && ((TileEntityGlassmould) tile).progress > 0) {
            if (random.nextInt(10) < 2) {
                partOffsetX = (double) (random.nextInt(6) + 1) / 10;
                partOffsetZ = (double) (random.nextInt(6) + 1) / 10;
                partOffsetY = (double) (random.nextInt(2) + 1) / 10;
                world.spawnParticle("smoke", x + 0.2D + partOffsetX, y + 0.45D + partOffsetY, z + 0.2D + partOffsetZ, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile != null && tile instanceof TileEntityGlassmould)
            world.removeTileEntity(x, y, z);
        super.breakBlock(world, x, y, z, block, meta);
    }

    private void dropItem (World world, int x, int y, int z, Item item, int count) {
        if (!world.isRemote) {
            float f = this.rand.nextFloat() * 0.8F + 0.1F;
            float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
            float f2 = this.rand.nextFloat() * 0.8F + 0.1F;
            EntityItem entityitem = new EntityItem(world, x + f, y + f1, z + f2,
                    new ItemStack(item, count));

            float f3 = 0.05F;
            entityitem.motionX = (float) this.rand.nextGaussian() * f3;
            entityitem.motionY = (float) this.rand.nextGaussian() * f3 + 0.2F;
            entityitem.motionZ = (float) this.rand.nextGaussian() * f3;
            world.spawnEntityInWorld(entityitem);
        }
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int i) {
        return Item.getItemFromBlock(this);
    }


    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return side == 1 ? topIcon : sideIcon;
    }


    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register)
    {
        topIcon = register.registerIcon("minefantasy2:zsAddon/" + name);
        sideIcon = register.registerIcon("minefantasy2:zsAddon/glassmould_side");
    }

    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z) {
        return Item.getItemFromBlock(this);
    }


}
