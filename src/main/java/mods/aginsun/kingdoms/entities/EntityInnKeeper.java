package mods.aginsun.kingdoms.entities;

import mods.aginsun.kingdoms.client.guis.GuiInnMenu;
import mods.aginsun.kingdoms.entities.api.EntityNPC;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;

public class EntityInnKeeper extends EntityNPC {

   private World field_70170_p;


   public EntityInnKeeper(World world) {
      super(world, (ItemStack)null, 100.0F);
      this.field_70170_p = world;
      this.field_70178_ae = false;
   }

   public boolean func_70104_M() {
      return false;
   }

   public boolean func_70085_c(EntityPlayer entityplayer) {
      if(!this.field_70170_p.isRemote) {
         entityplayer.addChatMessage("House Keeper: Would you like to take a rest sir?");
      }

      if(this.canInteractWith(entityplayer)) {
         Minecraft minecraft = ModLoader.getMinecraftInstance();
         minecraft.displayGuiScreen(new GuiInnMenu(entityplayer, this.field_70170_p));
      }

      return true;
   }
}
