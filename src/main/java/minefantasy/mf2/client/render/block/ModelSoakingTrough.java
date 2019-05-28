package minefantasy.mf2.client.render.block;

import javafx.geometry.Side;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;


public class ModelSoakingTrough extends ModelBase
{
    //fields
    ModelRenderer Side1;
    ModelRenderer Side2;
    ModelRenderer Side3;
    ModelRenderer Side4;
    ModelRenderer Bottom;
    ModelRenderer Liquid;

    public ModelSoakingTrough()
    {
        textureWidth = 64;
        textureHeight = 32;

        Side1 = new ModelRenderer(this, 17, 15);
        Side1.addBox(0F, 0F, 0F, 16, 15, 1);
        Side1.setRotationPoint(-8F, -14F, -8F);
        Side1.setTextureSize(64, 32);
        Side1.mirror = true;
        setRotation(Side1, 0F, 0F, 0F);
        Side2 = new ModelRenderer(this, 0, 15);
        Side2.addBox(0F, 0F, 0F, 16, 15, 1);
        Side2.setRotationPoint(-8F, -14F, 7F);
        Side2.setTextureSize(64, 32);
        Side2.mirror = true;
        setRotation(Side2, 0F, 0F, 0F);
        Side4 = new ModelRenderer(this, 3, 2);
        Side4.addBox(0F, 0F, 0F, 1, 15, 14);
        Side4.setRotationPoint(7F, -14F, -7F);
        Side4.setTextureSize(64, 32);
        Side4.mirror = true;
        setRotation(Side4, 0F, 0F, 0F);
        Bottom = new ModelRenderer(this, 1, 1);
        Bottom.addBox(0F, -5F, 0F, 14, 2, 14);
        Bottom.setRotationPoint(-7F, 4F, -7F);
        Bottom.setTextureSize(64, 32);
        Bottom.mirror = true;
        setRotation(Bottom, 0F, 0F, 0F);
        Liquid = new ModelRenderer(this, 36, 18);
        Liquid.addBox(0F, 0F, 0F, 14, 1, 14);
        Liquid.setRotationPoint(-7F, -2F, -7F);
        Liquid.setTextureSize(64, 32);
        Liquid.mirror = true;
        setRotation(Liquid, 0F, 0F, 0F);
        Side3 = new ModelRenderer(this, 20, 2);
        Side3.addBox(0F, 0F, 0F, 1, 15, 14);
        Side3.setRotationPoint(-8F, -14F, -7F);
        Side3.setTextureSize(64, 32);
        Side3.mirror = true;
        setRotation(Side3, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Side1.render(f5);
        Side2.render(f5);
        Side3.render(f5);
        Side4.render(f5);
        Bottom.render(f5);
        Liquid.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

    public void renderModel(float f) {
        Side1.render(f);
        Side2.render(f);
        Side3.render(f);
        Side4.render(f);
        Bottom.render(f);
    }

    public void renderLiquid(float f) {
        Liquid.render(f);
    }
}