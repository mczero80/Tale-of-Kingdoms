package mods.aginsun.kingdoms.entities;

import java.util.List;
import mods.aginsun.kingdoms.entities.EntityDefendArcher;
import mods.aginsun.kingdoms.entities.EntityDefendBandit;
import mods.aginsun.kingdoms.entities.EntityDefendKnight;
import mods.aginsun.kingdoms.entities.EntityDefendMage;
import mods.aginsun.kingdoms.entities.EntityDefendPaladin;
import mods.aginsun.kingdoms.entities.EntityDefendWarrior;
import mods.aginsun.kingdoms.entities.EntityHired;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityLightningBoltAttack extends EntityLightningBolt {

   private int field_70262_b;
   public long field_70264_a = 0L;
   private int field_70263_c;


   public EntityLightningBoltAttack(World world, double d, double d1, double d2) {
      super(world, d - 1000000.0D, d1 - 1000000.0D, d2 - 1000000.0D);
      this.func_70012_b(d, d1, d2, 0.0F, 0.0F);
      this.field_70262_b = 2;
      this.field_70264_a = this.field_70146_Z.nextLong();
      this.field_70263_c = this.field_70146_Z.nextInt(3) + 1;
      if(world.difficultySetting >= 2 && world.doChunksNearChunkExist(MathHelper.floor_double(d), MathHelper.floor_double(d1), MathHelper.floor_double(d2), 10)) {
         int i = MathHelper.floor_double(d);
         int j = MathHelper.floor_double(d1);
         int k = MathHelper.floor_double(d2);
         if(world.getBlockId(i, j, k) == 0) {
            ;
         }

         for(int l = 0; l < 4; ++l) {
            int i1 = MathHelper.floor_double(d) + this.field_70146_Z.nextInt(3) - 1;
            int j1 = MathHelper.floor_double(d1) + this.field_70146_Z.nextInt(3) - 1;
            int k1 = MathHelper.floor_double(d2) + this.field_70146_Z.nextInt(3) - 1;
            if(world.getBlockId(i1, j1, k1) == 0) {
               ;
            }
         }
      }

   }

   public void func_70071_h_() {
      if(this.field_70262_b == 2) {
         this.field_70170_p.playSoundEffect(this.field_70165_t, this.field_70163_u, this.field_70161_v, "ambient.weather.thunder", 10000.0F, 0.8F + this.field_70146_Z.nextFloat() * 0.2F);
         this.field_70170_p.playSoundEffect(this.field_70165_t, this.field_70163_u, this.field_70161_v, "random.explode", 2.0F, 0.5F + this.field_70146_Z.nextFloat() * 0.2F);
      }

      --this.field_70262_b;
      if(this.field_70262_b < 0) {
         if(this.field_70263_c == 0) {
            this.func_70106_y();
         } else if(this.field_70262_b < -this.field_70146_Z.nextInt(10)) {
            --this.field_70263_c;
            this.field_70262_b = 1;
            this.field_70264_a = this.field_70146_Z.nextLong();
            if(this.field_70170_p.doChunksNearChunkExist(MathHelper.floor_double(this.field_70165_t), MathHelper.floor_double(this.field_70163_u), MathHelper.floor_double(this.field_70161_v), 10)) {
               int d = MathHelper.floor_double(this.field_70165_t);
               int j = MathHelper.floor_double(this.field_70163_u);
               int list = MathHelper.floor_double(this.field_70161_v);
               if(this.field_70170_p.getBlockId(d, j, list) == 0) {
                  ;
               }
            }
         }
      }

      if(this.field_70262_b >= 0) {
         double var7 = 3.0D;
         List var8 = this.field_70170_p.getEntitiesWithinAABBExcludingEntity(this, AxisAlignedBB.getBoundingBox(this.field_70165_t - var7, this.field_70163_u - var7, this.field_70161_v - var7, this.field_70165_t + var7, this.field_70163_u + 6.0D + var7, this.field_70161_v + var7));

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

         this.field_70170_p.lastLightningBolt = 2;
      }

   }

   protected void func_70088_a() {}

   protected void func_70037_a(NBTTagCompound nbttagcompound) {}

   protected void func_70014_b(NBTTagCompound nbttagcompound) {}

   public boolean func_70102_a(Vec3 vec3d) {
      return this.field_70262_b >= 0;
   }
}
