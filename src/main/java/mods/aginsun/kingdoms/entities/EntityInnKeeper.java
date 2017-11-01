package mods.aginsun.kingdoms.entities;

import mods.aginsun.kingdoms.client.guis.GuiInnMenu;
import mods.aginsun.kingdoms.entities.api.EntityNPC;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class EntityInnKeeper extends EntityNPC {

   private World field_70170_p;


   public EntityInnKeeper(World world) {
      super(world, null, 100.0F);
      this.field_70170_p = world;
      this.isImmuneToFire = false;
   }

   public boolean canBePushed() {
      return false;
   }

   public boolean interact(EntityPlayer entityplayer) {
      if(!this.field_70170_p.isRemote) {
         entityplayer.addChatMessage(new ChatComponentText("House Keeper: Would you like to take a rest sir?"));
      }

      if(this.canInteractWith(entityplayer)) {
         Minecraft minecraft = Minecraft.getMinecraft();
         minecraft.displayGuiScreen(new GuiInnMenu(entityplayer, this.field_70170_p));
      }

      return true;
   }
}