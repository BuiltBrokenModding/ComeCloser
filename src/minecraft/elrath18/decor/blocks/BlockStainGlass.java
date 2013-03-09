package elrath18.decor.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import elrath18.decor.IllustriousElements;

public class BlockStainGlass extends Block
{

	public BlockStainGlass(int par1)
	{
		super(par1, Material.glass);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setHardness(.5f);
		this.setResistance(.5f);
		this.setBlockName("StainedGlass");
		this.setStepSound(soundGlassFootstep);
		this.setTextureFile(IllustriousElements.BLOCK_TEXTURE_FILE);
		this.setLightOpacity(2);
	}

	public int quantityDropped(Random par1Random)
	{
		return 0;
	}

	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass()
	{
		return 1;
	}

	public boolean isOpaqueCube()
	{
		return false;
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}

	protected boolean canSilkHarvest()
	{
		return true;
	}

	public int getBlockTextureFromSideAndMetadata(int side, int meta)
	{
		return this.blockIndexInTexture + meta;
	}

	public int damageDropped(int par1)
	{
		return par1;
	}

	@Override
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for (int i = 0; i < IllustriousElements.dyeColorNames.length; i++)
		{
			par3List.add(new ItemStack(this.blockID, 1, i));
		}
	}
}
