package minefantasy.mf2.block.wizardry;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemBlockTotem extends ItemBlock {
	public ItemBlockTotem(Block p_i45326_1_) {
		super(p_i45326_1_);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	/**
	 * Returns the metadata of the block which this Item (ItemBlock) can place
	 */
	public int getMetadata(int p_77647_1_) {
		return p_77647_1_;
	}
}