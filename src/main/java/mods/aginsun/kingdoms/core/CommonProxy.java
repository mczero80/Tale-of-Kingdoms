package mods.aginsun.kingdoms.core;

import cpw.mods.fml.common.network.IGuiHandler;
import mods.aginsun.kingdoms.client.guis.GuiSell;
import mods.aginsun.kingdoms.entities.TileEntitySell;
import mods.aginsun.kingdoms.handlers.GoldKeeper;
import mods.aginsun.kingdoms.inventory.ContainerSell;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class CommonProxy implements IGuiHandler {

   public GoldKeeper gold;


   public void registerRenderers() {}

   public void Init() {
      //TickRegistry.registerTickHandler(new CommonTickHandler(), Side.SERVER);
   }

   public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
      return id == 1?new ContainerSell(new TileEntitySell(), player.inventory):null;
   }

   public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
      return id == 1?new GuiSell(player.inventory, new TileEntitySell()):null;
   }

   public World getClientWorld() {
      return null;
   }
}
