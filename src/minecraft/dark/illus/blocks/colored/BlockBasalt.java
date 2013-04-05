package dark.illus.blocks.colored;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dark.illus.IllustriousElements;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;

public class BlockBasalt extends Block
{
	private Icon[] icons;
	public final static String[] blockNames = new String[] { "basalt","basaltcobble","basaltbrick","basaltchiseled","basaltcracked","basaltmossy" };

	public BlockBasalt(int par1)
	{
		super(par1, Material.rock);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setHardness(2f);
		this.setResistance(2f);
		this.setUnlocalizedName("basalt");
		this.setStepSound(soundStoneFootstep);
		this.setLightValue(1);
	}

	@Override
	public int quantityDropped(Random par1Random)
	{
		return 1;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return true;
	}

	@Override
	protected boolean canSilkHarvest()
	{
		return true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void func_94332_a(IconRegister iconReg)
	{
		this.icons = new Icon[16];

		for (int i = 0; i < this.blockNames.length; ++i)
		{
			this.icons[i] = iconReg.func_94245_a(this.blockNames[i]);
		}
	}

	@Override
	public int damageDropped(int par1)
	{
		return par1;
	}
}
