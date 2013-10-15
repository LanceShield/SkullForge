package mod.gamenature.skullforge.common;

import mod.gamenature.skullforge.update.ConnectionHandler;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

/*
 * Mod ported to Minecraft Forge by Entropimus, based on a mod by MuscleMan1234
 * http://www.minecraftforum.net/topic/1572189-145-craftable-mob-headsskulls-v11/
 * Please report bugs on this forum thread
 */

@Mod(modid = "skullforge", name = "SkullForge", version = SkullForge.version)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class SkullForge {


	@Instance("skullforge")
	public static SkullForge instance;

	// Proxy defs
	@SidedProxy(clientSide="mod.gamenature.skullforge.client.SkullForgeClientProxy", serverSide="mod.gamenature.skullforge.common.SkullForgeCommonProxy")
	public static SkullForgeCommonProxy proxy;

	//Version String
	public static final String version = "V1.6.4A";

	// Item declarations
	public static Item hotbones;
	public static Item witherbones;
	public static Item rehydratedmeat;
	public static Item paintbrush;
	public static Item paintscraper;


	@EventHandler
	public static void preInit( FMLPreInitializationEvent event ) {

		//Sound Loader
		MinecraftForge.EVENT_BUS.register(new SoundManager());

	}

	@EventHandler
	public void init(FMLInitializationEvent event) {

		//Register our Connection Handler please!
		NetworkRegistry.instance().registerConnectionHandler(new ConnectionHandler());

		// Construct our items
		hotbones = (new itemHotBones(4005)).setUnlocalizedName("skullforge:HotBones").setTextureName("HotBones");
		witherbones = (new itemWitherBones(4003)).setUnlocalizedName("skullforge:WitherBones").setTextureName("WitherBones");
		rehydratedmeat = (new itemWaterMeat(4004, 4, false)).setUnlocalizedName("skullforge:RehydratedMeat").setTextureName("RehydratedMeat");
		paintbrush = (new itemPaintbrush(4006)).setUnlocalizedName("skullforge:pb").setTextureName("pb");
		paintscraper = (new itemPaintScraper(4007)).setUnlocalizedName("skullforge:MyPaintScraper").setTextureName("MyPaintScrapper");

		// Add the recipes
		// Smelting recipe for bones into hot bones
		GameRegistry.addSmelting(Item.bone.itemID, new ItemStack(SkullForge.hotbones,1), 1);

		// Recipe to turn hot bones into wither bones with either charcoal or coal
		GameRegistry.addRecipe(new ItemStack(SkullForge.witherbones, 1), new Object [] {
			" # ", "#$#", " # ", Character.valueOf('#'), Item.coal, Character.valueOf('$'), SkullForge.hotbones});
		GameRegistry.addRecipe(new ItemStack(SkullForge.witherbones, 1), new Object [] {
			" # ", "#$#", " # ", Character.valueOf('#'), new ItemStack(Item.coal, 1, 1), Character.valueOf('$'), SkullForge.hotbones});

		// Wither Skull
		GameRegistry.addRecipe(new ItemStack(Item.skull, 1, 1), new Object [] 
				{"ccc", "csc", "ddd", Character.valueOf('c'), SkullForge.witherbones, Character.valueOf('s'), Item.skull, Character.valueOf('d'), Item.diamond});

		// Creeper Head
		GameRegistry.addRecipe(new ItemStack(Item.skull, 1, 4), new Object[]
				{"###", "#$#", "###", Character.valueOf('#'), Item.gunpowder, Character.valueOf('$'), Block.slowSand});

		// Zombie Head
		GameRegistry.addRecipe(new ItemStack(Item.skull, 1, 2), new Object[] 
				{"###", "#$#", "###", Character.valueOf('#'), Item.rottenFlesh, Character.valueOf('$'), Block.slowSand});

		// Skeleton Skull
		GameRegistry.addRecipe(new ItemStack(Item.skull, 1, 0), new Object[] 
				{"###", "#$#", "###", Character.valueOf('#'), Item.bone, Character.valueOf('$'), Block.slowSand});

		// Rehydrated flesh recipe
		GameRegistry.addRecipe(new ItemStack(SkullForge.rehydratedmeat, 8), new Object[] 
				{"###", "#$#", "###", Character.valueOf('#'), Item.rottenFlesh, Character.valueOf('$'), Item.bucketWater});

		// Cool down those hot bones!
		GameRegistry.addShapelessRecipe(new ItemStack(Item.bone, 1), new ItemStack(SkullForge.hotbones), new ItemStack(Item.bucketWater));

		// Steve Head
		GameRegistry.addRecipe(new ItemStack(Item.skull, 1, 3), new Object[]{"XXX","XYX","XXX", 
			Character.valueOf('X'), SkullForge.rehydratedmeat,
			Character.valueOf('Y'), Block.slowSand});

		//Paint Brush
		GameRegistry.addRecipe(new ItemStack(paintbrush), new Object[]{
			"z",
			"x",
			"n",
			'z', Item.silk, 'x', Item.stick, 'n', new ItemStack(Item.dyePowder, 1, 4)
		});
		
		//Putty Knife
		GameRegistry.addRecipe(new ItemStack(paintscraper), new Object[]{
			"p",
			"o",
			"f",
			'p', Item.ingotIron, 'o', Item.stick, 'f', new ItemStack(Item.dyePowder, 1, 1)
		});

		// Add the names
		LanguageRegistry.addName(SkullForge.hotbones, "Heated Bone");
		LanguageRegistry.addName(SkullForge.witherbones, "Wither Bone");
		LanguageRegistry.addName(SkullForge.rehydratedmeat, "Rehydrated Flesh");
		LanguageRegistry.addName(SkullForge.paintbrush, "Paintbrush");
		LanguageRegistry.addName(SkullForge.paintscraper, "Putty Knife");

		// Register the images
		proxy.registerRenderThings();

	}

}
