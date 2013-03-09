package elrath18.decor.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCloth;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import elrath18.decor.IllustriousElements;
import elrath18.decor.blocks.BlockColorSand;

public class ItemColorSand extends ItemBlock
{
	public ItemColorSand(int par1)
	{
		super(par1);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@SideOnly(Side.CLIENT)
	public int getIconFromDamage(int par1)
	{
		return IllustriousElements.blockColorSand.getBlockTextureFromSideAndMetadata(2, BlockCloth.getBlockFromDye(par1));
	}

	public int getMetadata(int par1)
	{
		return par1;
	}

	@Override
	public String getItemNameIS(ItemStack par1ItemStack)
	{
		return Block.blocksList[this.getBlockID()].getBlockName() + "." + IllustriousElements.dyeColorNames[BlockColorSand.getBlockFromDye(par1ItemStack.getItemDamage())];
	}

	@Override
	public String getItemName()
	{
		return Block.blocksList[this.getBlockID()].getBlockName() + "." + IllustriousElements.dyeColorNames[0];
	}
}
