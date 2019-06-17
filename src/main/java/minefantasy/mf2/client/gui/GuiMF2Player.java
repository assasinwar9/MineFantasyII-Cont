package minefantasy.mf2.client.gui;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.RenderingRegistry;
import minefantasy.mf2.api.armour.IRucksack;
import minefantasy.mf2.client.gui.tabs.InventoryTabMF2;
import minefantasy.mf2.client.gui.tabs.TabRegistry;
import minefantasy.mf2.player.ContainerMF2Player;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiMF2Player extends GuiContainer {

	private final ContainerMF2Player contMF;

	public GuiMF2Player() {
		super(new ContainerMF2Player(Minecraft.getMinecraft().thePlayer));
		this.contMF = (ContainerMF2Player) inventorySlots;
		ySize = 224;
	}

	@Override
	public void initGui() {
		super.initGui();

		TabRegistry.updateTabValues(guiLeft, guiTop, InventoryTabMF2.class);
		TabRegistry.addTabsToList(buttonList);
	}

	private int mouseX, mouseY;

	@Override
	public void drawScreen(int mouseX, int mouseY, float p_73863_3_) {
		super.drawScreen(mouseX, mouseY, p_73863_3_);

		this.mouseX = mouseX;
		this.mouseY = mouseY;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(new ResourceLocation("minefantasy2:textures/gui/mf2player.png"));
		int xPoint = (this.width - this.xSize) / 2;
		int yPoint = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(xPoint, yPoint, 0, 0, this.xSize, this.ySize);

		GuiInventory.func_147046_a(	guiLeft + 51,
									guiTop + 75,
									30,
									(float) (guiLeft + 51) - mouseX,
									(float) (guiTop + 75 - 50) - mouseY,
									this.mc.thePlayer);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
		super.drawGuiContainerForegroundLayer(p_146979_1_, p_146979_2_);

		for (int i = 0; i < 3; ++i) {
			final int i1 = i;
			for (int j = 0; j < 7; ++j) {
				final int j1 = j;
				ItemStack ruckSackStack = contMF.ieep.inventorySlotRucksack;
				if (ruckSackStack == null
						|| !(ruckSackStack.getItem() instanceof IRucksack)
						|| i1 * 7 + j1 >= ((IRucksack) ruckSackStack.getItem()).getRucksackSize(ruckSackStack)) {
					
					GL11.glPushAttrib(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_ENABLE_BIT | GL11.GL_CURRENT_BIT);
					GL11.glDisable(GL11.GL_TEXTURE_2D);
					GL11.glEnable(GL11.GL_BLEND);
					GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
					GL11.glColor4f(0.0f, 0.0f, 0.0f, 0.5f);
					drawTexturedModalRect(26 + j * 18, 84 + i * 18, 0, 0, 16, 16);
					GL11.glPopAttrib();
				}
			}
		}
	}

}
