package minefantasy.mf2.item.list;

import cpw.mods.fml.common.registry.GameRegistry;
import minefantasy.mf2.api.MineFantasyAPI;
import minefantasy.mf2.api.crafting.MineFantasyFuels;
import minefantasy.mf2.api.mining.RandomDigs;
import minefantasy.mf2.api.mining.RandomOre;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.item.*;
import minefantasy.mf2.item.custom.ItemCustomComponent;
import minefantasy.mf2.item.food.FoodListMF;
import minefantasy.mf2.item.gadget.ItemBombComponent;
import minefantasy.mf2.item.gadget.ItemCrossbowPart;
import minefantasy.mf2.item.heatable.ItemHeated;
import minefantasy.mf2.material.BaseMaterialMF;
import minefantasy.mf2.material.WoodMaterial;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * @author Anonymous Productions
 */
public class ComponentListMF {
    public static final String[] ingotMats = new String[]{"copper", "tin", "bronze", "pigiron", "steel", "encrusted",
            "blacksteelweak", "blacksteel", "silver", "redsteelweak", "redsteel", "bluesteelweak", "bluesteel",
            "adamantium", "mithril", "ignotumite", "mithium", "ender", "tungsten", "obsidian"};

    public static Item non_defined_item = new ItemComponentMF("non-defined", -1).setTextureName("minefantasy2:zsItems/non-defined");

    public static Item dull_ember = new ItemComponentMF("dull_ember", -1).setTextureName("minefantasy2:zsItems/dull_ember");

    public static Item schichtCopper = new ItemSchichts("schichtCopper", -1);
    public static Item schichtTin = new ItemSchichts("schichtTin", -1);
    public static Item schichtIron = new ItemSchichts("schichtIron", 0);
    public static Item schichtSilver = new ItemSchichts("schichtSilver", 0);
    public static Item schichtGold = new ItemSchichts("schichtGold", 0);

    public static Item clay_pot = new ItemMFBowl("clay_pot").setStoragePlacement("pot", "pot");
    public static Item clay_pot_uncooked = new ItemComponentMF("clay_pot_uncooked", 0);
    public static Item ingot_mould = new ItemComponentMF("ingot_mould").setStoragePlacement("bar", "mould");
    public static Item ingot_mould_uncooked = new ItemComponentMF("ingot_mould_uncooked", 0);
    public static Item pie_tray_uncooked = new ItemComponentMF("pie_tray_uncooked", 0);
    //zs items
    public static Item tar = new ItemComponentMF("tar", 0).setTextureName("minefantasy2:zsItems/tar").setContainerItem(clay_pot);
    public static Item bark = new ItemComponentMF("bark", 0).setTextureName("minefantasy2:zsItems/bark");;
    public static Item buildingCompound = new ItemComponentMF("buildingCompound", 0).setTextureName("minefantasy2:zsItems/buildingCompound").setContainerItem(clay_pot);
    public static Item slag = new ItemComponentMF("slag", 0).setTextureName("minefantasy2:zsItems/slag");
    public static Item slag_pot = new ItemComponentMF("slag_pot", 0).setTextureName("minefantasy2:zsItems/slag_pot").setContainerItem(clay_pot);
    //hide
    public static Item hideSmall_limed = new ItemComponentMF("hideSmall_limed", 0).setTextureName("minefantasy2:zsItems/hideSmall_limed");
    public static Item hideMedium_limed = new ItemComponentMF("hideMedium_limed", 0).setTextureName("minefantasy2:zsItems/hideMedium_limed");
    public static Item hideLarge_limed = new ItemComponentMF("hideLarge_limed", 0).setTextureName("minefantasy2:zsItems/hideLarge_limed");
    public static Item rawhideSmall_washed = new ItemComponentMF("rawhideSmall_washed", 0).setTextureName("minefantasy2:zsItems/rawhideSmall_washed");
    public static Item rawhideMedium_washed = new ItemComponentMF("rawhideMedium_washed", 0).setTextureName("minefantasy2:zsItems/rawhideMedium_washed");
    public static Item rawhideLarge_washed = new ItemComponentMF("rawhideLarge_washed", 0).setTextureName("minefantasy2:zsItems/rawhideLarge_washed");

