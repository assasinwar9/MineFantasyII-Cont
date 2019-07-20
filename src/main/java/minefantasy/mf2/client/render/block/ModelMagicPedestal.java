package minefantasy.mf2.client.render.block;//Made with Blockbench
//Paste this code into your mod.

import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMagicPedestal extends ModelBase {
	ModelRenderer bone;

	public ModelMagicPedestal() {
		textureWidth = 64;
		textureHeight = 64;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 0, 0, -8.0F, -6.0F, -8.0F, 16, 2, 16, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 2, 2, -7.0F, -8.0F, -7.0F, 14, 2, 14, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 4, 6, -6.0F, -20.0F, -6.0F, 12, 12, 12, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 0, 42, 6.0F, -18.0F, -4.0F, 1, 10, 8, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 18, 31, -4.0F, -18.0F, -7.0F, 8, 10, 1, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 18, 31, -4.0F, -18.0F, 6.0F, 8, 10, 1, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 0, 42, -7.0F, -18.0F, -4.0F, 1, 10, 8, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 0, 34, -8.0F, -21.0F, 4.0F, 4, 1, 4, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 0, 34, 4.0F, -21.0F, 4.0F, 4, 1, 4, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 0, 34, 4.0F, -21.0F, -8.0F, 4, 1, 4, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 0, 34, -8.0F, -21.0F, -8.0F, 4, 1, 4, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 16, 5, -7.0F, -20.0F, -7.0F, 1, 12, 1, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 16, 5, 6.0F, -20.0F, 6.0F, 1, 12, 1, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 16, 5, 6.0F, -20.0F, -7.0F, 1, 12, 1, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 16, 5, -7.0F, -20.0F, 6.0F, 1, 12, 1, 0.0F));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		renderModel(f5);
	}

	public void setRotation(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	public void renderModel(float f) {
		bone.render(f);
	}
}