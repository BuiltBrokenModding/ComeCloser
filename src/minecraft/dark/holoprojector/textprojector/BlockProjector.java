package dark.holoprojector.textprojector;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import universalelectricity.prefab.block.BlockAdvanced;

public class BlockProjector extends BlockAdvanced
{
	public BlockProjector(int id)
	{
		super(id, Material.iron);
		this.setUnlocalizedName("HoloProjector");
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}

	@Override
	public boolean onUseWrench(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		int meta = world.getBlockMetadata(x, y, z);
		/* ROTATES THE MODEL ** */
		if (meta >= 6)
		{
			world.setBlockMetadataWithNotify(x, y, z, meta - 6, 3);
		}
		else
		{
			world.setBlockMetadataWithNotify(x, y, z, meta + 6, 3);
		}
		return true;
	}

	@Override
	public boolean onSneakUseWrench(World world, int x, int y, int z, EntityPlayer par5EntityPlayer, int side, float hitX, float hitY, float hitZ)
	{
		int meta = world.getBlockMetadata(x, y, z);
		/* CHANGES THE MODEL's FACING DIRECTION ** */
		if (meta + 1 == 6)
		{
			world.setBlockMetadataWithNotify(x, y, z, 0, 3);
		}
		else if (meta + 1 >= 12)
		{
			world.setBlockMetadataWithNotify(x, y, z, 6, 3);
		}
		else
		{
			world.setBlockMetadataWithNotify(x, y, z, meta + 1, 3);
		}
		return true;
	}
}
