package mods.aginsun.kingdoms.client.guis;

import mods.aginsun.kingdoms.client.guis.GuiPriceBar;
import mods.aginsun.kingdoms.client.guis.GuiScreenToK;
import mods.aginsun.kingdoms.entities.EntityHired;
import mods.aginsun.kingdoms.handlers.GoldKeeper;
import mods.aginsun.kingdoms.handlers.HunterKeeper;
import mods.aginsun.kingdoms.handlers.WorthyKeeper;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GuiHunter extends GuiScreenToK {

   private World worldObj;
   public EntityPlayer player;
   boolean goldchecker = false;
   private GuiPriceBar worthness;
   private float worthyness;


   public GuiHunter(EntityPlayer player, World world) {
      this.player = player;
      this.worthyness = WorthyKeeper.getInstance().getWorthy();
      this.worldObj = world;
   }

   public void func_73866_w_() {
      this.field_73887_h.clear();
      if(!HunterKeeper.getInstance().getHunter()) {
         this.field_73887_h.add(new GuiButton(1, this.field_73880_f / 2 + 110, 140, 100, 20, "Sign contract!"));
      } else {
         this.field_73887_h.add(new GuiButton(1, this.field_73880_f / 2 + 110, 140, 100, 20, "discard contract!"));
      }

      this.field_73887_h.add(new GuiButton(2, this.field_73880_f / 2 + 110, 160, 100, 20, "Hire Hunters"));
      this.field_73887_h.add(new GuiButton(4, this.field_73880_f / 2 + 110, 180, 100, 20, "Fix the Guild"));
      this.field_73887_h.add(new GuiButton(5, this.field_73880_f / 2 + 110, 200, 100, 20, "Retire Hunters"));
      this.field_73887_h.add(new GuiButton(3, this.field_73880_f / 2 + 110, 220, 100, 20, "Exit"));
      this.worthness = new GuiPriceBar(0, this.field_73880_f / 2 + 110, 120, 125, 12, 1.0F, "red");
      this.worthness.setBar(this.worthyness / 10000.0F);
   }

   protected void func_73875_a(GuiButton guibutton) {
      if(guibutton.id == 1) {
         if(!HunterKeeper.getInstance().getHunter()) {
            this.player.addChatMessage("Guild Master: You are now one of us my friend. Kill monsters and you will soon be worthy of your title.");
         } else {
            this.player.addChatMessage("Why does one wanna do this :P");
         }

         this.func_73866_w_();
      } else if(guibutton.id == 1) {
         if(!this.worldObj.isRemote) {
            this.player.addChatMessage("Guild Master: We will await your participation, hero.");
         }

         this.func_73866_w_();
      }

      this.goldchecker = false;
      if(guibutton.id == 2) {
         if(1500 <= GoldKeeper.getGoldTotal()) {
            EntityHired i = new EntityHired(this.worldObj);
            EntityList.createEntityByName("Hired", this.worldObj);
            i.func_70012_b(this.player.field_70165_t, this.player.field_70163_u, this.player.field_70161_v, 0.0F, 0.0F);
            this.worldObj.spawnEntityInWorld(i);
            GoldKeeper.decreaseGold(1500);
         } else {
            this.goldchecker = true;
         }
      }

      if(guibutton.id == 3) {
         this.field_73882_e.displayGuiScreen((GuiScreen)null);
         this.goldchecker = false;
      }

      if(guibutton.id == 4) {
         InventoryPlayer var6 = this.player.inventory;
         boolean entity = false;
         if(var6.hasItem(17)) {
            for(int entityhired = 0; entityhired < var6.mainInventory.length; ++entityhired) {
               if(var6.mainInventory[entityhired] != null && var6.mainInventory[entityhired].itemID == 17) {
                  ItemStack itemstack = var6.getStackInSlot(entityhired);
                  if(itemstack.stackSize == itemstack.getMaxStackSize() && !entity) {
                     var6.setInventorySlotContents(entityhired, (ItemStack)null);
                     entity = true;
                  }
               }
            }
         }

         if(!entity) {
            if(!this.worldObj.isRemote) {
               this.player.addChatMessage("Guild Master: We need more resources.");
            }
         } else if(!this.worldObj.isRemote) {
            this.player.addChatMessage("Guild Master: The guild has been fixed. Thank you.");
         }
      }

      if(guibutton.id == 5 && !this.worldObj.loadedEntityList.isEmpty()) {
         for(int var7 = 0; var7 < this.worldObj.loadedEntityList.size(); ++var7) {
            Entity var8 = (Entity)this.worldObj.loadedEntityList.get(var7);
            if(var8 instanceof EntityHired) {
               EntityHired var9 = (EntityHired)var8;
               var9.func_70106_y();
               GoldKeeper.addGold(1000);
            }
         }
      }

   }

   public void func_73874_b() {
      if(!this.worldObj.isRemote) {
         this.player.addChatMessage("Guild Master: Good Hunting.");
      }

   }

   public void func_73863_a(int i, int j, float f) {
      for(int k = 0; k < this.field_73887_h.size(); ++k) {
         GuiButton guibutton = (GuiButton)this.field_73887_h.get(k);
         guibutton.drawButton(this.field_73882_e, i, j);
      }

      this.func_73732_a(this.field_73886_k, "Path to Kingship", this.field_73880_f / 2 + 100, 110, 11158783);
      if(this.goldchecker) {
         this.func_73732_a(this.field_73886_k, "The Guild Order  Total Money: " + GoldKeeper.getGoldTotal() + " Gold Coins - NOT ENOUGH GOLD", this.field_73880_f / 2, 20, 16772608);
      } else {
         this.func_73732_a(this.field_73886_k, "The Guild Order  Total Money: " + GoldKeeper.getGoldTotal() + " Gold Coins", this.field_73880_f / 2, 0, 16772608);
      }

      this.func_73732_a(this.field_73886_k, "Note: Hiring Cost 1500 gold, Retiring will Refund 1000. Fixing the Guild need 64 wood.", this.field_73880_f / 2, 10, 16772608);
      this.worthness.drawBar();
   }
}
