package minefantasy.mf2.client.gui;

import org.lwjgl.opengl.GL11;

import minefantasy.mf2.client.gui.tabs.InventoryTabMF2;
import minefantasy.mf2.client.gui.tabs.TabRegistry;
import minefantasy.mf2.player.ContainerMF2Player;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.util.ResourceLocation;

public class GuiMF2Player extends GuiContainer {

	public GuiMF2Player() {
		super(new ContainerMF2Player(Minecraft.getMinecraft().thePlayer));
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
        
        GuiInventory.func_147046_a(guiLeft + 51, guiTop + 75, 30, (float)(guiLeft + 51) - mouseX, (float)(guiTop + 75 - 50) - mouseY, this.mc.thePlayer);	
	}

}
