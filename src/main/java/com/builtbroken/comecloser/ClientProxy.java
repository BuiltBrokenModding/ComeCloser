package com.builtbroken.comecloser;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import io.netty.buffer.ByteBuf;

import net.minecraft.client.renderer.entity.RenderLiving;

public class ClientProxy extends CommonProxy
{
	@Override
	public void changeTagRange(float max, float min)
	{
		RenderLiving.NAME_TAG_RANGE = max;
		RenderLiving.NAME_TAG_RANGE_SNEAK = min;
	}

	@SubscribeEvent
	public void onPacketData(FMLNetworkEvent.ClientCustomPacketEvent event)
	{
		ByteBuf buf = event.packet.payload();
		float f = buf.readFloat();
		float f2 = buf.readFloat();
		this.changeTagRange(f2, f);
	}

	@SubscribeEvent
	public void connectionClosed(FMLNetworkEvent.ClientDisconnectionFromServerEvent event)
	{
		ComeCloser.sneakRange = 32;
		ComeCloser.standingRange = 64;
	}
}