    public static Item leather_clear = new ItemComponentMF("leather_clear", -1).setTextureName("minefantasy2:zsItems/leather_clear");

    //new color materials (zs items)
    public static Item colormat_black = new ItemColormats("colormat_black");
    public static Item colormat_red = new ItemColormats("colormat_red");
    public static Item colormat_green = new ItemColormats("colormat_green");
    public static Item colormat_brown = new ItemColormats("colormat_brown");
    public static Item colormat_blue = new ItemColormats("colormat_blue");
    public static Item colormat_purple = new ItemColormats("colormat_purple");
    public static Item colormat_cyan = new ItemColormats("colormat_cyan");
    public static Item colormat_light_gray = new ItemColormats("colormat_light_gray");
    public static Item colormat_gray = new ItemColormats("colormat_gray");
    public static Item colormat_pink = new ItemColormats("colormat_pink");
    public static Item colormat_lime = new ItemColormats("colormat_lime");
    public static Item colormat_yellow = new ItemColormats("colormat_yellow");
    public static Item colormat_light_blue = new ItemColormats("colormat_light_blue");
    public static Item colormat_magenta = new ItemColormats("colormat_magenta");
    public static Item colormat_orange = new ItemColormats("colormat_orange");
    public static Item colormat_white = new ItemColormats("colormat_white");
    //herbs
    public static Item silverhead_harvest = new ItemHerbMF("silverhead_harvest", 0, null).setTextureName("minefantasy2:herbs/herb_silverhead_harvest");
    public static Item carxanium_items = new ItemHerbMF("carxanium_items", 0, BlockListMF.herb_carxanium);
    public static Item carxanium_root = new ItemHerbMF("carxanium_root", 0, BlockListMF.herb_carxanium);
    public static Item sinister_grass_item = new ItemHerbMF("sinister_grass_item", 0);
    public static Item endure_root_item = new ItemHerbMF("endure_root_item", 0, BlockListMF.herb_endure_root);
    public static Item dogsear_item = new ItemHerbMF("dogsear_item", 0);
    public static Item flameflower_item = new ItemHerbMF("flameflower_item", 1, BlockListMF.herb_flameflower);
    public static Item wispflock_item = new ItemHerbMF("wispflock_item", 0);
    public static Item moonflower_item = new ItemHerbMF("moonflower_item", 1, BlockListMF.herb_moonflower);
    public static Item sadblossom_item = new ItemHerbMF("sadblossom_item", 0);
    public static Item boneberry_item = new ItemHerbMF("boneberry_item", 0);
    public static Item thorn_root_item = new ItemHerbMF("thorn_root_item", 0, BlockListMF.herb_thorn_root);
    public static Item savyire_item = new ItemHerbMF("savyire_item", 0, BlockListMF.herb_savyire);

    //orig mf items
    public static ItemComponentMF[] ingots = new ItemComponentMF[ingotMats.length];

    public static ItemComponentMF plank = new ItemComponentMF("plank").setCustom(1, "wood").setStoragePlacement("plank",
            "plank");
    public static Item vine = new ItemComponentMF("vine", -1);
    public static Item sharp_rock = new ItemComponentMF("sharp_rock", -1);

    public static Item limestone_item = new ItemComponentMF("limestone_item", 0);
    public static Item borax = new ItemComponentMF("borax", 0);

