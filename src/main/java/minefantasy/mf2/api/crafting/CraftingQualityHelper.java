package minefantasy.mf2.api.crafting;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

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

    public static EnumCraftingQualityType getQualityTypeByItemStack(ItemStack itemStack) {
        if (itemStack.hasTagCompound()) {
            if (itemStack.getTagCompound().hasKey(EnumCraftingQualityType.FLAWED.toString()))
                return EnumCraftingQualityType.FLAWED;
            else if (itemStack.getTagCompound().hasKey(EnumCraftingQualityType.POOR.toString()))
                return EnumCraftingQualityType.POOR;
            else if (itemStack.getTagCompound().hasKey(EnumCraftingQualityType.ORDINARY.toString()))
                return EnumCraftingQualityType.ORDINARY;
            else if (itemStack.getTagCompound().hasKey(EnumCraftingQualityType.PERFECT.toString()))
                return EnumCraftingQualityType.PERFECT;
            else if (itemStack.getTagCompound().hasKey(EnumCraftingQualityType.FLAWLESS.toString()))
                return EnumCraftingQualityType.FLAWLESS;
        }
        return null;
    }

    public static float getDamageByQualityType(EnumCraftingQualityType qualityType, float maxDamage) {
        if (qualityType == EnumCraftingQualityType.FLAWED)
            return 0.75F * maxDamage;
        else if (qualityType == EnumCraftingQualityType.POOR)
            return 0.5F * maxDamage;
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

    public static float getArmourValueModByQualityType(EnumCraftingQualityType qualityType) {
        if (qualityType == EnumCraftingQualityType.FLAWED)
            return 0.55F;
        else if (qualityType == EnumCraftingQualityType.POOR)
            return 0.75F;
        else if (qualityType == EnumCraftingQualityType.ORDINARY)
            return 1F;
        else if (qualityType == EnumCraftingQualityType.PERFECT)
            return 1.2F;
        return 1.35F;
    }

    public static String getFormattedTooltipByQualityType(EnumCraftingQualityType qualityType) {
        if (qualityType == EnumCraftingQualityType.FLAWED)
             return EnumChatFormatting.DARK_GRAY + StatCollector.translateToLocal("attribute.flawed.name");
        else if (qualityType == EnumCraftingQualityType.POOR)
            return EnumChatFormatting.GRAY + StatCollector.translateToLocal("attribute.poor.name");
        else if (qualityType == EnumCraftingQualityType.ORDINARY)
            return EnumChatFormatting.WHITE + StatCollector.translateToLocal("attribute.ordinary.name");
        else if (qualityType == EnumCraftingQualityType.PERFECT)
            return EnumChatFormatting.GREEN + StatCollector.translateToLocal("attribute.perfect.name");
        else if (qualityType == EnumCraftingQualityType.FLAWLESS)
            return EnumChatFormatting.LIGHT_PURPLE + StatCollector.translateToLocal("attribute.flawless.name");
        return "";
    }
}
