package minefantasy.mf2.item;

import cpw.mods.fml.common.registry.GameRegistry;
import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.item.list.CreativeTabMF;

public class ItemSchichts extends ItemComponentMF {
    public ItemSchichts(String name, int rarity) {
        super(rarity);
        setTextureName("minefantasy2:zsItems/" + name);
        this.setCreativeTab(CreativeTabMF.tabMaterialsMF);
        GameRegistry.registerItem(this, "MF_Schicht_" + name, MineFantasyII.MODID);
        this.setUnlocalizedName(name);
    }
}
