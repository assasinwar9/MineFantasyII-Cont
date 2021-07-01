package minefantasy.mf2.client.render.block;

import minefantasy.mf2.api.helpers.TextureHelperMF;
import minefantasy.mf2.api.material.CustomMaterial;
import minefantasy.mf2.block.tileentity.decor.TileEntityTrough;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

import java.util.Random;

public class TileEntityTroughRenderer extends TileEntitySpecialRenderer {
    private ModelTrough model;
    private float red, green, blue;


    public TileEntityTroughRenderer() {
        model = new ModelTrough();
    }

    public void renderAModelAt(TileEntityTrough tile, double d, double d1, double d2, float f) {
        int i = 0;
        if (tile.getWorldObj() != null) {
            i = tile.getBlockMetadata();
        }
        this.renderModelAt(tile, i, d, d1, d2, f);
    }

    public void renderModelAt(TileEntityTrough tile, int meta, double d, double d1, double d2, float f) {
        int i = meta;

        int j = 90 * i;

        if (i == 1) {
            j = 0;
        }

        if (i == 2) {
            j = 270;
        }

        if (i == 3) {
            j = 180;
        }

        if (i == 0) {
            j = 90;
        }
        if (i == 0) {
            j = 90;
        }

        GL11.glPushMatrix(); // start
        float scale = 1.0F;
        float yOffset = 1 / 16F;
        GL11.glTranslatef((float) d + 0.5F, (float) d1 + yOffset, (float) d2 + 0.5F); // size
        GL11.glRotatef(j, 0.0F, 1.0F, 0.0F); // rotate based on metadata
        GL11.glScalef(scale, -scale, -scale); // if you read this comment out this line and you can see what happens
        GL11.glPushMatrix();
        float level = 0F;

        CustomMaterial material = tile.getMaterial();
        GL11.glColor3f(material.colourRGB[0] / 255F, material.colourRGB[1] / 255F, material.colourRGB[2] / 255F);

        bindTextureByName("textures/models/tileentity/" + tile.getTexName() + "_base.png"); // texture
        model.renderModel(0.0625F);

        GL11.glColor3f(1F, 1F, 1F);

        float height = (float) tile.fill / (float) tile.getCapacity();
        if (tile.fill > 0) {
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

            //String liquidType = tile.liquid;
            String texture = getLiquidTex();
            bindTextureByName(texture); // texture

            //getLiquidColor(tile.liquid);
            GL11.glColor4f(1F, 1F, 1F, 0.9F);
            GL11.glTranslatef(0F, -height * 0.35F, 0F);
            model.renderWater(0.0625F);
        }
        GL11.glPopMatrix();
        GL11.glColor3f(255, 255, 255);
        GL11.glPopMatrix(); // end

    }

    private String getLiquidTex () {
            return "textures/blocks/zsAddon/liquid_water.png";

    }

    //  1 / 255 = 0.00392
   /* private void getLiquidColor (String liquid) {
        float k = 0.00392F;
        if (liquid == "water") {
            red = 1.0F;
            green = 1.0F;
            blue = 1.0F;
        }
        if (liquid == "black") {
            red = 0.05F;
            green = 0.05F;
            blue = 0.05F;
        }
        if (liquid == "red") {
            red = k * 176;
            green = k * 46;
            blue = k * 38;
        }
        if (liquid == "green") {
            red = k * 94;
            green = k * 124;
            blue = k * 22;
        }
        if (liquid == "brown") {
            red = k * 131;
            green = k * 84;
            blue = k * 50;
        }
        if (liquid == "blue") {
            red = k * 60;
            green = k * 68;
            blue = k * 170;
        }
        if (liquid == "purple") {
            red = k * 137;
            green = k * 50;
            blue = k * 184;
        }
        if (liquid == "cyan") {
            red = k * 22;
            green = k * 156;
            blue = k * 156;
        }
        if (liquid == "light_gray") {
            red = k * 157;
            green = k * 157;
            blue = k * 151;
        }
        if (liquid == "gray") {
            red = k * 71;
            green = k * 79;
            blue = k * 82;
        }
        if (liquid == "pink") {
            red = k * 243;
            green = k * 139;
            blue = k * 170;
        }
        if (liquid == "lime") {
            red = k * 128;
            green = k * 199;
            blue = k * 31;
        }
        if (liquid == "yellow") {
            red = k * 254;
            green = k * 216;
            blue = k * 61;
        }
        if (liquid == "light_blue") {
            red = k * 58;
            green = k * 179;
            blue = k * 218;
        }
        if (liquid == "magenta") {
            red = k * 199;
            green = k * 78;
            blue = k * 189;
        }
        if (liquid == "orange") {
            red = k * 249;
            green = k * 128;
            blue = k * 29;
        }
        if (liquid == "white") {
            red = 1.0F;
            green = 1.0F;
            blue = 1.0F;
        }
        if (liquid == "limestone") {
            red = k * 234;
            green = k * 225;
            blue = k * 200;
        }
        else {
            red = 1.0F;
            green = 1.0F;
            blue = 1.0F;
        }
    }*/

    public void renderInvModel(String tex, CustomMaterial material, double d, double d1, double d2) {
        int j = 90;

        GL11.glPushMatrix(); // start
        float scale = 1.0F;
        float yOffset = 1 / 16F;
        GL11.glTranslatef((float) d + 0.5F, (float) d1 + yOffset, (float) d2 + 0.5F); // size
        GL11.glRotatef(j, 0.0F, 1.0F, 0.0F); // rotate based on metadata
        GL11.glScalef(scale, -scale, -scale); // if you read this comment out this line and you can see what happens
        GL11.glPushMatrix();

        GL11.glColor3f(material.colourRGB[0] / 255F, material.colourRGB[1] / 255F, material.colourRGB[2] / 255F);

        bindTextureByName("textures/models/tileentity/" + tex + "_base.png"); // texture
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
        renderAModelAt((TileEntityTrough) tileentity, d, d1, d2, f); // where to render
    }
}
