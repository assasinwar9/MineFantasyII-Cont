package minefantasy.mf2.api.weapon;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public interface IWeaponSpeed {
    int modifyHitTime(EntityLivingBase user, ItemStack item);
}
