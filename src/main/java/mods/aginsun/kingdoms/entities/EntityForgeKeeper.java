package mods.aginsun.kingdoms.entities;

import mods.aginsun.kingdoms.client.guis.GuiShopList;
import mods.aginsun.kingdoms.entities.api.EntityNPC;
import mods.aginsun.kingdoms.handlers.GoldKeeper;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StringTranslate;
import net.minecraft.world.World;

public final class EntityForgeKeeper extends EntityNPC {

   private World field_70170_p;
   Integer[] itemget = new Integer[200];
   private GoldKeeper gold;
   private StringTranslate st = new StringTranslate();


   public EntityForgeKeeper(World world) {
      super(world, null, 100.0F);
      this.field_70170_p = world;
      this.isImmuneToFire = false;
   }

   public boolean canBePushed() {
      return false;
   }

   public boolean interact(EntityPlayer entityplayer) {
      if(this.canInteractWith(entityplayer)) {
         this.heal(100.0F);
         Minecraft minecraft = Minecraft.getMinecraft();
         int i = 0;

         for(int j = 0; j < 256; ++j) {
            if(Block.getBlockById(j) != null) {
               ItemStack itemstack = new ItemStack(Block.getBlockById(j), 1, 0);
               if(itemstack != null) {
                  Item item = itemstack.getItem();
                  if(item != null) {
                     String s1 = item.getUnlocalizedName();
                     if(s1 != null) {
                        GoldKeeper var10000 = this.gold;
                        int k = GoldKeeper.priceItem(s1);
                        if(itemstack != null) {
                           String s2 = item.getUnlocalizedName() + ".name";
                           if(s2 != null) {
                              String s3 = this.st.translateKey(s2);
                              Item l = itemstack.getItem();

                               if(l == Item.getItemById(26) || l == Item.getItemById(34) || l == Item.getItemById(36) ||
                                       l == Item.getItemById(43) || l == Item.getItemById(51) || l == Item.getItemById(52) ||
                                       l == Item.getItemById(55) || l == Item.getItemById(59) || l == Item.getItemById(60) ||
                                       l == Item.getItemById(62) || l == Item.getItemById(63) || l == Item.getItemById(64) ||
                                       l == Item.getItemById(68) || l == Item.getItemById(71) || l == Item.getItemById(74) ||
                                       l == Item.getItemById(75) || l == Item.getItemById(78) || l == Item.getItemById(90) ||
                                       l == Item.getItemById(93) || l == Item.getItemById(94) || l == Item.getItemById(97) ||
                                       l == Item.getItemById(99) || l == Item.getItemById(100) || l == Item.getItemById(104) ||
                                       l == Item.getItemById(105) || l == Item.getItemById(110) || l == Item.getItemById(92) || l == Items.clay_ball ||
                                       l == Items.iron_ingot || l == Items.diamond || l == Items.fish || l == Items.apple || l == Items.string ||
                                       l == Items.feather) {
                                   j = 0;
                               }

                              if(k > 0 && !s2.equals("null.name") && !s2.equals(s3)) {
                                 //this.itemget[i] = Integer.valueOf(itemstack.itemID);
                                 ++i;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         minecraft.displayGuiScreen(new GuiShopList(entityplayer, this.field_70170_p, this.itemget));
      }

      return true;
   }
}
