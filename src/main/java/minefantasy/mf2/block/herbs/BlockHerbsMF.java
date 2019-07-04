package minefantasy.mf2.block.herbs;

import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.item.ItemSchichts;
import minefantasy.mf2.item.list.ComponentListMF;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public abstract class BlockHerbsMF extends BlockBush {
    private String name;
    private int dropCount;
    private Random rand = new Random();

    public BlockHerbsMF() {
        super(Material.plants);

        this.setHardness(0f);
        this.setStepSound(soundTypeGrass);
        this.setResistance(0.1f);
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setLightOpacity(0);
        float f = 0.2F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 3.0F, 0.5F + f);

    }


    @Override
    public boolean canDropFromExplosion(Explosion unimportant) {
        return false;
    }


    @Override
    public boolean canHarvestBlock(EntityPlayer player, int meta) {
        return false;
    }



    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer user, int side, float xOffset,
                                    float yOffset, float zOffset) {
        return interract(world, x, y, z, user);
    }


    protected void dropItem(World world, int x, int y, int z, Item item, int count, boolean isRandom, boolean noLoss) {
        dropCount = isRandom ? rand.nextInt(count) : count;
        if (isRandom && noLoss && dropCount == 0){
            dropCount = 1;
        }

        ItemStack itemStack = new ItemStack(item, dropCount);

        if (!world.isRemote) {
            EntityItem drop = new EntityItem(world, x + 0.5, y + 0.5, z + 0.5, itemStack);
            drop.delayBeforeCanPickup = 10;
            world.spawnEntityInWorld(drop);
        }
    }

/*
    private boolean isRightTool(EntityPlayer player, Item tool) {
        ItemStack stackHeld = player.getEquipmentInSlot(0);
        if(stackHeld == null) {
            return tool == null;
        } else {
            Item held = stackHeld.getItem();
            return ((tool != null)
                    && (held != null) // Shouldn't ever be -- failsafe
                    &&  tool.getClass().isAssignableFrom(held.getClass()));
        }
    }*/


    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        if(world.getBlockLightValue(x, y, z) >=9) {
            if (isGrowingHerb())
                if (random.nextInt(100) <= getGrowChance())
                    world.setBlock(x, y, z, getNextGrowStage());

        }
    }

    @Override
    public void onNeighborBlockChange (World world, int x, int y, int z, Block block) {
        super.onNeighborBlockChange(world, x, y, z, block);
        if (!canBlockStay(world, x, y, z))
            world.setBlock(x, y, z, Blocks.air);
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        Block under = world.getBlock(x, y - 1, z);
        return isRightSoil(under);
    }

    @Override
    public Item getItemDropped (int meta, Random random, int fort) {
        return null;
    }

    @Override
    protected void checkAndDropBlock (World world, int x, int y, int z) { }

    /*protected void checkAndDropBlock(World world, int x, int y, int z) {
        world.setBlockToAir(x, y, z);
    }*/

    //is action for onBlockActivated event
    abstract boolean interract (World world, int x, int y, int z, EntityPlayer user);

    //define, can this herb to grow
    private boolean isGrowingHerb () {
        return getGrowChance() > 0;
    }
    //define, can this herb have harvest
    private boolean isHarvestHerb () {
        return getRipeChance() > 0;
    }

    //return chance of go to next grow stage
    abstract int getGrowChance();
    //return chance of generation mature harvest
    abstract int getRipeChance();

    //define the drop for breakBlock event
    abstract void getCustomDrop (World world, int x, int y, int z, Block block);

    //return next grow stage block
    abstract Block getNextGrowStage();
    //return block, which previous of mature herb
    abstract Block getPrevGrowStage();


    // ???
    abstract public boolean isRightSoil(Block ground);
    abstract public int getSizeFactor();
    abstract public boolean isGoodBiome(BiomeGenBase biome);

}