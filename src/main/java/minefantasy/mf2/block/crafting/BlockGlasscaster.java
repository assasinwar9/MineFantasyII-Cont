package minefantasy.mf2.block.crafting;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.block.tileentity.TileEntityGlasscaster;
import minefantasy.mf2.item.list.CreativeTabMF;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockGlasscaster extends BlockContainer {
    private static boolean keepInventory;
    private IIcon sideTex, frontTex;
    private Random rand = new Random();
    private double partOffsetX, partOffsetZ, partOffsetY, s;


    public BlockGlasscaster() {
        super(Material.rock);

        GameRegistry.registerBlock(this, "glasscaster_furnace");
        setBlockName("glasscaster_furnace");
        this.setStepSound(Block.soundTypePiston);
        this.setHardness(6F);
        this.setResistance(6F);
        this.setCreativeTab(CreativeTabMF.tabUtil);
    }

    private static TileEntityGlasscaster getTile(IBlockAccess world, int x, int y, int z) {
        return (TileEntityGlasscaster) world.getTileEntity(x, y, z);
    }

    @Override
    public void updateTick (World world, int x, int y, int z, Random random) {
        /*
        TileEntity tile = getTile(world, x, y, z);
        if (tile != null && tile instanceof TileEntityGlasscaster && ((TileEntityGlasscaster) tile).isActive) {
            if (random.nextInt(10) < 3) {
                s = 0.5; //basic centre-position offset
                if (world.getBlockMetadata(x, y, z) == 4) {
                    partOffsetX = (double) (random.nextInt(2) + 1) / 10 - s - 0.2;
                    partOffsetZ = (double) (random.nextInt(2) + 1) / 10 + s - 0.6;
                    partOffsetY = (double) (random.nextInt(3) + 1) / 10;
                }
                if (world.getBlockMetadata(x, y, z) == 5) {
                    partOffsetX = (double) (random.nextInt(2) + 1) / 10 + s;
                    partOffsetZ = (double) (random.nextInt(2) + 1) / 10 + s - 0.6;
                    partOffsetY = (double) (random.nextInt(3) + 1) / 10;
                }
                if (world.getBlockMetadata(x, y, z) == 2) {
                    partOffsetX = (double) (random.nextInt(2) + 1) / 10 + s - 0.6;
                    partOffsetZ = (double) (random.nextInt(2) + 1) / 10 - s - 0.2;
                    partOffsetY = (double) (random.nextInt(3) + 1) / 10;
                }
                if (world.getBlockMetadata(x, y, z) == 3) {
                    partOffsetX = (double) (random.nextInt(2) + 1) / 10 + s - 0.6;
                    partOffsetZ = (double) (random.nextInt(2) + 1) / 10 + s;
                    partOffsetY = (double) (random.nextInt(3) + 1) / 10;
                }
                world.spawnParticle("smoke", x + 0.5D + partOffsetX, y + 0.4D + partOffsetY, z + 0.5D + partOffsetZ, 0.0D, 0.0D, 0.0D);
            }
        }*/
    }

/*
    public static void updateBlockState(boolean filled, World world, int x, int y, int z) {
        int l = world.getBlockMetadata(x, y, z);
        TileEntityGlasscaster tileentity = getTile(world, x, y, z);
        keepInventory = true;

        Block block = world.getBlock(x, y, z);
        if (block instanceof BlockGlasscaster) {
            if (filled) {
                world.setBlock(x, y, z, getFilledTarKiln());
            } else {
                world.setBlock(x, y, z, getNoFilledTarKiln());
            }
        }

        keepInventory = false;
        world.setBlockMetadataWithNotify(x, y, z, l, 2);
        if (tileentity != null) {
            tileentity.validate();
            world.setTileEntity(x, y, z, tileentity);
        }
    }*/

     @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        super.randomDisplayTick(world, x, y, z, random);

    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer user, int side, float xOffset,
                                    float yOffset, float zOffset) {

        if (!world.isRemote) {
            user.openGui(MineFantasyII.instance, 0, world, x, y, z);
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityGlasscaster();
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase user, ItemStack item) {
        int direction = MathHelper.floor_double((double)(user.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (direction == 0)
        {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }

        if (direction == 1)
        {
            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }

        if (direction == 2)
        {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }

        if (direction == 3)
        {
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        if (keepInventory)
            return;

        TileEntityGlasscaster tile = getTile(world, x, y, z);

        if (tile != null) {
            int size = tile.getSizeInventory();
            for (int i1 = 0; i1 < size; ++i1) {
                ItemStack itemstack = tile.getStackInSlot(i1);

                if (itemstack != null) {
                    float f = this.rand.nextFloat() * 0.8F + 0.1F;
                    float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
                    float f2 = this.rand.nextFloat() * 0.8F + 0.1F;

                    while (itemstack.stackSize > 0) {
                        int j1 = this.rand.nextInt(21) + 10;

                        if (j1 > itemstack.stackSize) {
                            j1 = itemstack.stackSize;
                        }

                        itemstack.stackSize -= j1;
                        EntityItem entityitem = new EntityItem(world, x + f, y + f1, z + f2,
                                new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));

                        if (itemstack.hasTagCompound()) {
                            entityitem.getEntityItem()
                                    .setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
                        }

                        float f3 = 0.05F;
                        entityitem.motionX = (float) this.rand.nextGaussian() * f3;
                        entityitem.motionY = (float) this.rand.nextGaussian() * f3 + 0.2F;
                        entityitem.motionZ = (float) this.rand.nextGaussian() * f3;
                        world.spawnEntityInWorld(entityitem);
                    }
                }
            }

            world.func_147453_f(x, y, z, block);
        }

        super.breakBlock(world, x, y, z, block, meta);
        world.removeTileEntity(x, y, z);
    }

    public void spawnParticle (World world, int x, int y, int z) {
        if (rand.nextInt(10) < 3) {
            s = 0.5; //basic centre-position offset
            if (world.getBlockMetadata(x, y, z) == 4) {
                partOffsetX = (double) (rand.nextInt(2) + 1) / 10 - s - 0.2;
                partOffsetZ = (double) (rand.nextInt(2) + 1) / 10 + s - 0.6;
                partOffsetY = (double) (rand.nextInt(3) + 1) / 10;
            }
            if (world.getBlockMetadata(x, y, z) == 5) {
                partOffsetX = (double) (rand.nextInt(2) + 1) / 10 + s;
                partOffsetZ = (double) (rand.nextInt(2) + 1) / 10 + s - 0.6;
                partOffsetY = (double) (rand.nextInt(3) + 1) / 10;
            }
            if (world.getBlockMetadata(x, y, z) == 2) {
                partOffsetX = (double) (rand.nextInt(2) + 1) / 10 + s - 0.6;
                partOffsetZ = (double) (rand.nextInt(2) + 1) / 10 - s - 0.2;
                partOffsetY = (double) (rand.nextInt(3) + 1) / 10;
            }
            if (world.getBlockMetadata(x, y, z) == 3) {
                partOffsetX = (double) (rand.nextInt(2) + 1) / 10 + s - 0.6;
                partOffsetZ = (double) (rand.nextInt(2) + 1) / 10 + s;
                partOffsetY = (double) (rand.nextInt(3) + 1) / 10;
            }
            world.spawnParticle("smoke", x + 0.5D + partOffsetX, y + 0.4D + partOffsetY, z + 0.5D + partOffsetZ, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return side != meta ? sideTex : frontTex;
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        frontTex = reg.registerIcon("minefantasy2:zsAddon/glassfurnace_front");
        sideTex = reg.registerIcon("minefantasy2:zsAddon/glassfurnace_side");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z) {
        return Item.getItemFromBlock(this);
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int fort) {
        return Item.getItemFromBlock(this);
    }

}
