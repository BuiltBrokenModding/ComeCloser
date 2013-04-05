package dark.illus.blocks.reinforced;

import org.lwjgl.util.Color;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dark.illus.blocks.colored.BlockColored;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import universalelectricity.prefab.block.BlockAdvanced;

public class BlockReInforced extends BlockColored
{

	public BlockReInforced(int id)
	{
		super("HardWall", id, Material.rock);
		this.setHardness(10);
		this.setResistance(100);

	}
}
