package mods.aginsun.kingdoms;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import mods.aginsun.kingdoms.core.CommonProxy;
import mods.aginsun.kingdoms.entities.EntityRegistryToK;
import mods.aginsun.kingdoms.handlers.EntityLivingHandler;
import mods.aginsun.kingdoms.handlers.PickupHandler;
import mods.aginsun.kingdoms.handlers.SaveHandlerToK;
import mods.aginsun.kingdoms.items.Itemcoins;
import mods.aginsun.kingdoms.util.CommandGoldTaleOfKingdoms;
import net.minecraft.command.CommandHandler;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = "taleofkingdoms", name = "Tale of Kingdoms", version = "1.0-1.7.10")
public class TaleOfKingdoms
{
    @Instance("taleofkingdoms")
    public static TaleOfKingdoms instance;

    @SidedProxy(clientSide = "mods.aginsun.kingdoms.client.ClientProxy", serverSide = "mods.aginsun.kingdoms.core.CommonProxy")
    public static CommonProxy proxy;

    public static Item coins = new Itemcoins().setUnlocalizedName("Coins");

    @EventHandler
    public void preInit(FMLPreInitializationEvent e)
    {
        proxy.registerRenderers();
        GameRegistry.registerItem(coins, "Coints");
    }

    @EventHandler
    public void load(FMLInitializationEvent e)
    {
        proxy.Init();
        EntityRegistryToK.registerEntities();
        MinecraftForge.EVENT_BUS.register(new EntityLivingHandler());
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
    }

    @EventHandler
    public void serverStarting(FMLServerStartingEvent e)
    {
        ((CommandHandler) e.getServer().getCommandManager()).registerCommand(new CommandGoldTaleOfKingdoms());
    }

    @EventHandler
    public void serverStarted(FMLServerStartedEvent e)
    {
        FMLCommonHandler.instance().bus().register(new SaveHandlerToK());
        FMLCommonHandler.instance().bus().register(new PickupHandler());
    }
}