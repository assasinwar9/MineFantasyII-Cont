package minefantasy.mf2.client.render.block;//Made with Blockbench
//Paste this code into your mod.

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRefFurnaceVaporizer extends ModelBase {
	private final ModelRenderer bone;

	public ModelRefFurnaceVaporizer() {
		textureWidth = 64;
		textureHeight = 64;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 0, 0, -6.0F, -2.0F, -6.0F, 12, 2, 12, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 0, 14, -4.0F, -12.0F, -4.0F, 8, 10, 8, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 32, 14, -3.0F, -13.0F, -3.0F, 6, 1, 6, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 0, 32, -1.0F, -11.0F, 5.0F, 2, 12, 2, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 0, 32, -1.0F, -11.0F, -7.0F, 2, 12, 2, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 0, 32, -7.0F, -11.0F, -1.0F, 2, 12, 2, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 0, 46, 4.0F, -11.0F, -2.0F, 1, 4, 4, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 1, 33, -1.0F, -11.0F, -5.0F, 2, 2, 1, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 1, 33, -1.0F, -11.0F, 4.0F, 2, 2, 1, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 1, 32, -5.0F, -11.0F, -1.0F, 1, 2, 2, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 0, 38, 5.0F, -5.0F, -1.0F, 2, 6, 2, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 1, 32, 4.0F, -5.0F, -1.0F, 1, 2, 2, 0.0F));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bone.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}