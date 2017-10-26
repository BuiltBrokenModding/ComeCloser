package com.builtbroken.comecloser.network.packets;

import com.builtbroken.comecloser.ComeCloser;
import io.netty.buffer.ByteBuf;

/**
 * Used to sync settings to client
 *
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 10/25/2017.
 */
public final class PacketSyncSettings extends Packet
{
    private float min, max;
    private boolean doRay;

    public PacketSyncSettings()
    {
        //Need for packet builder
    }

    public PacketSyncSettings(float min, float max, boolean doRay)
    {
        this.min = min;
        this.max = max;
        this.doRay = doRay;
    }

    public static PacketSyncSettings getPacket()
    {
        return new PacketSyncSettings(ComeCloser.sneakRange, ComeCloser.standingRange, ComeCloser.doRayTrace);
    }

    @Override
    public void write(ByteBuf buffer)
    {
        buffer.writeFloat(min);
        buffer.writeFloat(max);
        buffer.writeBoolean(doRay);
    }

    @Override
    public void read(ByteBuf buffer)
    {
        min = buffer.readFloat();
        max = buffer.readFloat();
        doRay = buffer.readBoolean();
    }

    @Override
    public void handleClientSide()
    {
        ComeCloser.standingRange = max;
        ComeCloser.sneakRange = min;
        ComeCloser.doRayTrace = doRay;
        ComeCloser.proxy.onSettingsChanged();
    }
}
