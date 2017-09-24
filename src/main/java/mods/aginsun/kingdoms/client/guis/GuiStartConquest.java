package mods.aginsun.kingdoms.client.guis;

import mods.aginsun.kingdoms.client.guis.GuiScreenToK;
import mods.aginsun.kingdoms.client.guis.GuiToKLoading;
import mods.aginsun.kingdoms.handlers.Schematic;
import mods.aginsun.kingdoms.handlers.SchematicHandler;
import mods.aginsun.kingdoms.util.Buildings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;

public class GuiStartConquest extends GuiScreenToK {

   public EntityPlayer player;
   private Minecraft field_73882_e;


   public GuiStartConquest(Minecraft minecraft) {
      this.field_73882_e = minecraft;
   }

   public void func_73866_w_() {
      this.field_73887_h.clear();
      this.field_73887_h.add(new GuiButton(1, this.field_73880_f / 2 - 70, this.field_73881_g / 2, 140, 20, "Start new Conquest."));
      this.field_73887_h.add(new GuiButton(2, this.field_73880_f / 2 - 70, this.field_73881_g / 2 + 20, 140, 20, "Cancel."));
   }

   protected void func_73875_a(GuiButton guibutton) {
      if(guibutton.id == 1) {
         this.player = this.field_73882_e.thePlayer;
         Schematic schematic = (new Schematic("/mods/aginsun/kingdoms/schematics/GuildCastle")).setPosition((int)this.player.field_70165_t, (int)this.player.field_70163_u, (int)this.player.field_70161_v).setSpeed(75);
         SchematicHandler.getInstance().addBuilding(schematic);
         Buildings.setBuildingTrue(0);
         this.field_73882_e.displayGuiScreen((GuiScreen)null);
         this.field_73882_e.displayGuiScreen(new GuiToKLoading());
      }

      if(guibutton.id == 2) {
         this.field_73882_e.displayGuiScreen((GuiScreen)null);
      }

   }

   public void func_73863_a(int i, int j, float f) {
      this.func_73873_v_();
      this.func_73732_a(this.field_73886_k, "The Tale of Kingdoms ver 1.5-Pre Release", this.field_73880_f / 2, this.field_73881_g / 2 - 50, 16777215);
      this.func_73732_a(this.field_73886_k, "The Great Tides of Darkness has come.", this.field_73880_f / 2, this.field_73881_g / 2 - 40, 16777215);
      this.func_73732_a(this.field_73886_k, "The Reficules have come and it is up to you to save what is left.", this.field_73880_f / 2, this.field_73881_g / 2 - 30, 16777215);
      this.func_73732_a(this.field_73886_k, "You are the last heir to the throne. The only hope of the overworld.", this.field_73880_f / 2, this.field_73881_g / 2 - 20, 16777215);
      this.func_73732_a(this.field_73886_k, "Your adventure starts here, hero. The Guild will prepare you.", this.field_73880_f / 2, this.field_73881_g / 2 - 10, 16777215);
      this.func_73732_a(this.field_73886_k, "Note: Build outside of the kingdom unless the location is specified for you.", this.field_73880_f / 2, this.field_73881_g / 2 + 40, 16777215);
      this.func_73732_a(this.field_73886_k, "Note: selling is done through a block next to the npcs.", this.field_73880_f / 2, this.field_73881_g / 2 + 50, 16777215);

      for(int k = 0; k < this.field_73887_h.size(); ++k) {
         GuiButton guibutton = (GuiButton)this.field_73887_h.get(k);
         guibutton.drawButton(this.field_73882_e, i, j);
      }

   }
}
