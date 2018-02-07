package com.builtbroken.comecloser.network;

import com.builtbroken.comecloser.ComeCloser;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

    /**
    * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
    * Created by Hennamann(Ole Henrik Stabell) on 06/02/2018.
    */
    public class SyncSettings implements IMessage {

        private float min;
        private float max;
        private boolean doRay;

        public SyncSettings() {
        }

        public SyncSettings(float min, float max, boolean doRay) {
            this.min = min;
            this.max = max;
            this.doRay = doRay;
        }

        @Override
        public void fromBytes(ByteBuf buf) {
            min = buf.readFloat();
            max = buf.readFloat();
            doRay = buf.readBoolean();
        }

        @Override
        public void toBytes(ByteBuf buf) {
            buf.writeFloat(min);
            buf.writeFloat(max);
            buf.writeBoolean(doRay);
        }

        public static class Handler implements IMessageHandler<SyncSettings, IMessage> {

            @Override
            public IMessage onMessage(SyncSettings message, MessageContext ctx) {
                ComeCloser.standingRange = message.max;
                ComeCloser.sneakRange = message.min;
                ComeCloser.doRayTrace = message.doRay;
                ComeCloser.proxy.onSettingsChanged();
                return null; // no response in this case
            }
        }
}