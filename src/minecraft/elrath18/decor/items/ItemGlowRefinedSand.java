package elrath18.decor.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import elrath18.decor.IllustriousElements;

public class ItemGlowRefinedSand extends Item
{
	public ItemGlowRefinedSand(int par1)
	{
		super(par1);
		this.setIconIndex(16);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setCreativeTab(CreativeTabs.tabMaterials);
		this.setItemName("GlowRefinedSand");
		this.setTextureFile(IllustriousElements.ITEM_TEXTURE_FILE);
	}

	public int getIconFromDamage(int par1)
	{
		return this.iconIndex + par1;
	}

	public int getMetadata(int par1)
	{
		return par1;
	}

	public final String getItemNameIS(ItemStack par1ItemStack)
	{
		return this.getItemName() + "." + IllustriousElements.dyeColorNames[ItemRefinedSand.getBlockFromDye(par1ItemStack.getItemDamage())];
	}

	public static int getBlockFromDye(int par0)
	{
		return ~par0 & 15;
	}

	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for (int i = 0; i < IllustriousElements.dyeColorNames.length; i++)
		{
			par3List.add(new ItemStack(par1, 1, i));
		}
	}
}
