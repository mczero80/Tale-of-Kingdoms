package mods.aginsun.kingdoms.client.guis;

import cpw.mods.fml.common.FMLCommonHandler;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import mods.aginsun.kingdoms.client.guis.GuiScreenToK;
import mods.aginsun.kingdoms.handlers.GoldKeeper;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GuiMageHall extends GuiScreenToK {

   private World worldObj = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(0);
   public EntityPlayer entityplayer;
   boolean goldchecker = false;
   int price = 500;


   public GuiMageHall(EntityPlayer entityplayer1, World world) {
      this.entityplayer = entityplayer1;
      this.worldObj = world;
   }

   public void func_73866_w_() {
      this.field_73887_h.clear();
      this.field_73887_h.add(new GuiButton(2, this.field_73880_f / 2 + 90, 200, 125, 20, "Recruit a Mage"));
      this.field_73887_h.add(new GuiButton(3, this.field_73880_f / 2 + 90, 220, 125, 20, "Exit"));
   }

   protected void func_73875_a(GuiButton guibutton) {
      if(guibutton.id == 1) {
         ;
      }

      if(guibutton.id == 2) {
         if(2000 <= GoldKeeper.getGoldTotal()) {
            EntityLiving itemstack = (EntityLiving)EntityList.createEntityByName("DefendMage", this.worldObj);
            itemstack.func_70012_b(this.entityplayer.field_70165_t, this.entityplayer.field_70163_u, this.entityplayer.field_70161_v, 0.0F, 0.0F);
            this.worldObj.spawnEntityInWorld(itemstack);
            GoldKeeper.decreaseGold(2000);
         } else {
            this.goldchecker = true;
         }
      }

      if(guibutton.id == 3) {
         this.field_73882_e.displayGuiScreen((GuiScreen)null);
         this.goldchecker = false;
      }

      if(guibutton.id == 4) {
         ItemStack var9 = this.entityplayer.inventory.getCurrentItem();
         if(this.price <= GoldKeeper.getGoldTotal() && var9 != null) {
            Random random = new Random();
            int[] ai = new int[3];

            int k;
            for(k = 0; k < 3; ++k) {
               ai[k] = EnchantmentHelper.calcItemStackEnchantability(random, k, 30, var9);
            }

            k = random.nextInt(3);
            List list1 = EnchantmentHelper.buildEnchantmentList(random, var9, ai[k]);
            if(list1 != null) {
               Iterator iterator = list1.iterator();

               while(iterator.hasNext()) {
                  EnchantmentData enchantmentdata = (EnchantmentData)iterator.next();
                  var9.addEnchantment(enchantmentdata.enchantmentobj, enchantmentdata.enchantmentLevel);
               }

               GoldKeeper.decreaseGold(500);
            } else if(!this.worldObj.isRemote) {
               this.entityplayer.addChatMessage("Head Mage: I can\'t enchant this item.");
            }
         }
      } else {
         this.goldchecker = true;
      }

   }

   public void func_73874_b() {
      if(!this.worldObj.isRemote) {
         this.entityplayer.addChatMessage("Head Mage: Magic dwells in you.");
      }

   }

   public void func_73863_a(int i, int j, float f) {
      for(int k = 0; k < this.field_73887_h.size(); ++k) {
         GuiButton guibutton = (GuiButton)this.field_73887_h.get(k);
         guibutton.drawButton(this.field_73882_e, i, j);
      }

      this.func_73732_a(this.field_73886_k, "The Mage Hall Total Money: " + GoldKeeper.getGoldTotal() + " Gold Coins", this.field_73880_f / 2, 10, 16763904);
      if(this.goldchecker) {
         this.func_73732_a(this.field_73886_k, "Selected Item Cost to enchant: " + this.price + " - NOT ENOUGH GOLD", this.field_73880_f / 2, 30, 16763904);
      } else {
         this.func_73732_a(this.field_73886_k, "Selected Item Cost to enchant: " + this.price, this.field_73880_f / 2, 20, 16763904);
      }

      this.func_73732_a(this.field_73886_k, "Note: Recruiting a mage cost 2000", this.field_73880_f / 2, 30, 16763904);
   }
}
