package minefantasy.mf2.item.armour;

import java.util.List;

import minefantasy.mf2.api.armour.IRucksack;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemRucksack extends Item implements IRucksack {
	
	protected IIcon icons[];
	protected int numTiers;
	
	public ItemRucksack(int numTiers) {
		super();
		setHasSubtypes(true);
		this.numTiers = numTiers;
		icons = new IIcon[numTiers];
	}
	
	public ItemRucksack() {
		this(3);
	}
	
	@Override
	public IIcon getIconFromDamage(int damage) {
		return icons[damage];
	}
	
	@Override
	public void registerIcons(IIconRegister registry) {
		for(int i = 0; i < icons.length; i++) {
			icons[i] = registry.registerIcon(getIconString() + "_tier" + i);
		}
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for(int i = 0; i < numTiers; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}
	
	@Override
	public String getUnlocalizedName(ItemStack p_77667_1_) {
		return super.getUnlocalizedName() + ".tier_" + p_77667_1_.getItemDamage();
	}

	@Override
	public int getRucksackSize(ItemStack stack) {
		return (stack.getItemDamage() + 1) * 7;
	}

}
