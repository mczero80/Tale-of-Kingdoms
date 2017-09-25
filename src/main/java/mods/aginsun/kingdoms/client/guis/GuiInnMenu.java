package mods.aginsun.kingdoms.client.guis;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public final class GuiInnMenu extends GuiScreenToK {

   private World worldObj;
   public EntityPlayer player;
   boolean goldchecker = false;
   boolean screenpause = false;
   boolean isResting = false;


   public GuiInnMenu(EntityPlayer player, World world) {
      this.player = player;
      this.worldObj = world;
   }

   public void initGui() {
      if(!this.isResting) {
         this.buttonList.clear();
         this.buttonList.add(new GuiButton(1, this.width / 2 + 110, 180, 100, 20, "Rest in a room."));
         this.buttonList.add(new GuiButton(2, this.width / 2 + 110, 200, 100, 20, "Wait for night time."));
         this.buttonList.add(new GuiButton(3, this.width / 2 + 110, 220, 100, 20, "Exit"));
      } else if(this.isResting) {
         this.buttonList.clear();
         this.buttonList.add(new GuiButton(1, this.width / 2 + 110, 220, 70, 20, "Wake Up."));
      }

   }

   protected void actionPerformed(GuiButton guibutton) {
      if(guibutton.id == 1) {
         if(!this.isResting) {
            this.screenpause = true;
            this.isResting = true;
            this.player.heal(20.0F);
            long l = this.worldObj.getWorldInfo().getWorldTime() + 24000L;
            this.worldObj.getWorldInfo().setWorldTime(l - l % 24000L);
            this.initGui();
         } else {
            this.screenpause = false;
            this.isResting = false;
            this.initGui();
         }
      }

      if(guibutton.id == 2) {
         if(!this.isResting) {
            this.screenpause = true;
            this.isResting = true;
            this.player.heal(20.0F);
            this.worldObj.getWorldInfo().setWorldTime(14000L);
            this.initGui();
         } else {
            this.screenpause = false;
            this.isResting = false;
            this.initGui();
         }
      }

      if(guibutton.id == 3) {
         this.mc.displayGuiScreen(null);
         this.goldchecker = false;
      }

   }

   public boolean doesGuiPauseGame() {
      return this.screenpause;
   }

   public void onGuiClosed() {
      if(!this.worldObj.isRemote) {
         this.player.addChatMessage(new ChatComponentText("House Keeper: Have a nice day."));
      }

   }

   public void drawScreen(int i, int j, float f) {
      if(!this.isResting) {
         this.drawString(this.fontRendererObj, "Time flies when you rest..", this.width / 2, 10, 16772608);
         this.drawString(this.fontRendererObj, "Note: You could rest even in daylight but you will wake up the next day", this.width / 2, 20, 16772608);
      } else {
         this.drawDefaultBackground();
         this.drawString(this.fontRendererObj, "Resting..", this.width / 2, this.height / 2 - 20, 16772608);
      }

      for(int k = 0; k < this.buttonList.size(); ++k) {
         GuiButton guibutton = (GuiButton)this.buttonList.get(k);
         guibutton.drawButton(this.mc, i, j);
      }

   }
}
