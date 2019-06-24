package minefantasy.mf2.api.tool;

import net.minecraft.item.ItemStack;

public interface IToolMF {
    /**
     * Gets the efficienty of this tool for processing (recommended to use
     * ToolMaterial.getEfficiencyOnProperMaterial())
     */
    float getEfficiency(ItemStack item);

    /**
     * Gets the tier of the material, some recipes may need certain tiers
     */
    int getTier(ItemStack item);

    /**
     * Gets the type of tool for crafting (eg, hammer, knife, needle)
     */
    String getToolType(ItemStack item);
}
