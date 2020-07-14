package minefantasy.mf2.api.crafting;

public class EnumCraftingQualityType {
    private String type;

    private EnumCraftingQualityType(String type) {
        this.type = type;
    }

    public static EnumCraftingQualityType FLAWED = new EnumCraftingQualityType("Flawed");
    public static EnumCraftingQualityType POOR = new EnumCraftingQualityType("Poor");
    public static EnumCraftingQualityType ORDINARY = new EnumCraftingQualityType("Ordinary");
    public static EnumCraftingQualityType PERFECT = new EnumCraftingQualityType("Perfect");
    public static EnumCraftingQualityType FLAWLESS = new EnumCraftingQualityType("Flawless");

    @Override
    public String toString() {
        return "MF_" + type;
    }
}
