package dark.comecloser;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatMessageComponent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class CommonProxy implements IPacketHandler, IConnectionHandler
{

	public void changeTagRange(float max, float min)
	{
		ComeCloser.sneakRange = min;
		ComeCloser.standingRange = max;
		this.sendPacket(null,max, min);
	}

	public void sendPacket(Player player, float max, float min)
	{
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		DataOutputStream data = new DataOutputStream(bytes);
		try
		{
			data.writeFloat(min);
			data.writeFloat(max);

			Packet250CustomPayload packet = new Packet250CustomPayload();
			packet.channel = ComeCloser.CHANNEL;
			packet.data = bytes.toByteArray();
			packet.length = packet.data.length;
			if(player instanceof EntityPlayer)
			{
				//TODO setup packet to send only to the player and not everyone
			}
			if (FMLCommonHandler.instance().getMinecraftServerInstance() != null)
			{
				FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().sendPacketToAllPlayers(packet);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void playerLoggedIn(Player p, NetHandler netHandler, INetworkManager manager)
	{
		EntityPlayer player = netHandler.getPlayer();
		if (player != null && !player.worldObj.isRemote)
		{
			player.sendChatToPlayer(ChatMessageComponent.func_111066_d("ComeCloser>>TagRange:" + ComeCloser.sneakRange + "<->" + ComeCloser.standingRange));
		}
		this.sendPacket(p, ComeCloser.standingRange, ComeCloser.sneakRange);
	}

	@Override
	public String connectionReceived(NetLoginHandler netHandler, INetworkManager manager)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void connectionOpened(NetHandler netClientHandler, String server, int port, INetworkManager manager)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void connectionOpened(NetHandler netClientHandler, MinecraftServer server, INetworkManager manager)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void connectionClosed(INetworkManager manager)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void clientLoggedIn(NetHandler clientHandler, INetworkManager manager, Packet1Login login)
	{
		this.sendPacket((Player) clientHandler.getPlayer(),ComeCloser.standingRange, ComeCloser.sneakRange);

	}

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
	{
		/* Server side can't receive packets */
	}

}
