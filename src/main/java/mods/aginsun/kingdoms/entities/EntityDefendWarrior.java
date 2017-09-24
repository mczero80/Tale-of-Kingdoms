package mods.aginsun.kingdoms.entities;

import cpw.mods.fml.client.FMLClientHandler;
import java.util.List;
import java.util.Random;

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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.potion.Potion;
import net.minecraft.src.ModLoader;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityDefendWarrior extends EntityNPC {

   private World field_70170_p;
   private Random field_70146_Z = new Random();
   private EntityPlayer player;
   private static ItemStack defaultHeldItem = new ItemStack(Item.swordIron, 1);
   private int level;
   private boolean follow;
   private boolean checkPlayer;
   private boolean createdMarker;
   private EntityDefendMarker defend;
   protected int attackStrength;
   public boolean isSwinging;
   public int field_110158_av;


   public EntityDefendWarrior(World world) {
      super(world, defaultHeldItem, 40.0F);
      this.player = FMLClientHandler.instance().getClient().thePlayer;
      this.level = 0;
      this.follow = false;
      this.checkPlayer = true;
      this.createdMarker = false;
      this.field_70170_p = world;
      this.field_70178_ae = false;
      this.attackStrength = 6;
   }

   protected boolean func_70692_ba() {
      return false;
   }

   public void upgrade() {
      EntityLiving entityliving = (EntityLiving)EntityList.createEntityByName("DefendKnight", this.field_70170_p);
      entityliving.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0F, 0.0F);
      if(!this.field_70170_p.isRemote) {
         this.field_70170_p.spawnEntityInWorld(entityliving);
      }

      this.func_70106_y();
   }

   public boolean func_70104_M() {
      return true;
   }

   protected boolean func_70780_i() {
      return false;
   }

   public void func_70636_d() {
      super.func_70636_d();
      Minecraft minecraft = ModLoader.getMinecraftInstance();
      EntityClientPlayerMP entityplayersp = minecraft.thePlayer;
      float f1;
      PathEntity pathentity1;
      if(this.follow) {
         if(entityplayersp != null) {
            f1 = entityplayersp.func_70032_d(this);
            if(f1 > 5.0F && f1 < 18.0F) {
               pathentity1 = this.field_70170_p.getPathEntityToEntity(this, entityplayersp, 16.0F, true, false, false, true);
            } else {
               pathentity1 = null;
            }

            this.func_70778_a(pathentity1);
         }
      } else {
         if(!this.createdMarker) {
            System.out.println("Defend Location");
            this.defend = (EntityDefendMarker)EntityList.createEntityByName("DefendMark", this.field_70170_p);
            this.defend.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0F, 0.0F);
            this.field_70170_p.spawnEntityInWorld(this.defend);
            this.createdMarker = true;
         }

         if(this.createdMarker && this.defend != null) {
            f1 = this.defend.func_70032_d(this);
            if(f1 > 5.0F && f1 < 40.0F) {
               pathentity1 = this.field_70170_p.getPathEntityToEntity(this, this.defend, 40.0F, true, false, false, true);
            } else {
               pathentity1 = null;
            }

            this.func_70778_a(pathentity1);
         }
      }

   }

   public boolean func_70085_c(EntityPlayer entityplayer) {
      this.player = entityplayer;
      if(!this.follow) {
         this.follow = true;
         if(!this.field_70170_p.isRemote) {
            entityplayer.addChatMessage("Warrior: I will follow you.");
         }

         this.defend.func_70106_y();
         this.createdMarker = false;
      } else {
         this.follow = false;
         if(!this.field_70170_p.isRemote) {
            entityplayer.addChatMessage("Warrior:I will guard this area.");
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
      if(this.checkPlayer) {
         for(int list = 0; list < this.field_70170_p.loadedEntityList.size(); ++list) {
            entity1 = (Entity)this.field_70170_p.loadedEntityList.get(list);
            if(entity1 instanceof EntityPlayer) {
               this.player = (EntityPlayer)entity1;
            }
         }

         if(this.player != null && this.player.func_70068_e(this) <= 64.0D) {
            this.follow = true;
         }
      }

      this.checkPlayer = false;
      if(this.field_70789_a == null && !this.func_70781_l()) {
         List var4 = this.field_70170_p.getEntitiesWithinAABB(EntityCreature.class, AxisAlignedBB.getBoundingBox(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0D, this.field_70163_u + 1.0D, this.field_70161_v + 1.0D).expand(16.0D, 4.0D, 16.0D));
         if(!var4.isEmpty()) {
            entity1 = (Entity)var4.get(this.field_70170_p.rand.nextInt(var4.size()));
            if(this.func_70685_l(entity1) && (entity1 instanceof EntityMob || entity1 instanceof EntityReficulSoldier || entity1 instanceof EntityReficulGuardian || entity1 instanceof EntityReficulMage)) {
               this.field_70789_a = entity1;
            }
         }
      }

   }

   protected void func_70785_a(Entity entity, float f) {
      if(this.field_70724_aR <= 0 && f < 2.0F && entity.boundingBox.maxY > this.field_70121_D.minY && entity.boundingBox.minY < this.field_70121_D.maxY) {
         this.field_70724_aR = 20;
         this.func_71038_i();
         this.func_70652_k(entity);
         ++this.level;
         if(this.level > 7) {
            this.upgrade();
         }
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

   public ItemStack func_70694_bm() {
      return defaultHeldItem;
   }

   public void func_71038_i() {
      if(!this.isSwinging || this.field_110158_av < 0) {
         this.field_110158_av = -1;
         this.isSwinging = true;
      }

   }

   public boolean attackEntityFrom(DamageSource damagesource, int i) {
      if(this.field_70146_Z.nextInt(3) == 0) {
         boolean flag = true;
         Entity entity = damagesource.getSourceOfDamage();
         if(entity instanceof EntityDefendBandit || entity instanceof EntityDefendKnight || entity instanceof EntityDefendPaladin || entity instanceof EntityDefendWarrior || entity instanceof EntityDefendArcher || entity instanceof EntityHired || entity instanceof EntityPlayer || entity instanceof EntityPlayerSP) {
            flag = false;
         }

         if(flag) {
            super.func_70097_a(damagesource, (float)i);
         }
      }

      return true;
   }

   public void func_70645_a(DamageSource damagesource) {}

}
