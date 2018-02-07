package com.builtbroken.comecloser;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

public class ClientProxy extends CommonProxy
{
    @Override
    public void onSettingsChanged()
    {
        RenderLiving.NAME_TAG_RANGE = ComeCloser.standingRange;
        RenderLiving.NAME_TAG_RANGE_SNEAK = ComeCloser.sneakRange;
        if (Minecraft.getMinecraft() != null && Minecraft.getMinecraft().thePlayer != null && ComeCloser.doChatMessages)
        {
            Minecraft.getMinecraft().thePlayer.sendChatMessage("ComeCloser>>TagRange: " + ComeCloser.sneakRange + " <-> " + ComeCloser.standingRange);
        }
    }

    @SubscribeEvent
    public void connectionClosed(FMLNetworkEvent.ClientDisconnectionFromServerEvent event)
    {
        ComeCloser.instance.setDefaults();
    }

    @SubscribeEvent
    public void onRenderSpecialPre(RenderLivingEvent.Specials.Pre event)
    {
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        if (ComeCloser.doRayTrace && event.getEntity() instanceof EntityPlayer)
        {
            //Get distance to player squared
            double distanceSQ = player.getDistanceSqToEntity(event.getEntity());

            //Only do ray trace if within range
            if (distanceSQ <= (ComeCloser.standingRange * ComeCloser.standingRange)
                    //Do ray trace
                    && !player.canEntityBeSeen(event.getEntity()))
            {
                //Temp disable render range
                RenderLiving.NAME_TAG_RANGE = 1;
                RenderLiving.NAME_TAG_RANGE_SNEAK = 1;
            }
        }
    }

    @SubscribeEvent
    public void onRenderSpecialPost(RenderLivingEvent.Specials.Pre event)
    {
        if (ComeCloser.doRayTrace)
        {
            //Reset
            RenderLiving.NAME_TAG_RANGE = ComeCloser.standingRange;
            RenderLiving.NAME_TAG_RANGE_SNEAK = ComeCloser.sneakRange;
        }
    }
}