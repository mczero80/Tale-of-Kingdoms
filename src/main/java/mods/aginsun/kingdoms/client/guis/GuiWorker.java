package mods.aginsun.kingdoms.client.guis;

import mods.aginsun.kingdoms.entities.EntityWorkerMember;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public final class GuiWorker extends GuiScreenToK {

   public EntityPlayer entityplayer;
   private EntityWorkerMember member;


   public GuiWorker(EntityPlayer entityplayer1, World world, EntityWorkerMember entityworkermember) {
      this.entityplayer = entityplayer1;
      this.member = entityworkermember;
   }

   public void initGui() {
      this.buttonList.add(new GuiButton(1, this.width / 2 - 100, 75, 90, 20, "WoodCutting."));
      this.buttonList.add(new GuiButton(2, this.width / 2 - 100, 95, 90, 20, "Mining."));
      this.buttonList.add(new GuiButton(3, this.width / 2 - 100, 115, 90, 20, "Cancel."));
   }

   protected void actionPerformed(GuiButton guibutton) {
      EntityWorkerMember var10000;
      if(guibutton.id == 1) {
         this.entityplayer.addChatMessage(new ChatComponentText("Worker: Let us go woodcutting!"));
         var10000 = this.member;
         EntityWorkerMember.defaultHeldItem = new ItemStack(Items.iron_axe, 1);
         this.member.worktype = 1;
         this.member.follow = true;
         this.mc.displayGuiScreen((GuiScreen)null);
      }

      if(guibutton.id == 2) {
         this.entityplayer.addChatMessage(new ChatComponentText("Worker: Let us go mine stone!"));
         var10000 = this.member;
         EntityWorkerMember.defaultHeldItem = new ItemStack(Items.iron_pickaxe, 1);
         this.member.worktype = 2;
         this.member.follow = true;
         this.mc.displayGuiScreen((GuiScreen)null);
      }

      if(guibutton.id == 3) {
         this.mc.displayGuiScreen((GuiScreen)null);
      }

   }

   public void drawScreen(int i, int j, float f) {
      for(int k = 0; k < this.buttonList.size(); ++k) {
         GuiButton guibutton = (GuiButton)this.buttonList.get(k);
         guibutton.drawButton(this.mc, i, j);
      }

      this.drawString(this.fontRendererObj, "Worker Menu", this.width / 2 - 100, 60, 16777215);
   }
}
