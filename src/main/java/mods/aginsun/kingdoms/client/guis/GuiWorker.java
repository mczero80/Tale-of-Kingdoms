package mods.aginsun.kingdoms.client.guis;

import mods.aginsun.kingdoms.client.guis.GuiScreenToK;
import mods.aginsun.kingdoms.entities.EntityWorkerMember;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GuiWorker extends GuiScreenToK {

   public EntityPlayer entityplayer;
   private EntityWorkerMember member;


   public GuiWorker(EntityPlayer entityplayer1, World world, EntityWorkerMember entityworkermember) {
      this.entityplayer = entityplayer1;
      this.member = entityworkermember;
   }

   public void func_73866_w_() {
      this.buttonList.add(new GuiButton(1, this.field_73880_f / 2 - 100, 75, 90, 20, "WoodCutting."));
      this.buttonList.add(new GuiButton(2, this.field_73880_f / 2 - 100, 95, 90, 20, "Mining."));
      this.buttonList.add(new GuiButton(3, this.field_73880_f / 2 - 100, 115, 90, 20, "Cancel."));
   }

   protected void func_73875_a(GuiButton guibutton) {
      EntityWorkerMember var10000;
      if(guibutton.id == 1) {
         this.entityplayer.addChatMessage("Worker: Let us go woodcutting!");
         var10000 = this.member;
         EntityWorkerMember.defaultHeldItem = new ItemStack(Item.axeIron, 1);
         this.member.worktype = 1;
         this.member.follow = true;
         this.field_73882_e.displayGuiScreen((GuiScreen)null);
      }

      if(guibutton.id == 2) {
         this.entityplayer.addChatMessage("Worker: Let us go mine stone!");
         var10000 = this.member;
         EntityWorkerMember.defaultHeldItem = new ItemStack(Item.pickaxeIron, 1);
         this.member.worktype = 2;
         this.member.follow = true;
         this.field_73882_e.displayGuiScreen((GuiScreen)null);
      }

      if(guibutton.id == 3) {
         this.field_73882_e.displayGuiScreen((GuiScreen)null);
      }

   }

   public void func_73863_a(int i, int j, float f) {
      for(int k = 0; k < this.field_73887_h.size(); ++k) {
         GuiButton guibutton = (GuiButton)this.field_73887_h.get(k);
         guibutton.drawButton(this.field_73882_e, i, j);
      }

      this.func_73731_b(this.field_73886_k, "Worker Menu", this.field_73880_f / 2 - 100, 60, 16777215);
   }
}
