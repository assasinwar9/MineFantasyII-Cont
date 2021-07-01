package minefantasy.mf2.player;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.api.armour.IRucksack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class IEEPMF2 implements IExtendedEntityProperties, IInventory {
	public static final String PROP_NAME = MineFantasyII.MODID + "_PDATA";
	
	@SideOnly(Side.CLIENT)
	public static IIcon emptyArtifactSlotIcon;
	
	@SideOnly(Side.CLIENT)
	public static IIcon emptyRucksackSlotIcon;
	
	public ItemStack inventorySlotArtifact;
	public ItemStack inventorySlotRucksack;
	public ItemStack inventorySlotsRucksackContent[] = new ItemStack[21];
	
	public Entity theEntity;
	public World world;

	@Override
	public void saveNBTData(NBTTagCompound compound) {
	    NBTTagCompound propertyData = new NBTTagCompound();
	    
	    if(inventorySlotArtifact != null) {
	    	propertyData.setTag("artifact", inventorySlotArtifact.writeToNBT(new NBTTagCompound()));
	    }
	    
	    if(inventorySlotRucksack != null) {
	    	propertyData.setTag("rucksack", inventorySlotRucksack.writeToNBT(new NBTTagCompound()));
	    }
	    
	    for(int i = 0; i < inventorySlotsRucksackContent.length; i++) {
	    	if(inventorySlotsRucksackContent[i] != null) {
		    	propertyData.setTag("rsack_s" + i, inventorySlotsRucksackContent[i].writeToNBT(new NBTTagCompound()));
	    	}
	    }
	
	    compound.setTag(PROP_NAME, propertyData);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		if(compound.hasKey(PROP_NAME, Constants.NBT.TAG_COMPOUND)) {
			NBTTagCompound propertyData = compound.getCompoundTag(PROP_NAME);
		    
			if(propertyData.hasKey("artifact")) {
				inventorySlotArtifact = ItemStack.loadItemStackFromNBT(propertyData.getCompoundTag("artifact"));
			} else {
				inventorySlotArtifact = null;
			}
		    
			if(propertyData.hasKey("rucksack")) {
				inventorySlotRucksack = ItemStack.loadItemStackFromNBT(propertyData.getCompoundTag("rucksack"));
			} else {
				inventorySlotRucksack = null;
			}
			

		    for(int i = 0; i < inventorySlotsRucksackContent.length; i++) {
				if(propertyData.hasKey("rsack_s" + i)) {
					inventorySlotsRucksackContent[i] = ItemStack.loadItemStackFromNBT(propertyData.getCompoundTag("rsack_s" + i));
				} else {
					inventorySlotsRucksackContent[i] = null;
				}
		    }
		}
	}

	@Override
	public void init(Entity entity, World world) {
		theEntity = entity;
		this.world = world;
	}
	
	public static IEEPMF2 get(Entity p) {
		return (IEEPMF2) p.getExtendedProperties(PROP_NAME);
	}

	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		switch(slot) {
		case 0: return inventorySlotArtifact;
		case 1: return inventorySlotRucksack;
		}
		
		if(slot >= 100) {
			return inventorySlotsRucksackContent[slot - 100];
		}
		
		return null;
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		if(getStackInSlot(slot) != null) {
            ItemStack itemstack;

            if (this.getStackInSlot(slot).stackSize <= amount)
            {
                itemstack = this.getStackInSlot(slot);
                this.setInventorySlotContents(slot, null);
                this.markDirty();
                return itemstack;
            }
            else
            {
                itemstack = this.getStackInSlot(slot).splitStack(amount);

                if (this.getStackInSlot(slot).stackSize == 0)
                {
                    this.setInventorySlotContents(slot, null);
                }

                this.markDirty();
                return itemstack;
            }
		}
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack stack = getStackInSlot(slot);
		setInventorySlotContents(slot, null);
		return stack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		switch(slot) {
		case 0: inventorySlotArtifact = stack; break;
		case 1: inventorySlotRucksack = stack; break;
		}

		if(slot >= 100) {
			inventorySlotsRucksackContent[slot - 100] = stack;
		}
	}

	@Override
	public String getInventoryName() {
		return "MineFantasy 2 Player";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void markDirty() {
		int rsize = 0;
		if(inventorySlotRucksack != null && inventorySlotRucksack.getItem() instanceof IRucksack) {
			rsize = ((IRucksack)inventorySlotRucksack.getItem()).getRucksackSize(inventorySlotRucksack);
		}
		for(int i = rsize; i < inventorySlotsRucksackContent.length; i++) {
			if(inventorySlotsRucksackContent[i] != null) {
				if(!world.isRemote) theEntity.entityDropItem(inventorySlotsRucksackContent[i].copy(), 0.1f);
				inventorySlotsRucksackContent[i] = null;
			}
		}
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return player == theEntity;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
		return true;
	}
	

	public static class Handler {
		@SubscribeEvent
		public void entityConstruct(EntityEvent.EntityConstructing e) {
			if (e.entity instanceof EntityPlayer) {
				if (e.entity.getExtendedProperties(PROP_NAME) == null) {
					e.entity.registerExtendedProperties(PROP_NAME, new IEEPMF2());
				}
			}
		}
		
		@SubscribeEvent
		public void onLivingDeath(LivingDeathEvent e) {
			if(e.entityLiving instanceof EntityPlayer) {
				IExtendedEntityProperties ieep = e.entity.getExtendedProperties(PROP_NAME);
				if (ieep != null) {
					IEEPMF2 ieepmf = (IEEPMF2) ieep;
					if(ieepmf.inventorySlotArtifact != null) {
						if(!ieepmf.world.isRemote) e.entityLiving.entityDropItem(ieepmf.inventorySlotArtifact.copy(), 0.1f);
						ieepmf.inventorySlotArtifact = null;
					}
					if(ieepmf.inventorySlotRucksack != null) {
						if(!ieepmf.world.isRemote) e.entityLiving.entityDropItem(ieepmf.inventorySlotRucksack.copy(), 0.1f);
						ieepmf.inventorySlotRucksack = null;
					}
					for(int i = 0; i < ieepmf.inventorySlotsRucksackContent.length; i++) {
						if(ieepmf.inventorySlotsRucksackContent[i] != null) {
							if(!ieepmf.world.isRemote) e.entityLiving.entityDropItem(ieepmf.inventorySlotsRucksackContent[i].copy(), 0.1f);
							ieepmf.inventorySlotsRucksackContent[i] = null;
						}
					}
				}
			}
		}
		
		@SideOnly(Side.CLIENT)
		@SubscribeEvent
		public void registerIcons(TextureStitchEvent.Pre event) {
			if(event.map.getTextureType() == 1) {
				emptyArtifactSlotIcon = event.map.registerIcon("minefantasy2:empty_slot_amulet");
				emptyRucksackSlotIcon = event.map.registerIcon("minefantasy2:empty_slot_rucksack");
			}
		}
	}
}
