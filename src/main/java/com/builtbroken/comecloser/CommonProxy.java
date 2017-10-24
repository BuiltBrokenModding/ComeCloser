package com.builtbroken.comecloser;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.util.ChatComponentText;

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

    @SubscribeEvent
    public void playerLoggedIn(FMLNetworkEvent.ServerConnectionFromClientEvent event)
    {
        if(!event.isLocal)
        {
            try
            {
                EntityPlayerMP player = ((NetHandlerPlayServer) event.handler).playerEntity;
                if (player != null && !player.worldObj.isRemote)
                {
                    player.addChatMessage(new ChatComponentText("ComeCloser>>TagRange:" + ComeCloser.sneakRange + "<->" + ComeCloser.standingRange));
                }
                this.sendPacket(player);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    @SubscribeEvent
    public void clientLoggedIn(FMLNetworkEvent.ClientConnectedToServerEvent event)
    {
        this.sendPacket(FMLClientHandler.instance().getClientPlayerEntity());
    }
}
