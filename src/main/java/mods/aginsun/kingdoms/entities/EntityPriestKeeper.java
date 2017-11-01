package mods.aginsun.kingdoms.entities;

import cpw.mods.fml.common.FMLCommonHandler;
import mods.aginsun.kingdoms.client.guis.GuiPriest;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public final class EntityPriestKeeper extends EntityCreature {

   private World field_70170_p = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(0);


   public EntityPriestKeeper(World world) {
      super(world);
      this.field_70170_p = world;
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

   protected void updateEntityActionState() {
      super.updateEntityActionState();
   }

   protected boolean isMovementCeased() {
      return true;
   }

   public boolean interact(EntityPlayer entityplayer) {
      if(this.canInteractWith(entityplayer)) {
         this.heal(100.0F);
         Minecraft minecraft = Minecraft.getMinecraft();
         if(!this.field_70170_p.isRemote) {
            entityplayer.addChatMessage(new ChatComponentText("Head Priest: The light order gives blessing to you."));
         }

         minecraft.displayGuiScreen(new GuiPriest(entityplayer, this.field_70170_p));
      }

      return true;
   }
}
