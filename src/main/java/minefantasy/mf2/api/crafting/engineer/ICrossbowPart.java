package minefantasy.mf2.api.crafting.engineer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.IIcon;

import java.util.HashMap;

public interface ICrossbowPart {
    HashMap<String, ICrossbowPart> components = new HashMap<String, ICrossbowPart>();

    /**
     * The type of component only supported types (minecase, bombcase, filling)
     */
    String getComponentType();

    /**
     * The id, this depends on the component type
     */
    int getID();

    /**
     * The Icon for the finished Model
     */
    @SideOnly(Side.CLIENT)
    IIcon getIcon();

    /**
     * Gets the name for the part (mostly an adjective like "Heavy" or "Repeating")
     */
    String getUnlocalisedName();

    /**
     * Modifies a value by name (such as "power" or "spread"), "capacity" is treated
     * as an int
     */
    float getModifier(String type);

    /**
     * Only for stocks, whether it's defined as a hand crossbow
     */
    boolean makesSmallWeapon();
}
