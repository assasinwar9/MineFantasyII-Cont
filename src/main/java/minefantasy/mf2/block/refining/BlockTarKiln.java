package minefantasy.mf2.block.refining;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.block.list.BlockListMF;
import minefantasy.mf2.block.tileentity.TileEntityTarKiln;
import minefantasy.mf2.item.list.ComponentListMF;
import minefantasy.mf2.item.list.CreativeTabMF;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockTarKiln extends BlockContainer {
    public final boolean isFilled;
    private static boolean keepInventory;
    private IIcon sideTex, topTex;
    private Random rand = new Random();

    public BlockTarKiln(boolean isFilled) {
        super(Material.rock);

        this.isFilled = isFilled;
        GameRegistry.registerBlock(this, "TarKiln" + (isFilled ? "Filled" : ""));
        setBlockName("tarKiln");
        this.setStepSound(Block.soundTypePiston);
        this.setHardness(6F);
        this.setResistance(6F);
        this.setCreativeTab(CreativeTabMF.tabUtil);
    }

    private static TileEntityTarKiln getTile(IBlockAccess world, int x, int y, int z) {
        return (TileEntityTarKiln) world.getTileEntity(x, y, z);
    }

    public static void updateBlockState(boolean filled, World world, int x, int y, int z) {
        int l = world.getBlockMetadata(x, y, z);
        TileEntityTarKiln tileentity = getTile(world, x, y, z);
        keepInventory = true;

        Block block = world.getBlock(x, y, z);
        if (block instanceof BlockTarKiln) {
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
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer user, int side, float xOffset,
                                    float yOffset, float zOffset) {
        TileEntityTarKiln tile = getTile(world, x, y, z);
        if (tile != null) {
            ItemStack held = user.getHeldItem();

            if (held != null && held.getItem() == ComponentListMF.clay_pot && tile.tarOnOut > 0) {
                held.stackSize -= 1;
                tile.tarOnOut -= 1;

                EntityItem drop = new EntityItem(world, user.posX, user.posY, user.posZ, tile.getResultTar());
                drop.delayBeforeCanPickup = 0;
                world.spawnEntityInWorld(drop);
                return true;
            }
            if (!world.isRemote) {
                user.openGui(MineFantasyII.instance, 0, world, x, y, z);
            }
        }
        return true;
    }

    private static Block getFilledTarKiln () { return BlockListMF.tarKiln_filled; }

    private static Block getNoFilledTarKiln () { return BlockListMF.tarKiln; }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityTarKiln();
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        if (keepInventory)
            return;

        TileEntityTarKiln tile = getTile(world, x, y, z);

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

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return side == 1 ? topTex : sideTex;
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        topTex = isFilled ? reg.registerIcon("minefantasy2:zsAddon/tarKiln_top_ready")
                : reg.registerIcon("minefantasy2:zsAddon/tarKiln_top");
        sideTex = reg.registerIcon("minefantasy2:zsAddon/tarKiln_side");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z) {
        return Item.getItemFromBlock(getNoFilledTarKiln());
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int fort) {
        return Item.getItemFromBlock(getNoFilledTarKiln());
    }

}
