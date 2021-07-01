package minefantasy.mf2.client.render.block;//Made with Blockbench

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
//by flyse emberlight
public class ModelSoakingTrough extends ModelBase {
	private ModelRenderer bone;
	private ModelRenderer lq_1, lq_2, lq_3, lq_4, lq_5, lq_6, lq_7, lq_8, lq_9, lq_10, lq_11, lq_12;


	public ModelSoakingTrough() {
		textureWidth = 128;
		textureHeight = 128;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 37, 0, 7.0F, -19.0F, -7.0F, 1, 15, 14, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 0, 13, -8.0F, -19.0F, -8.0F, 16, 15, 1, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 3, 0, -8.0F, -19.0F, -7.0F, 1, 15, 14, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 34, 13, -8.0F, -19.0F, 7.0F, 16, 15, 1, 0.0F));
		bone.cubeList.add(new ModelBox(bone, 7, 14, -7.0F, -5.0F, -7.0F, 14, 1, 14, 0.0F));

		//liquid levels
		lq_1 = new ModelRenderer(this);
		lq_1.setRotationPoint(-8.0F, 16.0F, 8.0F);
		lq_1.cubeList.add(new ModelBox(lq_1, 4, 29, 1.0F, 1.0F, -15.0F, 14, 1, 14, 0.0F));
		lq_2 = new ModelRenderer(this);
		lq_2.setRotationPoint(-8.0F, 16.0F, 8.0F);
		lq_2.cubeList.add(new ModelBox(lq_2, 4, 29, 1.0F, 0.0F, -15.0F, 14, 1, 14, 0.0F));
		lq_3 = new ModelRenderer(this);
		lq_3.setRotationPoint(-8.0F, 16.0F, 8.0F);
		lq_3.cubeList.add(new ModelBox(lq_3, 4, 29, 1.0F, -1.0F, -15.0F, 14, 1, 14, 0.0F));
		lq_4 = new ModelRenderer(this);
		lq_4.setRotationPoint(-8.0F, 16.0F, 8.0F);
		lq_4.cubeList.add(new ModelBox(lq_4, 4, 29, 1.0F, -2.0F, -15.0F, 14, 1, 14, 0.0F));
		lq_5 = new ModelRenderer(this);
		lq_5.setRotationPoint(-8.0F, 16.0F, 8.0F);
		lq_5.cubeList.add(new ModelBox(lq_5, 4, 29, 1.0F, -3.0F, -15.0F, 14, 1, 14, 0.0F));
		lq_6 = new ModelRenderer(this);
		lq_6.setRotationPoint(-8.0F, 16.0F, 8.0F);
		lq_6.cubeList.add(new ModelBox(lq_6, 4, 29, 1.0F, -4.0F, -15.0F, 14, 1, 14, 0.0F));
		lq_7 = new ModelRenderer(this);
		lq_7.setRotationPoint(-8.0F, 16.0F, 8.0F);
		lq_7.cubeList.add(new ModelBox(lq_7, 4, 29, 1.0F, -5.0F, -15.0F, 14, 1, 14, 0.0F));
		lq_8 = new ModelRenderer(this);
		lq_8.setRotationPoint(-8.0F, 16.0F, 8.0F);
		lq_8.cubeList.add(new ModelBox(lq_8, 4, 29, 1.0F, -6.0F, -15.0F, 14, 1, 14, 0.0F));
		lq_9 = new ModelRenderer(this);
		lq_9.setRotationPoint(-8.0F, 16.0F, 8.0F);
		lq_9.cubeList.add(new ModelBox(lq_9, 4, 29, 1.0F, -7.0F, -15.0F, 14, 1, 14, 0.0F));
		lq_10 = new ModelRenderer(this);
		lq_10.setRotationPoint(-8.0F, 16.0F, 8.0F);
		lq_10.cubeList.add(new ModelBox(lq_10, 4, 29, 1.0F, -8.0F, -15.0F, 14, 1, 14, 0.0F));
		lq_11 = new ModelRenderer(this);
		lq_11.setRotationPoint(-8.0F, 16.0F, 8.0F);
		lq_11.cubeList.add(new ModelBox(lq_11, 4, 29, 1.0F, -9.0F, -15.0F, 14, 1, 14, 0.0F));
		lq_12 = new ModelRenderer(this);
		lq_12.setRotationPoint(-8.0F, 16.0F, 8.0F);
		lq_12.cubeList.add(new ModelBox(lq_12, 4, 29, 1.0F, -10.0F, -15.0F, 14, 1, 14, 0.0F));

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
	public void renderLqLvl_1 (float f) { lq_1.render(f); }
	public void renderLqLvl_2 (float f) { lq_2.render(f); }
	public void renderLqLvl_3 (float f) { lq_3.render(f); }
	public void renderLqLvl_4 (float f) { lq_4.render(f); }
	public void renderLqLvl_5 (float f) { lq_5.render(f); }
	public void renderLqLvl_6 (float f) { lq_6.render(f); }
	public void renderLqLvl_7 (float f) { lq_7.render(f); }
	public void renderLqLvl_8 (float f) { lq_8.render(f); }
	public void renderLqLvl_9 (float f) { lq_9.render(f); }
	public void renderLqLvl_10 (float f) { lq_10.render(f); }
	public void renderLqLvl_11 (float f) { lq_11.render(f); }
	public void renderLqLvl_12 (float f) { lq_12.render(f); }

}