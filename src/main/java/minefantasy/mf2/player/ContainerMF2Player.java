package minefantasy.mf2.player;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import minefantasy.mf2.api.armour.IArtifact;
import minefantasy.mf2.api.armour.IRucksack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ContainerMF2Player extends Container {
	public final IEEPMF2 ieep;
	public final InventoryPlayer mainInventory;

	public ContainerMF2Player(EntityPlayer player) {
		this.ieep = IEEPMF2.get(player);
		this.mainInventory = player.inventory;

		addSlotToContainer(new Slot(ieep, 0, 80, 8) {
			@Override
			public int getSlotStackLimit() {
				return 1;
			}

			@Override
			public boolean isItemValid(ItemStack p_75214_1_) {
				if (p_75214_1_ == null) {
					return false;
				}
				return p_75214_1_.getItem() instanceof IArtifact;
			}

			@Override
			public IIcon getBackgroundIconIndex() {
				return IEEPMF2.emptyArtifactSlotIcon;
			}
		});
		addSlotToContainer(new Slot(ieep, 1, 80, 62) {
			@Override
			public int getSlotStackLimit() {
				return 1;
			}

			@Override
			public boolean isItemValid(ItemStack p_75214_1_) {
				if (p_75214_1_ == null) {
					return false;
				}
				return p_75214_1_.getItem() instanceof IRucksack;
			}

			@Override
			public IIcon getBackgroundIconIndex() {
				return IEEPMF2.emptyRucksackSlotIcon;
			}
		});

		for (int i = 0; i < 3; ++i) {
			final int i1 = i;
			for (int j = 0; j < 7; ++j) {
				final int j1 = j;
				this.addSlotToContainer(new Slot(ieep, 100 + i * 7 + j, 26 + j * 18, 84 + i * 18) {
					@Override
					public boolean isItemValid(ItemStack p_75214_1_) {
						if (p_75214_1_ == null) {
							return false;
						}
						ItemStack ruckSackStack = ieep.inventorySlotRucksack;
						if (ruckSackStack == null) {
							return false;
						} else if (!(ruckSackStack.getItem() instanceof IRucksack)) { // WHAT THE FUCK
							return false;
						} else return i1 * 7 + j1 < ((IRucksack) ruckSackStack.getItem()).getRucksackSize(ruckSackStack);
					}
				});
			}
		}

		for (int i = 0; i < 4; ++i) {
			final int k = i;
			this.addSlotToContainer(new Slot(mainInventory, mainInventory.getSizeInventory() - 1 - i, 8, 8 + i * 18) {
				/**
				 * Returns the maximum stack size for a given slot (usually the same as
				 * getInventoryStackLimit(), but 1 in the case of armor slots)
				 */
				public int getSlotStackLimit() {
					return 1;
				}

				/**
				 * Check if the stack is a valid item for this slot. Always true beside for the
				 * armor slots.
				 */
				public boolean isItemValid(ItemStack p_75214_1_) {
					if (p_75214_1_ == null)
						return false;
					return p_75214_1_.getItem().isValidArmor(p_75214_1_, k, player);
				}

				/**
				 * Returns the icon index on items.png that is used as background image of the
				 * slot.
				 */
				@SideOnly(Side.CLIENT)
				public IIcon getBackgroundIconIndex() {
					return ItemArmor.func_94602_b(k);
				}
			});
		}

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlotToContainer(new Slot(mainInventory, j + (i + 1) * 9, 8 + j * 18, 142 + i * 18));
			}
		}

		for (int i = 0; i < 9; ++i) {
			this.addSlotToContainer(new Slot(mainInventory, i, 8 + i * 18, 200));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return p_75145_1_ == this.ieep.theEntity;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int p_82846_2_) {
		return null;
	}

}
