package minefantasy.mf2.block.basic;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.item.list.CreativeTabMF;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;
import java.util.Random;

public class BlockSlabMF extends BlockSlab {
    private String name;
    private Random rand = new Random();
    @SideOnly(Side.CLIENT)
    private IIcon sideIcon, topIcon, bottomIcon;

    public BlockSlabMF(String name, Material material) {
        super(false, material);

        GameRegistry.registerBlock(this,"MF_slab_" + name);
        setBlockName("MF_slab_" + name);

        this.name = name;
        this.setHardness(2F);
        //this.setHarvestLevel("pickaxe", 1);
        this.setCreativeTab(CreativeTabMF.tabOres);
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int i) {
        return Item.getItemFromBlock(this);
    }


    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return side == 1 ? topIcon : side == 0 ? bottomIcon : sideIcon;
    }

    @Override
    public String func_150002_b(int meta) {
        return name;
    }


    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register)
    {
        topIcon = register.registerIcon("minefantasy2:zsAddon/" + name);
        sideIcon = register.registerIcon("minefantasy2:zsAddon/" + name);
        bottomIcon = register.registerIcon("minefantasy2:zsAddon/" + name);
    }

    @SideOnly(Side.CLIENT)
    public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
        return Item.getItemFromBlock(this);
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_) { }

}
