package mods.aginsun.kingdoms.entities;

import java.util.Random;
import mods.aginsun.kingdoms.entities.api.EntityNPC;
import mods.aginsun.kingdoms.handlers.WorthyKeeper;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityReficulGuardian extends EntityNPC {

   private static ItemStack defaultHeldItem = new ItemStack(Item.bow, 1);
   private EntityPlayer player;
   private boolean playerPresence = true;
   private Random field_70146_Z = new Random();
   private World field_70170_p;
   protected int attackStrength;


   public EntityReficulGuardian(World world) {
      super(world, defaultHeldItem, 30.0F);
      this.field_70170_p = world;
      this.attackStrength = 7;
      this.field_70178_ae = true;
   }

   public void func_70636_d() {
      super.func_70636_d();

      for(int i = 0; i < 2; ++i) {
         this.field_70170_p.spawnParticle("portal", this.field_70165_t + (this.field_70146_Z.nextDouble() - 0.5D) * (double)this.field_70130_N, this.field_70163_u + this.field_70146_Z.nextDouble() * (double)this.field_70131_O - 0.25D, this.field_70161_v + (this.field_70146_Z.nextDouble() - 0.5D) * (double)this.field_70130_N, (this.field_70146_Z.nextDouble() - 0.5D) * 2.0D, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5D) * 2.0D);
      }

   }

   protected boolean teleportToEntity(Entity entity) {
      Vec3 vec3d = Vec3.createVectorHelper(this.field_70165_t - entity.posX, this.field_70121_D.minY + (double)(this.field_70131_O / 2.0F) - entity.posY + (double)entity.getEyeHeight(), this.field_70161_v - entity.posZ);
      vec3d = vec3d.normalize();
      double d = 16.0D;
      double d1 = this.field_70165_t + (this.field_70146_Z.nextDouble() - 0.5D) * 8.0D - vec3d.xCoord * d;
      double d2 = this.field_70163_u + (double)(this.field_70146_Z.nextInt(16) - 8) - vec3d.yCoord * d;
      double d3 = this.field_70161_v + (this.field_70146_Z.nextDouble() - 0.5D) * 8.0D - vec3d.zCoord * d;
      return this.teleportTo(d1, d2, d3);
   }

   protected boolean teleportTo(double d, double d1, double d2) {
      if(this.field_70146_Z.nextInt(10) == 0) {
         double d3 = this.field_70165_t;
         double d4 = this.field_70163_u;
         double d5 = this.field_70161_v;
         this.field_70165_t = d;
         this.field_70163_u = d1;
         this.field_70161_v = d2;
         boolean flag = false;
         int i = MathHelper.floor_double(this.field_70165_t);
         int j = MathHelper.floor_double(this.field_70163_u);
         int k = MathHelper.floor_double(this.field_70161_v);
         int j1;
         if(this.field_70170_p.blockExists(i, j, k)) {
            boolean l = false;

            while(!l && j > 0) {
               j1 = this.field_70170_p.getBlockId(i, j - 1, k);
               if(j1 != 0 && Block.blocksList[j1].blockMaterial.isSolid()) {
                  l = true;
               } else {
                  --this.field_70163_u;
                  --j;
               }
            }

            if(l) {
               this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
               if(this.field_70170_p.getCollidingBoundingBoxes(this, this.field_70121_D).size() == 0 && !this.field_70170_p.isAnyLiquid(this.field_70121_D)) {
                  flag = true;
               }
            }
         }

         if(!flag) {
            this.func_70107_b(d3, d4, d5);
            return false;
         }

         short var30 = 128;

         for(j1 = 0; j1 < var30; ++j1) {
            double d6 = (double)j1 / ((double)var30 - 1.0D);
            float f = (this.field_70146_Z.nextFloat() - 0.5F) * 0.2F;
            float f1 = (this.field_70146_Z.nextFloat() - 0.5F) * 0.2F;
            float f2 = (this.field_70146_Z.nextFloat() - 0.5F) * 0.2F;
            double d7 = d3 + (this.field_70165_t - d3) * d6 + (this.field_70146_Z.nextDouble() - 0.5D) * (double)this.field_70130_N * 2.0D;
            double d8 = d4 + (this.field_70163_u - d4) * d6 + this.field_70146_Z.nextDouble() * (double)this.field_70131_O;
            double d9 = d5 + (this.field_70161_v - d5) * d6 + (this.field_70146_Z.nextDouble() - 0.5D) * (double)this.field_70130_N * 2.0D;
            this.field_70170_p.spawnParticle("portal", d7, d8, d9, (double)f, (double)f1, (double)f2);
         }
      }

      return true;
   }

   public void func_70645_a(DamageSource damagesource) {
      WorthyKeeper.getInstance().addWorthy(70.0F);
   }

   protected void func_70785_a(Entity entity, float f) {
      if(f < 10.0F) {
         double d = entity.posX - this.field_70165_t;
         double d1 = entity.posZ - this.field_70161_v;
         if(this.field_70724_aR == 0) {
            EntityArrow entityarrow = new EntityArrow(this.field_70170_p, this, 1.0F);
            double d2 = entity.posY + (double)entity.getEyeHeight() - 0.699999988079071D - entityarrow.field_70163_u;
            float f1 = MathHelper.sqrt_double(d * d + d1 * d1) * 0.2F;
            this.field_70170_p.playSoundAtEntity(this, "random.bow", 1.0F, 1.0F / (this.field_70146_Z.nextFloat() * 0.4F + 0.8F));
            if(this.field_70146_Z.nextInt(8) != 0) {
               this.field_70170_p.spawnEntityInWorld(entityarrow);
               entityarrow.setThrowableHeading(d, d2 + (double)f1, d1, 1.6F, 12.0F);
            }

            this.field_70724_aR = 30;
         }

         this.field_70177_z = (float)(Math.atan2(d1, d) * 180.0D / 3.1415927410125732D) - 90.0F;
         this.field_70787_b = true;
      }

   }

   protected boolean func_70780_i() {
      return this.playerPresence;
   }

   public void knockBack(Entity entity, int i, double d, double d1) {
      if(this.field_70146_Z.nextInt(2) == 0) {
         this.field_70160_al = true;
         float f = MathHelper.sqrt_double(d * d + d1 * d1);
         float f1 = 0.4F;
         this.field_70159_w /= 2.0D;
         this.field_70181_x /= 2.0D;
         this.field_70179_y /= 2.0D;
         this.field_70159_w -= d / (double)f * (double)f1;
         this.field_70181_x += 0.4000000059604645D;
         this.field_70179_y -= d1 / (double)f * (double)f1;
         if(this.field_70181_x > 0.4000000059604645D) {
            this.field_70181_x = 0.4000000059604645D;
         }
      }

   }

   protected void func_70626_be() {
      super.func_70626_be();

      int j;
      for(j = 0; j < this.field_70170_p.loadedEntityList.size(); ++j) {
         Entity entity = (Entity)this.field_70170_p.loadedEntityList.get(j);
         if(entity instanceof EntityPlayer) {
            this.player = (EntityPlayer)entity;
         }
      }

      if(this.player != null) {
         if(this.player.func_70068_e(this) <= 220.0D && this.field_70170_p.difficultySetting != 0) {
            this.playerPresence = false;
            if(this.field_70146_Z.nextInt(6) == 0) {
               this.teleportToEntity(this.player);
               if(this.field_70146_Z.nextInt(10) == 0) {
                  for(j = 0; j < 2; ++j) {
                     this.field_70170_p.spawnParticle("largesmoke", this.field_70165_t + (this.field_70146_Z.nextDouble() - 0.5D) * (double)this.field_70130_N, this.field_70163_u + this.field_70146_Z.nextDouble() * (double)this.field_70131_O - 0.25D, this.field_70161_v + (this.field_70146_Z.nextDouble() - 0.5D) * (double)this.field_70130_N, (this.field_70146_Z.nextDouble() - 0.5D) * 2.0D, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5D) * 2.0D);
                  }
               }
            }
         } else {
            this.playerPresence = true;
         }
      }

   }

   protected Entity func_70782_k() {
      EntityPlayer entityplayer = this.field_70170_p.getClosestPlayerToEntity(this, 16.0D);
      return entityplayer != null && this.func_70685_l(entityplayer) && this.field_70170_p.difficultySetting != 0?entityplayer:null;
   }

   public boolean attackEntityFrom(DamageSource damagesource, int i) {
      if(!this.playerPresence && this.field_70170_p.difficultySetting != 0) {
         if(super.func_70097_a(damagesource, (float)i)) {
            Entity entity = damagesource.getSourceOfDamage();
            if(this.field_70153_n != entity && this.field_70154_o != entity) {
               if(entity != this) {
                  this.field_70789_a = entity;
               }

               return true;
            } else {
               return true;
            }
         } else {
            return false;
         }
      } else {
         return true;
      }
   }

}
