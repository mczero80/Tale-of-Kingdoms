package mods.aginsun.kingdoms.client.guis;

import java.util.Random;
import mods.aginsun.kingdoms.client.guis.GuiScreenToK;
import mods.aginsun.kingdoms.handlers.GoldKeeper;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class GuiTavernGame extends GuiScreenToK {

   private EntityPlayer entityplayer;
   private Integer dealer;
   private Integer player;
   private int fRollp = 0;
   private int sRollp = 0;
   private int fRolld = 0;
   private int sRolld = 0;
   private boolean dealerR = false;
   private boolean playerR = false;
   private boolean game = false;
   private int wintype = 0;
   private boolean gameNotEnded = true;
   private boolean rolledDouble = true;
   private boolean notEnough = false;


   public GuiTavernGame(EntityPlayer entityplayer1, World world) {
      this.entityplayer = entityplayer1;
   }

   public boolean deal(int i) {
      Random random = new Random();
      if(i == 0) {
         GoldKeeper.decreaseGold(50);
         this.fRollp = random.nextInt(6) + 1;
         this.fRolld = random.nextInt(6) + 1;
         this.sRollp = random.nextInt(6) + 1;
         this.sRolld = random.nextInt(6) + 1;
         this.player = Integer.valueOf(this.fRollp + this.sRollp);
         this.dealer = Integer.valueOf(this.fRolld + this.sRolld);
         if(this.fRollp == this.sRollp) {
            this.wintype = 4;
            this.gameNotEnded = false;
            this.rolledDouble = false;
            GoldKeeper.addGold(100);
            this.func_73866_w_();
         }

         if(this.fRolld == this.sRolld) {
            this.wintype = 5;
            this.gameNotEnded = false;
            this.rolledDouble = false;
            this.func_73866_w_();
         }
      }

      if(i == 1) {
         if(this.dealer.intValue() < this.player.intValue()) {
            if(this.fRolld > this.sRolld) {
               this.sRolld = random.nextInt(6) + 1;
            }

            if(this.sRolld > this.fRolld) {
               this.fRolld = random.nextInt(6) + 1;
            }

            this.dealer = Integer.valueOf(this.fRolld + this.sRolld);
            this.dealerR = true;
            if(this.fRolld == this.sRolld) {
               this.wintype = 5;
               this.gameNotEnded = false;
               this.rolledDouble = false;
               this.func_73866_w_();
            }
         }

         return false;
      } else if(i == 2 && this.player.intValue() > this.dealer.intValue()) {
         GoldKeeper.addGold(75);
         return true;
      } else {
         if(i == 3) {
            if(this.player.intValue() < this.dealer.intValue()) {
               return true;
            }

            GoldKeeper.decreaseGold(25);
         }

         if(i == 4) {
            this.fRollp = random.nextInt(6) + 1;
            this.player = Integer.valueOf(this.fRollp + this.sRollp);
            if(this.fRollp == this.sRollp) {
               this.wintype = 4;
               GoldKeeper.addGold(100);
               this.gameNotEnded = false;
               this.rolledDouble = false;
               this.func_73866_w_();
            }
         }

         if(i == 5) {
            this.sRollp = random.nextInt(6) + 1;
            this.player = Integer.valueOf(this.fRollp + this.sRollp);
            if(this.fRollp == this.sRollp) {
               this.wintype = 4;
               GoldKeeper.addGold(100);
               this.gameNotEnded = false;
               this.rolledDouble = false;
               this.func_73866_w_();
            }
         }

         return false;
      }
   }

   public void func_73866_w_() {
      this.field_73887_h.clear();
      if(!this.game) {
         this.field_73887_h.add(new GuiButton(1, this.field_73880_f / 2 - 50, this.field_73881_g / 2, 100, 20, "Roll the Die!"));
         if(this.gameNotEnded) {
            this.field_73887_h.add(new GuiButton(6, this.field_73880_f / 2 - 50, this.field_73881_g / 2 + 20, 100, 20, "Exit"));
         }
      }

      if(this.game) {
         if(!this.playerR && this.rolledDouble) {
            this.field_73887_h.add(new GuiButton(2, this.field_73880_f / 2 - 105, this.field_73881_g / 2 + 80, 100, 20, "Re-roll Die 1"));
            this.field_73887_h.add(new GuiButton(3, this.field_73880_f / 2 + 5, this.field_73881_g / 2 + 80, 100, 20, "Re-roll Die 2"));
         }

         if(this.gameNotEnded) {
            this.field_73887_h.add(new GuiButton(4, this.field_73880_f / 2 - 50, this.field_73881_g / 2 + 30, 100, 20, "Stay"));
         }

         if(!this.gameNotEnded) {
            this.field_73887_h.add(new GuiButton(5, this.field_73880_f / 2 - 50, this.field_73881_g / 2 + 30, 100, 20, "Exit"));
         }
      }

   }

   public void func_73874_b() {
      this.entityplayer.addChatMessage("One-Eyed Gambler: Just come by if your feeling a bit... lucky.");
   }

   protected void func_73875_a(GuiButton guibutton) {
      if(guibutton.id == 1) {
         if(GoldKeeper.getGoldTotal() >= 50) {
            this.game = true;
            this.deal(0);
         } else {
            this.notEnough = true;
         }

         this.func_73866_w_();
      }

      if(guibutton.id == 2) {
         this.deal(4);
         this.playerR = true;
         this.func_73866_w_();
      }

      if(guibutton.id == 3) {
         this.deal(5);
         this.playerR = true;
         this.func_73866_w_();
      }

      if(guibutton.id == 4) {
         this.deal(1);
         if(this.deal(2)) {
            this.wintype = 2;
         }

         if(this.deal(3)) {
            this.wintype = 1;
         }

         if(this.dealer == this.player) {
            this.wintype = 3;
         }

         this.gameNotEnded = false;
         this.func_73866_w_();
      }

      if(guibutton.id == 5) {
         this.field_73882_e.displayGuiScreen((GuiScreen)null);
      }

      if(guibutton.id == 6) {
         this.field_73882_e.displayGuiScreen((GuiScreen)null);
      }

   }

   public void func_73863_a(int i, int j, float f) {
      this.func_73873_v_();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      ResourceLocation resource = new ResourceLocation("taleofkingdoms", "textures/guis/round.png");
      this.field_73882_e.renderEngine.bindTexture(resource);
      short c = 176;
      short c1 = 166;
      int l = (this.field_73880_f - c) / 2;
      int i1 = (this.field_73881_g - c1) / 2;
      this.func_73729_b(l, i1, 0, 0, c, c1);
      if(this.game) {
         this.func_73732_a(this.field_73886_k, "Player: " + this.player, this.field_73880_f / 2 - 30, i1 + 40, 16777215);
         this.func_73732_a(this.field_73886_k, "Die one: " + this.fRollp, this.field_73880_f / 2 - 30, i1 + 60, 16777215);
         this.func_73732_a(this.field_73886_k, "Die two: " + this.sRollp, this.field_73880_f / 2 - 30, i1 + 80, 16777215);
         this.func_73732_a(this.field_73886_k, "Dealer: " + this.dealer, this.field_73880_f / 2 + 30, i1 + 40, 16777215);
         this.func_73732_a(this.field_73886_k, "Die one: " + this.fRolld, this.field_73880_f / 2 + 30, i1 + 60, 16777215);
         this.func_73732_a(this.field_73886_k, "Die two: " + this.sRolld, this.field_73880_f / 2 + 30, i1 + 80, 16777215);
      }

      if(!this.game) {
         this.func_73732_a(this.field_73886_k, "Play Round Robin!", this.field_73880_f / 2, i1 + 50, 16777215);
         this.func_73732_a(this.field_73886_k, "50 gold coins to play!", this.field_73880_f / 2, i1 + 70, 16777215);
      }

      if(this.wintype == 1) {
         this.func_73732_a(this.field_73886_k, "You Lose!-Outnumbered the Player", this.field_73880_f / 2, this.field_73881_g / 2 - 95, 16777215);
      }

      if(this.wintype == 2) {
         this.func_73732_a(this.field_73886_k, "You Win!-Outnumbered the Dealer", this.field_73880_f / 2, this.field_73881_g / 2 - 95, 16777215);
      }

      if(this.wintype == 3) {
         this.func_73732_a(this.field_73886_k, "Draw!-Same number rolled!", this.field_73880_f / 2, this.field_73881_g / 2 - 80, 16777215);
      }

      if(this.wintype == 4) {
         this.func_73732_a(this.field_73886_k, "You win!-Player Rolled a Double!", this.field_73880_f / 2, this.field_73881_g / 2 - 95, 16777215);
      }

      if(this.wintype == 5) {
         this.func_73732_a(this.field_73886_k, "You Lose!-Dealer Rolled a Double!", this.field_73880_f / 2, this.field_73881_g / 2 - 95, 16777215);
      }

      if(this.playerR) {
         this.func_73732_a(this.field_73886_k, "Player Rerolled!", this.field_73880_f / 2, this.field_73881_g / 2 + 10, 16777215);
      }

      if(this.dealerR) {
         this.func_73732_a(this.field_73886_k, "Dealer Rerolled!", this.field_73880_f / 2, this.field_73881_g / 2 + 20, 16777215);
      }

      if(!this.notEnough) {
         this.func_73732_a(this.field_73886_k, "Round Robin - Total Money: " + GoldKeeper.getGoldTotal() + " Gold Coins", this.field_73880_f / 2, 15, 16777215);
      }

      if(this.notEnough) {
         this.func_73732_a(this.field_73886_k, "Round Robin - Total Money: " + GoldKeeper.getGoldTotal() + " Gold Coins -NOT ENOUGH GOLD", this.field_73880_f / 2, 15, 16777215);
      }

      super.func_73863_a(i, j, f);
   }
}
