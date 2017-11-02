package mods.aginsun.kingdoms.entities;

import mods.aginsun.kingdoms.client.guis.GuiLibrary;
import mods.aginsun.kingdoms.entities.api.EntityNPC;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public final class EntityLibraryKeeper extends EntityNPC {

   private World field_70170_p;
   public boolean studied = true;
   private int counter = 0;


   public EntityLibraryKeeper(World world) {
      super(world, null, 100.0F);
      this.field_70170_p = world;
      this.isImmuneToFire = false;
   }

   public boolean canBePushed() {
      return false;
   }

   protected void updateEntityActionState() {
      super.updateEntityActionState();
      if(this.counter > 10000) {
         this.studied = true;
         this.counter = 0;
      }

      ++this.counter;
   }

   protected boolean isMovementCeased() {
      return true;
   }

   public boolean interact(EntityPlayer entityplayer) {
      if(this.canInteractWith(entityplayer)) {
         this.heal(100.0F);
         Minecraft minecraft = Minecraft.getMinecraft();
         if(!this.field_70170_p.isRemote) {
            entityplayer.addChatMessage(new ChatComponentText("Librarian: You picked a good day to visit the library, young one"));
         }

         minecraft.displayGuiScreen(new GuiLibrary(entityplayer, this.field_70170_p, this));
      }

      return true;
   }
}
