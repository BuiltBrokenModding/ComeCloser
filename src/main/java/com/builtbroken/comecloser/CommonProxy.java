package com.builtbroken.comecloser;

import com.builtbroken.comecloser.network.packets.PacketSyncSettings;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;

public class CommonProxy
{
    public void onSettingsChanged()
    {
        this.sendPacket(null);
    }

    public void sendPacket(EntityPlayer player)
    {
        //Send packet
        if (player != null)
        {
            if (player instanceof EntityPlayerMP)
            {
                ComeCloser.packetHandler.sendToPlayer(PacketSyncSettings.getPacket(), (EntityPlayerMP) player);
            }
            else
            {
                //Likely a fake player
            }
        }
        else
        {
            ComeCloser.packetHandler.sendToAll(PacketSyncSettings.getPacket());
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
