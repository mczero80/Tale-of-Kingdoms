package mods.aginsun.kingdoms.entities;

import mods.aginsun.kingdoms.client.guis.GuiHunter;
import mods.aginsun.kingdoms.entities.api.EntityNPC;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;

public class EntityHunterKeeper extends EntityNPC {

   private World field_70170_p;


   public EntityHunterKeeper(World world) {
      super(world, (ItemStack)null, 100.0F);
      this.field_70170_p = world;
      this.field_70178_ae = false;
   }

   public boolean func_70104_M() {
      return false;
   }

   protected boolean func_70780_i() {
      return true;
   }

   public boolean func_70085_c(EntityPlayer entityplayer) {
      if(this.canInteractWith(entityplayer)) {
         this.func_70691_i(100.0F);
         Minecraft minecraft = ModLoader.getMinecraftInstance();
         if(!this.field_70170_p.isRemote) {
            entityplayer.addChatMessage("Guild Master: Welcome to the order, hero.");
         }

         minecraft.displayGuiScreen(new GuiHunter(entityplayer, this.field_70170_p));
      }

      return true;
   }
}
