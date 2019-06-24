package minefantasy.mf2.client.render.block;

import codechicken.lib.render.CCModel;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.uv.IconTransformation;
import codechicken.lib.vec.Translation;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import minefantasy.mf2.block.crafting.BlockSoakingTrough;
import minefantasy.mf2.block.tileentity.TileEntitySoakingTrough;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.Map;

public class RenderSoakingTrough implements ISimpleBlockRenderingHandler {
	// private static final TileEntitySoakingTroughRenderer invModel = new
	// TileEntitySoakingTroughRenderer();

	public static final Map<String, CCModel> obj;

	private IIcon baseIcon = null;

	static {
		obj = CCModel.parseObjModels(	new ResourceLocation("minefantasy2:models/zsModels/SoakingTrough.obj"),
										GL11.GL_QUADS,
										null);
		for (Map.Entry<String, CCModel> entry : obj.entrySet()) {
			entry.setValue(entry.getValue().backfacedCopy());
		}
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		if (!(block instanceof BlockSoakingTrough))
			return;

		GL11.glPushMatrix();
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		// invModel.renderInvModel(getTexName(), 0F, 0F, 0F);
		GL11.glPopMatrix();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
			RenderBlocks renderer) {
		/*
		 * if(baseIcon != block.getIcon(world, x, y, z, 0)) { baseIcon =
		 * block.getIcon(world, x, y, z, 0); iconObj.clear(); for(Map.Entry<String,
		 * CCModel> entry : obj.entrySet()) { CCModel iconModel =
		 * entry.getValue().copy().apply(new IconTransformation(baseIcon));
		 * iconObj.put(entry.getKey(), iconModel); } }
		 */
		CCRenderState.reset();
		CCRenderState.lightMatrix.locate(world, x, y, z);
		for (CCModel model : obj.values()) {
			model.render(	new Translation(x, y, z),
							CCRenderState.lightMatrix,
							new IconTransformation(renderer.getBlockIcon(block, world, x, y, z, 0)));
		}
		
		TileEntity te_ = world.getTileEntity(x, y, z);
		if(te_ != null && te_ instanceof TileEntitySoakingTrough) {
			TileEntitySoakingTrough te = (TileEntitySoakingTrough) te_;
			if(te.fill > 0) {
				double liquidY = (double)te.fill / (double)te.capacity;
				Color color = te.getLiquidColor(te.liquid);
				Tessellator.instance.setColorOpaque_F(color.getRed(), color.getGreen(), color.getBlue());
				Tessellator.instance.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
				Tessellator.instance.setNormal(0.0f, 1.0f, 0.0f);
				renderer.setRenderBounds(0.0, 0.0, 0.0, 1.0, liquidY, 1.0);
				renderer.renderFaceYPos(block, x, y, z, te.getLiquidTex(te.liquid));
			}
		}
		
		return true;
		/*
		 * Side1 = new ModelRenderer(this, 17, 15); Side1.addBox(0F, 0F, 0F, 16, 15, 1);
		 * Side1.setRotationPoint(-8F, -14F, -8F); Side1.setTextureSize(64, 32);
		 * Side1.mirror = true; setRotation(Side1, 0F, 0F, 0F); Side2 = new
		 * ModelRenderer(this, 0, 15); Side2.addBox(0F, 0F, 0F, 16, 15, 1);
		 * Side2.setRotationPoint(-8F, -14F, 7F); Side2.setTextureSize(64, 32);
		 * Side2.mirror = true; setRotation(Side2, 0F, 0F, 0F); Side4 = new
		 * ModelRenderer(this, 3, 2); Side4.addBox(0F, 0F, 0F, 1, 15, 14);
		 * Side4.setRotationPoint(7F, -14F, -7F); Side4.setTextureSize(64, 32);
		 * Side4.mirror = true; setRotation(Side4, 0F, 0F, 0F); Bottom = new
		 * ModelRenderer(this, 1, 1); Bottom.addBox(0F, -5F, 0F, 14, 2, 14);
		 * Bottom.setRotationPoint(-7F, 4F, -7F); Bottom.setTextureSize(64, 32);
		 * Bottom.mirror = true; setRotation(Bottom, 0F, 0F, 0F); Liquid = new
		 * ModelRenderer(this, 36, 18); Liquid.addBox(0F, 0F, 0F, 14, 1, 14);
		 * Liquid.setRotationPoint(-7F, -2F, -7F); Liquid.setTextureSize(64, 32);
		 * Liquid.mirror = true; setRotation(Liquid, 0F, 0F, 0F); Side3 = new
		 * ModelRenderer(this, 20, 2); Side3.addBox(0F, 0F, 0F, 1, 15, 14);
		 * Side3.setRotationPoint(-8F, -14F, -7F); Side3.setTextureSize(64, 32);
		 * Side3.mirror = true; setRotation(Side3, 0F, 0F, 0F); return true;
		 */
	}

	@Override
	public int getRenderId() {
		return BlockSoakingTrough.soaking_RI;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return false;
	}

}
