package minefantasy.mf2.container;

import minefantasy.mf2.block.tileentity.decor.TileEntityTrough;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerTrough extends Container {
    private TileEntityTrough tile;

    public ContainerTrough(TileEntityTrough tile) {
        this.tile = tile;
        this.addSlotToContainer(new Slot(tile, 0, 0, 0));
    }

    @Override
    public boolean canInteractWith(EntityPlayer p_75145_1_) {
        return this.tile.isUseableByPlayer(p_75145_1_);
    }
}