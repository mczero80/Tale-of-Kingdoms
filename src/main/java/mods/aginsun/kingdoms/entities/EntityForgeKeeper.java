package mods.aginsun.kingdoms.entities;

import mods.aginsun.kingdoms.client.guis.GuiShopList;
import mods.aginsun.kingdoms.entities.api.EntityNPC;
import mods.aginsun.kingdoms.handlers.GoldKeeper;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.util.StringTranslate;
import net.minecraft.world.World;

public class EntityForgeKeeper extends EntityNPC {

   private World field_70170_p;
   Integer[] itemget = new Integer[200];
   private GoldKeeper gold;
   private StringTranslate st = new StringTranslate();


   public EntityForgeKeeper(World world) {
      super(world, (ItemStack)null, 100.0F);
      this.field_70170_p = world;
      this.field_70178_ae = false;
   }

   public boolean func_70104_M() {
      return false;
   }

   public boolean func_70085_c(EntityPlayer entityplayer) {
      if(this.canInteractWith(entityplayer)) {
         this.func_70691_i(100.0F);
         Minecraft minecraft = ModLoader.getMinecraftInstance();
         int i = 0;

         for(int j = 0; j < 256; ++j) {
            if(Block.blocksList[j] != null) {
               ItemStack itemstack = new ItemStack(Block.blocksList[j].blockID, 1, 0);
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
                              int l = itemstack.itemID;
                              if(l == 26 || l == 34 || l == 36 || l == 43 || l == 51 || l == 52 || l == 55 || l == 59 || l == 60 || l == 62 || l == 63 || l == 64 || l == 68 || l == 71 || l == 74 || l == 75 || l == 78 || l == 90 || l == 93 || l == 94 || l == 97 || l == 99 || l == 100 || l == 104 || l == 105 || l == 110 || l == 92 || l == Item.clay.itemID || l == Item.ingotIron.itemID || l == Item.diamond.itemID || l == Item.fishRaw.itemID || l == Item.appleRed.itemID || l == Item.silk.itemID || l == Item.feather.itemID) {
                                 k = 0;
                              }

                              if(k > 0 && !s2.equals("null.name") && !s2.equals(s3)) {
                                 this.itemget[i] = Integer.valueOf(itemstack.itemID);
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