    public static Item coalDust = new ItemComponentMF("coalDust", 0).setContainerItem(clay_pot);
    public static Item nitre = new ItemComponentMF("nitre", 0);
    public static Item sulfur = new ItemComponentMF("sulfur", 0);
    public static Item iron_prep = new ItemComponentMF("iron_prep", 0);
    public static Item blackpowder = new ItemBombComponent("blackpowder", 0, "powder", 0).setContainerItem(clay_pot);
    public static Item blackpowder_advanced = new ItemBombComponent("blackpowder_advanced", 1, "powder", 1)
            .setContainerItem(clay_pot);
    public static Item fletching = new ItemComponentMF("fletching", 0);
    public static Item shrapnel = new ItemBombComponent("shrapnel", 0, "filling", 1)
            .setContainerItem(ComponentListMF.clay_pot);
    public static Item magma_cream_refined = new ItemBombComponent("magma_cream_refined", 1, "filling", 2)
            .setContainerItem(clay_pot);
    public static Item bomb_fuse = new ItemBombComponent("bomb_fuse", 0, "fuse", 0);
    public static Item bomb_fuse_long = new ItemBombComponent("bomb_fuse_long", 0, "fuse", 1);
    public static Item bomb_casing_uncooked = new ItemComponentMF("bomb_casing_uncooked", 0);
    public static Item bomb_casing = new ItemBombComponent("bomb_casing", 0, "bombcase", 0);
    public static Item mine_casing_uncooked = new ItemComponentMF("mine_casing_uncooked", 0);
    public static Item mine_casing = new ItemBombComponent("mine_casing", 0, "minecase", 0);
    public static Item bomb_casing_iron = new ItemBombComponent("bomb_casing_iron", 0, "bombcase", 1);
    public static Item mine_casing_iron = new ItemBombComponent("mine_casing_iron", 0, "minecase", 1);
    public static Item bomb_casing_obsidian = new ItemBombComponent("bomb_casing_obsidian", 1, "bombcase", 2);
    public static Item mine_casing_obsidian = new ItemBombComponent("mine_casing_obsidian", 1, "minecase", 2);
    public static Item bomb_casing_crystal = new ItemBombComponent("bomb_casing_crystal", 1, "bombcase", 3);
    public static Item mine_casing_crystal = new ItemBombComponent("mine_casing_crystal", 1, "minecase", 3);
    public static Item bomb_casing_arrow = new ItemBombComponent("bomb_casing_arrow", 1, "arrow", 0);
    public static Item bomb_casing_bolt = new ItemBombComponent("bomb_casing_bolt", 1, "bolt", 0);

    public static Item coke = new ItemComponentMF("coke", 1);
    public static Item diamond_shards = new ItemComponentMF("diamond_shards", 0);

    public static Item clay_brick = new ItemComponentMF("clay_brick", 0);
    public static Item kaolinite = new ItemComponentMF("kaolinite", 0);
    public static Item kaolinite_dust = new ItemComponentMF("kaolinite_dust", 0).setContainerItem(clay_pot);
    public static Item fireclay = new ItemComponentMF("fireclay", 0);
    public static Item fireclay_brick = new ItemComponentMF("fireclay_brick", 0);
    public static Item strong_brick = new ItemComponentMF("strong_brick", 0).setStoragePlacement("bar", "firebrick");

    public static Item hideSmall = new ItemComponentMF("hideSmall", 0);
    public static Item hideMedium = new ItemComponentMF("hideMedium", 0);
    public static Item hideLarge = new ItemComponentMF("hideLarge", 0);
    public static Item rawhideSmall = new ItemHide("rawhideSmall", rawhideSmall_washed, 1.0F);
    public static Item rawhideMedium = new ItemHide("rawhideMedium", rawhideMedium_washed, 1.5F);
    public static Item rawhideLarge = new ItemHide("rawhideLarge", rawhideLarge_washed, 3.0F);

    public static Item dragon_heart = new ItemSpecialDesign("dragon_heart", 1, "dragon");

    public static Item leather_strip = new ItemComponentMF("leather_strip", 0);
    public static Item nail = new ItemComponentMF("nail", 0);
    public static Item rivet = new ItemComponentMF("rivet", 0);
    public static Item thread = new ItemComponentMF("thread", 0);
    public static Item obsidian_rock = new ItemComponentMF("obsidian_rock", 0);

    public static Item oreCopper = new ItemRawOreMF("oreCopper", -1);
    public static Item oreTin = new ItemRawOreMF("oreTin", -1);
    public static Item oreIron = new ItemRawOreMF("oreIron", 0);
    public static Item oreSilver = new ItemRawOreMF("oreSilver", 0);
    public static Item oreGold = new ItemRawOreMF("oreGold", 0);
    public static Item oreTungsten = new ItemRawOreMF("oreTungsten", 1);

    public static Item hotItem = new ItemHeated();

    public static Item plant_oil = new ItemComponentMF("plant_oil", 0).setStoragePlacement("jug", "jugoil")
            .setContainerItem(FoodListMF.jug_empty);

