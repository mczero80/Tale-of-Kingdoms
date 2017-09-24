package mods.aginsun.kingdoms.entities;

import mods.aginsun.kingdoms.client.guis.GuiFoodKeeper;
import mods.aginsun.kingdoms.entities.api.EntityNPC;
import mods.aginsun.kingdoms.handlers.GoldKeeper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;

public class EntityFarmerKeeper extends EntityNPC {

   private static ItemStack defaultHeldItem = new ItemStack(Item.hoeIron, 1);
   public static GoldKeeper gold;


   public EntityFarmerKeeper(World world) {
      super(world, defaultHeldItem, 100.0F);
      this.field_70178_ae = false;
   }

   public boolean func_70104_M() {
      return false;
   }

   protected boolean func_70780_i() {
      return true;
   }

   public boolean func_70085_c(EntityPlayer entityplayer) {
      Minecraft minecraft = ModLoader.getMinecraftInstance();
      if(this.canInteractWith(entityplayer)) {
         minecraft.displayGuiScreen(new GuiFoodKeeper(entityplayer, this.field_70170_p));
      }

      return true;
   }

}
