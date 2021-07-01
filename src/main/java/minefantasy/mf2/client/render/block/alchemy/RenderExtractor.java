package minefantasy.mf2.client.render.block.alchemy;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import minefantasy.mf2.block.alchemy.BlockRefFurnaceExtractor;
import minefantasy.mf2.block.tileentity.alchemy.TileEntityExtractor;
import minefantasy.mf2.block.wizardry.BlockMagicPedestal;
import minefantasy.mf2.client.render.block.TileEntityMagicPedestalRenderer;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

public class RenderExtractor implements ISimpleBlockRenderingHandler {
    private static final TileEntityExtractorRenderer invModel = new TileEntityExtractorRenderer();

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        GL11.glPushMatrix();
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        BlockRefFurnaceExtractor extractor = (BlockRefFurnaceExtractor) block;
        invModel.renderInvModel(extractor.ruined ? "reffurnace_extractor_ruined" : "reffurnace_extractor", 0F, 0F, 0F);
        GL11.glPopMatrix();
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
                                    RenderBlocks renderer) {
        return false;
    }

    @Override
    public int getRenderId() {
        return BlockRefFurnaceExtractor.extractor_RI;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

}
