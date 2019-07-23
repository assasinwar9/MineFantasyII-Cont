package minefantasy.mf2.client.render.block;

import minefantasy.mf2.api.helpers.TextureHelperMF;
import minefantasy.mf2.block.tileentity.TileEntityMagicChalice;
import minefantasy.mf2.block.tileentity.TileEntityMagicPedestal;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

import java.util.Random;

public class TileEntityMagicChaliceRenderer extends TileEntitySpecialRenderer {
    private ModelChalice model;
    private Random random = new Random();

    public TileEntityMagicChaliceRenderer() {
        model = new ModelChalice();
    }

    public void renderAModelAt(TileEntityMagicChalice tile, double d, double d1, double d2, float f) {
        int i = 0;
        if (tile.getWorldObj() != null) {
            i = tile.getBlockMetadata();
        }
        this.renderModelAt(tile, i, d, d1, d2, f);
    }

    public void renderModelAt(TileEntityMagicChalice tile, int meta, double d, double d1, double d2, float f) {
        int i = meta;

        int j = 90 * i;

        if (i == 0) {
            j = 0;
        }

        if (i == 1) {
            j = 270;
        }

        if (i == 2) {
            j = 180;
        }

        if (i == 3) {
            j = 90;
        }
        if (i == 4) {
            j = 90;
        }

        String tex = "chalice_lvl" + tile.getLevel() + "_" + tile.getModification();
        bindTextureByName("textures/models/zsModels/" + tex + ".png"); // texture

        GL11.glPushMatrix(); // start
        float yOffset = 1 / 2F;
        GL11.glTranslatef((float) d + 0.5F, (float) d1 + 0.3F, (float) d2 + 0.5F); // size
        GL11.glRotatef(j + 180F, 0.0F, 1.0F, 0.0F); // rotate based on metadata
        GL11.glScalef(1F, -1F, -1F); // if you read this comment out this line and you can see what happens
        GL11.glPushMatrix();
        model.renderModel(0.0625F);


        if (tile.isCraftingPhase()) {
            //GL11.glEnable(GL11.GL_BLEND);
            //GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

            String tex2 = tex + "_active";
            bindTextureByName("textures/models/zsModels/" + tex2 + ".png"); // texture

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glTranslatef(0F, 0F, 0F);
            model.renderFire(0.0625F);
        }

        GL11.glPopMatrix();
        GL11.glColor3f(255, 255, 255);
        GL11.glPopMatrix(); // end

    }

    public void renderInvModel(String tex, double d, double d1, double d2) {
        int j = 90;

        GL11.glPushMatrix(); // start
        float scale = 1.0F;
        float yOffset = 1 / 2F;
        GL11.glTranslatef((float) d + 0.5F, (float) d1 + 0.3F, (float) d2 + 0.5F); // size
        GL11.glRotatef(j, 0.0F, 1.0F, 0.0F); // rotate based on metadata
        GL11.glScalef(scale, -scale, -scale); // if you read this comment out this line and you can see what happens
        GL11.glPushMatrix();

        GL11.glColor3f(255, 255, 255);

        bindTextureByName("textures/models/zsModels/" + tex + ".png"); // texture
        model.renderModel(0.0625F);

        GL11.glColor3f(1F, 1F, 1F);

        GL11.glPopMatrix();
        GL11.glColor3f(255, 255, 255);
        GL11.glPopMatrix(); // end

    }

    private void bindTextureByName(String image) {
        Minecraft.getMinecraft().renderEngine.bindTexture(TextureHelperMF.getResource(image));
    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f) {
        renderAModelAt( (TileEntityMagicChalice) tileentity, d, d1, d2, f); // where to render
    }

    private boolean shouldRender(TileEntity tile, int p) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer sp = mc.thePlayer;
        if (p == 1)// GRID
        {
            return false;
        }
        return true;
    }
}
