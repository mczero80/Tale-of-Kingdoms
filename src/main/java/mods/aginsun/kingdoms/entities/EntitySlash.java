package mods.aginsun.kingdoms.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import java.util.List;

public final class EntitySlash extends EntityBlaze {

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
         this.field_70170_p.newExplosion((Entity)null, this.posX, this.posY + 1.0D, this.posZ, 2.0F, true, false);
      }

      if(this.counter % 2 == 0 && this.surround) {
         List list = this.field_70170_p.getEntitiesWithinAABB(EntityLiving.class, AxisAlignedBB.getBoundingBox(this.posX, this.posY, this.posZ, this.posX + 1.0D, this.posY + 1.0D, this.posZ + 1.0D).expand(this.range, this.range, this.range));
         if(!list.isEmpty()) {
            boolean flag = true;
            EntityLivingBase entityliving = (EntityLivingBase)list.get(this.field_70170_p.rand.nextInt(list.size()));
            if(entityliving instanceof EntityPlayer || entityliving instanceof EntitySlash) {
               flag = false;
            }

            if(this.entityplayer != null && flag) {
               this.field_70170_p.newExplosion(this.entityplayer, entityliving.posX, entityliving.posY + 1.0D, entityliving.posZ, 1.0F, true, false);
            }
         }
      }

   }

   protected void attackEntity(Entity entity, float f) {}

   protected void fall(float f) {}

   public void writeEntityToNBT(NBTTagCompound nbttagcompound) {}

   public void readEntityFromNBT(NBTTagCompound nbttagcompound) {}

   @Override
   protected Item getDropItem()
   {
      return Item.getItemFromBlock(Blocks.air);
   }

   public boolean isBurning() {
      return false;
   }

   protected void dropFewItems(boolean flag, int i) {}
}