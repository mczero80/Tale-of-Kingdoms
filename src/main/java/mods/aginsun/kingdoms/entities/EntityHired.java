package mods.aginsun.kingdoms.entities;

import java.util.List;
import java.util.Random;

import mods.aginsun.kingdoms.entities.api.EntityNPC;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityHired extends EntityNPC {

   private World field_70170_p;
   private EntityPlayer player;
   private Random field_70146_Z = new Random();
   private static ItemStack defaultHeldItem = new ItemStack(Item.swordIron, 1);
   public boolean isSwinging;
   public int field_110158_av;
   protected int attackStrength;


   public EntityHired(World world) {
      super(world, defaultHeldItem, 40.0F);
      this.field_70170_p = world;
      this.field_70178_ae = false;
      this.attackStrength = 10;
   }

   public boolean func_70104_M() {
      return false;
   }

   public boolean func_70085_c(EntityPlayer entityplayer) {
      this.player = entityplayer;
      if(defaultHeldItem.itemID == 267) {
         defaultHeldItem = new ItemStack(Item.bow, 1);
         if(!this.field_70170_p.isRemote) {
            this.player.addChatMessage("Hunter: I shall use my bow.");
         }
      } else {
         defaultHeldItem = new ItemStack(Item.swordIron, 1);
         if(!this.field_70170_p.isRemote) {
            this.player.addChatMessage("Hunter: I shall use my sword.");
         }
      }

      return true;
   }

   protected void func_70626_be() {
      super.func_70626_be();
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

      this.field_70733_aJ = (float)this.field_110158_av / (float)i;

      Entity entity1;
      for(int list = 0; list < this.field_70170_p.loadedEntityList.size(); ++list) {
         entity1 = (Entity)this.field_70170_p.loadedEntityList.get(list);
         if(entity1 instanceof EntityPlayer) {
            this.player = (EntityPlayer)entity1;
         }
      }

      if(!this.field_70787_b && !this.func_70781_l() && this.field_70154_o == null && this.player != null) {
         float var4 = this.player.func_70032_d(this);
         if(var4 > 5.0F) {
            this.getPathOrWalkableBlock(this.player, var4);
         }
      }

      if(this.field_70789_a == null && !this.func_70781_l()) {
         List var5 = this.field_70170_p.getEntitiesWithinAABB(EntityCreature.class, AxisAlignedBB.getBoundingBox(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0D, this.field_70163_u + 1.0D, this.field_70161_v + 1.0D).expand(16.0D, 4.0D, 16.0D));
         if(!var5.isEmpty()) {
            entity1 = (Entity)var5.get(this.field_70170_p.rand.nextInt(var5.size()));
            if(this.func_70685_l(entity1) && (entity1 instanceof EntityMob || entity1 instanceof EntityReficulSoldier || entity1 instanceof EntityReficulGuardian || entity1 instanceof EntityReficulMage)) {
               this.field_70789_a = entity1;
            }
         }
      }

   }

   private void getPathOrWalkableBlock(Entity entity, float f) {
      PathEntity pathentity = this.field_70170_p.getPathEntityToEntity(this, entity, 16.0F, true, false, false, true);
      if(pathentity == null && f > 12.0F) {
         int i = MathHelper.floor_double(entity.posX) - 2;
         int j = MathHelper.floor_double(entity.posZ) - 2;
         int k = MathHelper.floor_double(entity.boundingBox.minY);

         for(int l = 0; l <= 4; ++l) {
            for(int i1 = 0; i1 <= 4; ++i1) {
               if((l < 1 || i1 < 1 || l > 3 || i1 > 3) && this.field_70170_p.isBlockNormalCube(i + l, k - 1, j + i1) && !this.field_70170_p.isBlockNormalCube(i + l, k, j + i1) && !this.field_70170_p.isBlockNormalCube(i + l, k + 1, j + i1)) {
                  this.func_70012_b((double)((float)(i + l) + 0.5F), (double)k, (double)((float)(j + i1) + 0.5F), this.field_70177_z, this.field_70125_A);
                  return;
               }
            }
         }
      } else {
         this.func_70778_a(pathentity);
      }

   }

   public boolean func_70652_k(Entity entity) {
      int i = this.attackStrength;
      if(this.func_70644_a(Potion.damageBoost)) {
         i += 3 << this.func_70660_b(Potion.damageBoost).getAmplifier();
      }

      if(this.func_70644_a(Potion.weakness)) {
         i -= 2 << this.func_70660_b(Potion.weakness).getAmplifier();
      }

      return entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float)i);
   }

   protected void func_70785_a(Entity entity, float f) {
      if(defaultHeldItem.itemID == 267) {
         if(this.field_70724_aR <= 0 && f < 2.0F && entity.boundingBox.maxY > this.field_70121_D.minY && entity.boundingBox.minY < this.field_70121_D.maxY) {
            this.func_71038_i();
            this.field_70724_aR = 20;
            this.func_70652_k(entity);
         }
      } else if(f < 10.0F) {
         double d = entity.posX - this.field_70165_t;
         double d1 = entity.posZ - this.field_70161_v;
         if(this.field_70724_aR == 0) {
            EntityArrow entityarrow = new EntityArrow(this.field_70170_p, this, 1.0F);
            double d2 = entity.posY + (double)entity.getEyeHeight() - 0.699999988079071D - entityarrow.field_70163_u;
            float f1 = MathHelper.sqrt_double(d * d + d1 * d1) * 0.2F;
            this.field_70170_p.playSoundAtEntity(this, "random.bow", 1.0F, 1.0F / (this.field_70146_Z.nextFloat() * 0.4F + 0.8F));
            this.field_70170_p.spawnEntityInWorld(entityarrow);
            entityarrow.setThrowableHeading(d, d2 + (double)f1, d1, 1.6F, 12.0F);
            this.field_70724_aR = 30;
         }

         this.field_70177_z = (float)(Math.atan2(d1, d) * 180.0D / 3.1415927410125732D) - 90.0F;
         this.field_70787_b = true;
      }

   }

   public boolean attackEntityFrom(DamageSource damagesource, int i) {
      if(this.field_70146_Z.nextInt(2) == 0) {
         boolean flag = true;
         Entity entity = damagesource.getSourceOfDamage();
         if(!this.field_70170_p.isRemote && entity instanceof EntityDefendBandit || entity instanceof EntityDefendKnight || entity instanceof EntityDefendPaladin || entity instanceof EntityDefendWarrior || entity instanceof EntityDefendArcher || entity instanceof EntityHired || entity instanceof EntityPlayer || entity instanceof EntityPlayerSP) {
            flag = false;
         }

         if(flag) {
            super.func_70097_a(damagesource, (float)i);
         }
      }

      return true;
   }

   public void func_71038_i() {
      if(!this.isSwinging || this.field_110158_av < 0) {
         this.field_110158_av = -1;
         this.isSwinging = true;
      }

   }

}
