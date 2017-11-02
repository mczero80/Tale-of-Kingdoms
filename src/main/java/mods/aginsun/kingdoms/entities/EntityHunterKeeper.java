package mods.aginsun.kingdoms.entities;

import mods.aginsun.kingdoms.client.guis.GuiHunter;
import mods.aginsun.kingdoms.entities.api.EntityNPC;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public final class EntityHunterKeeper extends EntityNPC {

   private World field_70170_p;


   public EntityHunterKeeper(World world) {
      super(world, null, 100.0F);
      this.field_70170_p = world;
      this.isImmuneToFire = false;
   }

   public boolean canBePushed() {
      return false;
   }

   protected boolean isMovementCeased() {
      return true;
   }

   public boolean interact(EntityPlayer entityplayer) {
      if(this.canInteractWith(entityplayer)) {
         this.heal(100.0F);
         Minecraft minecraft = Minecraft.getMinecraft();
         if(!this.field_70170_p.isRemote) {
            entityplayer.addChatMessage(new ChatComponentText("Guild Master: Welcome to the order, hero."));
         }

         minecraft.displayGuiScreen(new GuiHunter(entityplayer, this.field_70170_p));
      }

      return true;
   }
}
