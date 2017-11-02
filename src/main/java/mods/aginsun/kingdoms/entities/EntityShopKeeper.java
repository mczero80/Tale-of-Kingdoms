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
import net.minecraft.util.StringTranslate;
import net.minecraft.world.World;

public final class EntityShopKeeper extends EntityNPC {

   private World field_70170_p;
   private Integer[] itemget = new Integer[200];
   private StringTranslate st = new StringTranslate();


   public EntityShopKeeper(World world) {
      super(world, null, 100.0F);
      this.field_70170_p = world;
      this.isImmuneToFire = false;
   }

   protected boolean canDespawn() {
      return false;
   }

   public boolean canInteractWith(EntityPlayer entityplayer) {
      return this.isDead?false:entityplayer.getDistanceSqToEntity(this) <= 64.0D;
   }

   protected void jump() {}

   protected boolean isMovementCeased() {
      return true;
   }

   public boolean canBePushed() {
      return false;
   }

   public boolean interact(EntityPlayer entityplayer) {
      if(this.canInteractWith(entityplayer)) {
         this.heal(100.0F);
         Minecraft minecraft = Minecraft.getMinecraft();
         int i = 0;
         int j = 0;
         String s = "";

         for(int k = 256; k < 32000; ++k) {
            boolean flag2 = false;
            boolean flag3 = false;
            if(Item.getItemById(k) != null) {
               ItemStack itemstack = new ItemStack(Item.getItemById(k), 1, 0);
               Item item = itemstack.getItem();
               if(item instanceof ItemFood) {
                  flag2 = true;
               }

               if(item instanceof ItemArmor) {
                  flag3 = true;
               }

               if(item instanceof ItemSword) {
                  flag3 = true;
               }

               if(item instanceof ItemTool) {
                  flag3 = true;
               }

               if(item != null) {
                  s = item.getUnlocalizedName();
               }

               if(s != null) {
                  j = GoldKeeper.priceItem(s);
               }

               String s1 = item.getUnlocalizedName() + ".name";
               String s2 = this.st.translateKey(s1);
               Item l = itemstack.getItem();
               if(l == Item.getItemById(26) || l == Item.getItemById(34) || l == Item.getItemById(36) ||
                       l == Item.getItemById(43) || l == Item.getItemById(51) || l == Item.getItemById(52) ||
                       l == Item.getItemById(55) || l == Item.getItemById(59) || l == Item.getItemById(60) ||
                       l == Item.getItemById(62) || l == Item.getItemById(63) || l == Item.getItemById(64) ||
                       l == Item.getItemById(68) || l == Item.getItemById(71) || l == Item.getItemById(74) ||
                       l == Item.getItemById(75) || l == Item.getItemById(78) || l == Item.getItemById(90) ||
                       l == Item.getItemById(93) || l == Item.getItemById(94) || l == Item.getItemById(97) ||
                       l == Item.getItemById(99) || l == Item.getItemById(100) || l == Item.getItemById(104) ||
                       l == Item.getItemById(261) || l == Item.getItemById(262) || l == Item.getItemById(105) ||
                       l == Item.getItemById(110) || l == Item.getItemById(92) || l == Item.getItemById(354) ||
                       l == Items.flint || l == Items.clay_ball || l == Items.iron_ingot || l == Items.diamond ||
                       l == Items.fish || l == Items.apple || l == Items.string || l == Items.feather || flag2 || flag3) {
                  j = 0;
               }

               if(j > 0 && !s1.equals("null.name") && !s1.equals(s2)) {
                  //this.itemget[i] = Integer.valueOf(itemstack.itemID);
                  ++i;
               }
            }
         }

         minecraft.displayGuiScreen(new GuiShopList(entityplayer, this.field_70170_p, this.itemget));
      }

      return true;
   }
}
