package minefantasy.mf2.recipe;

import minefantasy.mf2.api.MineFantasyAPI;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.item.food.FoodListMF;
import minefantasy.mf2.item.list.ComponentListMF;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BurningKilnRecipes {
    public static void init () {
        /*
        first item = raw item
        second item = result item
        minTemp (first float) = lower limit of executing progress
        basicTimeScale (next float) = ticks on ever one item.
        //
        Total progress time will be calculated = basicTimeScale * stackSize on slot * getProgressScale()
        progress scale:
        if (stackSize <= 4)
            return 1;
        else if (stackSize <= 8)
            return 1.2F;
        else if (stackSize <= 16)
            return 1.5F;
        else if (stackSize <= 32)
            return 2.0F;
        else if (stackSize > 32)
            return 2.5F;
        //
        all times are in ticks!
        one second = 20 ticks
         */

        //MineFantasyAPI.addBurningKilnRecipe(, , 800F, 180);

        MineFantasyAPI.addBurningKilnRecipe(Item.getItemFromBlock(Blocks.cobblestone), Item.getItemFromBlock(Blocks.stone), 1000F, 200F);
        MineFantasyAPI.addBurningKilnRecipe(Items.clay_ball, Items.brick, 800F, 120);
        //glass casting
        MineFantasyAPI.addBurningKilnRecipe(ComponentListMF.glassmould_block_filled, Item.getItemFromBlock(BlockListMF.glassmould_block_melted), 800F, 180);
        MineFantasyAPI.addBurningKilnRecipe(ComponentListMF.glassmould_bottle_filled, Item.getItemFromBlock(BlockListMF.glassmould_bottle_melted), 800F, 180);
        MineFantasyAPI.addBurningKilnRecipe(ComponentListMF.glassmould_panel_filled, Item.getItemFromBlock(BlockListMF.glassmould_panel_melted), 800F, 180);
        //bake ceramics and chamotte
        MineFantasyAPI.addBurningKilnRecipe(ComponentListMF.clay_pot_uncooked, ComponentListMF.clay_pot, 800F, 180);
        MineFantasyAPI.addBurningKilnRecipe(FoodListMF.jug_uncooked, FoodListMF.jug_empty, 800F, 180);
        MineFantasyAPI.addBurningKilnRecipe(ComponentListMF.pie_tray_uncooked, FoodListMF.pie_tray, 800F, 180);
        MineFantasyAPI.addBurningKilnRecipe(ComponentListMF.ingot_mould_uncooked, ComponentListMF.ingot_mould, 800F, 180);
        MineFantasyAPI.addBurningKilnRecipe(ComponentListMF.mine_casing_uncooked, ComponentListMF.mine_casing, 800F, 180);
        MineFantasyAPI.addBurningKilnRecipe(ComponentListMF.bomb_casing_uncooked, ComponentListMF.bomb_casing, 800F, 180);
        MineFantasyAPI.addBurningKilnRecipe(ComponentListMF.fireclay_brick, ComponentListMF.strong_brick, 800F, 180);

        MineFantasyAPI.addBurningKilnRecipe(ComponentListMF.glassmould_block_uncooked, ComponentListMF.glassmould_block, 800F, 180);
        MineFantasyAPI.addBurningKilnRecipe(ComponentListMF.glassmould_bottle_uncooked, ComponentListMF.glassmould_bottle, 800F, 180);
        MineFantasyAPI.addBurningKilnRecipe(ComponentListMF.glassmould_panel_uncooked, ComponentListMF.glassmould_panel, 800F, 180);

    }
}
