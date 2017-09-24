package mods.aginsun.kingdoms.handlers;

import mods.aginsun.kingdoms.TaleOfKingdoms;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public final class PickupHandler {

   public void notifyPickup(EntityItem item, EntityPlayer player) {
      ItemStack itemstack = item.getEntityItem();
      if(itemstack.getItem() == TaleOfKingdoms.coins) {
         player.inventory.consumeInventoryItem(itemstack.getItem());
         GoldKeeper.addGold(2);
         WorthyKeeper.getInstance().addWorthy(1.0F);
      }

   }
}
