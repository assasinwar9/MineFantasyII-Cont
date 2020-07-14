package minefantasy.mf2.api.crafting.kiln;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface IKilnRecipe {
    //required time
    Item raw ();

    //result item
    Item result ();

    //meta of result, default = 0
    int meta ();

    //temperature param
    float minTemp();

    //basic time scale
    float time ();
}
