package com.builtbroken.comecloser;

import com.builtbroken.comecloser.network.SyncSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class CommonProxy
{
    public void onSettingsChanged()
    {
        this.sendPacket(null);
    }

    public void sendPacket(EntityPlayer player)
    {
        // Send packet
        if (player != null)
        {
            if (player instanceof EntityPlayerMP)
            {
                ComeCloser.network.sendTo(new SyncSettings(), (EntityPlayerMP) player);
            }
            else
            {
                // Likely a fake player
            }
        }
        else
        {
            ComeCloser.network.sendToAll(new SyncSettings());
        }
    }

    @SubscribeEvent //fired on the server
    public void onPlayerConnectServer(PlayerEvent.PlayerLoggedInEvent event)
    {
        if (event.player instanceof EntityPlayerMP) {
            this.sendPacket((EntityPlayerMP) event.player);
        }
    }
}