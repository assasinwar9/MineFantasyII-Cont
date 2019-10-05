package minefantasy.mf2.recipe;

import minefantasy.mf2.api.MineFantasyAPI;
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

        MineFantasyAPI.addBurningKilnRecipe(Item.getItemFromBlock(Blocks.cobblestone), Item.getItemFromBlock(Blocks.stone), 1000F, 200F);
        MineFantasyAPI.addBurningKilnRecipe(Items.clay_ball, Items.brick, 800F, 180F);

        //glass casting

    }
}