    public static Item talisman_lesser = new ItemComponentMF("talisman_lesser", 1);
    public static Item talisman_greater = new ItemComponentMF("talisman_greater", 3);

    public static Item bolt = new ItemComponentMF("bolt", 0);
    public static Item iron_frame = new ItemComponentMF("iron_frame", 0);
    public static Item iron_strut = new ItemComponentMF("iron_strut", 0);
    public static Item bronze_gears = new ItemComponentMF("bronze_gears", 0);
    public static Item tungsten_gears = new ItemComponentMF("tungsten_gears", 1);
    public static Item steel_tube = new ItemComponentMF("steel_tube", 0);
    public static Item cogwork_shaft = new ItemComponentMF("cogwork_shaft", 1);
    public static Item ingotCompositeAlloy = new ItemComponentMF("ingotCompositeAlloy", 1);
    public static Item coal_prep = new ItemComponentMF("coal_prep", 0);

    public static Item ingot_mould_filled = new ItemFilledMould();

    public static Item crossbow_stock_wood = new ItemCrossbowPart("cross_stock_wood", "stock").addSpeed(1.0F)
            .addRecoil(0F);
    public static Item crossbow_stock_iron = new ItemCrossbowPart("cross_stock_iron", "stock").addSpeed(1.0F)
            .addRecoil(-2F).addDurability(150);
    public static Item crossbow_handle_wood = new ItemCrossbowPart("cross_handle_wood", "stock").addSpeed(0.5F)
            .addRecoil(2F).addSpread(1.0F).setHandCrossbow(true);

    public static Item cross_arms_basic = new ItemCrossbowPart("cross_arms_basic", "mechanism").addPower(1.00F)
            .addSpeed(0.50F).addRecoil(4F).addSpread(1.00F);
    public static Item cross_arms_light = new ItemCrossbowPart("cross_arms_light", "mechanism").addPower(0.85F)
            .addSpeed(0.25F).addRecoil(2F).addSpread(0.50F);
    public static Item cross_arms_heavy = new ItemCrossbowPart("cross_arms_heavy", "mechanism").addPower(1.15F)
            .addSpeed(1.00F).addRecoil(8F).addSpread(2.00F);
    public static Item cross_arms_advanced = new ItemCrossbowPart("cross_arms_advanced", "mechanism").addPower(1.15F)
            .addSpeed(1.00F).addRecoil(6F).addSpread(0.25F).addDurability(150);

    public static Item cross_bayonet = new ItemCrossbowPart("cross_bayonet", "muzzle").addBash(4.0F).addRecoil(-1F)
            .addSpeed(0.5F);
    public static Item cross_ammo = new ItemCrossbowPart("cross_ammo", "mod").addCapacity(5).addSpread(2.00F);
    public static Item cross_scope = new ItemCrossbowPart("cross_scope", "mod").setScope(0.75F);

    public static ItemCustomComponent chainmesh = new ItemCustomComponent("chainmesh", 1F, "metal")
            .setStoragePlacement("sheet", "mail");
    public static ItemCustomComponent scalemesh = new ItemCustomComponent("scalemesh", 1F, "metal")
            .setStoragePlacement("sheet", "scale");
    public static ItemCustomComponent splintmesh = new ItemCustomComponent("splintmesh", 1F, "metal")
            .setStoragePlacement("sheet", "splint");
    public static ItemCustomComponent plate = new ItemCustomComponent("plate", 2F, "metal").setStoragePlacement("sheet",
            "plate");
    public static ItemCustomComponent plate_huge = new ItemCustomComponent("plate_huge", 8F, "metal")
            .setStoragePlacement("bigplate", "bigplate");
    public static ItemCustomComponent metalHunk = new ItemCustomComponent("hunk", 0.25F, "metal");
    public static ItemCustomComponent arrowhead = new ItemCustomComponent("arrowhead", 1 / 4F, "metal");
    public static ItemCustomComponent bodkinhead = new ItemCustomComponent("bodkinhead", 1 / 4F, "metal");
    public static ItemCustomComponent broadhead = new ItemCustomComponent("broadhead", 1 / 4F, "metal");
    public static ItemCustomComponent cogwork_armour = (ItemCustomComponent) new ItemCustomComponent("cogwork_armour",
            30F, "metal").setCanDamage().setCreativeTab(CreativeTabMF.tabGadget).setMaxStackSize(1);
    public static ItemCustomComponent bar = (ItemCustomComponent) new ItemCustomComponent("bar", 1F, "metal")
            .setStoragePlacement("bar", "bar").setCreativeTab(CreativeTabMF.tabMaterialsMF);

