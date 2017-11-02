package mods.aginsun.kingdoms.entities;

import cpw.mods.fml.client.FMLClientHandler;
import java.util.List;

import mods.aginsun.kingdoms.entities.api.EntityNPC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityList;
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

public class EntityDefendBandit extends EntityNPC {

   private EntityPlayer player;
   private static ItemStack defaultHeldItem = new ItemStack(Items.bow, 1);
   private boolean follow;
   private boolean checkPlayer;
   private boolean createdMarker;
   private EntityDefendMarker defend;


   public EntityDefendBandit(World world) {
      super(world, defaultHeldItem, 40.0F);
      this.player = FMLClientHandler.instance().getClient().thePlayer;
      this.follow = false;
      this.checkPlayer = true;
      this.createdMarker = false;
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
               pathentity1 = this.worldObj.getPathEntityToEntity(this, entityplayersp, 16.0F, true, false, false, true);
            } else {
               pathentity1 = null;
            }

            this.setPathToEntity(pathentity1);
         }
      } else {
         if(!this.createdMarker) {
            System.out.println("Defend Location");
            this.defend = (EntityDefendMarker)EntityList.createEntityByName("DefendMark", this.worldObj);
            this.defend.setLocationAndAngles(this.posX, this.posY, this.posZ, 0.0F, 0.0F);
            this.worldObj.spawnEntityInWorld(this.defend);
            this.createdMarker = true;
         }

         if(this.createdMarker && this.defend != null) {
            f1 = this.defend.getDistanceToEntity(this);
            if(f1 > 5.0F && f1 < 40.0F) {
               pathentity1 = this.worldObj.getPathEntityToEntity(this, this.defend, 40.0F, true, false, false, true);
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
         if(!this.world.isRemote) {
            entityplayer.addChatMessage(new ChatComponentText("Bandit: I will follow you."));
         }

         this.defend.setDead();
         this.createdMarker = false;
      } else {
         this.follow = false;
         if(!this.world.isRemote) {
            entityplayer.addChatMessage(new ChatComponentText("Bandit: I will guard this area."));
         }
      }

      return true;
   }

   protected void updateEntityActionState() {
      super.updateEntityActionState();
      Entity entity1;
      if(this.checkPlayer) {
         for(int list = 0; list < this.worldObj.loadedEntityList.size(); ++list) {
            entity1 = (Entity)this.worldObj.loadedEntityList.get(list);
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
         List var3 = this.worldObj.getEntitiesWithinAABB(EntityCreature.class, AxisAlignedBB.getBoundingBox(this.posX, this.posY, this.posZ, this.posX + 1.0D, this.posY + 1.0D, this.posZ + 1.0D).expand(16.0D, 4.0D, 16.0D));
         if(!var3.isEmpty()) {
            entity1 = (Entity)var3.get(this.worldObj.rand.nextInt(var3.size()));
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
            EntityArrow entityarrow = new EntityArrow(this.worldObj, this, 1.0F);
            double d2 = entity.posY + (double)entity.getEyeHeight() - 0.699999988079071D - entityarrow.posY;
            float f1 = MathHelper.sqrt_double(d * d + d1 * d1) * 0.2F;
            this.worldObj.playSoundAtEntity(this, "random.bow", 1.0F, 1.0F / (this.rand.nextFloat() * 0.4F + 0.8F));
            this.worldObj.spawnEntityInWorld(entityarrow);
            entityarrow.setThrowableHeading(d, d2 + (double)f1, d1, 1.6F, 12.0F);
            this.attackTime = 20;
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
