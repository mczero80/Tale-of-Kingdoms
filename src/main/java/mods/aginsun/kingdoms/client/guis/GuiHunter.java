package mods.aginsun.kingdoms.client.guis;

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
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public final class GuiHunter extends GuiScreenToK {

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

   public void initGui() {
      this.buttonList.clear();
      if(!HunterKeeper.getInstance().getHunter()) {
         this.buttonList.add(new GuiButton(1, this.width / 2 + 110, 140, 100, 20, "Sign contract!"));
      } else {
         this.buttonList.add(new GuiButton(1, this.width / 2 + 110, 140, 100, 20, "discard contract!"));
      }

      this.buttonList.add(new GuiButton(2, this.width / 2 + 110, 160, 100, 20, "Hire Hunters"));
      this.buttonList.add(new GuiButton(4, this.width / 2 + 110, 180, 100, 20, "Fix the Guild"));
      this.buttonList.add(new GuiButton(5, this.width / 2 + 110, 200, 100, 20, "Retire Hunters"));
      this.buttonList.add(new GuiButton(3, this.width / 2 + 110, 220, 100, 20, "Exit"));
      this.worthness = new GuiPriceBar(0, this.width / 2 + 110, 120, 125, 12, 1.0F, "red");
      this.worthness.setBar(this.worthyness / 10000.0F);
   }

   protected void actionPerformed(GuiButton guibutton) {
      if(guibutton.id == 1) {
         if(!HunterKeeper.getInstance().getHunter()) {
            this.player.addChatMessage(new ChatComponentText("Guild Master: You are now one of us my friend. Kill monsters and you will soon be worthy of your title."));
         } else {
            this.player.addChatMessage(new ChatComponentText("Why does one wanna do this :P"));
         }

         this.initGui();
      } else if(guibutton.id == 1) {
         if(!this.worldObj.isRemote) {
            this.player.addChatMessage(new ChatComponentText("Guild Master: We will await your participation, hero."));
         }

         this.initGui();
      }

      this.goldchecker = false;
      if(guibutton.id == 2) {
         if(1500 <= GoldKeeper.getGoldTotal()) {
            EntityHired i = new EntityHired(this.worldObj);
            EntityList.createEntityByName("Hired", this.worldObj);
            i.setLocationAndAngles(this.player.posX, this.player.posY, this.player.posZ, 0.0F, 0.0F);
            this.worldObj.spawnEntityInWorld(i);
            GoldKeeper.decreaseGold(1500);
         } else {
            this.goldchecker = true;
         }
      }

      if(guibutton.id == 3) {
         this.mc.displayGuiScreen((GuiScreen)null);
         this.goldchecker = false;
      }

      if(guibutton.id == 4) {
         InventoryPlayer var6 = this.player.inventory;
         boolean entity = false;
         if(var6.hasItem(Item.getItemFromBlock(Blocks.log))) {
            for(int entityhired = 0; entityhired < var6.mainInventory.length; ++entityhired) {
               if(var6.mainInventory[entityhired] != null && var6.mainInventory[entityhired].getItem() == Item.getItemFromBlock(Blocks.log)) {
                  ItemStack itemstack = var6.getStackInSlot(entityhired);
                  if(itemstack.stackSize == itemstack.getMaxStackSize() && !entity) {
                     var6.setInventorySlotContents(entityhired, null);
                     entity = true;
                  }
               }
            }
         }

         if(!entity) {
            if(!this.worldObj.isRemote) {
               this.player.addChatMessage(new ChatComponentText("Guild Master: We need more resources."));
            }
         } else if(!this.worldObj.isRemote) {
            this.player.addChatMessage(new ChatComponentText("Guild Master: The guild has been fixed. Thank you."));
         }
      }

      if(guibutton.id == 5 && !this.worldObj.loadedEntityList.isEmpty()) {
         for(int var7 = 0; var7 < this.worldObj.loadedEntityList.size(); ++var7) {
            Entity var8 = (Entity)this.worldObj.loadedEntityList.get(var7);
            if(var8 instanceof EntityHired) {
               EntityHired var9 = (EntityHired)var8;
               var9.setDead();
               GoldKeeper.addGold(1000);
            }
         }
      }

   }

   public void onGuiClosed() {
      if(!this.worldObj.isRemote) {
         this.player.addChatMessage(new ChatComponentText("Guild Master: Good Hunting."));
      }

   }

   public void drawScreen(int i, int j, float f) {
      for(int k = 0; k < this.buttonList.size(); ++k) {
         GuiButton guibutton = (GuiButton)this.buttonList.get(k);
         guibutton.drawButton(this.mc, i, j);
      }

      this.drawString(this.fontRendererObj, "Path to Kingship", this.width / 2 + 100, 110, 11158783);
      if(this.goldchecker) {
         this.drawString(this.fontRendererObj, "The Guild Order  Total Money: " + GoldKeeper.getGoldTotal() + " Gold Coins - NOT ENOUGH GOLD", this.width / 2, 20, 16772608);
      } else {
         this.drawString(this.fontRendererObj, "The Guild Order  Total Money: " + GoldKeeper.getGoldTotal() + " Gold Coins", this.width / 2, 0, 16772608);
      }

      this.drawString(this.fontRendererObj, "Note: Hiring Cost 1500 gold, Retiring will Refund 1000. Fixing the Guild need 64 wood.", this.width / 2, 10, 16772608);
      this.worthness.drawBar();
   }
}
