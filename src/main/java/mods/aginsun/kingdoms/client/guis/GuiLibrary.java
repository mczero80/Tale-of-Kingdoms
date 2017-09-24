package mods.aginsun.kingdoms.client.guis;

import mods.aginsun.kingdoms.client.guis.GuiScreenToK;
import mods.aginsun.kingdoms.entities.EntityLibraryKeeper;
import mods.aginsun.kingdoms.handlers.GoldKeeper;
import mods.aginsun.kingdoms.util.UtilToK;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiLibrary extends GuiScreenToK {

   private World worldObj;
   public EntityPlayer entityplayer;
   boolean goldchecker = false;
   private EntityLibraryKeeper get;


   public GuiLibrary(EntityPlayer entityplayer1, World world, EntityLibraryKeeper entitylibrarykeeper) {
      this.entityplayer = entityplayer1;
      this.worldObj = world;
      this.get = entitylibrarykeeper;
   }

   public void func_73866_w_() {
      this.field_73887_h.clear();
      this.field_73887_h.add(new GuiButton(1, this.field_73880_f / 2 - 70, 80, 140, 20, "Study in the library"));
      this.field_73887_h.add(new GuiButton(2, this.field_73880_f / 2 - 70, 100, 140, 20, "Invest for the library"));
      this.field_73887_h.add(new GuiButton(4, this.field_73880_f / 2 - 70, 120, 140, 20, "Research on Excalibur"));
      this.field_73887_h.add(new GuiButton(3, this.field_73880_f / 2 - 70, 140, 140, 20, "Exit"));
   }

   protected void func_73875_a(GuiButton guibutton) {
      if(guibutton.id == 1) {
         if(this.get.studied) {
            this.worldObj.spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.entityplayer.field_70165_t, this.entityplayer.field_70163_u, this.entityplayer.field_70161_v, 150));
            if(!this.worldObj.isRemote) {
               this.entityplayer.addChatMessage("You have gained experience.");
            }

            this.get.studied = false;
         } else if(!this.worldObj.isRemote) {
            this.entityplayer.addChatMessage("You have already studied for a while, go back in a few moments");
         }

         this.goldchecker = false;
      }

      if(guibutton.id == 2) {
         if(500 + UtilToK.libraryInvestment * 2 <= GoldKeeper.getGoldTotal()) {
            UtilToK.libraryInvestment += 5;
            if(!this.worldObj.isRemote) {
               this.entityplayer.addChatMessage("Tax is now increased by " + UtilToK.libraryInvestment + " gold per house.");
            }

            GoldKeeper.decreaseGold(500 + UtilToK.libraryInvestment * 2);
         } else {
            this.goldchecker = true;
         }
      }

      if(guibutton.id == 3) {
         this.field_73882_e.displayGuiScreen((GuiScreen)null);
         this.goldchecker = false;
      }

      if(guibutton.id == 4) {
         ;
      }

   }

   public void func_73874_b() {
      if(!this.worldObj.isRemote) {
         this.entityplayer.addChatMessage("Librarian: I will see you again hero.");
      }

   }

   public void func_73863_a(int i, int j, float f) {
      for(int k = 0; k < this.field_73887_h.size(); ++k) {
         GuiButton guibutton = (GuiButton)this.field_73887_h.get(k);
         guibutton.drawButton(this.field_73882_e, i, j);
      }

      if(this.goldchecker) {
         this.func_73732_a(this.field_73886_k, "The Library Total Money: " + GoldKeeper.getGoldTotal() + " Gold Coins - NOT ENOUGH GOLD", this.field_73880_f / 2, 20, 16777215);
      } else {
         this.func_73732_a(this.field_73886_k, "The Library  Total Money: " + GoldKeeper.getGoldTotal() + " Gold Coins", this.field_73880_f / 2, 20, 16777215);
      }

      this.func_73732_a(this.field_73886_k, "Note: The more you invest, the more knowledge people gain to yield more tax.", this.field_73880_f / 2, 170, 16777215);
   }
}
