package minefantasy.mf2.client.render.block.alchemy;//Made with Blockbench
//Paste this code into your mod.

import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRefFurnaceExtractor extends ModelBase {
	private final ModelRenderer bone;

	public ModelRefFurnaceExtractor() {
		textureWidth = 64;
		textureHeight = 64;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 0, 0, -6.0F, -6.0F, -6.0F, 12, 2, 12, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 0, 14, -4.0F, -16.0F, -4.0F, 8, 10, 8, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 0, 32, -5.0F, -17.0F, -5.0F, 10, 1, 10, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 35, 21, 4.0F, -16.0F, 1.0F, 3, 10, 1, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 35, 21, 4.0F, -16.0F, -2.0F, 3, 10, 1, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 35, 19, 1.0F, -16.0F, 4.0F, 1, 10, 3, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 35, 19, -2.0F, -16.0F, 4.0F, 1, 10, 3, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 35, 21, -7.0F, -16.0F, 1.0F, 3, 10, 1, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 35, 21, -7.0F, -16.0F, -2.0F, 3, 10, 1, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 35, 19, -2.0F, -16.0F, -7.0F, 1, 10, 3, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 35, 19, 1.0F, -16.0F, -7.0F, 1, 10, 3, 0.0F));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bone.render(f5);
	}

	public void setRotation (ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void renderModel(float f) {
		bone.render(f);
	}}