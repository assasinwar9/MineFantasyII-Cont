package minefantasy.mf2.block.tree;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;



public class BlockGrindedLogs extends BlockLog {
    private IIcon sideTex, topTex;
    public String tree;

    public BlockGrindedLogs(String name, String treeType) {
        this.tree = treeType;
        GameRegistry.registerBlock(this, name);
        setBlockName(name);
        this.setResistance(1.0F);
        this.setHarvestLevel("axe", 0);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {
        if (meta == 15) {
            return side <= 1 ? topTex : sideTex;
        }
        return super.getIcon(side, meta);
    }

    @SideOnly(Side.CLIENT)
    @Override
    protected IIcon getSideIcon(int meta) {
        return sideTex;
    }

    @SideOnly(Side.CLIENT)
    @Override
    protected IIcon getTopIcon(int meta) {
        return topTex;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        sideTex = reg.registerIcon("minefantasy2:zsAddon/grindedLogs/side_gr_log_" + tree);
        topTex = reg.registerIcon("minefantasy2:zsAddon/grindedLogs/top_gr_log_" + tree);
    }
}