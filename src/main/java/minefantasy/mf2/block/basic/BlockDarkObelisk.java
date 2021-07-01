package minefantasy.mf2.block.basic;


import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.block.tileentity.TileEntityDenseGravel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockDarkObelisk extends Block {

    public BlockDarkObelisk() {
        super(Material.rock);

        GameRegistry.registerBlock(this, "obelisk");
        setBlockName("obelisk");
        setBlockTextureName("minefantasy2:zsAddon/mono_black");
        this.setBlockUnbreakable();
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isOpaqueCube() {
        return true;
    }

    @Override
    public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
        if (rand.nextInt(20) < 15 && world.isRemote) {
            /*
            world.playSound(x + 0.5D, y + 0.5D, z + 0.5D, ("minefantasy2:blocks.dark_obelisk"),
                    1.0F, rand.nextFloat() * 0.4F + 1.1F, true);*/
            //world.playRecord("minefantasy2:blocks.dark_obelisk", x, y, z );
            world.playSoundEffect(x + 0.5D, y - 0.5D, z + 0.5D, "minefantasy2:blocks.dark_obelisk", 1.0F,
                    rand.nextFloat() * 0.4F + 0.8F);
        }
    }

}

