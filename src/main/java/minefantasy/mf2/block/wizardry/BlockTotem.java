package minefantasy.mf2.block.wizardry;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import minefantasy.mf2.block.tileentity.TileEntityTotem;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockTotem extends BlockContainer {

	public BlockTotem() {
		super(Material.rock);

		GameRegistry.registerBlock(this, ItemBlockTotem.class, "mf_totem");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_) {
		for (int i = 0; i < TileEntityTotem.TotemLogic.metatable.length; i++) {
			if (TileEntityTotem.TotemLogic.metatable[i] != null) {
				p_149666_3_.add(new ItemStack(p_149666_1_, 1, i));
			}
		}
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityTotem();
	}

}
