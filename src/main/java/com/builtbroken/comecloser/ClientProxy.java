package com.builtbroken.comecloser;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.RenderLivingEvent;

public class ClientProxy extends CommonProxy
{
    @Override
    public void changeTagRange(float max, float min)
    {
        RenderLiving.NAME_TAG_RANGE = ComeCloser.standingRange = max;
        RenderLiving.NAME_TAG_RANGE_SNEAK = ComeCloser.sneakRange = min;
        Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText("ComeCloser>>TagRange: " + ComeCloser.sneakRange + " <-> " + ComeCloser.standingRange));
    }

    @SubscribeEvent
    public void onPacketData(FMLNetworkEvent.ClientCustomPacketEvent event)
    {
        ByteBuf buf = event.packet.payload();
        float f = buf.readFloat();
        float f2 = buf.readFloat();

        ComeCloser.doRayTrace = buf.readBoolean();
        this.changeTagRange(f2, f);
    }

    @SubscribeEvent
    public void connectionClosed(FMLNetworkEvent.ClientDisconnectionFromServerEvent event)
    {
        ComeCloser.sneakRange = 32;
        ComeCloser.standingRange = 64;
        ComeCloser.doRayTrace = false;
    }

    @SubscribeEvent
    public void onRenderSpecialPre(RenderLivingEvent.Specials.Pre event)
    {
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        if (ComeCloser.doRayTrace && event.entity instanceof EntityPlayer)
        {
            //Get distance to player squared
            double distanceSQ = player.getDistanceSqToEntity(event.entity);

            //Only do ray trace if within range
            if(distanceSQ <= (ComeCloser.standingRange * ComeCloser.standingRange)
                    //Do ray trace
                    && !player.canEntityBeSeen(event.entity))
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
