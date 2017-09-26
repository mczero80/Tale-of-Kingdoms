package mods.aginsun.kingdoms.entities;

import mods.aginsun.kingdoms.client.guis.GuiShopList;
import mods.aginsun.kingdoms.entities.api.EntityNPC;
import mods.aginsun.kingdoms.handlers.GoldKeeper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.src.ModLoader;
import net.minecraft.util.StringTranslate;
import net.minecraft.world.World;

public class EntityWeaponKeeper extends EntityNPC {

   private World field_70170_p;
   private Integer[] itemget = new Integer[200];
   private GoldKeeper gold;
   private StringTranslate st = new StringTranslate();


   public EntityWeaponKeeper(World world) {
      super(world, null, 100.0F);
      this.field_70170_p = world;
      this.field_70178_ae = false;
   }

   protected boolean func_70780_i() {
      return true;
   }

   public boolean func_70104_M() {
      return false;
   }

   public boolean func_70085_c(EntityPlayer entityplayer) {
      if(this.canInteractWith(entityplayer)) {
         this.heal(100.0F);
         Minecraft minecraft = Minecraft.getMinecraft();
         int i = 0;
         int j = 0;
         String s = "";

         for(int k = 256; k < 32000; ++k) {
            boolean flag2 = false;
            boolean flag3 = true;
            if(Item.itemsList[k] != null) {
               ItemStack itemstack = new ItemStack(Item.itemsList[k].itemID, 1, 0);
               Item item = itemstack.getItem();
               if(item instanceof ItemFood) {
                  flag2 = true;
               }

               if(item instanceof ItemArmor) {
                  flag3 = false;
               }

               if(item instanceof ItemSword) {
                  flag3 = false;
               }

               if(item instanceof ItemTool) {
                  flag3 = false;
               }

               if(item != null) {
                  s = item.getUnlocalizedName();
               }

               if(s != null) {
                  GoldKeeper var10000 = this.gold;
                  j = GoldKeeper.priceItem(s);
               }

               String s1 = item.getUnlocalizedName() + ".name";
               String s2 = this.st.translateKey(s1);
               Item l = itemstack.getItem();
               if(l == Items.bow || l == Items.arrow) {
                  flag3 = false;
               }

               if(l == 26 || l == 34 || l == 36 || l == 43 || l == 51 || l == 52 || l == 55 || l == 59 || l == 60 || l == 62 || l == 63 || l == 64 || l == 68 || l == 71 || l == 74 || l == 75 || l == 78 || l == 90 || l == 93 || l == 94 || l == 97 || l == 99 || l == 100 || l == 104 || l == 105 || l == 110 || l == 92 || l == 354 || l == Items.flint || l == Items.clay_ball || l == Items.iron_ingot || l == Items.diamond || l == Items.fishing_rod || l == Items.apple || l == Items.string || l == Items.feather || flag2 || flag3) {
                  j = 0;
               }

               if(j > 0 && !s1.equals("null.name") && !s1.equals(s2)) {
                  this.itemget[i] = Integer.valueOf(itemstack.getItem());
                  ++i;
               }
            }
         }

         minecraft.displayGuiScreen(new GuiShopList(entityplayer, this.field_70170_p, this.itemget));
      }

      return true;
   }
}
