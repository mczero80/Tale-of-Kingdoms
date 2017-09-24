package mods.aginsun.kingdoms;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import mods.aginsun.kingdoms.core.CommonProxy;
import mods.aginsun.kingdoms.entities.EntityRegistryToK;
import mods.aginsun.kingdoms.handlers.EntityLivingHandler;
import mods.aginsun.kingdoms.items.Itemcoins;
import mods.aginsun.kingdoms.util.CommandGoldTaleOfKingdoms;
import net.minecraft.command.CommandHandler;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

@Mod(
   modid = "taleofkingdoms",
   name = "Tale of Kingdoms",
   version = "1.0-1.7.10"
)
public class TaleOfKingdoms {

   @Instance("taleofkingdoms")
   public static TaleOfKingdoms instance = new TaleOfKingdoms();
   @SidedProxy(
      clientSide = "mods.aginsun.kingdoms.client.ClientProxy",
      serverSide = "mods.aginsun.kingdoms.core.CommonProxy"
   )
   public static CommonProxy proxy;
   public static Item coins;
   public static int coinID;


   @EventHandler
   public void preInit(FMLPreInitializationEvent event) {
      Configuration config = new Configuration(event.getSuggestedConfigurationFile());
      config.load();
      coinID = config.get("item", "Coins", 7865).getInt();
      config.save();
   }

   @EventHandler
   public void load(FMLInitializationEvent event) {
      proxy.registerRenderers();
      proxy.Init();
      EntityRegistryToK.registerEntities();
      MinecraftForge.EVENT_BUS.register(new EntityLivingHandler());
      NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
      coins = new Itemcoins().setUnlocalizedName("Coins");
   }

   @EventHandler
   public void serverStarting(FMLServerStartingEvent event) {
      CommandHandler commandManager = (CommandHandler)event.getServer().getCommandManager();
      commandManager.registerCommand(new CommandGoldTaleOfKingdoms());
   }

   @EventHandler
   public void serverStarted(FMLServerStartedEvent event)
   {
      //GameRegistry.registerPlayerTracker(new SaveHandlerToK());
      //GameRegistry.registerPickupHandler(new PickupHandler());
   }
}