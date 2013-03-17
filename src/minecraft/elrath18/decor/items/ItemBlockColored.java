package elrath18.decor.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import elrath18.decor.IllustriousElements;
import elrath18.decor.blocks.BlockColorSand;

public class ItemBlockColored extends ItemBlock
{
	public ItemBlockColored(int par1)
	{
		super(par1);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int par1)
	{
		return par1;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		return Block.blocksList[this.getBlockID()].getUnlocalizedName() + "." + IllustriousElements.dyeColorNames[itemStack.getItemDamage()];
	}
}
