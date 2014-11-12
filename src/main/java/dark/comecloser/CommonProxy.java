package dark.comecloser;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.util.ChatComponentText;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class CommonProxy
{
	public void changeTagRange(float max, float min)
	{
		ComeCloser.sneakRange = min;
		ComeCloser.standingRange = max;
		this.sendPacket(null,max, min);
	}

	public void sendPacket(EntityPlayer player, float max, float min)
	{
		ByteBuf buf = Unpooled.buffer();
		buf.writeFloat(min);
		buf.writeFloat(max);
		FMLProxyPacket packet = new FMLProxyPacket(buf, ComeCloser.CHANNEL);
		ComeCloser.packetHandler.sendToAll(packet); //TODO setup packet to send only to the player and not everyone
	}

	@SubscribeEvent
	public void playerLoggedIn(FMLNetworkEvent.ServerConnectionFromClientEvent event)
	{
		EntityPlayerMP player = ((NetHandlerPlayServer)event.handler).playerEntity;
		if (player != null && !player.worldObj.isRemote)
		{
			player.addChatMessage(new ChatComponentText("ComeCloser>>TagRange:" + ComeCloser.sneakRange + "<->" + ComeCloser.standingRange));
		}
		this.sendPacket(player, ComeCloser.standingRange, ComeCloser.sneakRange);
	}

	@SubscribeEvent
	public void clientLoggedIn(FMLNetworkEvent.ClientConnectedToServerEvent event)
	{
		this.sendPacket(FMLClientHandler.instance().getClientPlayerEntity(), ComeCloser.standingRange, ComeCloser.sneakRange);
	}
}
