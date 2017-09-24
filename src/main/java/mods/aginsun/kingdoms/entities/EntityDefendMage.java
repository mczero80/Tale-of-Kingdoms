package mods.aginsun.kingdoms.entities;

import cpw.mods.fml.client.FMLClientHandler;
import java.util.List;

import mods.aginsun.kingdoms.entities.api.EntityNPC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
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

public class EntityDefendMage extends EntityNPC {

   private EntityPlayer player;
   private static ItemStack defaultHeldItem;
   private boolean follow;
   private boolean checkPlayer;
   protected int attackStrength;
   public boolean isSwinging;
   public int field_110158_av;


   public EntityDefendMage(World world) {
      super(world, defaultHeldItem, 40.0F);
      this.player = FMLClientHandler.instance().getClient().thePlayer;
      defaultHeldItem = new ItemStack(Item.stick, 1);
      this.follow = false;
      this.checkPlayer = true;
      this.field_70170_p = world;
      this.field_70178_ae = false;
      this.attackStrength = 10;
   }

   public void func_70636_d() {
      super.func_70636_d();
      if(this.follow) {
         Minecraft minecraft = ModLoader.getMinecraftInstance();
         EntityClientPlayerMP entityplayersp = minecraft.thePlayer;
         if(entityplayersp != null) {
            float f = entityplayersp.func_70032_d(this);
            PathEntity pathentity;
            if(f > 5.0F && f < 18.0F) {
               pathentity = this.field_70170_p.getPathEntityToEntity(this, entityplayersp, 16.0F, true, false, false, true);
            } else {
               pathentity = null;
            }

            this.func_70778_a(pathentity);
         }
      }

   }

   public boolean func_70085_c(EntityPlayer entityplayer) {
      this.player = entityplayer;
      if(!this.follow) {
         this.follow = true;
         if(!this.field_70170_p.isRemote) {
            entityplayer.addChatMessage("Mage: I will follow you.");
         }
      } else {
         this.follow = false;
         if(!this.field_70170_p.isRemote) {
            entityplayer.addChatMessage("Mage: I will guard this area.");
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
      if(f < 10.0F) {
         if(this.field_70724_aR == 0) {
            this.field_70724_aR = 80;
            this.func_71038_i();
            this.func_70652_k(entity);
            this.func_70625_a(entity, 30.0F, 30.0F);
         }

         this.field_70787_b = true;
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

   public boolean attackEntityFrom(DamageSource damagesource, int i) {
      boolean flag = true;
      Entity entity = damagesource.getSourceOfDamage();
      if(entity instanceof EntityDefendBandit || entity instanceof EntityDefendKnight || entity instanceof EntityDefendPaladin || entity instanceof EntityDefendWarrior || entity instanceof EntityDefendArcher || entity instanceof EntityHired || entity instanceof EntityDefendMage || entity instanceof EntityPlayer || entity instanceof EntityPlayerSP) {
         flag = false;
      }

      if(flag) {
         super.func_70097_a(damagesource, (float)i);
      }

      return true;
   }

   public void func_70645_a(DamageSource damagesource) {}

   public void func_71038_i() {
      if(!this.isSwinging || this.field_110158_av < 0) {
         this.field_110158_av = -1;
         this.isSwinging = true;
      }

   }
}
