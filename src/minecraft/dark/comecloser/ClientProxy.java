package dark.comecloser;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.Player;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;

public class ClientProxy extends CommonProxy
{
	@Override
	public void changeTagRange(float max, float min)
	{
		RenderLiving.NAME_TAG_RANGE = max;
		RenderLiving.NAME_TAG_RANGE_SNEAK = min;
	}

	@Override
	public void sendPacket(Player player, float max, float min)
	{
		/* Client can't send packets */
	}

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
	{
		try
		{
			ByteArrayDataInput data = ByteStreams.newDataInput(packet.data);

			float f = data.readFloat();
			float f2 = data.readFloat();
			this.changeTagRange(f2, f);
			if (player instanceof EntityPlayer)
			{
				World world = ((EntityPlayer) player).worldObj;
				if (world != null && world.isRemote)
				{
					/* TODO remove */// ((ICommandSender) player).sendChatToPlayer("ping");
				}
			}
		}
		catch (Exception e)
		{

		}

	}

	@Override
	public void connectionClosed(INetworkManager manager)
	{
		ComeCloser.sneakRange = 32;
		ComeCloser.standingRange = 64;

	}
}
