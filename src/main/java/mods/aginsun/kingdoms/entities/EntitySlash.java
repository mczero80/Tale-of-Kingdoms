package mods.aginsun.kingdoms.entities;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntitySlash extends EntityBlaze {

   public int counter = 0;
   public int speed = 200;
   public EntityPlayer entityplayer;
   public World field_70170_p;
   public boolean explode = false;
   public boolean surround = false;
   public double range = 0.0D;


   public EntitySlash(World world) {
      super(world);
      this.field_70170_p = world;
   }

   public boolean attackEntityFrom(DamageSource damagesource, int i) {
      return false;
   }

   public void func_70645_a(DamageSource damagesource) {}

   public int getEntityBrightnessForRender(float f) {
      return 15728880;
   }

   public float getEntityBrightness(float f) {
      return 1.0F;
   }

   public void onUpdate() {
      this.counter += this.speed;
      if(this.counter > 200) {
         this.setDead();
      }

      if(this.counter % 2 == 0 && this.explode && this.speed != 200) {
         this.field_70170_p.newExplosion((Entity)null, this.field_70165_t, this.field_70163_u + 1.0D, this.field_70161_v, 2.0F, true, false);
      }

      if(this.counter % 2 == 0 && this.surround) {
         List list = this.field_70170_p.getEntitiesWithinAABB(EntityLiving.class, AxisAlignedBB.getBoundingBox(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0D, this.field_70163_u + 1.0D, this.field_70161_v + 1.0D).expand(this.range, this.range, this.range));
         if(!list.isEmpty()) {
            boolean flag = true;
            EntityLivingBase entityliving = (EntityLivingBase)list.get(this.field_70170_p.rand.nextInt(list.size()));
            if(entityliving instanceof EntityPlayer || entityliving instanceof EntitySlash) {
               flag = false;
            }

            if(this.entityplayer != null && flag) {
               this.field_70170_p.newExplosion(this.entityplayer, entityliving.field_70165_t, entityliving.field_70163_u + 1.0D, entityliving.field_70161_v, 1.0F, true, false);
            }
         }
      }

   }

   protected void attackEntity(Entity entity, float f) {}

   protected void func_70069_a(float f) {}

   public void func_70014_b(NBTTagCompound nbttagcompound) {}

   public void func_70037_a(NBTTagCompound nbttagcompound) {}

   protected int func_70633_aT() {
      return 0;
   }

   public boolean func_70027_ad() {
      return false;
   }

   protected void func_70628_a(boolean flag, int i) {}

   public boolean func_40151_ac() {
      return false;
   }

   public void func_40150_a(boolean flag) {}

   protected boolean func_40147_Y() {
      return true;
   }
}
