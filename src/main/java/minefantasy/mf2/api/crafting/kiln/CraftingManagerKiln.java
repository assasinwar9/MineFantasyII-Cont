package minefantasy.mf2.api.crafting.kiln;

import minefantasy.mf2.api.alchemy.IRefFurnaceRecipe;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class CraftingManagerKiln {
    private static final CraftingManagerKiln instance = new CraftingManagerKiln();
    private Item reference;
    public List recipes = new ArrayList();

    public static CraftingManagerKiln getInstance() {
        return instance;
    }

    public IKilnRecipe findMatchingRecipe (Item currentItem) {
        for (int rec = 0; rec < recipes.size(); rec++) { //checking on recipes
            Object currentRec = recipes.get(rec);
            if (currentRec instanceof IKilnRecipe) {
                reference = ((IKilnRecipe) currentRec).raw();

                if (compareInventory(currentItem, reference)) {
                    return (IKilnRecipe) currentRec;
                }
            }
        }
        return null;
    }

    private boolean compareInventory (Item verify, Item reference) {
            if(verify == null && reference != null)
                return false;
            if(verify != null && reference == null)
                return false;
            if (verify != reference)
                return false;
        return true;
    }

    public void addRecipe (Item raw, Item result, float minTemp, float time) {
        addRecipe(raw, result, 0, minTemp, time);
    }

    public void addRecipe (Item raw, Item result, int meta, float minTemp, float time) {

        IKilnRecipe recipe = new IKilnRecipe() {
            @Override
            public Item raw() {
                return raw;
            }

            @Override
            public Item result() {
                return result;
            }

            @Override
            public int meta() { return meta;}

            @Override
            public float minTemp() {
                return minTemp;
            }

            @Override
            public float time() {
                return time;
            }
        };


        this.recipes.add(recipe);

    }
}
