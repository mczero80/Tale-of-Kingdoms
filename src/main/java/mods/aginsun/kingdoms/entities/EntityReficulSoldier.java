package mods.aginsun.kingdoms.entities;

import java.util.Random;
import mods.aginsun.kingdoms.entities.api.EntityNPC;
import mods.aginsun.kingdoms.handlers.WorthyKeeper;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityReficulSoldier extends EntityNPC {

   private static ItemStack defaultHeldItem = new ItemStack(Items.iron_sword, 1);
   private EntityPlayer player;
   private boolean playerPresence = true;
   private Random field_70146_Z = new Random();
   private World field_70170_p;
   protected int attackStrength;
   public boolean isSwinging;
   public int field_110158_av;


   public EntityReficulSoldier(World world) {
      super(world, defaultHeldItem, 40.0F);
      this.field_70170_p = world;
      this.attackStrength = 4;
      this.isImmuneToFire = true;
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();

      for(int i = 0; i < 2; ++i) {
         this.field_70170_p.spawnParticle("portal", this.posX + (this.field_70146_Z.nextDouble() - 0.5D) * (double)this.width, this.posY + this.field_70146_Z.nextDouble() * (double)this.height - 0.25D, this.posZ + (this.field_70146_Z.nextDouble() - 0.5D) * (double)this.width, (this.field_70146_Z.nextDouble() - 0.5D) * 2.0D, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5D) * 2.0D);
      }

   }

   protected boolean teleportToEntity(Entity entity) {
      Vec3 vec3d = Vec3.createVectorHelper(this.posX - entity.posX, this.boundingBox.minY + (double)(this.height / 2.0F) - entity.posY + (double)entity.getEyeHeight(), this.posZ - entity.posZ);
      vec3d = vec3d.normalize();
      double d = 16.0D;
      double d1 = this.posX + (this.field_70146_Z.nextDouble() - 0.5D) * 8.0D - vec3d.xCoord * d;
      double d2 = this.posY + (double)(this.field_70146_Z.nextInt(16) - 8) - vec3d.yCoord * d;
      double d3 = this.posZ + (this.field_70146_Z.nextDouble() - 0.5D) * 8.0D - vec3d.zCoord * d;
      return this.teleportTo(d1, d2, d3);
   }

   protected boolean teleportTo(double d, double d1, double d2) {
      if(this.field_70146_Z.nextInt(10) == 0) {
         double d3 = this.posX;
         double d4 = this.posY;
         double d5 = this.posZ;
         this.posX = d;
         this.posY = d1;
         this.posZ = d2;
         boolean flag = false;
         int i = MathHelper.floor_double(this.posX);
         int j = MathHelper.floor_double(this.posY);
         int k = MathHelper.floor_double(this.posZ);
         Block j1;
         if(this.field_70170_p.blockExists(i, j, k)) {
            boolean l = false;

            while(!l && j > 0) {
               j1 = this.field_70170_p.getBlock(i, j - 1, k);
               if(j1 != null && j1.getMaterial().isSolid()) {
                  l = true;
               } else {
                  --this.posY;
                  --j;
               }
            }

            if(l) {
               this.setPosition(this.posX, this.posY, this.posZ);
               if(this.field_70170_p.getCollidingBoundingBoxes(this, this.boundingBox).size() == 0 && !this.field_70170_p.isAnyLiquid(this.boundingBox)) {
                  flag = true;
               }
            }
         }

         if(!flag) {
            this.setPosition(d3, d4, d5);
            return false;
         }

         short var30 = 128;

         /*for (j1 = 0; j1 < var30; ++j1)
         {
            double d6 = (double) j1 / ((double)var30 - 1.0D);
            float f = (this.field_70146_Z.nextFloat() - 0.5F) * 0.2F;
            float f1 = (this.field_70146_Z.nextFloat() - 0.5F) * 0.2F;
            float f2 = (this.field_70146_Z.nextFloat() - 0.5F) * 0.2F;
            double d7 = d3 + (this.posX - d3) * d6 + (this.field_70146_Z.nextDouble() - 0.5D) * this.width * 2.0D;
            double d8 = d4 + (this.posY - d4) * d6 + this.field_70146_Z.nextDouble() * (double)this.height;
            double d9 = d5 + (this.posZ - d5) * d6 + (this.field_70146_Z.nextDouble() - 0.5D) * this.width * 2.0D;
            this.field_70170_p.spawnParticle("portal", d7, d8, d9, (double)f, (double)f1, (double)f2);
         }*/
      }

      return true;
   }

   public void swingItem() {
      if(!this.isSwinging || this.field_110158_av < 0) {
         this.field_110158_av = -1;
         this.isSwinging = true;
      }

   }

   protected void attackEntity(Entity entity, float f) {
      if(this.attackTime <= 0 && f < 2.0F && entity.boundingBox.maxY > this.boundingBox.minY && entity.boundingBox.minY < this.boundingBox.maxY) {
         this.attackTime = 20;
         this.swingItem();
         this.attackEntityAsMob(entity);
         entity.setFire(8);
      }

   }

   public void onDeath(DamageSource damagesource) {
      WorthyKeeper.getInstance().addWorthy(70.0F);
   }

   protected boolean isMovementCeased() {
      return this.playerPresence;
   }

   public void knockBack(Entity entity, int i, double d, double d1) {
      if(this.field_70146_Z.nextInt(2) == 0) {
         this.isAirBorne = true;
         float f = MathHelper.sqrt_double(d * d + d1 * d1);
         float f1 = 0.4F;
         this.motionX /= 2.0D;
         this.motionY /= 2.0D;
         this.motionZ /= 2.0D;
         this.motionX -= d / (double)f * (double)f1;
         this.motionY += 0.4000000059604645D;
         this.motionZ -= d1 / (double)f * (double)f1;
         if(this.motionY > 0.4000000059604645D) {
            this.motionY = 0.4000000059604645D;
         }
      }

   }

   public boolean attackEntityAsMob(Entity entity) {
      int i = this.attackStrength;
      if(this.isPotionActive(Potion.damageBoost)) {
         i += 3 << this.getActivePotionEffect(Potion.damageBoost).getAmplifier();
      }

      if(this.isPotionActive(Potion.weakness)) {
         i -= 2 << this.getActivePotionEffect(Potion.weakness).getAmplifier();
      }

      return entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float)i);
   }

   protected void updateEntityActionState() {
      super.updateEntityActionState();
      byte i = 6;
      if(this.isSwinging) {
         ++this.field_110158_av;
         if(this.field_110158_av >= i) {
            this.field_110158_av = 0;
            this.isSwinging = false;
         }
      } else {
         this.field_110158_av = 0;
      }

      this.swingProgress = (float)this.field_110158_av / (float)i;

      int k;
      for(k = 0; k < this.field_70170_p.loadedEntityList.size(); ++k) {
         Entity entity = (Entity)this.field_70170_p.loadedEntityList.get(k);
         if(entity instanceof EntityPlayer) {
            this.player = (EntityPlayer)entity;
         }
      }

      if(this.player != null) {
         if(this.player.getDistanceSqToEntity(this) <= 220.0D && this.field_70170_p.difficultySetting != null) {
            this.playerPresence = false;
            if(this.field_70146_Z.nextInt(6) == 0) {
               this.teleportToEntity(this.player);
               if(this.field_70146_Z.nextInt(10) == 0) {
                  for(k = 0; k < 2; ++k) {
                     this.field_70170_p.spawnParticle("largesmoke", this.posX + (this.field_70146_Z.nextDouble() - 0.5D) * (double)this.width, this.posY + this.field_70146_Z.nextDouble() * (double)this.height - 0.25D, this.posZ + (this.field_70146_Z.nextDouble() - 0.5D) * (double)this.width, (this.field_70146_Z.nextDouble() - 0.5D) * 2.0D, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5D) * 2.0D);
                  }
               }
            }
         } else {
            this.playerPresence = true;
         }
      }

   }

   protected Entity findPlayerToAttack() {
      EntityPlayer entityplayer = this.field_70170_p.getClosestPlayerToEntity(this, 16.0D);
      return entityplayer != null && this.canEntityBeSeen(entityplayer) && this.field_70170_p.difficultySetting != null?entityplayer:null;
   }

   public boolean attackEntityFrom(DamageSource damagesource, int i) {
      if(!this.playerPresence && this.field_70170_p.difficultySetting != null) {
         if(super.attackEntityFrom(damagesource, (float)i)) {
            Entity entity = damagesource.getSourceOfDamage();
            if(this.riddenByEntity != entity && this.ridingEntity != entity) {
               if(entity != this) {
                  this.entityToAttack = entity;
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
