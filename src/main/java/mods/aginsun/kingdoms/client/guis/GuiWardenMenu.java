package mods.aginsun.kingdoms.client.guis;

import mods.aginsun.kingdoms.client.guis.GuiScreenToK;
import mods.aginsun.kingdoms.handlers.GoldKeeper;
import mods.aginsun.kingdoms.util.UtilToK;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

public class GuiWardenMenu extends GuiScreenToK {

   private World worldObj;
   public EntityPlayer player;
   boolean goldchecker;


   public GuiWardenMenu(EntityPlayer player, World world) {
      this.player = player;
      this.goldchecker = false;
      this.worldObj = world;
   }

   public void func_73866_w_() {
      this.field_73887_h.clear();
      this.field_73887_h.add(new GuiButton(1, this.field_73880_f / 2 + 110, 160, 100, 20, "Recruit a Knight."));
      this.field_73887_h.add(new GuiButton(2, this.field_73880_f / 2 + 110, 180, 100, 20, "Recruit an Archer."));
      this.field_73887_h.add(new GuiButton(4, this.field_73880_f / 2 + 110, 200, 100, 20, "Recall defenders"));
      this.field_73887_h.add(new GuiButton(3, this.field_73880_f / 2 + 110, 220, 100, 20, "Exit"));
   }

   protected void func_73875_a(GuiButton guibutton) {
      if(guibutton.id == 1) {
         if(1000 <= GoldKeeper.getGoldTotal()) {
            UtilToK.spawnEntity(this.worldObj, "DefendWarrior", new ChunkCoordinates((int)this.player.field_70165_t, (int)this.player.field_70163_u, (int)this.player.field_70161_v));
            GoldKeeper.decreaseGold(1000);
         } else {
            this.goldchecker = true;
         }
      }

      if(guibutton.id == 2) {
         if(1000 <= GoldKeeper.getGoldTotal()) {
            UtilToK.spawnEntity(this.worldObj, "DefendArcher", new ChunkCoordinates((int)this.player.field_70165_t, (int)this.player.field_70163_u, (int)this.player.field_70161_v));
            GoldKeeper.decreaseGold(1000);
         } else {
            this.goldchecker = true;
         }
      }

      if(guibutton.id == 3) {
         this.field_73882_e.displayGuiScreen((GuiScreen)null);
         this.goldchecker = false;
      }

   }

   public void func_73874_b() {
      this.player.addChatMessage("Warden: Good Day.");
   }

   public void func_73863_a(int i, int j, float f) {
      for(int k = 0; k < this.field_73887_h.size(); ++k) {
         GuiButton guibutton = (GuiButton)this.field_73887_h.get(k);
         guibutton.drawButton(this.field_73882_e, i, j);
      }

      if(this.goldchecker) {
         this.func_73732_a(this.field_73886_k, "Barracks Total Money: " + GoldKeeper.getGoldTotal() + " Gold Coins - NOT ENOUGH GOLD", this.field_73880_f / 2, 20, 16763904);
      } else {
         this.func_73732_a(this.field_73886_k, "Barracks  Total Money: " + GoldKeeper.getGoldTotal() + " Gold Coins", this.field_73880_f / 2, 10, 16763904);
      }

      this.func_73732_a(this.field_73886_k, "Note: The knights and archers will upgrade if they damage enough monsters!", this.field_73880_f / 2, 20, 16763904);
      this.func_73732_a(this.field_73886_k, " 1000 gold per hire.", this.field_73880_f / 2, 30, 16763904);
   }
}
