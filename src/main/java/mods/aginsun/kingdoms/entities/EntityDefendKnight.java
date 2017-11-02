package mods.aginsun.kingdoms.entities;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import java.util.List;

import mods.aginsun.kingdoms.entities.api.EntityNPC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public final class EntityDefendKnight extends EntityNPC {

   private World field_70170_p = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(0);
   private EntityPlayer player;
   private static ItemStack defaultHeldItem = new ItemStack(Items.iron_sword, 1);
   private int level;
   private boolean follow;
   private boolean checkPlayer;
   private boolean createdMarker;
   private EntityDefendMarker defend;
   protected int attackStrength;
   public boolean isSwinging;
   public int field_110158_av;


   public EntityDefendKnight(World world) {
      super(world, defaultHeldItem, 40.0F);
      this.player = FMLClientHandler.instance().getClient().thePlayer;
      this.level = 0;
      this.follow = false;
      this.checkPlayer = true;
      this.createdMarker = false;
      this.field_70170_p = world;
      this.isImmuneToFire = false;
      this.attackStrength = 8;
   }

   public void upgrade() {
      EntityLiving entityliving = (EntityLiving)EntityList.createEntityByName("DefendPaladin", this.field_70170_p);
      entityliving.setLocationAndAngles(this.posX, this.posY, this.posZ, 0.0F, 0.0F);
      if(!this.field_70170_p.isRemote) {
         this.field_70170_p.spawnEntityInWorld(entityliving);
      }

      this.setDead();
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
      this.player = entityplayer;
      if(!this.follow) {
         this.follow = true;
         if(!this.field_70170_p.isRemote) {
            entityplayer.addChatMessage(new ChatComponentText("Knight: I will follow you."));
         }

         this.defend.setDead();
         this.createdMarker = false;
      } else {
         this.follow = false;
         if(!this.field_70170_p.isRemote) {
            entityplayer.addChatMessage(new ChatComponentText("Knight:I will guard this area."));
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
      if(this.checkPlayer) {
         for(int list = 0; list < this.field_70170_p.loadedEntityList.size(); ++list) {
            entity1 = (Entity)this.field_70170_p.loadedEntityList.get(list);
            if(entity1 instanceof EntityPlayer) {
               this.player = (EntityPlayer)entity1;
            }
         }

         if(this.player != null && this.player.getDistanceSqToEntity(this) <= 64.0D) {
            this.follow = true;
         }
      }

      this.checkPlayer = false;
      if(this.entityToAttack == null && !this.hasPath()) {
         List var4 = this.field_70170_p.getEntitiesWithinAABB(EntityCreature.class, AxisAlignedBB.getBoundingBox(this.posX, this.posY, this.posZ, this.posX + 1.0D, this.posY + 1.0D, this.posZ + 1.0D).expand(16.0D, 4.0D, 16.0D));
         if(!var4.isEmpty()) {
            entity1 = (Entity)var4.get(this.field_70170_p.rand.nextInt(var4.size()));
            if(this.canEntityBeSeen(entity1) && (entity1 instanceof EntityMob || entity1 instanceof EntityReficulSoldier || entity1 instanceof EntityReficulGuardian || entity1 instanceof EntityReficulMage)) {
               this.entityToAttack = entity1;
            }
         }
      }

   }

   protected void attackEntity(Entity entity, float f) {
      if(this.attackTime <= 0 && f < 2.0F && entity.boundingBox.maxY > this.boundingBox.minY && entity.boundingBox.minY < this.boundingBox.maxY) {
         this.attackTime = 20;
         this.swingItem();
         this.attackEntityAsMob(entity);
         ++this.level;
         if(this.level > 12) {
            this.upgrade();
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

   public void swingItem() {
      if(!this.isSwinging || this.field_110158_av < 0) {
         this.field_110158_av = -1;
         this.isSwinging = true;
      }

   }

   public void onDeath(DamageSource damagesource) {}
}