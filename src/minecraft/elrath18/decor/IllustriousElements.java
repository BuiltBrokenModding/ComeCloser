package elrath18.decor;

import java.io.File;
import java.util.logging.Logger;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.BlockStep;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.common.Configuration;
import universalelectricity.prefab.TranslationHelper;
import universalelectricity.prefab.network.PacketManager;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import elrath18.decor.blocks.BlockColorSand;
import elrath18.decor.blocks.BlockRandomBlock;
import elrath18.decor.blocks.BlockGlowGlass;
import elrath18.decor.blocks.BlockStainGlass;
import elrath18.decor.items.ItemColorSand;
import elrath18.decor.items.ItemRandomBlock;
import elrath18.decor.items.ItemGlowGlass;
import elrath18.decor.items.ItemGlowRefinedSand;
import elrath18.decor.items.ItemRefinedSand;
import elrath18.decor.items.ItemStainGlass;

@Mod(modid = IllustriousElements.NAME, name = IllustriousElements.NAME, version = IllustriousElements.VERSION)
@NetworkMod(channels = { IllustriousElements.CHANNEL }, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketManager.class)
public class IllustriousElements
{
	// TODO Change in Version Release
	public static final String VERSION = "0.1.2";

	// Constants
	public static final String NAME = "Illustrious_Elements";
	public static final String CHANNEL = "IllustElem";

	public static IllustriousElements Instance;

	@SidedProxy(clientSide = "elrath18.decor.ClientProxy", serverSide = "elrath18.decor.CommonProxy")
	public static CommonProxy proxy;

	public static final String PATH = "/elrath18/";
	public static final String RESOURCE_PATH = PATH + "resources/";
	public static final String BLOCK_TEXTURE_FILE = RESOURCE_PATH + "blocks.png";
	public static final String ITEM_TEXTURE_FILE = RESOURCE_PATH + "items.png";
	public static final String LANGUAGE_PATH = RESOURCE_PATH + "lang/";

	// Variables //
	private static final String[] LANGUAGES_SUPPORTED = new String[] { "en_US" };

	static Configuration config = new Configuration(new File(Loader.instance().getConfigDir(), "IllustriousElements.cfg"));

	public static Logger FMLog = Logger.getLogger(NAME);

	public static final String[] dyeColorNames = new String[] { "White", "Red", "Green", "Brown", "Blue", "Purple", "Cyan", "Silver", "Gray", "Pink", "Lime", "Yellow", "LightBlue", "Magenta", "Orange", "Black" };

	// Blocks //
	public static Block blockStainGlass;
	public static Block blockColorSand;
	public static Block blockRandom;
	public static Block blockGlowGlass;

	// Item //
	public static Item itemRefinedSand;
	public static Item itemGlowingSand;

	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		proxy.preInit();

		// // Configuration ////
		config.load();
		blockStainGlass = new BlockStainGlass(Integer.parseInt(config.getBlock(Configuration.CATEGORY_BLOCK, "StainedGlassBlockID", 1200).value));
		blockColorSand = new BlockColorSand(Integer.parseInt(config.getBlock(Configuration.CATEGORY_BLOCK, "ColoredSandBlockID", 1201).value));
		blockRandom = new BlockRandomBlock(Integer.parseInt(config.getBlock(Configuration.CATEGORY_BLOCK, "ExtraBlocksBlockID", 1202).value));
		blockGlowGlass = new BlockGlowGlass(Integer.parseInt(config.getBlock(Configuration.CATEGORY_BLOCK, "GlowingGlassBlockID", 1203).value));


		itemRefinedSand = new ItemRefinedSand(Integer.parseInt(config.getItem(Configuration.CATEGORY_ITEM, "RefinedSandItemID", 30010).value));
		itemGlowingSand = new ItemGlowRefinedSand(Integer.parseInt(config.getItem(Configuration.CATEGORY_ITEM, "GlowingRefinedSandItemID", 30020).value));
		config.save();

		// // Registration ////
		GameRegistry.registerBlock(blockStainGlass, ItemStainGlass.class, "stainGlass");
		GameRegistry.registerBlock(blockColorSand, ItemColorSand.class, "stainSand");
		GameRegistry.registerBlock(blockRandom, ItemRandomBlock.class, "extraBlocks");
		GameRegistry.registerBlock(blockGlowGlass, ItemGlowGlass.class, "stainGlowGlass");
	}

	@Init
	public void generalLoad(FMLInitializationEvent event)
	{
		proxy.init();
		// // Block Names ////
		FMLog.info(" Loaded: " + TranslationHelper.loadLanguages(LANGUAGE_PATH, LANGUAGES_SUPPORTED) + " Languages.");
	}

	@PostInit
	public void postLoad(FMLPostInitializationEvent event)
	{
		proxy.postInit();
		// // Block Recipes ////

		// Stained Glass //
		for (int i = 0; i < dyeColorNames.length; i++)
		{
			FurnaceRecipes.smelting().addSmelting(this.itemRefinedSand.itemID, i, new ItemStack(this.blockStainGlass, 1, i), 10F);
		}

		// Glowing Glass //
		for (int i = 0; i < dyeColorNames.length; i++)
		{
			FurnaceRecipes.smelting().addSmelting(this.itemGlowingSand.itemID, i, new ItemStack(this.blockGlowGlass, 1, i), 10F);
		}

		// Colored Sand //
		for (int j = 0; j < dyeColorNames.length; j++)
		{
			GameRegistry.addRecipe(new ItemStack(blockColorSand, 8, j), new Object[] { "SSS", "SDS", "SSS", 'S', Block.sand, 'D', new ItemStack(Item.dyePowder, 1, j) });
		}

		// Extra Block //

		GameRegistry.addShapelessRecipe(new ItemStack(blockRandom, 1, 6), new Object[] { new ItemStack(blockRandom, 1, 3), Block.cobblestone });
		GameRegistry.addShapelessRecipe(new ItemStack(blockRandom, 1, 5), new Object[] { new ItemStack(blockRandom, 1, 3), Block.vine });
		GameRegistry.addShapelessRecipe(new ItemStack(blockRandom, 1, 4), new Object[] { new ItemStack(blockRandom, 1, 3), new ItemStack(blockRandom, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(blockRandom, 1, 3), new Object[] { "SS", "SS", 'S', new ItemStack(blockRandom, 1, 0) });
		GameRegistry.addShapelessRecipe(new ItemStack(blockRandom, 2, 2), new Object[] { Block.cobblestone, new ItemStack(blockRandom, 1, 0) });
		GameRegistry.addSmelting(Block.stone.blockID, new ItemStack(blockRandom, 1, 0), 0f);

		// // Item Recipes ////

		// Refined Sand //
		for (int j = 0; j < dyeColorNames.length; j++)
		{
			GameRegistry.addShapelessRecipe(new ItemStack(itemRefinedSand, 1, j), new Object[] { new ItemStack(blockColorSand, 1, j) });
		}

		// Glowing Refined Sand //
		for (int j = 0; j < dyeColorNames.length; j++)
		{
			GameRegistry.addShapelessRecipe(new ItemStack(itemGlowingSand, 1, j), new Object[] { new ItemStack(itemRefinedSand, 1, j), new ItemStack(Item.lightStoneDust, 1) });
		}
	}
}