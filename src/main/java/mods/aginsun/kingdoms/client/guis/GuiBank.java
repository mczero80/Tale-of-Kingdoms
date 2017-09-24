package mods.aginsun.kingdoms.client.guis;

import mods.aginsun.kingdoms.handlers.GoldKeeper;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public final class GuiBank extends GuiScreenToK {

   private World worldObj;
   public EntityPlayer entityplayer;
   boolean check = false;


   public GuiBank(EntityPlayer entityplayer1, World world) {
      this.entityplayer = entityplayer1;
      this.worldObj = world;
   }

   public void initGui() {
      this.buttonList.add(new GuiButton(1, this.width / 2 - 95, 55, 90, 20, "Deposit 1."));
      this.buttonList.add(new GuiButton(2, this.width / 2 - 95, 75, 90, 20, "Deposit 10."));
      this.buttonList.add(new GuiButton(3, this.width / 2 - 95, 95, 90, 20, "Deposit 100."));
      this.buttonList.add(new GuiButton(4, this.width / 2 - 95, 115, 90, 20, "Deposit 1000."));
      this.buttonList.add(new GuiButton(5, this.width / 2 - 95, 135, 90, 20, "Deposit 10000."));
      this.buttonList.add(new GuiButton(6, this.width / 2 - 95, 155, 90, 20, "Deposit All."));
      this.buttonList.add(new GuiButton(7, this.width / 2 + 5, 55, 90, 20, "Withdraw 1."));
      this.buttonList.add(new GuiButton(8, this.width / 2 + 5, 75, 90, 20, "Withdraw 10."));
      this.buttonList.add(new GuiButton(9, this.width / 2 + 5, 95, 90, 20, "Withdraw 100."));
      this.buttonList.add(new GuiButton(10, this.width / 2 + 5, 115, 90, 20, "Withdraw 1000."));
      this.buttonList.add(new GuiButton(11, this.width / 2 + 5, 135, 90, 20, "Withdraw 10000."));
      this.buttonList.add(new GuiButton(12, this.width / 2 + 5, 155, 90, 20, "Withdraw All."));
      this.buttonList.add(new GuiButton(13, this.width / 2 - 45, 195, 90, 20, "Cancel."));
   }

   protected void actionPerformed(GuiButton guibutton) {
      byte c3;
      if(guibutton.id == 1) {
         this.check = false;
         c3 = 1;
         if(GoldKeeper.getGoldTotal() >= c3) {
            GoldKeeper.decreaseGold(c3);
            GoldKeeper.addBankGold(c3);
         } else {
            this.check = true;
         }
      }

      if(guibutton.id == 2) {
         this.check = false;
         c3 = 10;
         if(GoldKeeper.getGoldTotal() >= c3) {
            GoldKeeper.decreaseGold(c3);
            GoldKeeper.addBankGold(c3);
         } else {
            this.check = true;
         }
      }

      if(guibutton.id == 3) {
         this.check = false;
         c3 = 100;
         if(GoldKeeper.getGoldTotal() >= c3) {
            GoldKeeper.decreaseGold(c3);
            GoldKeeper.addBankGold(c3);
         } else {
            this.check = true;
         }
      }

      short c31;
      if(guibutton.id == 4) {
         this.check = false;
         c31 = 1000;
         if(GoldKeeper.getGoldTotal() >= c31) {
            GoldKeeper.decreaseGold(c31);
            GoldKeeper.addBankGold(c31);
         } else {
            this.check = true;
         }
      }

      if(guibutton.id == 5) {
         this.check = false;
         c31 = 10000;
         if(GoldKeeper.getGoldTotal() >= c31) {
            GoldKeeper.decreaseGold(c31);
            GoldKeeper.addBankGold(c31);
         } else {
            this.check = true;
         }
      }

      if(guibutton.id == 6) {
         this.check = false;
         GoldKeeper.addBankGold(GoldKeeper.getGoldTotal());
         GoldKeeper.decreaseGold(GoldKeeper.getGoldTotal());
      }

      if(guibutton.id == 7) {
         this.check = false;
         c3 = 1;
         if(GoldKeeper.getBankGold() >= c3) {
            GoldKeeper.addGold(c3);
            GoldKeeper.decreaseBankGold(c3);
         } else {
            this.check = true;
         }
      }

      if(guibutton.id == 8) {
         this.check = false;
         c3 = 10;
         if(GoldKeeper.getBankGold() >= c3) {
            GoldKeeper.addGold(c3);
            GoldKeeper.decreaseBankGold(c3);
         } else {
            this.check = true;
         }
      }

      if(guibutton.id == 9) {
         this.check = false;
         c3 = 100;
         if(GoldKeeper.getBankGold() >= c3) {
            GoldKeeper.addGold(c3);
            GoldKeeper.decreaseBankGold(c3);
         } else {
            this.check = true;
         }
      }

      if(guibutton.id == 10) {
         this.check = false;
         c31 = 1000;
         if(GoldKeeper.getBankGold() >= c31) {
            GoldKeeper.addGold(c31);
            GoldKeeper.decreaseBankGold(c31);
         } else {
            this.check = true;
         }
      }

      if(guibutton.id == 11) {
         this.check = false;
         c31 = 10000;
         if(GoldKeeper.getBankGold() >= c31) {
            GoldKeeper.addGold(c31);
            GoldKeeper.decreaseBankGold(c31);
         } else {
            this.check = true;
         }
      }

      if(guibutton.id == 12) {
         GoldKeeper.addGold(GoldKeeper.getBankGold());
         GoldKeeper.setBankGold(0);
      }

      if(guibutton.id == 13) {
         if(!this.worldObj.isRemote) {
            this.entityplayer.addChatMessage(new ChatComponentText("Banker: I promise I won\'t spend this!"));
         }

         this.mc.displayGuiScreen(null);
      }

   }

   public void drawScreen(int i, int j, float f) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      short c = 255;
      short c1 = 255;
      ResourceLocation resource = new ResourceLocation("taleofkingdoms", "textures/guis/crafting.png");
      this.mc.renderEngine.bindTexture(resource);
      int i1 = (this.width - c) / 2;
      this.drawTexturedModalRect(i1, 0, 0, 0, c, c1);

      for(int l = 0; l < this.buttonList.size(); ++l) {
         GuiButton guibutton = (GuiButton)this.buttonList.get(l);
         guibutton.drawButton(this.mc, i, j);
      }

      this.drawCenteredString(this.fontRendererObj, "Bank Menu -", this.width / 2, 15, 16777215);
      this.drawCenteredString(this.fontRendererObj, "Total Money You Have: " + GoldKeeper.getGoldTotal() + " Gold Coins", this.width / 2, 25, 16777215);
      this.drawCenteredString(this.fontRendererObj, "Total Money in the Bank: " + GoldKeeper.getBankGold() + " Gold Coins", this.width / 2, 35, 16777215);
      if(this.check) {
         this.drawCenteredString(this.fontRendererObj, "Don\'t Have Enough Gold", this.width / 2, 45, 16777215);
      }

   }

   protected void keyTyped(char par1, int par2) {
      if(par2 == 1 || par2 == this.mc.gameSettings.keyBindInventory.getKeyCode()) {
         this.mc.thePlayer.closeScreen();
      }
   }
}
