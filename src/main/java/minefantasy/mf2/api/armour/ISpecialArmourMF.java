package minefantasy.mf2.api.armour;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public interface ISpecialArmourMF {
    float getDTValue(EntityLivingBase user, ItemStack armour, DamageSource src);

    @SideOnly(Side.CLIENT)
    /**
     * DamageType: 0=cutting, 1=blunt, 2=piercing
     */ float getDTDisplay(ItemStack armour, int damageType);

    float getDRValue(EntityLivingBase user, ItemStack armour, DamageSource src);

    @SideOnly(Side.CLIENT)
    /**
     * DamageType: 0=cutting, 1=blunt, 2=piercing
     */ float getDRDisplay(ItemStack armour, int damageType);
}
