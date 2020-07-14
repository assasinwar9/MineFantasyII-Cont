package minefantasy.mf2.block.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import minefantasy.mf2.block.crafting.BlockSoakingTrough;
import minefantasy.mf2.item.ItemColormats;
import minefantasy.mf2.item.list.ComponentListMF;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;

import java.awt.*;
import java.util.Random;

public class TileEntitySoakingTrough extends TileEntity implements IInventory {
	public float progress = 0, progressMax = 100;
	private ItemStack[] inv = new ItemStack[1];
	private Random rand = new Random();
	public int fill;
	public int capacity = 8;
	public String liquid = "water", reqLiquid;

	public ItemStack soakingItems;
	public float soakTimeScale;
	public float recipeTimeScale = 1;
	public ItemStack result;

	public boolean soakingMode = false;

	public int ticksExisted;

	@Override
	public void updateEntity() {
		super.updateEntity();

		// soaking

		result = getSoakingResult();

		if (result != null && soakingMode && liquid.equals(reqLiquid)) {
			soakingItem();
		}
		// syncData();
	}
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound data = new NBTTagCompound();
		data.setInteger("fill", fill);
		data.setString("liquid", liquid);
		S35PacketUpdateTileEntity packet = new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 3, data);
		return packet;
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		super.onDataPacket(net, pkt);
		NBTTagCompound data = pkt.func_148857_g();
		int oldFill = this.fill;
		String oldLiq = this.liquid;
		if(data.hasKey("fill")) this.fill = data.getInteger("fill");
		if(data.hasKey("liquid")) this.liquid = data.getString("liquid");
		if(oldFill != fill || !oldLiq.equals(liquid)) {
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
	}

	private void soakingItem() {
		progressMax = 100;
		progressMax *= (getSoakTimeScale() * recipeTimeScale); // *5
		progress += 1;

		if (progress >= progressMax) {
			progress = 0;
			setCap(-1);
			this.setInventorySlotContents(0, result);
		}

	}

	private float getSoakTimeScale() {
		if (inv[0] != null && inv[0].stackSize > 2)
			return soakTimeScale = inv[0].stackSize / 2F;
		else
			return soakTimeScale = 1F;
	}

	private ItemStack getSoakingResult() {
		if (inv[0] != null) {
			//hide cleaning
			if (inv[0].getItem() == ComponentListMF.rawhideSmall) {
				reqLiquid = "water";
				return new ItemStack(ComponentListMF.rawhideSmall_washed, inv[0].stackSize);
			}
			if (inv[0].getItem() == ComponentListMF.rawhideMedium) {
				reqLiquid = "water";
				return new ItemStack(ComponentListMF.rawhideMedium_washed, inv[0].stackSize);
			}
			if (inv[0].getItem() == ComponentListMF.rawhideLarge) {
				reqLiquid = "water";
				return new ItemStack(ComponentListMF.rawhideLarge_washed, inv[0].stackSize);
			}
			//hide liming
			if (inv[0].getItem() == ComponentListMF.rawhideSmall_washed) {
				reqLiquid = "limestone";
				return new ItemStack(ComponentListMF.hideSmall_limed, inv[0].stackSize);
			}
			if (inv[0].getItem() == ComponentListMF.rawhideMedium_washed) {
				reqLiquid = "limestone";
				return new ItemStack(ComponentListMF.hideMedium_limed, inv[0].stackSize);
			}
			if (inv[0].getItem() == ComponentListMF.rawhideLarge_washed) {
				reqLiquid = "limestone";
				return new ItemStack(ComponentListMF.hideLarge_limed, inv[0].stackSize);
			}
			//tanning
			if (inv[0].getItem() == ComponentListMF.leather_clear) {
				reqLiquid = "tanning";
				recipeTimeScale = 2; // 10
				return new ItemStack(Items.leather, inv[0].stackSize);
			}

			else return null;
		}
		else return null;
	}

	public void consumeLiquid() {

	}

	public boolean interract(EntityPlayer user, ItemStack held) {
		if (held != null) {
			if (fill < capacity - 1) {
				if (held.getItem() == Items.water_bucket) {
					user.setCurrentItemOrArmor(0, new ItemStack(Items.bucket));
					setCap(1);
					liquid = "water";
					soakingMode = false;
					return true;
				}
				if (held.getItem() == Items.emerald) { //only for test !
					--held.stackSize;
					setCap(4);
					liquid = "water";
					soakingMode = false;
					return true;
				}
			}
			// of color materials
			if (held.getItem() instanceof ItemColormats && fill > 0 && liquid.equals("water")) {
				if (held.getItem() == ComponentListMF.colormat_black)
					liquid = "black";
				if (held.getItem() == ComponentListMF.colormat_red)
					liquid = "red";
				if (held.getItem() == ComponentListMF.colormat_green)
					liquid = "green";
				if (held.getItem() == ComponentListMF.colormat_brown)
					liquid = "brown";
				if (held.getItem() == ComponentListMF.colormat_blue)
					liquid = "blue";
				if (held.getItem() == ComponentListMF.colormat_purple)
					liquid = "purple";
				if (held.getItem() == ComponentListMF.colormat_cyan)
					liquid = "cyan";
				if (held.getItem() == ComponentListMF.colormat_light_gray)
					liquid = "light_gray";
				if (held.getItem() == ComponentListMF.colormat_gray)
					liquid = "gray";
				if (held.getItem() == ComponentListMF.colormat_pink)
					liquid = "pink";
				if (held.getItem() == ComponentListMF.colormat_lime)
					liquid = "lime";
				if (held.getItem() == ComponentListMF.colormat_yellow)
					liquid = "yellow";
				if (held.getItem() == ComponentListMF.colormat_light_blue)
					liquid = "light_blue";
				if (held.getItem() == ComponentListMF.colormat_magenta)
					liquid = "magenta";
				if (held.getItem() == ComponentListMF.colormat_orange)
					liquid = "orange";
				if (held.getItem() == ComponentListMF.colormat_white)
					liquid = "white";

				--held.stackSize;

				dropItems(user, new ItemStack(ComponentListMF.clay_pot));

				soakingMode = true;
				return true;
			}
			// set liming liquid
			if (held.getItem() == ComponentListMF.limestone_item_pot && fill > 0 && liquid.equals("water")) {
				liquid = "limestone";

				--held.stackSize;
				dropItems(user, new ItemStack(ComponentListMF.clay_pot));

				soakingMode = true;
				return true;
			}
			//set tanning fluid
			if (held.getItem() == ComponentListMF.bark && fill > 0 && liquid.equals("water")) {
				liquid = "tanning";

				--held.stackSize;
				soakingMode = true;
				return true;
			}

			// Take
			if (fill > 0 && held.getItem() == Items.bucket && !soakingMode) {
				--held.stackSize;
				dropItems(user, new ItemStack(Items.water_bucket));
				setCap(-1);
				return true;
			}

			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
		return false;
	}

	private void setCap(int count) {
		fill += count;
	}

	public void dropItems(EntityPlayer user, ItemStack items) {
		if (!user.worldObj.isRemote) {
			EntityItem drop = new EntityItem(worldObj, user.posX, user.posY, user.posZ, items);
			drop.delayBeforeCanPickup = 0;
			worldObj.spawnEntityInWorld(drop);
		}
	}

	private boolean isOutside() {
		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				if (!worldObj.canBlockSeeTheSky(xCoord + x, yCoord + 1, zCoord + y)) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public Block getBlockType() {
		if (worldObj == null)
			return Blocks.air;

		return super.getBlockType();
	}

	/*public void syncData() {
		if (worldObj.isRemote)
			return;

		NetworkUtils.sendToWatchers(new SoakingTroughPacket(this).generatePacket(),
									(WorldServer) worldObj,
									this.xCoord,
									this.zCoord);
	}*/

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("fill", fill);
		nbt.setString("liquid", liquid);
		nbt.setBoolean("soakingMode", soakingMode);

		NBTTagList savedItems = new NBTTagList();
		for (int i = 0; i < this.inv.length; ++i) {
			if (this.inv[i] != null) {
				NBTTagCompound savedSlot = new NBTTagCompound();
				savedSlot.setByte("Slot", (byte) i);
				this.inv[i].writeToNBT(savedSlot);
				savedItems.appendTag(savedSlot);
			}
		}
		nbt.setTag("Items", savedItems);

	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		fill = nbt.getInteger("fill");
		liquid = nbt.getString("liquid");
		soakingMode = nbt.getBoolean("soakingMode");

		NBTTagList savedItems = nbt.getTagList("Items", 10);
		this.inv = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < savedItems.tagCount(); ++i) {
			NBTTagCompound savedSlot = savedItems.getCompoundTagAt(i);
			byte slotNum = savedSlot.getByte("Slot");

			if (slotNum >= 0 && slotNum < this.inv.length) {
				this.inv[slotNum] = ItemStack.loadItemStackFromNBT(savedSlot);
			}
		}
	}

	@Override
	public int getSizeInventory() {
		return inv.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inv[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int num) {
		onInventoryChanged();
		if (this.inv[slot] != null) {
			ItemStack itemstack;

			if (this.inv[slot].stackSize <= num) {
				itemstack = this.inv[slot];
				this.inv[slot] = null;
				return itemstack;
			} else {
				itemstack = this.inv[slot].splitStack(num);

				if (this.inv[slot].stackSize == 0) {
					this.inv[slot] = null;
				}

				return itemstack;
			}
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		return inv[slot];
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack item) {
		onInventoryChanged();
		inv[slot] = item;
	}

	public void onInventoryChanged() {
	}

	@Override
	public String getInventoryName() {
		return "gui.soaking_trough.name";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 4;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer user) {
		return user.getDistance(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) < 8D;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack item) {
		return true;
	}
/*
	@SideOnly(Side.CLIENT)
    public IIcon getLiquidTex (String liquid) {
		BlockSoakingTrough block = ((BlockSoakingTrough)worldObj.getBlock(xCoord, yCoord, zCoord));
        if (liquid.equals("water")) {
            return block.liquidIconWater;
        }
        else return block.liquidIconColordef;
    }*/
	
	@SideOnly(Side.CLIENT)
    public Color getLiquidColor (String liquid) {
        float k = 0.00392F;
        
        float red = 0, green = 0, blue = 0;
        
        if (liquid.equals("water")) {
            red = 1.0F;
            green = 1.0F;
            blue = 1.0F;
        }
        if (liquid.equals("black")) {
            red = 0.05F;
            green = 0.05F;
            blue = 0.05F;
        }
        if (liquid.equals("red")) {
            red = k * 176;
            green = k * 46;
            blue = k * 38;
        }
        if (liquid.equals("green")) {
            red = k * 94;
            green = k * 124;
            blue = k * 22;
        }
        if (liquid.equals("brown")) {
            red = k * 131;
            green = k * 84;
            blue = k * 50;
        }
        if (liquid.equals("blue")) {
            red = k * 60;
            green = k * 68;
            blue = k * 170;
        }
        if (liquid.equals("purple")) {
            red = k * 137;
            green = k * 50;
            blue = k * 184;
        }
        if (liquid.equals("cyan")) {
            red = k * 22;
            green = k * 156;
            blue = k * 156;
        }
        if (liquid.equals("light_gray")) {
            red = k * 157;
            green = k * 157;
            blue = k * 151;
        }
        if (liquid.equals("gray")) {
            red = k * 71;
            green = k * 79;
            blue = k * 82;
        }
        if (liquid.equals("pink")) {
            red = k * 243;
            green = k * 139;
            blue = k * 170;
        }
        if (liquid.equals("lime")) {
            red = k * 128;
            green = k * 199;
            blue = k * 31;
        }
        if (liquid.equals("yellow")) {
            red = k * 254;
            green = k * 216;
            blue = k * 61;
        }
        if (liquid.equals("light_blue")) {
            red = k * 58;
            green = k * 179;
            blue = k * 218;
        }
        if (liquid.equals("magenta")) {
            red = k * 199;
            green = k * 78;
            blue = k * 189;
        }
        if (liquid.equals("orange")) {
            red = k * 249;
            green = k * 128;
            blue = k * 29;
        }
        if (liquid.equals("white")) {
            red = 1.0F;
            green = 1.0F;
            blue = 1.0F;
        }
        if (liquid.equals("limestone")) {
            red = k * 234;
            green = k * 225;
            blue = k * 200;
        }
        /*else {
            red = 1.0F;
            green = 1.0F;
            blue = 1.0F;
        }*/
        
        return new Color(red, green, blue);
    }

}
