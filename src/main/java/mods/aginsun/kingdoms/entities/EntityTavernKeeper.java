package mods.aginsun.kingdoms.entities;

import mods.aginsun.kingdoms.client.guis.GuiTavernGame;
import mods.aginsun.kingdoms.entities.api.EntityNPC;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public final class EntityTavernKeeper extends EntityNPC {

   private World worldObj;


   public EntityTavernKeeper(World world) {
      super(world, null, 100.0F);
      this.worldObj = world;
      this.isImmuneToFire = false;
   }

   public boolean canBePushed() {
      return false;
   }

   protected boolean isMovementCeased() {
      return true;
   }

   public boolean interact(EntityPlayer entityplayer) {
      if(!this.world.isRemote) {
         entityplayer.addChatMessage(new ChatComponentText("One-Eyed Gambler: Feeling a bit lucky eh?"));
      }

      if(this.canInteractWith(entityplayer)) {
         Minecraft minecraft = Minecraft.getMinecraft();
         minecraft.displayGuiScreen(new GuiTavernGame(entityplayer, this.worldObj));
      }

      return true;
   }
}
