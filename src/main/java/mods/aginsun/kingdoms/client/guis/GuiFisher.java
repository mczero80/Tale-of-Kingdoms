package mods.aginsun.kingdoms.client.guis;

import java.awt.Color;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

public class GuiFisher extends GuiScreen {

   EntityPlayer player;


   public GuiFisher(EntityPlayer player) {
      this.player = player;
   }

   public void func_73866_w_() {
      this.field_73887_h.clear();
      this.field_73887_h.add(new GuiButton(1, this.field_73880_f / 2 - 60, this.field_73881_g / 2 - 10, 120, 20, "Get Fishing Rod!"));
      this.field_73887_h.add(new GuiButton(2, this.field_73880_f / 2 - 60, this.field_73881_g / 2 + 10, 120, 20, "Exit!"));
   }

   public void func_73875_a(GuiButton guibutton) {
      if(guibutton.id == 1) {
         this.player.func_70025_b(Item.fishingRod.field_77779_bT, 1);
         this.field_73882_e.thePlayer.func_71035_c("Here ya go, your new beautiful fishing rod!");
      }

      if(guibutton.id == 2) {
         this.field_73882_e.setIngameFocus();
      }

   }

   public void func_73863_a(int i, int j, float f) {
      this.func_73731_b(this.field_73882_e.fontRenderer, "Get your best fishing rods here lad.", this.field_73880_f / 2 - this.field_73882_e.fontRenderer.getStringWidth("Get your best fishing rods here lad.") / 2, this.field_73881_g / 2 - 30, Color.ORANGE.getRGB());
      super.drawScreen(i, j, f);
   }
}
