package minefantasy.mf2.recipe;

import minefantasy.mf2.api.MineFantasyAPI;
import minefantasy.mf2.item.list.ComponentListMF;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class AlchemyRecipes {
    public static void init () {

        MineFantasyAPI.addRefFurnaceRecipe(new ItemStack(Items.redstone, 2), null, new ItemStack(ComponentListMF.halite, 2), new ItemStack(ComponentListMF.dull_ember),
                null, new ItemStack(Items.emerald, 3), null, 100F, 500F, 1600F, 10);
        MineFantasyAPI.addRefFurnaceRecipe(new ItemStack(Items.redstone, 2), new ItemStack(Items.diamond, 3), null, new ItemStack(Items.bone, 5),
                null, new ItemStack(Items.emerald, 2), new ItemStack(ComponentListMF.limestone_item, 2), 60F, 400F, 1600F, 10);
    }
}
