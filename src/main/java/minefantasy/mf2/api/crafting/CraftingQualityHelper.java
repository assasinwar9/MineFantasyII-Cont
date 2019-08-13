package minefantasy.mf2.api.crafting;

public class CraftingQualityHelper {
    public static EnumCraftingQualityType getQualityType(int threshold, int total) {
        final float result = total / threshold;

        if (result > 6F)
            return EnumCraftingQualityType.FLAWED;
        else if (result > 3F)
            return EnumCraftingQualityType.POOR;
        else if (result > 1F)
            return EnumCraftingQualityType.ORDINARY;
        else if (result > 0.5F)
            return EnumCraftingQualityType.PERFECT;
        return EnumCraftingQualityType.FLAWLESS;
    }

    public static float getDamageByQualityType(EnumCraftingQualityType qualityType) {
        if (qualityType == EnumCraftingQualityType.FLAWED)
            return 75F;
        else if (qualityType == EnumCraftingQualityType.POOR)
            return 50F;
        return 0;
    }

    public static float getToolQualityByQualityType(EnumCraftingQualityType qualityType) {
        if (qualityType == EnumCraftingQualityType.FLAWED)
            return 50F;
        else if (qualityType == EnumCraftingQualityType.POOR)
            return 75F;
        else if (qualityType == EnumCraftingQualityType.ORDINARY)
            return 100F;
        else if (qualityType == EnumCraftingQualityType.PERFECT)
            return 150F;
        return 200F;
    }
}
