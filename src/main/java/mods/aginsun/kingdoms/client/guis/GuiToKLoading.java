package mods.aginsun.kingdoms.client.guis;

import mods.aginsun.kingdoms.handlers.SchematicHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;

import java.awt.*;

public final class GuiToKLoading extends GuiScreenToK {

   GuiPriceBar bar;
   boolean flag;


   public void initGui() {
      this.bar = new GuiPriceBar(1, this.width / 2 - 100, this.height / 2 - 10, 200, 20, 1.0F, "red");
      this.buttonList.add(new GuiButton(1, this.width / 2 - 60, this.height / 2 + 25, 120, 20, I18n.format("gui.exit")));
   }

   protected void actionPerformed(GuiButton guibutton) {
      if(guibutton.id == 1) {
         this.mc.displayGuiScreen(null);
      }

   }

   public void drawScreen(int i, int j, float f) {
      this.drawDefaultBackground();
      float d = SchematicHandler.getInstance().getProgressCurrentBuilding();
      if(!SchematicHandler.getInstance().getBuildingList().isEmpty()) {
         this.bar.setBar(d / 100.0F);
         this.bar.drawBar();
         this.drawString(this.fontRendererObj, "Building the Guild...", this.width / 2 - fontRendererObj.getStringWidth("Building the Guild...") / 2, this.height / 2 + 15, Color.pink.getRGB());
      } else {
         this.drawString(this.fontRendererObj, "Press exit to continue...", this.width / 2 - fontRendererObj.getStringWidth("Press exit to continue...") / 2, this.height / 2 + 15, Color.pink.getRGB());
      }

      super.drawScreen(i, j, f);
   }

   protected void keyTyped(char par1, int par2) {
      if(par2 == 1 || par2 == this.mc.gameSettings.keyBindInventory.getKeyCode()) {
         this.mc.thePlayer.closeScreen();
      }

   }
}
