package minefantasy.mf2.block.crafting;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import minefantasy.mf2.MineFantasyII;
import minefantasy.mf2.block.tileentity.TileEntitySoakingTrough;
import minefantasy.mf2.item.list.CreativeTabMF;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class BlockSoakingTrough extends BlockContainer {
    public static int soaking_RI = RenderingRegistry.getNextAvailableRenderId();

    @SideOnly(Side.CLIENT)
    public int CarpenterRenderSide;
    private int tier = 0;
    private Random rand = new Random();
    public IIcon liquidIconWater, liquidIconColordef;

    public BlockSoakingTrough() {
        super(Material.wood);

        GameRegistry.registerBlock(this, "MF_SoakingTrough");
        setBlockTextureName("minefantasy2:zsAddon/SoakingTroughTex");
        setBlockName("soakingTrough");
        this.setStepSound(Block.soundTypeWood);
        this.setHardness(4F);
        this.setResistance(2F);
        this.setLightOpacity(0);
        this.setCreativeTab(CreativeTabMF.tabUtil);
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase user, ItemStack item) {
        int direction = MathHelper.floor_double(user.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

        world.setBlockMetadataWithNotify(x, y, z, direction, 2);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer user, int side, float xOffset,
                                    float yOffset, float zOffset) {
        ItemStack held = user.getHeldItem();
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile != null && tile instanceof TileEntitySoakingTrough) {
            if (((TileEntitySoakingTrough) tile).interract(user, held)) {
                world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, "random.splash", 0.125F + user.getRNG().nextFloat() / 4F, 0.5F + user.getRNG().nextFloat());
                return true;
            }
            if (!world.isRemote) {
                user.openGui(MineFantasyII.instance, 0, world, x, y, z);
                return true;
            }
        }

        return false;
    }


    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntitySoakingTrough();
    }

    private TileEntitySoakingTrough getTile(World world, int x, int y, int z) {
        return (TileEntitySoakingTrough) world.getTileEntity(x, y, z);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        TileEntitySoakingTrough tile = getTile(world, x, y, z);

        if (tile != null) {
            for (int i1 = 0; i1 < tile.getSizeInventory(); ++i1) {
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
    }
    
    /*@Override
    public IIcon getIcon(IBlockAccess p_149673_1_, int p_149673_2_, int p_149673_3_, int p_149673_4_, int side) {
    	if(side == 0) return super.getIcon(p_149673_1_, p_149673_2_, p_149673_3_, p_149673_4_, side);
    	else if(side == 1) return liquidIconWater;
    	else if(side == 2) return liquidIconColordef;
    	else return null;
    }*/
    
    @Override
    public void registerBlockIcons(IIconRegister reg) {
    	super.registerBlockIcons(reg);
    	liquidIconWater = reg.registerIcon("minefantasy2:zsAddon/soaking_liquid_water");
    	liquidIconColordef = reg.registerIcon("minefantasy2:zsAddon/soaking_liquid_colordef");
    }

    @Override
    public int getRenderType() {
        return soaking_RI;
    }
}