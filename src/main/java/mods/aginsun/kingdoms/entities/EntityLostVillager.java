package mods.aginsun.kingdoms.entities;

import mods.aginsun.kingdoms.entities.api.EntityNPC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public final class EntityLostVillager extends EntityNPC {

   private World field_70170_p;
   private boolean follow = true;


   public EntityLostVillager(World world) {
      super(world, null, 20.0F);
      this.field_70170_p = world;
      this.isImmuneToFire = true;
   }

   protected boolean isMovementCeased() {
      return this.follow;
   }

   public boolean interact(EntityPlayer entityplayer) {
      this.follow = false;
      if(!this.field_70170_p.isRemote) {
         entityplayer.addChatMessage(new ChatComponentText("Lost Villager: Thank the heavens! Our village is attacked by the reficuls! Please lead me back to the guild."));
      }

      return true;
   }

   protected void jump() {
      if(!this.follow) {
         this.motionY = 0.41999998688697815D;
         if(this.isSprinting()) {
            float f = this.rotationYaw * 0.01745329F;
            this.motionX -= (double)(MathHelper.sin(f) * 0.2F);
            this.motionZ += (double)(MathHelper.cos(f) * 0.2F);
         }

         this.isAirBorne = true;
      }

   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      if(!this.follow) {
         Minecraft minecraft = Minecraft.getMinecraft();
         EntityClientPlayerMP entityplayersp = minecraft.thePlayer;
         if(entityplayersp != null) {
            float f = entityplayersp.getDistanceToEntity(this);
            PathEntity pathentity;
            if(f > 5.0F && f < 18.0F) {
               pathentity = this.field_70170_p.getPathEntityToEntity(this, entityplayersp, 16.0F, true, false, false, true);
            } else {
               pathentity = null;
            }

            this.setPathToEntity(pathentity);
         }
      }

   }
}
