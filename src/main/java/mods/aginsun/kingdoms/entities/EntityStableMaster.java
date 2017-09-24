package mods.aginsun.kingdoms.entities;

import mods.aginsun.kingdoms.client.guis.GuiStableMaster;
import mods.aginsun.kingdoms.entities.api.EntityNPC;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityStableMaster extends EntityNPC {

   public EntityStableMaster(World par1World) {
      super(par1World, new ItemStack(Item.leash), 40.0F);
   }

   public boolean func_70104_M() {
      return false;
   }

   protected boolean func_70780_i() {
      return true;
   }

   public boolean func_70085_c(EntityPlayer entityplayer) {
      if(this.canInteractWith(entityplayer)) {
         Minecraft.getMinecraft().displayGuiScreen(new GuiStableMaster());
      }

      return true;
   }
}