    public static Item limestone_item_pot = new ItemComponentMF("limestone_item_pot", 0).setContainerItem(clay_pot);
    public static Item coal_flux = new ItemComponentMF("coal_flux", 0);

    public static Item copper_coin = new ItemComponentMF("copper_coin", 0);
    public static Item silver_coin = new ItemComponentMF("silver_coin", 0);
    public static Item gold_coin = new ItemComponentMF("gold_coin", 0);

    public static Item hinge = new ItemComponentMF("hinge", 0);
    public static Item plank_cut = new ItemComponentMF("plank_cut").setCustom(1, "wood").setStoragePlacement("plank",
            "plankcut");
    public static Item plank_pane = new ItemComponentMF("plank_pane").setCustom(6, "wood").setStoragePlacement("sheet",
            "woodpane");

    public static Item cogwork_pulley = new ItemComponentMF("cogwork_pulley", 1)
            .setCreativeTab(CreativeTabMF.tabGadget);

    public static Item artefacts = new ItemArtefact("artefact_any");

    public static Item ornate_items = new ItemSpecialDesign("ornate_items", 1, "ornate");

    public static void load() {
        WoodMaterial.init();
        Items.potionitem.setContainerItem(Items.glass_bottle);
        GameRegistry.registerFuelHandler(new FuelHandlerMF());
        MineFantasyAPI.registerFuelHandler(new AdvancedFuelHandlerMF());
        Items.iron_ingot.setTextureName("minefantasy2:component/ingotWroughtIron");
        Blocks.iron_block.setBlockTextureName("minefantasy2:metal/iron_block");
        Blocks.iron_bars.setBlockTextureName("minefantasy2:metal/iron_bars");
        for (int a = 0; a < ingotMats.length; a++) {
            BaseMaterialMF mat = BaseMaterialMF.getMaterial(ingotMats[a]);
            String name = mat.name;
            int rarity = mat.rarity;

            ingots[a] = new ItemComponentMF("ingot" + name, rarity);
            OreDictionary.registerOre("ingot" + name, ingots[a]);
        }

        addRandomDrops();
        initFuels();
        OreDictionary.registerOre("ingotCompositeAlloy", ingotCompositeAlloy);
        OreDictionary.registerOre("ingotIron", Items.iron_ingot);
        OreDictionary.registerOre("ingotGold", Items.gold_ingot);

        AdvancedFuelHandlerMF.registerItems();
    }

    private static void initFuels() {
        MineFantasyFuels.addForgeFuel(new ItemStack(Items.coal, 1, 0), 900, 1500);// 1500C , 45s
        MineFantasyFuels.addForgeFuel(new ItemStack(Items.coal, 1, 1), 1200, 1800);// 1800C , 1m
        MineFantasyFuels.addForgeFuel(Items.blaze_powder, 200, 3000, true);// 3000C , 10s
        MineFantasyFuels.addForgeFuel(Items.blaze_rod, 300, 3000, true);// 3000C , 15s
        MineFantasyFuels.addForgeFuel(Items.fire_charge, 1200, 3500, true);// 3500C , 1m
        MineFantasyFuels.addForgeFuel(Items.lava_bucket, 2400, 5000, true);// 5000C , 2m
        MineFantasyFuels.addForgeFuel(Items.magma_cream, 2400, 4000, true, true);// 4000C , 2m

        MineFantasyFuels.addForgeFuel(ComponentListMF.coke, 1200, 2500, false, true);// 2500C , 1m
        MineFantasyFuels.addForgeFuel(ComponentListMF.magma_cream_refined, 2400, 5000, true, true);// 5000C , 2m

        MineFantasyFuels.addForgeFuel(ComponentListMF.coalDust, 1200, 180);// 180C , 60s
    }

