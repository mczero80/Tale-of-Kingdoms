package mods.aginsun.kingdoms.client.guis;

import mods.aginsun.kingdoms.client.guis.GuiButtonShop;
import mods.aginsun.kingdoms.client.guis.GuiPriceBar;
import mods.aginsun.kingdoms.client.guis.GuiScreenToK;
import mods.aginsun.kingdoms.handlers.GoldKeeper;
import mods.aginsun.kingdoms.handlers.ResourceHandler;
import mods.aginsun.kingdoms.handlers.WorkerHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GuiLumber extends GuiScreenToK {

   private World worldObj;
   public EntityPlayer player;
   public GoldKeeper gold;
   boolean goldchecker = false;
   private GuiPriceBar bar;


   public GuiLumber(EntityPlayer entityplayer1, World world) {
      this.player = entityplayer1;
      this.worldObj = world;
   }

   public void func_73866_w_() {
      this.bar = new GuiPriceBar(0, this.field_73880_f / 2 - 100, 40, 90, 12, 1.0F, "red");
      this.bar.setBar((float)ResourceHandler.getInstance().getWoodPool() / 320.0F);
      this.field_73887_h.add(new GuiButton(1, this.field_73880_f / 2 - 100, 75, 90, 20, "Collect 64"));
      this.field_73887_h.add(new GuiButton(2, this.field_73880_f / 2 - 100, 95, 90, 20, "Buy Worker."));
      this.field_73887_h.add(new GuiButton(3, this.field_73880_f / 2 - 100, 115, 90, 20, "Cancel."));
   }

   protected void func_73875_a(GuiButton guibutton) {
      if(guibutton.id == 1 && ResourceHandler.getInstance().getWoodPool() >= 64) {
         ItemStack itemstack = new ItemStack(17, 64, 0);
         EntityItem entityitem = new EntityItem(this.worldObj, this.player.field_70165_t, this.player.field_70163_u, this.player.field_70161_v, itemstack);
         this.worldObj.spawnEntityInWorld(entityitem);
         this.goldchecker = false;
         ResourceHandler.getInstance().decreaseWoodPool(64);
      } else if(!this.worldObj.isRemote) {
         this.player.addChatMessage("Foreman: Come back again later when we have the resources.");
      }

      if(guibutton.id == 2) {
         if(GoldKeeper.getGoldTotal() >= 1500) {
            if(WorkerHandler.getInstance().getLumberMembers() < 12) {
               WorkerHandler.getInstance().addLumberMember();
               GoldKeeper.decreaseGold(1500);
               if(!this.worldObj.isRemote) {
                  this.player.addChatMessage("Foreman: He will begin to work immediately.");
               }
            } else if(!this.worldObj.isRemote) {
               this.player.addChatMessage("Foreman: We have reached the capacity of men. Hire civilian workers instead.");
            }
         } else {
            this.goldchecker = true;
         }
      }

      if(guibutton.id == 3) {
         this.field_73882_e.displayGuiScreen((GuiScreen)null);
      }

   }

   public void func_73863_a(int i, int j, float f) {
      for(int k = 0; k < this.field_73887_h.size(); ++k) {
         if(this.field_73887_h.get(k) instanceof GuiButtonShop) {
            GuiButtonShop guibutton = (GuiButtonShop)this.field_73887_h.get(k);
            guibutton.func_73737_a(this.field_73882_e, i, j);
         }

         GuiButton var6 = (GuiButton)this.field_73887_h.get(k);
         var6.drawButton(this.field_73882_e, i, j);
      }

      if(!this.goldchecker) {
         this.func_73732_a(this.field_73886_k, "Foreman Menu - Total Money: " + GoldKeeper.getGoldTotal() + " Gold Coins", this.field_73880_f / 2, 15, 16777215);
      } else {
         this.func_73732_a(this.field_73886_k, "Foreman Menu - Total Money: " + GoldKeeper.getGoldTotal() + " Gold Coins - NOT ENOUGH GOLD", this.field_73880_f / 2, 15, 16777215);
      }

      this.func_73731_b(this.field_73886_k, "-Resources-    Note: Worker cost 1500 gold coins.", this.field_73880_f / 2 - 87, 55, 16777215);
      this.bar.drawBar();
   }
}
