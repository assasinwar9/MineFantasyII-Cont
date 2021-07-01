package minefantasy.mf2.item;

import cpw.mods.fml.common.registry.GameRegistry;
import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.block.herbs.BlockHerbCarxanium;
import minefantasy.mf2.block.herbs.BlockHerbsMF;
import minefantasy.mf2.item.list.CreativeTabMF;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ItemHerbMF extends Item {
    protected String name;
    protected int itemRarity, meta;
    private Block plantableBlock;

    public ItemHerbMF(String name, int rarity) {
        this (name, rarity, Blocks.air, 0);
    }


    public ItemHerbMF(String name, int rarity, Block plantableBlock, int meta) {
        itemRarity = rarity;
        this.plantableBlock = plantableBlock;
        this.name = name;
        this.meta = meta;
        setTextureName("minefantasy2:herbs/" + name);

        this.setCreativeTab(CreativeTabMF.tabDecorations);

        GameRegistry.registerItem(this, "MF_Herb_" + name, MineFantasyII.MODID);

        this.setUnlocalizedName(name);
    }

    public boolean onItemUse(ItemStack held, EntityPlayer user, World world, int x, int y, int z, int side, float xOffset, float yOffset, float zOffset)
    {
        if (plantableBlock == Blocks.air) {
            return false;
        }
        if (side != 1)
        {
            return false;
        }
        if (plantableBlock != null && (plantableBlock instanceof BlockHerbsMF)) {
            Block target = world.getBlock(x, y, z);
            if (world.isAirBlock(x, y + 1, z) && user.canPlayerEdit(x, y, z, side, held) && user.canPlayerEdit(x, y + 1, z, side, held)
                    && ((BlockHerbsMF) plantableBlock).isRightSoil(target)) {
                world.setBlock(x, y + 1, z, plantableBlock);
                world.setBlockMetadataWithNotify(x, y + 1, z, meta, 2);
                --held.stackSize;
                return true;
            }
            else return false;
        }
        else return false;
    }

}
