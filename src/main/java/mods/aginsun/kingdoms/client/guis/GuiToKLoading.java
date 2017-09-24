package mods.aginsun.kingdoms.client.guis;

import java.awt.Color;
import mods.aginsun.kingdoms.client.guis.GuiPriceBar;
import mods.aginsun.kingdoms.client.guis.GuiScreenToK;
import mods.aginsun.kingdoms.handlers.SchematicHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiToKLoading extends GuiScreenToK {

   GuiPriceBar bar;
   boolean flag;


   public void func_73866_w_() {
      this.bar = new GuiPriceBar(1, this.field_73880_f / 2 - 100, this.field_73881_g / 2 - 10, 200, 20, 1.0F, "red");
      this.field_73887_h.add(new GuiButton(1, this.field_73880_f / 2 - 60, this.field_73881_g / 2 + 25, 120, 20, "Exit"));
   }

   protected void func_73875_a(GuiButton guibutton) {
      if(guibutton.id == 1) {
         this.field_73882_e.displayGuiScreen((GuiScreen)null);
      }

   }

   public void func_73863_a(int i, int j, float f) {
      this.func_73873_v_();
      float d = SchematicHandler.getInstance().getProgressCurrentBuilding();
      if(!SchematicHandler.getInstance().getBuildingList().isEmpty()) {
         this.bar.setBar(d / 100.0F);
         this.bar.drawBar();
         this.func_73731_b(this.field_73886_k, "Building the Guild...", this.field_73880_f / 2 - this.field_73886_k.getStringWidth("Building the Guild...") / 2, this.field_73881_g / 2 + 15, Color.pink.getRGB());
      } else {
         this.func_73731_b(this.field_73886_k, "Press exit to continue...", this.field_73880_f / 2 - this.field_73886_k.getStringWidth("Press exit to continue...") / 2, this.field_73881_g / 2 + 15, Color.pink.getRGB());
      }

      super.func_73863_a(i, j, f);
   }

   protected void func_73869_a(char par1, int par2) {
      if(par2 == 1 || par2 == this.field_73882_e.gameSettings.keyBindInventory.keyCode) {
         this.field_73882_e.thePlayer.closeScreen();
      }

   }
}
