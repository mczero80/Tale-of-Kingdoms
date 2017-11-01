package mods.aginsun.kingdoms.entities;

import cpw.mods.fml.common.FMLCommonHandler;
import mods.aginsun.kingdoms.client.guis.GuiLumber;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class EntityLumber extends EntityCreature {

   private World field_70170_p = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(0);


   public EntityLumber(World world) {
      super(world);
      this.field_70170_p = world;
      this.isImmuneToFire = false;
   }

   protected boolean func_70692_ba() {
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
         if(!this.field_70170_p.isRemote) {
            entityplayer.addChatMessage(new ChatComponentText("Foreman: Do you need resources sir?"));
         }

         minecraft.displayGuiScreen(new GuiLumber(entityplayer, this.field_70170_p));
      }

      return true;
   }
}
