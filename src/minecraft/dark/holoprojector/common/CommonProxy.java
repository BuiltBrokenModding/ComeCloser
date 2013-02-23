package dark.holoprojector.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler
{
	
	public static int guiID = 0;
	
	public void preInit()
	{
	}

	public void init()
	{
	}

	public void postInit()
	{
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity ent = world.getBlockTileEntity(x, y, z);
		switch (ID)
		{

		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity ent = world.getBlockTileEntity(x, y, z);
		switch (ID)
		{

		}
		return null;
	}
}
