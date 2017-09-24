package mods.aginsun.kingdoms.client.guis;

import java.awt.Color;
import mods.aginsun.kingdoms.handlers.GoldKeeper;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.item.Item;

public class GuiStableMaster extends GuiScreen {

   public void func_73866_w_() {
      this.field_73887_h.clear();
      this.field_73887_h.add(new GuiButton(1, this.field_73880_f / 2 - 60, this.field_73881_g / 2 - 10, 120, 20, "Buy Starter Kit!"));
      this.field_73887_h.add(new GuiButton(2, this.field_73880_f / 2 - 60, this.field_73881_g / 2 - 10, 120, 20, "Buy Expert Kit!"));
      this.field_73887_h.add(new GuiButton(3, this.field_73880_f / 2 - 60, this.field_73881_g / 2 + 10, 120, 20, "Exit!"));
   }

   public void func_73875_a(GuiButton guibutton) {
      if(guibutton.id == 1) {
         if(GoldKeeper.getGoldTotal() >= 1500) {
            this.field_73882_e.thePlayer.func_70025_b(Item.saddle.itemID, 1);
            this.field_73882_e.thePlayer.func_70025_b(Item.leash.itemID, 1);
            this.field_73882_e.thePlayer.func_70025_b(Item.wheat.itemID, 15);
            this.field_73882_e.thePlayer.func_70025_b(Item.nameTag.itemID, 1);
         } else {
            this.field_73882_e.thePlayer.func_71035_c("You don\'t have enough money!");
         }
      } else if(guibutton.id == 2) {
         if(GoldKeeper.getGoldTotal() >= 7500) {
            this.field_73882_e.thePlayer.func_70025_b(Item.horseArmorDiamond.itemID, 1);
            this.field_73882_e.thePlayer.func_70025_b(Item.wheat.itemID, 64);
            EntityHorse entity = new EntityHorse(this.field_73882_e.theWorld);
            entity.setHorseType(0);
            this.field_73882_e.theWorld.spawnEntityInWorld(entity);
         } else {
            this.field_73882_e.thePlayer.func_71035_c("You don\'t have enough money!");
         }
      } else if(guibutton.id == 3) {
         this.field_73882_e.setIngameFocus();
      }

   }

   public void func_73863_a(int i, int j, float f) {
      String s = "Starter Kit: Saddle, lead, wheat and a name tag for the price of 1500 gold!";
      String s1 = "Expert Kit: Horse armor, a stack of wheat and a horse for the low price of 7500 gold!";
      this.func_73731_b(this.field_73882_e.fontRenderer, s, this.field_73880_f / 2 - this.field_73882_e.fontRenderer.getStringWidth(s) / 2, this.field_73881_g / 2 - 30, Color.ORANGE.getRGB());
      this.func_73731_b(this.field_73882_e.fontRenderer, s, this.field_73880_f / 2 - this.field_73882_e.fontRenderer.getStringWidth(s1) / 2, this.field_73881_g / 2 - 30, Color.ORANGE.getRGB());
   }
}
