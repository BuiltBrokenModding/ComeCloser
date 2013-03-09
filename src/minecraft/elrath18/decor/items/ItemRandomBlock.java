package elrath18.decor.items;

import java.util.List;

import elrath18.decor.IllustriousElements;
import elrath18.decor.blocks.BlockColorSand;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemRandomBlock extends ItemBlock
{
	public ItemRandomBlock(int par1)
	{
		super(par1);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setIconIndex(0);
	}

	@Override
	public String getItemNameIS(ItemStack itemstack)
	{
		int meta = itemstack.getItemDamage();
		
		return Block.blocksList[this.getBlockID()].getBlockName()+"."+meta;
	}

	@Override
	public String getItemName()
	{
		return Block.blocksList[this.getBlockID()].getBlockName() + ".0";
	}

	/**
	 * Returns the metadata of the block which this Item (ItemBlock) can place
	 */
	public int getMetadata(int par1)
	{
		return par1;
	}

	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		par3List.add(new ItemStack(this, 1, 0));
		par3List.add(new ItemStack(this, 1, 1));
		par3List.add(new ItemStack(this, 1, 2));
		par3List.add(new ItemStack(this, 1, 3));
		par3List.add(new ItemStack(this, 1, 4));
		par3List.add(new ItemStack(this, 1, 5));
	}
}
