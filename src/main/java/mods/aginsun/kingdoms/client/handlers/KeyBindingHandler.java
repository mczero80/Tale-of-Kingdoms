package mods.aginsun.kingdoms.client.handlers;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import mods.aginsun.kingdoms.client.guis.GuiStartConquest;
import mods.aginsun.kingdoms.util.Buildings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

import java.util.EnumSet;

public class KeyBindingHandler
{

   private static KeyBinding key = new KeyBinding("Start Conquest", 21, "new");
   private static KeyBinding[] keybindings = new KeyBinding[]{key};
   private static boolean[] booleans = new boolean[]{false};

   public String getLabel() {
      return "Start Conquest";
   }

   public void keyDown(EnumSet types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {
      if(FMLClientHandler.instance().getClient().currentScreen == null && kb.getKeyCode() == key.getKeyCode() && !Buildings.getBuilding(0)) {
         FMLCommonHandler.instance().showGuiScreen(new GuiStartConquest(Minecraft.getMinecraft()));
      }

   }

   public void keyUp(EnumSet types, KeyBinding kb, boolean tickEnd) {}
}
