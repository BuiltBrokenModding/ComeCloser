package com.builtbroken.comecloser.network;

import com.builtbroken.comecloser.ComeCloser;
import com.builtbroken.comecloser.network.packets.Packet;
import com.builtbroken.comecloser.network.packets.PacketSyncSettings;
import cpw.mods.fml.common.network.FMLIndexedMessageToMessageCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author tgame14
 * @since 31/05/14
 */
public class ChannelHandler extends FMLIndexedMessageToMessageCodec<Packet>
{
    public boolean silenceStackTrace = false; //TODO add command and config

    public ChannelHandler()
    {
        this.addDiscriminator(0, PacketSyncSettings.class);
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, Packet packet, ByteBuf target) throws Exception
    {
        try
        {
            packet.write(target);
        }
        catch (Exception e)
        {
            if (!silenceStackTrace)
                ComeCloser.LOGGER.error("Failed to encode packet " + packet, e);
            else
                ComeCloser.LOGGER.error("Failed to encode packet " + packet + " E: " + e.getMessage());
        }
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf source, Packet packet)
    {
        try
        {
            packet.read(source);
        }
        catch (Exception e)
        {
            if (!silenceStackTrace)
                ComeCloser.LOGGER.error("Failed to decode packet " + packet, e);
            else
                ComeCloser.LOGGER.error("Failed to decode packet " + packet + " E: " + e.getMessage());
        }
    }
}
