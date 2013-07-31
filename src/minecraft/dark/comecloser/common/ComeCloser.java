package dark.comecloser.common;

import java.io.File;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = "ComeCloser ", name = "Come Closer", version = "0.3.0")
@NetworkMod(channels = { ComeCloser.CHANNEL }, clientSideRequired = true, serverSideRequired = false, connectionHandler = ComeCloser.class, packetHandler = ComeCloser.class)
public class ComeCloser implements IPacketHandler, IConnectionHandler
{
    @Instance("ComeCloser")
    public static ComeCloser Instance;

    @SidedProxy(clientSide = "dark.comecloser.common.ClientProxy", serverSide = "dark.comecloser.common.CommonProxy")
    public static CommonProxy proxy;

    public static final String CHANNEL = "ComeCloser";

    public static float standingRange = 64f;
    public static float sneakRange = 32f;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        this.Instance = this;
        // // Config ////
        if (event.getSide() == Side.SERVER)
        {
            Configuration config = new Configuration(new File(cpw.mods.fml.common.Loader.instance().getConfigDir(), "ComeCloser.cfg"));
            config.load();
            sneakRange = config.get(Configuration.CATEGORY_GENERAL, "SneakRange", 32).getInt();
            standingRange = config.get(Configuration.CATEGORY_GENERAL, "NormalRange", 64).getInt();
            config.save();
        }

    }

    @Override
    public void playerLoggedIn(Player player, NetHandler netHandler, INetworkManager manager)
    {
        proxy.playerLoggedIn(player, netHandler, manager);

    }

    @Override
    public String connectionReceived(NetLoginHandler netHandler, INetworkManager manager)
    {
        return proxy.connectionReceived(netHandler, manager);
    }

    @Override
    public void connectionOpened(NetHandler netClientHandler, String server, int port, INetworkManager manager)
    {
        proxy.connectionOpened(netClientHandler, server, port, manager);

    }

    @Override
    public void connectionOpened(NetHandler netClientHandler, MinecraftServer server, INetworkManager manager)
    {
        proxy.connectionOpened(netClientHandler, server, manager);

    }

    @Override
    public void connectionClosed(INetworkManager manager)
    {
        proxy.connectionClosed(manager);

    }

    @Override
    public void clientLoggedIn(NetHandler clientHandler, INetworkManager manager, Packet1Login login)
    {
        proxy.clientLoggedIn(clientHandler, manager, login);

    }

    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
    {
        proxy.onPacketData(manager, packet, player);

    }

}