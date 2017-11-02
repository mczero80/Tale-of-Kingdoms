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
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public final class EntityHired extends EntityNPC {

   private World field_70170_p;
   private EntityPlayer player;
   private Random field_70146_Z = new Random();
   private static ItemStack defaultHeldItem = new ItemStack(Items.iron_sword, 1);
   public boolean isSwinging;
   public int field_110158_av;
   protected int attackStrength;


   public EntityHired(World world) {
      super(world, defaultHeldItem, 40.0F);
      this.field_70170_p = world;
      this.isImmuneToFire = false;
      this.attackStrength = 10;
   }

   public boolean canBePushed() {
      return false;
   }

   public boolean interact(EntityPlayer entityplayer) {
      this.player = entityplayer;
      if(defaultHeldItem.getItem() == Item.getItemById(267)) {
         defaultHeldItem = new ItemStack(Items.bow, 1);
         if(!this.field_70170_p.isRemote) {
            this.player.addChatMessage(new ChatComponentText("Hunter: I shall use my bow."));
         }
      } else {
         defaultHeldItem = new ItemStack(Items.iron_sword, 1);
         if(!this.field_70170_p.isRemote) {
            this.player.addChatMessage(new ChatComponentText("Hunter: I shall use my sword."));
         }
      }

      return true;
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

      Entity entity1;
      for(int list = 0; list < this.field_70170_p.loadedEntityList.size(); ++list) {
         entity1 = (Entity)this.field_70170_p.loadedEntityList.get(list);
         if(entity1 instanceof EntityPlayer) {
            this.player = (EntityPlayer)entity1;
         }
      }

      if(!this.hasAttacked && !this.hasPath() && this.ridingEntity == null && this.player != null) {
         float var4 = this.player.getDistanceToEntity(this);
         if(var4 > 5.0F) {
            this.getPathOrWalkableBlock(this.player, var4);
         }
      }

      if(this.entityToAttack == null && !this.hasPath()) {
         List var5 = this.field_70170_p.getEntitiesWithinAABB(EntityCreature.class, AxisAlignedBB.getBoundingBox(this.posX, this.posY, this.posZ, this.posX + 1.0D, this.posY + 1.0D, this.posZ + 1.0D).expand(16.0D, 4.0D, 16.0D));
         if(!var5.isEmpty()) {
            entity1 = (Entity)var5.get(this.field_70170_p.rand.nextInt(var5.size()));
            if(this.canEntityBeSeen(entity1) && (entity1 instanceof EntityMob || entity1 instanceof EntityReficulSoldier || entity1 instanceof EntityReficulGuardian || entity1 instanceof EntityReficulMage)) {
               this.entityToAttack = entity1;
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
               if((l < 1 || i1 < 1 || l > 3 || i1 > 3) && this.field_70170_p.isBlockNormalCubeDefault(i + l, k - 1, j + i1, false) && !this.field_70170_p.isBlockNormalCubeDefault(i + l, k, j + i1, false) && !this.field_70170_p.isBlockNormalCubeDefault(i + l, k + 1, j + i1, false)) {
                  this.setLocationAndAngles((double)((float)(i + l) + 0.5F), (double)k, (double)((float)(j + i1) + 0.5F), this.rotationYaw, this.rotationPitch);
                  return;
               }
            }
         }
      } else {
         this.setPathToEntity(pathentity);
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

   protected void attackEntity(Entity entity, float f) {
      if(defaultHeldItem.getItem() == Item.getItemById(267)) {
         if(this.attackTime <= 0 && f < 2.0F && entity.boundingBox.maxY > this.boundingBox.minY && entity.boundingBox.minY < this.boundingBox.maxY) {
            this.swingItem();
            this.attackTime = 20;
            this.attackEntityAsMob(entity);
         }
      } else if(f < 10.0F) {
         double d = entity.posX - this.posX;
         double d1 = entity.posZ - this.posZ;
         if(this.attackTime == 0) {
            EntityArrow entityarrow = new EntityArrow(this.field_70170_p, this, 1.0F);
            double d2 = entity.posY + (double)entity.getEyeHeight() - 0.699999988079071D - entityarrow.posY;
            float f1 = MathHelper.sqrt_double(d * d + d1 * d1) * 0.2F;
            this.field_70170_p.playSoundAtEntity(this, "random.bow", 1.0F, 1.0F / (this.field_70146_Z.nextFloat() * 0.4F + 0.8F));
            this.field_70170_p.spawnEntityInWorld(entityarrow);
            entityarrow.setThrowableHeading(d, d2 + (double)f1, d1, 1.6F, 12.0F);
            this.attackTime = 30;
         }

         this.rotationYaw = (float)(Math.atan2(d1, d) * 180.0D / 3.1415927410125732D) - 90.0F;
         this.hasAttacked = true;
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
            super.attackEntityFrom(damagesource, (float)i);
         }
      }

      return true;
   }

   public void swingItem() {
      if(!this.isSwinging || this.field_110158_av < 0) {
         this.field_110158_av = -1;
         this.isSwinging = true;
      }

   }

}
