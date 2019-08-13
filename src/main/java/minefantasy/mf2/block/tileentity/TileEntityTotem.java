package minefantasy.mf2.block.tileentity;

import java.util.EnumSet;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityTotem extends TileEntity {
	private static int randomBetweenIncl(Random rand, int a, int b) {
		return rand.nextInt(b - a + 1) + a;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		
		TotemLogic.metatable[worldObj.getBlockMetadata(xCoord, yCoord, zCoord)].updateEntity(this);
	}

	public static enum TotemLogic {
		GROWTH(0) {
			@Override
			public void updateEntity(TileEntityTotem tile) {
				if (!tile.worldObj.isRemote) {
					int randomCount = tile.worldObj.rand.nextInt(10);
					for (int i = 0; i < randomCount; i++) {
						int x = randomBetweenIncl(tile.worldObj.rand, tile.xCoord - 3, tile.xCoord + 3);
						int y = randomBetweenIncl(tile.worldObj.rand, tile.yCoord - 3, tile.yCoord + 3);
						int z = randomBetweenIncl(tile.worldObj.rand, tile.zCoord - 3, tile.zCoord + 3);
						Block block = tile.worldObj.getBlock(x, y, z);
						if (block != null && block.getTickRandomly()) {
							block.updateTick(tile.worldObj, x, y, z, tile.worldObj.rand);
						}
					}
				}
			}
		},
		FIRE(1) {
			@Override
			public void updateEntity(TileEntityTotem tile) {
				if (!tile.worldObj.isRemote) {
					@SuppressWarnings("unchecked")
					List<EntityLivingBase> entities = tile.worldObj
							.getEntitiesWithinAABB(	EntityLivingBase.class,
													AxisAlignedBB.getBoundingBox(	tile.xCoord - 3,
																					tile.yCoord - 3,
																					tile.zCoord - 3,
																					tile.xCoord + 4,
																					tile.yCoord + 4,
																					tile.zCoord + 4));
					for(EntityLivingBase entity : entities) {
						if(!(entity instanceof EntityPlayer)) entity.setFire(5);
					}
				}
			}
		};

		public static TotemLogic[] metatable = new TotemLogic[16];
		private final int meta;

		private TotemLogic(int meta) {
			this.meta = meta;
		}

		public abstract void updateEntity(TileEntityTotem tile);

		static {
			for (TotemLogic logic : EnumSet.allOf(TotemLogic.class)) {
				metatable[logic.meta] = logic;
			}
		}
	}
}
