package minefantasy.mf2.api.alchemy;

import net.minecraft.item.ItemStack;

public interface IRefFurnaceRecipe {
    //laute desintegra or permutatio actidentorum
    ItemStack catalyst();

    //item list: recipe
    ItemStack[] slot();

    //item list: result
    ItemStack[] result();

    //required time
    float time();

    //temperature param
    float minTemp();
    float maxTemp();

    //chance of generation vitro-arkha sordida
    int slagGenChance();

    //block for get result
    String mod();
}
