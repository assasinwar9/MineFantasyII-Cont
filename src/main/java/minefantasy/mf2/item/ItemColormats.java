package minefantasy.mf2.item;

import cpw.mods.fml.common.registry.GameRegistry;
import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.item.list.CreativeTabMF;

public class ItemColormats extends ItemComponentMF {
    public ItemColormats(String name) {
        super(1);
        setTextureName("minefantasy2:zsItems/colormats/" + name);
        this.setCreativeTab(CreativeTabMF.tabMaterialsMF);
        GameRegistry.registerItem(this, "MF_" + name, MineFantasyII.MODID);
        this.setUnlocalizedName(name);
    }
}
