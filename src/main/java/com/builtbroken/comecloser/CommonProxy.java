package com.builtbroken.comecloser;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;

public class CommonProxy
{
    public void changeTagRange(float max, float min)
    {
        ComeCloser.sneakRange = min;
        ComeCloser.standingRange = max;
        this.sendPacket(null);
    }

    public void sendPacket(EntityPlayer player)
    {
        //Build data
        ByteBuf buf = Unpooled.buffer();
        buf.writeFloat(ComeCloser.sneakRange);
        buf.writeFloat(ComeCloser.standingRange);
        buf.writeBoolean(ComeCloser.doRayTrace);

        //Build packet
        FMLProxyPacket packet = new FMLProxyPacket(buf, ComeCloser.CHANNEL);

        //Send packet
        if (player instanceof EntityPlayerMP)
        {
            ComeCloser.packetHandler.sendTo(packet, (EntityPlayerMP) player);
        }
        else
        {
            ComeCloser.packetHandler.sendToAll(packet);
        }
    }

    @SubscribeEvent //fired on the server
    public void onPlayerConnectServer(FMLNetworkEvent.ServerConnectionFromClientEvent event)
    {
        if (!event.isLocal)
        {
            //Send packet to player
            this.sendPacket(((NetHandlerPlayServer) event.handler).playerEntity);
        }
    }
}
