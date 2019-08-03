package minefantasy.mf2.block.basic;

import cpw.mods.fml.common.registry.GameRegistry;
import minefantasy.mf2.item.list.CreativeTabMF;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.Random;

public class BlockMineralOreMF extends Block {
    public int rarity;
    private int xp;
    private Item dropFirst, dropSec, dropThir;
    private int dropCountFirst, dropCountSec, dropCountThir;
    private Random rand = new Random();

    public BlockMineralOreMF(String name, int rarity, Item dropFirst, int dropCountFirst) {
        this(name, rarity, dropFirst, dropCountFirst, null, 0, null, 0);
    }

    public BlockMineralOreMF(String name, int rarity, Item dropFirst, int dropCountFirst, Item dropSec, int dropCountSec) {
        this(name, rarity, dropFirst, dropCountFirst, dropSec, dropCountSec, null, 0);
    }

    public BlockMineralOreMF(String name, int rarity, Item dropFirst, int dropCountFirst, Item dropSec, int dropCountSec,
                             Item dropThir, int dropCountThir) {
        super(Material.rock);
        this.dropFirst = dropFirst;
        this.dropSec = dropSec;
        this.dropThir = dropThir;
        this.dropCountFirst = dropCountFirst;
        this.dropCountSec = dropCountSec;
        this.dropCountThir = dropCountThir;
        this.rarity = rarity;

        this.setStepSound(Block.soundTypeStone);
        GameRegistry.registerBlock(this, name);
        setBlockName(name);
        setBlockTextureName("minefantasy2:zsAddon/ores/" + name);

        this.setHarvestLevel("pickaxe", 2);
        this.setHardness(4.0F);

        this.setCreativeTab(CreativeTabMF.tabOres);
        OreDictionary.registerOre(name, this);
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int i) {
        return null;
    }


    @Override
    public int getExpDrop(IBlockAccess world, int meta, int fortune) {
        return rand.nextInt(3);
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> ret = super.getDrops(world, x, y, z, metadata, fortune);

        if (dropFirst != null)
            ret.add(new ItemStack(dropFirst, (rand.nextInt(dropCountFirst) + 1)));
        if (dropSec != null)
            ret.add(new ItemStack(dropSec, (rand.nextInt(dropCountSec) + 1)));
        if (dropThir != null)
            ret.add(new ItemStack(dropThir, (rand.nextInt(dropCountThir) + 1)));

        return ret;
    }
}
