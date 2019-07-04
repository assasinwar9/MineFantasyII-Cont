package minefantasy.mf2.block.herbs;

import cpw.mods.fml.common.registry.GameRegistry;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.item.list.ComponentListMF;
import minefantasy.mf2.item.list.HerbalicListMF;
import minefantasy.mf2.item.list.ToolListMF;
import minefantasy.mf2.item.tool.ItemSpadeMF;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;

public class BlockHerbCarxanium extends BlockHerbsMF {
    private int stage;
    private Block block;
    private String name = "herb_carxanium";

    public BlockHerbCarxanium (int stage) {
        setBlockName(name);
        this.stage = stage;

        setBlockTextureName("minefantasy2:herbs/herb_carxanium_stage_" + stage);
        GameRegistry.registerBlock(this, name + "_stage_" + stage);
    }

    @Override
    public boolean interract (World world, int x, int y, int z, EntityPlayer user) {
        ItemStack held = user.getEquipmentInSlot(0);

        if (held != null && held.getItem() instanceof ItemSpadeMF) {
            if (stage == 3) {
                dropItem(world, x, y, z, ComponentListMF.carxanium_root, 1, false, false);
                dropItem(world, x, y, z, ComponentListMF.carxanium_items, 3, true, true);
            }
            if (stage == 2) {
                dropItem(world, x, y, z, ComponentListMF.carxanium_root, 1, false, false);
            }
            held.damageItem(1, user);
            if (held.getItemDamage() >= held.getMaxDamage()) {
                if (world.isRemote)
                    user.renderBrokenItemStack(held);
                user.destroyCurrentEquippedItem();
            }
            world.setBlock(x, y, z, Blocks.air);
            return true;
        }
        if (stage == 3) {
            dropItem(world, x, y, z, ComponentListMF.carxanium_items, 3, true, true);
            world.setBlock(x, y, z, getPrevGrowStage());
            return true;
        }
        return false;
    }

    @Override
    public void getCustomDrop (World world, int x, int y, int z, Block block) {
        dropItem(world, x, y, z, ComponentListMF.carxanium_items, 3, true, true);
        if (stage == 2 || stage == 3)
            dropItem(world, x, y, z, ComponentListMF.carxanium_root, 1, false, false);
    }

    @Override
    public Block getNextGrowStage () {
        if (stage == 1)
            return BlockListMF.herb_carxanium_2;
        if (stage == 2)
            return BlockListMF.herb_carxanium_3;
        else return BlockListMF.herb_carxanium_3;
    }

    @Override
    public Block getPrevGrowStage () {
        return BlockListMF.herb_carxanium_2;
    }

    @Override
    public int getGrowChance () {
        return 80;
    }

    @Override
    public int getRipeChance () {
        return 0;
    }

    @Override
    public boolean isRightSoil (Block ground) {
        return ((ground == Blocks.stone) || (ground == Blocks.stonebrick) || (ground == Blocks.cobblestone));
    }

    @Override
    public int getSizeFactor () {
        return 1;
    }

    @Override
    public boolean isGoodBiome(BiomeGenBase biome) {
        return ((biome.rainfall >= 0.5f) && (biome.temperature > 0.2f) && (biome.temperature <= 0.5))
                || (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.FOREST))
                || (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.DENSE))
                || (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.CONIFEROUS));
    }

    @Override
    public boolean canPlaceBlockOn(Block ground) {
        return ((ground == Blocks.stone) || (ground == Blocks.stonebrick) || (ground == Blocks.cobblestone));
    }


}
