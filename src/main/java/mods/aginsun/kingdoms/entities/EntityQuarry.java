package mods.aginsun.kingdoms.entities;

import cpw.mods.fml.common.FMLCommonHandler;
import mods.aginsun.kingdoms.client.guis.GuiQuarry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public final class EntityQuarry extends EntityCreature {

   private World world = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(0);


   public EntityQuarry(World world) {
      super(world);
      this.world = world;
      this.isImmuneToFire = false;
   }

   protected boolean canDespawn() {
      return false;
   }

   public boolean canInteractWith(EntityPlayer entityplayer) {
      return this.isDead?false:entityplayer.getDistanceSqToEntity(this) <= 64.0D;
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
            entityplayer.addChatMessage(new ChatComponentText("Foreman: Do you need resources sir?"));
         }

         minecraft.displayGuiScreen(new GuiQuarry(entityplayer, this.world));
      }

      return true;
   }
}
