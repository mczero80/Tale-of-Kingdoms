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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.src.ModLoader;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityDefendBandit extends EntityNPC {

   private EntityPlayer player;
   private static ItemStack defaultHeldItem = new ItemStack(Item.bow, 1);
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
         if(!this.world.isRemote) {
            entityplayer.addChatMessage(new ChatComponentText("Bandit: I will follow you."));
         }

         this.defend.func_70106_y();
         this.createdMarker = false;
      } else {
         this.follow = false;
         if(!this.world.isRemote) {
            entityplayer.addChatMessage(new ChatComponentText("Bandit: I will guard this area."));
         }
      }

      return true;
   }

   protected void func_70626_be() {
      super.func_70626_be();
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
         List var3 = this.field_70170_p.getEntitiesWithinAABB(EntityCreature.class, AxisAlignedBB.getBoundingBox(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0D, this.field_70163_u + 1.0D, this.field_70161_v + 1.0D).expand(16.0D, 4.0D, 16.0D));
         if(!var3.isEmpty()) {
            entity1 = (Entity)var3.get(this.field_70170_p.rand.nextInt(var3.size()));
            if(this.func_70685_l(entity1) && (entity1 instanceof EntityMob || entity1 instanceof EntityReficulSoldier || entity1 instanceof EntityReficulGuardian || entity1 instanceof EntityReficulMage)) {
               this.field_70789_a = entity1;
            }
         }
      }

   }

   protected void func_70785_a(Entity entity, float f) {
      if(f < 10.0F) {
         double d = entity.posX - this.field_70165_t;
         double d1 = entity.posZ - this.field_70161_v;
         if(this.field_70724_aR == 0) {
            EntityArrow entityarrow = new EntityArrow(this.field_70170_p, this, 1.0F);
            double d2 = entity.posY + (double)entity.getEyeHeight() - 0.699999988079071D - entityarrow.field_70163_u;
            float f1 = MathHelper.sqrt_double(d * d + d1 * d1) * 0.2F;
            this.field_70170_p.playSoundAtEntity(this, "random.bow", 1.0F, 1.0F / (this.field_70146_Z.nextFloat() * 0.4F + 0.8F));
            this.field_70170_p.spawnEntityInWorld(entityarrow);
            entityarrow.setThrowableHeading(d, d2 + (double)f1, d1, 1.6F, 12.0F);
            this.field_70724_aR = 20;
         }

         this.field_70177_z = (float)(Math.atan2(d1, d) * 180.0D / 3.1415927410125732D) - 90.0F;
         this.field_70787_b = true;
      }

   }

   public boolean attackEntityFrom(DamageSource damagesource, int i) {
      boolean flag = true;
      Entity entity = damagesource.getSourceOfDamage();
      if(entity instanceof EntityDefendBandit || entity instanceof EntityDefendKnight || entity instanceof EntityDefendMage || entity instanceof EntityDefendPaladin || entity instanceof EntityDefendWarrior || entity instanceof EntityDefendArcher || entity instanceof EntityHired || entity instanceof EntityPlayer || entity instanceof EntityPlayerSP) {
         flag = false;
      }

      if(flag) {
         super.func_70097_a(damagesource, (float)i);
      }

      return true;
   }

   public void func_70645_a(DamageSource damagesource) {}

}
