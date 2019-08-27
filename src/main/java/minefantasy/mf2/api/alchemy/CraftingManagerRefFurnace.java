package minefantasy.mf2.api.alchemy;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class CraftingManagerRefFurnace {
    private static final CraftingManagerRefFurnace instance = new CraftingManagerRefFurnace();
    private ItemStack[] reference = new ItemStack[4];

    private boolean[] validate = new boolean[4];

    public List recipes = new ArrayList();

    public static CraftingManagerRefFurnace getInstance() {
        return instance;
    }

    public IRefFurnaceRecipe findMatchingRecipe (ItemStack[] currentInv) {
        for (int rec = 0; rec < recipes.size(); rec++) { //checking on recipes
            Object currentRec = recipes.get(rec);
            if (currentRec != null && currentRec instanceof IRefFurnaceRecipe) {
                for (int slot = 0; slot <4; slot++) {
                    reference[slot] = ((IRefFurnaceRecipe) currentRec).slot()[slot];
                }
                /*
                reference[0] = ((IRefFurnaceRecipe) currentRec).catalyst() == null ? null : new ItemStack(((IRefFurnaceRecipe) currentRec).catalyst(), ((IRefFurnaceRecipe) currentRec).catalyst_count());
                reference[1] = ((IRefFurnaceRecipe) currentRec).slot_1() == null ? null : new ItemStack(((IRefFurnaceRecipe) currentRec).slot_1(), ((IRefFurnaceRecipe) currentRec).slot_1_count());
                reference[2] = ((IRefFurnaceRecipe) currentRec).slot_2() == null ? null : new ItemStack(((IRefFurnaceRecipe) currentRec).slot_2(), ((IRefFurnaceRecipe) currentRec).slot_2_count());
                reference[3] = ((IRefFurnaceRecipe) currentRec).slot_3() == null ? null : new ItemStack(((IRefFurnaceRecipe) currentRec).slot_3(), ((IRefFurnaceRecipe) currentRec).slot_3_count());
                */
                if (compareInventory(currentInv, reference)) {
                    return (IRefFurnaceRecipe) currentRec;
                }
            }
        }
        return null;
    }

    private boolean compareInventory (ItemStack[] verify, ItemStack[] reference) {
        for (int q = 0; q < 4; q++) {
            if(verify[q] == null && reference[q] != null)
                return false;
            if(verify[q] != null && reference[q] == null)
                return false;
            if (verify[q] != null && reference[q] != null)
                if (verify[q].getItem() != reference[q].getItem() || verify[q].stackSize < reference[q].stackSize)
                    return false;
        }
        return true;
    }

    public IRefFurnaceRecipe addRecipe (ItemStack catalyst, ItemStack slot_1, ItemStack slot_2, ItemStack slot_3,
                                        ItemStack res_1, ItemStack res_2, ItemStack res_3,
                                        float time, float minTemp, float maxTemp, int slagGenChance) {

        IRefFurnaceRecipe recipe;
        ItemStack[] slot = new ItemStack[4];
        slot[0] = catalyst;
        slot[1] = slot_1;
        slot[2] = slot_2;
        slot[3] = slot_3;

        ItemStack[] result = new ItemStack[3];
        result[0] = res_1;
        result[1] = res_2;
        result[2] = res_3;

        recipe = new IRefFurnaceRecipe() {
            @Override
            public ItemStack catalyst() {
                return catalyst;
            }

            @Override
            public ItemStack[] slot() {
                return slot;
            }

            @Override
            public ItemStack[] result() {
                return result;
            }

            @Override
            public float time() {
                return time;
            }

            @Override
            public float minTemp() {
                return minTemp;
            }

            @Override
            public float maxTemp() {
                return maxTemp;
            }

            @Override
            public int slagGenChance() {
                return slagGenChance;
            }
        };

        this.recipes.add(recipe);
        return recipe;
    }
}
