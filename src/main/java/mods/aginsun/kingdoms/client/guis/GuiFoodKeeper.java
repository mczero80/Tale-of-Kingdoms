package mods.aginsun.kingdoms.client.guis;

import mods.aginsun.kingdoms.client.guis.GuiScreenToK;
import mods.aginsun.kingdoms.handlers.GoldKeeper;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GuiFoodKeeper extends GuiScreenToK {

   private World worldObj;
   public EntityPlayer entityplayer;
   boolean goldchecker = false;
   public boolean freebread = true;
   public Item item;


   public GuiFoodKeeper(EntityPlayer entityplayer1, World world) {
      this.entityplayer = entityplayer1;
      this.worldObj = world;
   }

   public void func_73866_w_() {
      this.field_73887_h.clear();
      this.field_73887_h.add(new GuiButton(2, this.field_73880_f / 2 + 110, 160, 120, 20, "Give me some bread!"));
      this.field_73887_h.add(new GuiButton(3, this.field_73880_f / 2 + 110, 180, 120, 20, "Exit."));
   }

   public void func_73875_a(GuiButton guibutton) {
      if(guibutton.id == 1) {
         this.func_73866_w_();
      }

      if(guibutton.id == 2) {
         if(this.freebread) {
            if(!this.worldObj.isRemote) {
               this.entityplayer.addChatMessage("Farmer: Here, take a bread!");
            }

            ItemStack itemstack = new ItemStack(297, 1, 0);
            EntityItem entityitem = new EntityItem(this.worldObj, this.entityplayer.field_70165_t, this.entityplayer.field_70163_u, this.entityplayer.field_70161_v, itemstack);
            this.worldObj.spawnEntityInWorld(entityitem);
            this.freebread = false;
         } else if(!this.worldObj.isRemote) {
            this.entityplayer.addChatMessage("Farmer: You got your bread for now!");
         }
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

      if(this.goldchecker) {
         this.func_73732_a(this.field_73886_k, "The Guild Order  Total Money: " + GoldKeeper.getGoldTotal() + " Gold Coins - NOT ENOUGH GOLD", this.field_73880_f / 2, 20, 16772608);
      } else {
         this.func_73732_a(this.field_73886_k, "Total Money: " + GoldKeeper.getGoldTotal() + " Gold Coins", this.field_73880_f / 2, 0, 16772608);
      }

   }

   protected void func_73869_a(char par1, int par2) {
      if(par2 == 1 || par2 == this.field_73882_e.gameSettings.keyBindInventory.keyCode) {
         this.field_73882_e.thePlayer.closeScreen();
      }

   }
}
