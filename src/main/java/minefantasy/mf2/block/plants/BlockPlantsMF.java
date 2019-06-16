package minefantasy.mf2.block.plants;

import cpw.mods.fml.common.registry.GameRegistry;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.item.list.ComponentListMF;
import minefantasy.mf2.mechanics.worldGen.WorldGenMFTree;
import net.minecraft.block.BlockContainer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.ArrayList;
import java.util.Random;

public class BlockPlantsMF extends BlockBush implements IGrowable {
    private Item harvest_1;
    private Item harvest_2;
    private Random rand = new Random();
    private int fruitGenCh;
    private int growChance;
    private String regName;

    private int firstHarvestChance = 100;
    private int secondHarvestChance = 100;

    private int firstHarvestCount = 1;
    private int secondHarvestBound = 3;

    public BlockPlantsMF(String name, int fruitGenChance, Item harvest_1, Item harvest_2, int growChance, int growStage) {
        super(Material.plants);
        setStepSound(Block.soundTypeGrass);
        float f = 0.4F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.regName = (growStage == 0) ? name : name + "_stage_" + growStage;
        this.harvest_1 = harvest_1;
        this.harvest_2 = harvest_2;
        this.fruitGenCh = fruitGenChance;
        this.growChance = growChance;

        setBlockTextureName("minefantasy2:plants/" + regName);
        GameRegistry.registerBlock(this, regName);
        setBlockName(regName);
    }



    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        if (!world.isRemote) {
            super.updateTick(world, x, y, z, rand);

            Block block = world.getBlock(x, y, z);

            if (world.getBlockLightValue(x, y + 1, z) >= 9) {
                if (block == BlockListMF.silverhead_empty)
                    //initGrow(world, x, y, z, true, BlockListMF.silverhead);
                	;
                if (block == BlockListMF.carxanium_stage_1)
                    initGrow(world, x, y, z, false, BlockListMF.carxanium_stage_2);
                if (block == BlockListMF.carxanium_stage_2)
                    //initGrow(world, x, y, z, false, BlockListMF.carxanium_stage_3);;
                	;
            }
        }
    }

    private void initGrow (World world, int x, int y, int z, boolean isHarvest, Block block) {
        if (isHarvest) {
            if (rand.nextInt(100) <= this.fruitGenCh)
                world.setBlock(x, y, z, block);
        }
        if (!isHarvest) {
            if (rand.nextInt(100) <= this.growChance)
                world.setBlock(x, y, z, block);
        }
    }

    private void processGrow(World world, int x, int y, int z, Random rand) {

    }


    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer user, int side, float xOffset,
                                    float yOffset, float zOffset) {
        Block block = world.getBlock(x, y, z);
        if (block instanceof BlockPlantsMF) {
            /*if (block == BlockListMF.silverhead) {
                dropItems(world, x, y, z, new ItemStack(ComponentListMF.silverhead_harvest, (rand.nextInt(3) + 1)));
                world.setBlock(x, y, z, BlockListMF.silverhead_empty);
                return true;
            }*/
        }
        return false;
    }

    public void dropItems(World world, int x, int y, int z, ItemStack items) {
        if (!world.isRemote) {
            EntityItem drop = new EntityItem(world, x , y, z, items);
            drop.delayBeforeCanPickup = 0;
            world.spawnEntityInWorld(drop);
        }
    }

    public Block setFirstHarvestChance (int chance) {
        this.firstHarvestChance = chance;
        return this;
    }

    public Block setFirstHarvestCount (int count) {
        this.firstHarvestCount = count;
        return this;
    }

    public Block setSecondHarvestChance (int chance) {
        this.secondHarvestChance = chance;
        return this;
    }

    public Block setSecondHarvestCount (int bound) {
        this.secondHarvestBound = bound;
        return this;
    }


    public boolean func_149880_a(World p_149880_1_, int p_149880_2_, int p_149880_3_, int p_149880_4_,
                                 int p_149880_5_) {
        return p_149880_1_.getBlock(p_149880_2_, p_149880_3_, p_149880_4_) == this
                && (p_149880_1_.getBlockMetadata(p_149880_2_, p_149880_3_, p_149880_4_) & 7) == p_149880_5_;
    }


    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> ret = super.getDrops(world, x, y, z, metadata, fortune);

        if (this.harvest_2 != null){
            if (rand.nextInt(100) <= secondHarvestChance)
                ret.add(new ItemStack(this.harvest_2, rand.nextInt(secondHarvestBound)));
        }

        if (this.harvest_1 != null) {
            if (rand.nextInt(100) <= firstHarvestChance)
                ret.add(new ItemStack(this.harvest_1, firstHarvestCount));
        }

        return ret;
    }


    public int damageDropped(int p_149692_1_) {
        return MathHelper.clamp_int(p_149692_1_ & 7, 0, 5);
    }

    public boolean func_149851_a(World p_149851_1_, int p_149851_2_, int p_149851_3_, int p_149851_4_,
                                 boolean p_149851_5_) {
        return true;
    }

    public boolean func_149852_a(World p_149852_1_, Random p_149852_2_, int p_149852_3_, int p_149852_4_,
                                 int p_149852_5_) {
        return true;
    }

    public void func_149853_b(World p_149853_1_, Random p_149853_2_, int p_149853_3_, int p_149853_4_,
                              int p_149853_5_) {
        this.processGrow(p_149853_1_, p_149853_3_, p_149853_4_, p_149853_5_, p_149853_2_);
    }
}