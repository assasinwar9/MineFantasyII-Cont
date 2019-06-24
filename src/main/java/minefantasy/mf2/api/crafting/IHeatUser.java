package minefantasy.mf2.api.crafting;

import net.minecraft.tileentity.TileEntity;

public interface IHeatUser {
    boolean canAccept(TileEntity tile);
}
