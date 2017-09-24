package mods.aginsun.kingdoms.entities;

import mods.aginsun.kingdoms.entities.api.EntityNPC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.src.ModLoader;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityLostVillager extends EntityNPC {

   private World field_70170_p;
   private boolean follow = true;


   public EntityLostVillager(World world) {
      super(world, (ItemStack)null, 20.0F);
      this.field_70170_p = world;
      this.field_70178_ae = true;
   }

   protected boolean func_70780_i() {
      return this.follow;
   }

   public boolean func_70085_c(EntityPlayer entityplayer) {
      this.follow = false;
      if(!this.field_70170_p.isRemote) {
         entityplayer.addChatMessage("Lost Villager: Thank the heavens! Our village is attacked by the reficuls! Please lead me back to the guild.");
      }

      return true;
   }

   protected void func_70664_aZ() {
      if(!this.follow) {
         this.field_70181_x = 0.41999998688697815D;
         if(this.func_70051_ag()) {
            float f = this.field_70177_z * 0.01745329F;
            this.field_70159_w -= (double)(MathHelper.sin(f) * 0.2F);
            this.field_70179_y += (double)(MathHelper.cos(f) * 0.2F);
         }

         this.field_70160_al = true;
      }

   }

   public void func_70636_d() {
      super.func_70636_d();
      if(!this.follow) {
         Minecraft minecraft = ModLoader.getMinecraftInstance();
         EntityClientPlayerMP entityplayersp = minecraft.thePlayer;
         if(entityplayersp != null) {
            float f = entityplayersp.func_70032_d(this);
            PathEntity pathentity;
            if(f > 5.0F && f < 18.0F) {
               pathentity = this.field_70170_p.getPathEntityToEntity(this, entityplayersp, 16.0F, true, false, false, true);
            } else {
               pathentity = null;
            }

            this.func_70778_a(pathentity);
         }
      }

   }
}
