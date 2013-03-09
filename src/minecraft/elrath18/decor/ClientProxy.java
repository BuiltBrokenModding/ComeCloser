package elrath18.decor;

import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy
{
	public void preInit()
	{
		MinecraftForgeClient.preloadTexture(IllustriousElements.BLOCK_TEXTURE_FILE);
		MinecraftForgeClient.preloadTexture(IllustriousElements.ITEM_TEXTURE_FILE);
	}

	public void init()
	{

	}
}
