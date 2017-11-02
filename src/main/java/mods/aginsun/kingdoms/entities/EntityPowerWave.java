package mods.aginsun.kingdoms.entities;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public final class EntityPowerWave extends EntityThrowable {

   private World field_70170_p;
   private int counter = 0;
   private EntityLiving entityplayer;
   int duration = 0;
   boolean isDrive = true;


   public EntityPowerWave(World world) {
      super(world);
      this.field_70170_p = world;
      this.setSize(5.0E-6F, 5.0E-6F);
   }

   public EntityPowerWave(World world, EntityLiving entityliving, int i) {
      super(world, entityliving);
      this.field_70170_p = world;
      this.setSize(5.0E-6F, 5.0E-6F);
      this.entityplayer = entityliving;
      this.duration = i;
   }

   public EntityPowerWave(World world, double d, double d1, double d2) {
      super(world, d, d1, d2);
      this.field_70170_p = world;
      this.setSize(5.0E-6F, 5.0E-6F);
   }

   protected void onThrowableCollision(MovingObjectPosition movingobjectposition) {
      if(movingobjectposition.entityHit != null) {
      }

   }

   public void onUpdate() {
      super.onUpdate();
      ++this.counter;
      if(this.counter % 2 == 0 && this.counter > 1) {
         int i = MathHelper.floor_double(this.posX);
         int j = MathHelper.floor_double(this.posY);
         int k = MathHelper.floor_double(this.posZ);

         for(int l = j; l > 1; --l) {
            Block list = this.field_70170_p.getBlock(i, l, k);
            if(list != Blocks.air) {
               j = l;
               break;
            }
         }

         List var8 = this.field_70170_p.getEntitiesWithinAABB(EntityLiving.class, AxisAlignedBB.getBoundingBox(this.posX, this.posY, this.posZ, this.posX + 1.0D, this.posY + 1.0D, this.posZ + 1.0D).expand(5.0D, 5.0D, 5.0D));
         if(!var8.isEmpty()) {
            boolean flag = true;
            EntityLivingBase entityliving = (EntityLivingBase)var8.get(this.field_70170_p.rand.nextInt(var8.size()));
            if(entityliving instanceof EntityPlayer) {
               flag = false;
            }

            if(this.entityplayer != null && flag) {
               entityliving.attackEntityFrom(DamageSource.causeMobDamage(this.entityplayer), 10.0F);
            }
         }

         this.field_70170_p.newExplosion(null, (double)i, (double)(j + 1), (double)k, 1.0F, false, false);
      }

      if(this.counter >= this.duration) {
         this.setDead();
      }

   }

   public void writeEntityToNBT(NBTTagCompound nbttagcompound) {}

   public void readEntityFromNBT(NBTTagCompound nbttagcompound) {}

   protected void onImpact(MovingObjectPosition movingobjectposition) {}
}
