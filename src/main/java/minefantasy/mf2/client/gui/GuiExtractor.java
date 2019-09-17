package minefantasy.mf2.client.gui;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import minefantasy.mf2.api.helpers.TextureHelperMF;
import minefantasy.mf2.block.tileentity.TileEntityRefFurnace;
import minefantasy.mf2.block.tileentity.alchemy.TileEntityExtractor;
import minefantasy.mf2.container.ContainerExtractor;
import minefantasy.mf2.container.ContainerRefFurnace;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiExtractor extends GuiContainer {
    private TileEntityExtractor tile;

    public GuiExtractor(InventoryPlayer user, TileEntityExtractor tile) {
        super(new ContainerExtractor(user, tile));
        this.ySize = 200;
        this.xSize = 185;
        this.tile = tile;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(TextureHelperMF.getResource(getGuiTex()));
        int xPoint = (this.width - this.xSize) / 2;
        int yPoint = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(xPoint, yPoint, 0, 0, this.xSize, this.ySize);

    }


    private String getGuiTex() {
        return "textures/gui/gui_reffurnace_extractor.png";
    }

    @Override
    public void drawScreen(int x, int y, float f) {
        super.drawScreen(x, y, f);
    }
}

