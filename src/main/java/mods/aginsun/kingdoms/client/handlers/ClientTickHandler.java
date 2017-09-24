package mods.aginsun.kingdoms.client.handlers;

import mods.aginsun.kingdoms.handlers.GoldKeeper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiInventory;

import java.awt.*;
import java.util.EnumSet;

public final class ClientTickHandler {

   public void tickStart(EnumSet type, Object ... tickData) {}

   public String getLabel() {
      return "TaleofKingdomsClientTick";
   }

   public void onRenderTick() {
      Minecraft mc = Minecraft.getMinecraft();
      ScaledResolution scaled = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
      if(mc.currentScreen instanceof GuiInventory && !mc.playerController.isInCreativeMode()) {
         mc.fontRenderer.drawString("Gold Total: " + GoldKeeper.getGoldTotal(), scaled.getScaledWidth() / 2 - 7, scaled.getScaledHeight() / 2 - 15, Color.RED.getRGB());
      }

   }

   public void onTickInGUI(GuiScreen guiscreen) {}

   public void onTickInGame() {}
}
