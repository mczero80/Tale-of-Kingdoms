package mods.aginsun.kingdoms.entities;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import java.util.List;

import mods.aginsun.kingdoms.entities.api.EntityNPC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.src.ModLoader;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityDefendPriest extends EntityNPC {

   private World field_70170_p = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(0);
   private EntityPlayer player;
   private static ItemStack defaultHeldItem = new ItemStack(Items.stick, 1);
   private boolean follow;
   private boolean checkPlayer;
   private int healCounter;
   private int whenHealing;
   protected int attackStrength;
   public boolean isSwinging;
   public int field_110158_av;


   public EntityDefendPriest(World world) {
      super(world, defaultHeldItem, 40.0F);
      this.player = FMLClientHandler.instance().getClient().thePlayer;
      this.follow = false;
      this.checkPlayer = true;
      this.healCounter = 0;
      this.whenHealing = 0;
      this.field_70170_p = world;
      this.field_70178_ae = false;
      this.attackStrength = 10;
   }

   public void func_70636_d() {
      super.func_70636_d();
      if(this.whenHealing < 50) {
         for(int minecraft = 0; minecraft < 2; ++minecraft) {
            this.field_70170_p.spawnParticle("heart", this.field_70165_t + (this.field_70146_Z.nextDouble() - 0.5D) * (double)this.field_70130_N, this.field_70163_u + this.field_70146_Z.nextDouble() * (double)this.field_70131_O - 0.25D, this.field_70161_v + (this.field_70146_Z.nextDouble() - 0.5D) * (double)this.field_70130_N, (this.field_70146_Z.nextDouble() - 0.5D) * 2.0D, -this.field_70146_Z.nextDouble(), (this.field_70146_Z.nextDouble() - 0.5D) * 2.0D);
         }
      }

      ++this.whenHealing;
      if(this.whenHealing > 500) {
         this.whenHealing = 100;
      }

      if(this.follow) {
         Minecraft var5 = Minecraft.getMinecraft();
         EntityClientPlayerMP entityplayersp = var5.thePlayer;
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
            entityplayer.addChatMessage(new ChatComponentText("Priest: I will follow you."));
         }
      } else {
         this.follow = false;
         if(!this.field_70170_p.isRemote) {
            entityplayer.addChatMessage(new ChatComponentText("Priest: I will guard this area."));
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
      if(this.checkPlayer) {
         for(int flag = 0; flag < this.field_70170_p.loadedEntityList.size(); ++flag) {
            Entity list = (Entity)this.field_70170_p.loadedEntityList.get(flag);
            if(list instanceof EntityPlayer) {
               this.player = (EntityPlayer)list;
            }
         }

         if(this.player != null && this.player.func_70068_e(this) <= 64.0D) {
            this.follow = true;
         }
      }

      this.checkPlayer = false;
      boolean var5 = false;
      List var6 = this.field_70170_p.getEntitiesWithinAABB(EntityLiving.class, AxisAlignedBB.getBoundingBox(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0D, this.field_70163_u + 1.0D, this.field_70161_v + 1.0D).expand(16.0D, 4.0D, 16.0D));
      if(!var6.isEmpty()) {
         EntityLivingBase entityliving = (EntityLivingBase)var6.get(this.field_70170_p.rand.nextInt(var6.size()));
         if(this.healCounter > 20 && (entityliving instanceof EntityDefendBandit || entityliving instanceof EntityDefendKnight || entityliving instanceof EntityDefendMage || entityliving instanceof EntityDefendPaladin || entityliving instanceof EntityDefendWarrior || entityliving instanceof EntityDefendArcher || entityliving instanceof EntityHired || entityliving instanceof EntityPlayer || entityliving instanceof EntityPlayerSP) && entityliving.getHealth() < 15.0F) {
            entityliving.heal(2.0F);
            System.out.println("heal");
            var5 = true;
            this.whenHealing = 0;
         }
      }

      if(var5) {
         this.healCounter = 0;
      }

      ++this.healCounter;
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
