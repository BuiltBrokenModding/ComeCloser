package elrath18.decor.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import elrath18.decor.IllustriousElements;

public class ItemColored extends Item
{
	@SideOnly(Side.CLIENT)
	private Icon[] icons;

	public ItemColored(int par1, String name)
	{
		super(par1);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setCreativeTab(CreativeTabs.tabMaterials);
		this.setUnlocalizedName(name);
	}

	@Override
	public Icon getIconFromDamage(int par1)
	{
		return icons[~par1 & 15];
	}

	@SideOnly(Side.CLIENT)
	public void func_94581_a(IconRegister par1IconRegister)
	{
		this.icons = new Icon[IllustriousElements.dyeColorNames.length];

		for (int i = 0; i < IllustriousElements.dyeColorNames.length; ++i)
		{
			this.icons[i] = par1IconRegister.func_94245_a(IllustriousElements.dyeColorNames[i] + this.getUnlocalizedName());
		}
	}

	@Override
	public int getMetadata(int par1)
	{
		return par1;
	}

	@Override
	public final String getUnlocalizedName(ItemStack par1ItemStack)
	{
		return this.getUnlocalizedName() + "." + IllustriousElements.dyeColorNames[par1ItemStack.getItemDamage()];
	}

	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for (int i = 0; i < IllustriousElements.dyeColorNames.length; i++)
		{
			par3List.add(new ItemStack(par1, 1, i));
		}
	}
}
