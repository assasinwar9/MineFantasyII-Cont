package minefantasy.mf2.block.basic;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.World;

public class BlockMithrilOre extends BlockOreMF {
    private boolean isPure;

    public BlockMithrilOre(String name, boolean pure) {
        super(name, 4, pure ? 3 : 2);
        isPure = pure;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
        if (rand.nextInt(20) == 0 && world.isRemote) {
            // "minefantasy2:block.mithrilore"
            world.playSound(x + 0.5D, y + 0.5D, z + 0.5D, (isPure ? "minefantasy2:block.oremithril" : ""),
                    1.0F, rand.nextFloat() * 0.4F + 1.1F, true);
        }
    }
}
