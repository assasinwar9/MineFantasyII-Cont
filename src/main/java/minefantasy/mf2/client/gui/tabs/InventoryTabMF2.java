package minefantasy.mf2.client.gui.tabs;

import minefantasy.mf2.network.packet.MFPlayerGUIPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InventoryTabMF2 extends AbstractTab {

	public InventoryTabMF2() {
		super(0, 0, 0, new ItemStack(Items.golden_chestplate));
	}

	@Override
	public void onTabClicked() {
		Minecraft mc = Minecraft.getMinecraft();
		mc.getNetHandler().addToSendQueue(new MFPlayerGUIPacket().generatePacket());
	}

	@Override
	public boolean shouldAddToList() {
		return true;
	}

}
