package dark.holoprojector.textprojector;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import universalelectricity.prefab.BlockMachine;

public class BlockProjector extends BlockMachine
{
	public BlockProjector(int id)
	{
		super("HoloProjector", id, Material.iron, CreativeTabs.tabDecorations);
	}

	@Override
	public boolean onUseWrench(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		int meta = world.getBlockMetadata(x, y, z);
		/* ROTATES THE MODEL ** */
		if (meta >= 6)
		{
			world.setBlockMetadataWithNotify(x, y, z, meta - 6);
		}
		else
		{
			world.setBlockMetadataWithNotify(x, y, z, meta + 6);
		}
		return true;
	}

	@Override
	public boolean onSneakUseWrench(World world, int x, int y, int z, EntityPlayer par5EntityPlayer, int side, float hitX, float hitY, float hitZ)
	{
		int meta = world.getBlockMetadata(x, y, z);
		if (meta + 1 == 6)
		{
			world.setBlockMetadataWithNotify(x, y, z, 0);
		}
		else if (meta + 1 == 12)
		{
			world.setBlockMetadataWithNotify(x, y, z, 6);
		}
		else
		{
			world.setBlockMetadataWithNotify(x, y, z, meta + 1);
		}
		return true;
	}
}
