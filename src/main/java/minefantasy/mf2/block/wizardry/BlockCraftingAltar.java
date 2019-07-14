package minefantasy.mf2.block.wizardry;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.block.tileentity.TileEntityCraftingAltar;
import minefantasy.mf2.block.tileentity.TileEntityCrucible;
import minefantasy.mf2.config.ConfigHardcore;
import minefantasy.mf2.item.ItemFilledMould;
import minefantasy.mf2.item.list.ComponentListMF;
import minefantasy.mf2.item.list.CreativeTabMF;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class BlockCraftingAltar extends BlockContainer {
    private Random rand = new Random();
    private IIcon sideTex, topTex;

    public BlockCraftingAltar() {
        super(Material.rock);
        GameRegistry.registerBlock(this, "MF_CraftingAltar");
        setBlockName("craftingAltar");
        this.setStepSound(Block.soundTypeStone);
        this.setHardness(8F);
        this.setResistance(8F);
        this.setCreativeTab(CreativeTabMF.tabUtil);
        setBlockTextureName("minefantasy2:zsAddon/zs_block");
    }

    private static TileEntityCraftingAltar getTile(IBlockAccess world, int x, int y, int z) {
        return (TileEntityCraftingAltar) world.getTileEntity(x, y, z);
    }

   /* public static void updateFurnaceBlockState(boolean state, World world, int x, int y, int z) {
        int l = world.getBlockMetadata(x, y, z);
        TileEntityCrucible tileentity = getTile(world, x, y, z);
        keepInventory = true;
        Block block = world.getBlock(x, y, z);

        if (block != null && block instanceof BlockCraftingAltar) {
            int blocktier = ((BlockCraftingAltar) block).tier;
            boolean auto = ((BlockCraftingAltar) block).isAuto;
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
        return new TileEntityCraftingAltar();
    }

    /*@Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {

        super.breakBlock(world, x, y, z, block, meta);
    }*/

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer user, int side, float xOffset,
                                    float yOffset, float zOffset) {
        TileEntity tile = getTile(world, x, y, z);
        if (tile instanceof TileEntityCraftingAltar) {
            return ((TileEntityCraftingAltar) tile).interract(world, x, y, z, user);
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
}
