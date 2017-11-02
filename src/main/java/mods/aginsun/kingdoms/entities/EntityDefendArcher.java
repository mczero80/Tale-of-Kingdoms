package mods.aginsun.kingdoms.entities;

import mods.aginsun.kingdoms.entities.api.EntityNPC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.List;

public final class EntityDefendArcher extends EntityNPC {

   private World field_70170_p;
   private static ItemStack defaultHeldItem = new ItemStack(Items.bow, 1);
   private int level = 0;
   private boolean follow = false;
   private boolean checkPlayer = true;
   private boolean createdMarker = false;
   private EntityDefendMarker defend;
   protected int attackStrength;


   public EntityDefendArcher(World world) {
      super(world, defaultHeldItem, 40.0F);
      this.field_70170_p = world;
      this.isImmuneToFire = false;
      this.attackStrength = 10;
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      Minecraft minecraft = Minecraft.getMinecraft();
      EntityClientPlayerMP entityplayersp = minecraft.thePlayer;
      float f1;
      PathEntity pathentity1;
      if(this.follow) {
         if(entityplayersp != null) {
            f1 = entityplayersp.getDistanceToEntity(this);
            if(f1 > 5.0F && f1 < 18.0F) {
               pathentity1 = this.field_70170_p.getPathEntityToEntity(this, entityplayersp, 16.0F, true, false, false, true);
            } else {
               pathentity1 = null;
            }

            this.setPathToEntity(pathentity1);
         }
      } else {
         if(!this.createdMarker) {
            System.out.println("Defend Location");
            this.defend = (EntityDefendMarker)EntityList.createEntityByName("DefendMark", this.field_70170_p);
            this.defend.setLocationAndAngles(this.posX, this.posY, this.posZ, 0.0F, 0.0F);
            this.field_70170_p.spawnEntityInWorld(this.defend);
            this.createdMarker = true;
         }

         if(this.createdMarker && this.defend != null) {
            f1 = this.defend.getDistanceToEntity(this);
            if(f1 > 5.0F && f1 < 40.0F) {
               pathentity1 = this.field_70170_p.getPathEntityToEntity(this, this.defend, 40.0F, true, false, false, true);
            } else {
               pathentity1 = null;
            }

            this.setPathToEntity(pathentity1);
         }
      }

   }

   public boolean interact(EntityPlayer entityplayer) {
      if(!this.follow) {
         this.follow = true;
         if(!this.field_70170_p.isRemote) {
            entityplayer.addChatMessage(new ChatComponentText("Archer: I will follow you."));
         }

         this.defend.setDead();
         this.createdMarker = false;
      } else {
         this.follow = false;
         if(!this.field_70170_p.isRemote) {
            entityplayer.addChatMessage(new ChatComponentText("Archer:I will guard this area."));
         }
      }

      return true;
   }

   public void upgrade() {
      EntityLiving entityliving = (EntityLiving)EntityList.createEntityByName("DefendBandit", this.field_70170_p);
      entityliving.setLocationAndAngles(this.posX, this.posY, this.posZ, 0.0F, 0.0F);
      if(!this.field_70170_p.isRemote) {
         this.field_70170_p.spawnEntityInWorld(entityliving);
      }

      this.setDead();
   }

   protected void updateEntityActionState() {
      super.updateEntityActionState();
      Entity entity1;
      if(this.checkPlayer) {
         for(int list = 0; list < this.field_70170_p.loadedEntityList.size(); ++list) {
            entity1 = (Entity)this.field_70170_p.loadedEntityList.get(list);
            if(entity1 instanceof EntityPlayer) {
               super.player = (EntityPlayer)entity1;
            }
         }

         if(super.player != null && super.player.getDistanceSqToEntity(this) <= 64.0D) {
            this.follow = true;
         }
      }

      this.checkPlayer = false;
      if(this.entityToAttack == null && !this.hasPath()) {
         List var3 = this.field_70170_p.getEntitiesWithinAABB(EntityCreature.class, AxisAlignedBB.getBoundingBox(this.posX, this.posY, this.posZ, this.posX + 1.0D, this.posY + 1.0D, this.posZ + 1.0D).expand(16.0D, 4.0D, 16.0D));
         if(!var3.isEmpty()) {
            entity1 = (Entity)var3.get(this.field_70170_p.rand.nextInt(var3.size()));
            if(this.canEntityBeSeen(entity1) && (entity1 instanceof EntityMob || entity1 instanceof EntityReficulSoldier || entity1 instanceof EntityReficulGuardian || entity1 instanceof EntityReficulMage)) {
               this.entityToAttack = entity1;
            }
         }
      }

   }

   protected void attackEntity(Entity entity, float f) {
      if(f < 10.0F) {
         double d = entity.posX - this.posX;
         double d1 = entity.posZ - this.posZ;
         if(this.attackTime == 0) {
            EntityArrow entityarrow = new EntityArrow(this.field_70170_p, this, 1.0F);
            double d2 = entity.posY + (double)entity.getEyeHeight() - 0.699999988079071D - entityarrow.posY;
            float f1 = MathHelper.sqrt_double(d * d + d1 * d1) * 0.2F;
            this.field_70170_p.playSoundAtEntity(this, "random.bow", 1.0F, 1.0F / (this.rand.nextFloat() * 0.4F + 0.8F));
            this.field_70170_p.spawnEntityInWorld(entityarrow);
            entityarrow.setThrowableHeading(d, d2 + (double)f1, d1, 1.6F, 12.0F);
            this.attackTime = 30;
            ++this.level;
            if(this.level > 12) {
               this.upgrade();
            }
         }

         this.rotationYaw = (float)(Math.atan2(d1, d) * 180.0D / 3.1415927410125732D) - 90.0F;
         this.hasAttacked = true;
      }

   }

   public boolean attackEntityFrom(DamageSource damagesource, int i) {
      boolean flag = true;
      Entity entity = damagesource.getSourceOfDamage();
      if(entity instanceof EntityDefendBandit || entity instanceof EntityDefendKnight || entity instanceof EntityDefendMage || entity instanceof EntityDefendPaladin || entity instanceof EntityDefendWarrior || entity instanceof EntityDefendArcher || entity instanceof EntityHired || entity instanceof EntityPlayer || entity instanceof EntityPlayerSP) {
         flag = false;
      }

      if(flag) {
         super.attackEntityFrom(damagesource, (float)i);
      }

      return true;
   }

   public void onDeath(DamageSource damagesource) {}
}