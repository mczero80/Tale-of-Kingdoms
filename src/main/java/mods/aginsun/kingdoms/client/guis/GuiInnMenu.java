package mods.aginsun.kingdoms.client.guis;

import mods.aginsun.kingdoms.client.guis.GuiScreenToK;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiInnMenu extends GuiScreenToK {

   private World worldObj;
   public EntityPlayer player;
   boolean goldchecker = false;
   boolean screenpause = false;
   boolean isResting = false;


   public GuiInnMenu(EntityPlayer player, World world) {
      this.player = player;
      this.worldObj = world;
   }

   public void func_73866_w_() {
      if(!this.isResting) {
         this.field_73887_h.clear();
         this.field_73887_h.add(new GuiButton(1, this.field_73880_f / 2 + 110, 180, 100, 20, "Rest in a room."));
         this.field_73887_h.add(new GuiButton(2, this.field_73880_f / 2 + 110, 200, 100, 20, "Wait for night time."));
         this.field_73887_h.add(new GuiButton(3, this.field_73880_f / 2 + 110, 220, 100, 20, "Exit"));
      } else if(this.isResting) {
         this.field_73887_h.clear();
         this.field_73887_h.add(new GuiButton(1, this.field_73880_f / 2 + 110, 220, 70, 20, "Wake Up."));
      }

   }

   protected void func_73875_a(GuiButton guibutton) {
      if(guibutton.id == 1) {
         if(!this.isResting) {
            this.screenpause = true;
            this.isResting = true;
            this.player.func_70691_i(20.0F);
            long l = this.worldObj.getWorldInfo().getWorldTime() + 24000L;
            this.worldObj.getWorldInfo().setWorldTime(l - l % 24000L);
            this.func_73866_w_();
         } else {
            this.screenpause = false;
            this.isResting = false;
            this.func_73866_w_();
         }
      }

      if(guibutton.id == 2) {
         if(!this.isResting) {
            this.screenpause = true;
            this.isResting = true;
            this.player.func_70691_i(20.0F);
            this.worldObj.getWorldInfo().setWorldTime(14000L);
            this.func_73866_w_();
         } else {
            this.screenpause = false;
            this.isResting = false;
            this.func_73866_w_();
         }
      }

      if(guibutton.id == 3) {
         this.field_73882_e.displayGuiScreen((GuiScreen)null);
         this.goldchecker = false;
      }

   }

   public boolean func_73868_f() {
      return this.screenpause;
   }

   public void func_73874_b() {
      if(!this.worldObj.isRemote) {
         this.player.addChatMessage("House Keeper: Have a nice day.");
      }

   }

   public void func_73863_a(int i, int j, float f) {
      if(!this.isResting) {
         this.func_73732_a(this.field_73886_k, "Time flies when you rest..", this.field_73880_f / 2, 10, 16772608);
         this.func_73732_a(this.field_73886_k, "Note: You could rest even in daylight but you will wake up the next day", this.field_73880_f / 2, 20, 16772608);
      } else {
         this.func_73873_v_();
         this.func_73732_a(this.field_73886_k, "Resting..", this.field_73880_f / 2, this.field_73881_g / 2 - 20, 16772608);
      }

      for(int k = 0; k < this.field_73887_h.size(); ++k) {
         GuiButton guibutton = (GuiButton)this.field_73887_h.get(k);
         guibutton.drawButton(this.field_73882_e, i, j);
      }

   }
}
