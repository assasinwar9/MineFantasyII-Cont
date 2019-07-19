package minefantasy.mf2.client.render.block;

import minefantasy.mf2.api.helpers.TextureHelperMF;
import minefantasy.mf2.block.tileentity.TileEntitySoakingTrough;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class TileEntitySoakingTroughRenderer extends TileEntitySpecialRenderer {
    private ModelSoakingTrough model;
    private float red, green, blue;
    private int height;

    public TileEntitySoakingTroughRenderer() {
        model = new ModelSoakingTrough();
    }	

    public void renderAModelAt(TileEntitySoakingTrough tile, double d, double d1, double d2, float f) {
        int i = 0;
        if (tile.getWorldObj() != null) {
            i = tile.getBlockMetadata();
        }
        this.renderModelAt(tile, i, d, d1, d2, f);
    }

    public void renderModelAt(TileEntitySoakingTrough tile, int meta, double d, double d1, double d2, float f) {
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
        GL11.glTranslatef((float) d + 0.5F, (float) d1 + 1.25F, (float) d2 + 0.5F); // size
        GL11.glRotatef(j + 180F, 0.0F, 1.0F, 0.0F); // rotate based on metadata
        GL11.glScalef(1F, -1F, -1F); // if you read this comment out this line and you can see what happens
        GL11.glPushMatrix();
        model.renderModel(0.0625F);

        bindTextureByName("textures/models/zsModels/soaking_trough.png"); // texture
        model.renderModel(0.0625F);

        height = tile.fill;
        if (height > 0) {

            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

            String texture = getLiquidTex(tile.liquid);
            bindTextureByName(texture); // texture

            getLiquidColor(tile.liquid);
            GL11.glColor4f(red, green, blue, 0.9F);
            GL11.glTranslatef(0F, 0F, 0F);
            if (height == 1) model.renderLqLvl_1(0.0625F);
            if (height == 2) model.renderLqLvl_2(0.0625F);
            if (height == 3) model.renderLqLvl_3(0.0625F);
            if (height == 4) model.renderLqLvl_4(0.0625F);
            if (height == 5) model.renderLqLvl_5(0.0625F);
            if (height == 6) model.renderLqLvl_6(0.0625F);
            if (height == 7) model.renderLqLvl_7(0.0625F);
            if (height == 8) model.renderLqLvl_8(0.0625F);
            if (height == 9) model.renderLqLvl_9(0.0625F);
            if (height == 10) model.renderLqLvl_10(0.0625F);
            if (height == 11) model.renderLqLvl_11(0.0625F);
            if (height == 12) model.renderLqLvl_12(0.0625F);
        }
        GL11.glPopMatrix();
        GL11.glColor3f(255, 255, 255);
        GL11.glPopMatrix(); // end

    }

    private String getLiquidTex (String liquid) {
        if (liquid.equals("water"))
            return "textures/models/zsModels/soaking_trough_water.png";
        if (liquid.equals("limestone"))
            return "textures/models/zsModels/soaking_trough_limestone.png";
        if (liquid.equals("tanning"))
            return "textures/models/zsModels/soaking_trough_tanning.png";
        else return "textures/models/zsModels/soaking_trough_colordef.png";
    }

    //  1 / 255 = 0.00392
    private void getLiquidColor (String liquid) {
        float k = 0.00392F;
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
        if (liquid.equals("tanning")) {
            red = k * 255;
            green = k * 225;
            blue = k * 255;
        }
        /*
        else {
            red = 1.0F;
            green = 1.0F;
            blue = 1.0F;
        }*/
    }

    public void renderInvModel(String tex, double d, double d1, double d2) {
        int j = 90;

        GL11.glPushMatrix(); // start
        float scale = 1.0F;
        float yOffset = 1 / 16F;
        GL11.glTranslatef((float) d + 0.5F, (float) d1 + 1.25F, (float) d2 + 0.5F); // size
        GL11.glRotatef(j, 0.0F, 1.0F, 0.0F); // rotate based on metadata
        GL11.glScalef(scale, -scale, -scale); // if you read this comment out this line and you can see what happens
        GL11.glPushMatrix();

        GL11.glColor3f(1F, 1F, 1F);

        bindTextureByName("textures/models/zsModels/soaking_trough.png"); // texture
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
        renderAModelAt((TileEntitySoakingTrough) tileentity, d, d1, d2, f); // where to render
    }
}
