package dark.illus.blocks.colored;

import java.util.List;



import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockBasalt extends ItemBlock
{
	public ItemBlockBasalt(int par1)
	{
		super(par1);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack)
	{
		int meta = itemstack.getItemDamage();

		return BlockBasalt.blockNames[~meta & (BlockBasalt.blockNames.length-1)];
	}

	@Override
	public int getMetadata(int par1)
	{
		return par1;
	}

	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for (int i = 0; i < BlockBasalt.blockNames.length; i++)
		{
			par3List.add(new ItemStack(this, 1, i));
		}

	}
}
