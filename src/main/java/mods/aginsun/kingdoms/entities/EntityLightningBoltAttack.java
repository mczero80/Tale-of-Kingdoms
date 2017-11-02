package mods.aginsun.kingdoms.entities;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import java.util.List;

public final class EntityLightningBoltAttack extends EntityLightningBolt {

   private int field_70262_b;
   public long field_70264_a = 0L;
   private int field_70263_c;


   public EntityLightningBoltAttack(World world, double d, double d1, double d2) {
      super(world, d - 1000000.0D, d1 - 1000000.0D, d2 - 1000000.0D);
      this.setLocationAndAngles(d, d1, d2, 0.0F, 0.0F);
      this.field_70262_b = 2;
      this.field_70264_a = this.rand.nextLong();
      this.field_70263_c = this.rand.nextInt(3) + 1;
      if(world.difficultySetting == EnumDifficulty.NORMAL && world.doChunksNearChunkExist(MathHelper.floor_double(d), MathHelper.floor_double(d1), MathHelper.floor_double(d2), 10)) {
         int i = MathHelper.floor_double(d);
         int j = MathHelper.floor_double(d1);
         int k = MathHelper.floor_double(d2);
         if(world.getBlock(i, j, k) == Blocks.air) {
            ;
         }

         for(int l = 0; l < 4; ++l) {
            int i1 = MathHelper.floor_double(d) + this.rand.nextInt(3) - 1;
            int j1 = MathHelper.floor_double(d1) + this.rand.nextInt(3) - 1;
            int k1 = MathHelper.floor_double(d2) + this.rand.nextInt(3) - 1;
            if(world.getBlock(i1, j1, k1) == Blocks.air) {
               ;
            }
         }
      }

   }

   public void onUpdate() {
      if(this.field_70262_b == 2) {
         this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "ambient.weather.thunder", 10000.0F, 0.8F + this.rand.nextFloat() * 0.2F);
         this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "random.explode", 2.0F, 0.5F + this.rand.nextFloat() * 0.2F);
      }

      --this.field_70262_b;
      if(this.field_70262_b < 0) {
         if(this.field_70263_c == 0) {
            this.setDead();
         } else if(this.field_70262_b < -this.rand.nextInt(10)) {
            --this.field_70263_c;
            this.field_70262_b = 1;
            this.field_70264_a = this.rand.nextLong();
            if(this.worldObj.doChunksNearChunkExist(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ), 10)) {
               int d = MathHelper.floor_double(this.posX);
               int j = MathHelper.floor_double(this.posY);
               int list = MathHelper.floor_double(this.posZ);
               if(this.worldObj.getBlock(d, j, list) == Blocks.air) {
                  ;
               }
            }
         }
      }

      if(this.field_70262_b >= 0) {
         double var7 = 3.0D;
         List var8 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, AxisAlignedBB.getBoundingBox(this.posX - var7, this.posY - var7, this.posZ - var7, this.posX + var7, this.posY + 6.0D + var7, this.posZ + var7));

         for(int l = 0; l < var8.size(); ++l) {
            boolean flag = true;
            Entity entity = (Entity)var8.get(l);
            if(entity instanceof EntityDefendBandit || entity instanceof EntityDefendMage || entity instanceof EntityDefendKnight || entity instanceof EntityDefendPaladin || entity instanceof EntityDefendWarrior || entity instanceof EntityDefendArcher || entity instanceof EntityHired || entity instanceof EntityPlayer || entity instanceof EntityPlayerSP) {
               flag = false;
            }

            if(flag) {
               entity.setFire(8);
            }
         }

         this.worldObj.lastLightningBolt = 2;
      }

   }

   protected void entityInit() {}

   protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {}

   protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {}

   @Override
   public boolean isInRangeToRenderDist(double p_70112_1_)
   {
      return this.field_70262_b >= 0;
   }
}