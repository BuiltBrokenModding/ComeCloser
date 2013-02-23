package dark.holoprojector.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class guiHandler implements IGuiHandler 
{
	/* DECLARED GUI IDs ***/
	public static int guiID = 0;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		TileEntity ent = world.getBlockTileEntity(x, y, z);
		
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		TileEntity ent = world.getBlockTileEntity(x, y, z);
		
		
		return null;
	}

}
