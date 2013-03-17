package elrath18.decor.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import elrath18.decor.IllustriousElements;

public class BlockColored extends Block
{
	@SideOnly(Side.CLIENT)
	private Icon[] icons;

	public BlockColored(String name, int id, Material par2Material)
	{
		super(id, par2Material);
		this.setUnlocalizedName(name);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Icon getBlockTextureFromSideAndMetadata(int side, int meta)
	{
		return this.icons[~meta & 15];
	}

	@Override
	public int damageDropped(int meta)
	{
		return meta;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(int par1, CreativeTabs tab, List contentList)
	{
		for (int j = 0; j < 16; ++j)
		{
			contentList.add(new ItemStack(par1, 1, j));
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void func_94332_a(IconRegister iconReg)
	{
		this.icons = new Icon[16];

		for (int i = 0; i < this.icons.length; ++i)
		{
			this.icons[i] = iconReg.func_94245_a(IllustriousElements.dyeColorNames[~i & 15] + this.getUnlocalizedName());
		}
	}
}