    private static void addRandomDrops() {
        RandomOre.addOre(new ItemStack(kaolinite), 1.5F, Blocks.stone, -1, 32, 128, false);
        RandomOre.addOre(new ItemStack(limestone_item), 2F, Blocks.stone, -1, 0, 128, false);
        RandomOre.addOre(new ItemStack(borax), 1F, Blocks.stone, 2, 0, 128, false);
        RandomOre.addOre(new ItemStack(limestone_item), 20F, BlockListMF.limestone, 0, -1, 0, 256, true);
        RandomOre.addOre(new ItemStack(borax), 10F, BlockListMF.limestone, 0, 2, 0, 256, true);
        RandomOre.addOre(new ItemStack(Items.coal), 2F, Blocks.stone, -1, 0, 128, false);
        RandomOre.addOre(new ItemStack(sulfur), 2F, Blocks.stone, -1, 0, 16, false);
        RandomOre.addOre(new ItemStack(nitre), 3F, Blocks.stone, -1, 0, 64, false);
        RandomOre.addOre(new ItemStack(Items.redstone), 5F, Blocks.stone, 2, 0, 16, false);
        RandomOre.addOre(new ItemStack(Items.flint), 1F, Blocks.stone, -1, 0, 64, false);
        RandomOre.addOre(new ItemStack(diamond_shards), 0.2F, Blocks.stone, 2, 0, 16, false);
        RandomOre.addOre(new ItemStack(Items.quartz), 0.5F, Blocks.stone, 3, 0, 16, false);

        RandomOre.addOre(new ItemStack(sulfur), 10F, Blocks.netherrack, -1, 0, 512, false);
        RandomOre.addOre(new ItemStack(Items.glowstone_dust), 5F, Blocks.netherrack, -1, 0, 512, false);
        RandomOre.addOre(new ItemStack(Items.quartz), 5F, Blocks.netherrack, -1, 0, 512, false);
        RandomOre.addOre(new ItemStack(Items.blaze_powder), 5F, Blocks.netherrack, -1, 0, 512, false);
        RandomOre.addOre(new ItemStack(Items.nether_wart), 1F, Blocks.netherrack, -1, 0, 512, false);
        RandomOre.addOre(new ItemStack(Items.nether_star), 0.01F, Blocks.netherrack, -1, 0, 512, false);

        RandomDigs.addOre(new ItemStack(Blocks.skull, 1, 1), 0.1F, Blocks.soul_sand, 3, 0, 256, false);
        RandomDigs.addOre(new ItemStack(Items.bone), 5F, Blocks.dirt, -1, 0, 256, false);
        RandomDigs.addOre(new ItemStack(Items.rotten_flesh), 2F, Blocks.dirt, -1, 0, 256, false);
        RandomDigs.addOre(new ItemStack(Items.coal, 1, 1), 1F, Blocks.dirt, -1, 32, 64, false);

        RandomDigs.addOre(new ItemStack(Items.melon_seeds), 5F, Blocks.grass, -1, 0, 256, false);
        RandomDigs.addOre(new ItemStack(Items.pumpkin_seeds), 8F, Blocks.grass, -1, 0, 256, false);

        RandomOre.addOre(new ItemStack(oreCopper), 4F, Blocks.stone, 0, 48, 96, false);
        RandomOre.addOre(new ItemStack(oreTin), 2F, Blocks.stone, 0, 48, 96, false);
        RandomOre.addOre(new ItemStack(oreIron), 5F, Blocks.stone, 0, 0, 64, false);
        RandomOre.addOre(new ItemStack(oreSilver), 1.5F, Blocks.stone, 0, 0, 32, false);
        RandomOre.addOre(new ItemStack(oreGold), 1F, Blocks.stone, 0, 0, 32, false);

        RandomOre.addOre(new ItemStack(oreTungsten), 2F, Blocks.stone, 3, 0, 16, false, "tungsten");
    }

    public static ItemStack bar(String material) {
        return bar(material, 1);
    }

    public static ItemStack bar(String material, int stackSize) {
        return bar.createComm(material, stackSize);
    }
}
