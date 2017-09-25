package mods.aginsun.kingdoms.entities;

import mods.aginsun.kingdoms.client.guis.GuiStockList;
import mods.aginsun.kingdoms.entities.api.EntityNPC;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public final class EntityStockKeeper extends EntityNPC {

   private World world;


   public EntityStockKeeper(World world) {
      super(world, null, 100.0F);
      this.world = world;
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
         if(!this.world.isRemote) {
            entityplayer.addChatMessage(new ChatComponentText("Stock Keeper: Here is my stock for today!"));
         }

         minecraft.displayGuiScreen(new GuiStockList(entityplayer, this.world));
      }

      return true;
   }
}
