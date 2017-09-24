package mods.aginsun.kingdoms.entities;

import cpw.mods.fml.common.FMLCommonHandler;
import java.util.List;
import java.util.Random;
import mods.aginsun.kingdoms.entities.api.EntityNPC;
import mods.aginsun.kingdoms.handlers.WorthyKeeper;
import mods.aginsun.kingdoms.util.UtilToK;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityGuildMember extends EntityNPC {

   private World field_70170_p = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(0);
   private EntityPlayer player;
   private static ItemStack defaultHeldItem = new ItemStack(Item.swordIron, 1);
   private boolean fight = false;
   private int counter = 0;
   public boolean isSwinging;
   public int field_110158_av;
   protected int attackStrength;


   public EntityGuildMember(World world) {
      super(world, defaultHeldItem, 40.0F);
      this.field_70170_p = world;
      this.field_70178_ae = true;
      this.attackStrength = 15;
   }

   public boolean func_70104_M() {
      return false;
   }

   public boolean func_70085_c(EntityPlayer entityplayer) {
      this.player = entityplayer;
      if(UtilToK.guildFightEnded) {
         ItemStack itemstack = entityplayer.inventory.getCurrentItem();
         if(itemstack != null) {
            if(itemstack.itemID == 268) {
               defaultHeldItem = new ItemStack(Item.swordWood, 1);
               entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, (ItemStack)null);
               if(!this.field_70170_p.isRemote) {
                  entityplayer.addChatMessage("Guild Member: Get Ready.");
               }

               this.fight = true;
            } else if(!this.field_70170_p.isRemote) {
               entityplayer.addChatMessage("Guild Member: Greetings. You seem like a tough fighter. Give me a wooden sword and lets have a sparing match!");
            }
         } else if(!this.field_70170_p.isRemote) {
            entityplayer.addChatMessage("Guild Member: Greetings. You seem like a tough fighter. Give me a wooden sword and lets have a sparing match!");
         }
      } else if(!this.field_70170_p.isRemote) {
         entityplayer.addChatMessage("Guild Member: Damn this Reficules");
      }

      return true;
   }

   public void func_70645_a(DamageSource damagesource) {
      if(this.fight) {
         WorthyKeeper.getInstance().addWorthy(50.0F);
         if(this.player != null && !this.field_70170_p.isRemote) {
            this.player.addChatMessage("Guild Member: Your a good fighter my friend, I will let the guild master know of your strength.");
         }
      }

   }

   protected void func_70626_be() {
      super.func_70626_be();
      if(this.fight) {
         ++this.counter;
         if(this.counter == 10 && !this.field_70170_p.isRemote) {
            this.player.addChatMessage("Guild Member: 3");
         }

         if(this.counter == 20 && !this.field_70170_p.isRemote) {
            this.player.addChatMessage("Guild Member: 2");
         }

         if(this.counter == 30 && !this.field_70170_p.isRemote) {
            this.player.addChatMessage("Guild Member: 1");
         }

         if(this.counter == 40 && !this.field_70170_p.isRemote) {
            this.player.addChatMessage("Guild Member: Begin!");
            this.field_70789_a = this.player;
         }
      }

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
      if(this.field_70789_a == null && !this.func_70781_l()) {
         List list = this.field_70170_p.getEntitiesWithinAABB(EntityCreature.class, AxisAlignedBB.getBoundingBox(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0D, this.field_70163_u + 1.0D, this.field_70161_v + 1.0D).expand(20.0D, 4.0D, 20.0D));
         if(!list.isEmpty()) {
            Entity entity = (Entity)list.get(this.field_70170_p.rand.nextInt(list.size()));
            if(entity instanceof EntityCreeper) {
               entity.setDead();
            } else if(entity instanceof EntityMob || entity instanceof EntityReficulSoldier || entity instanceof EntityReficulGuardian || entity instanceof EntityReficulMage) {
               defaultHeldItem = new ItemStack(Item.swordIron, 1);
               this.field_70789_a = entity;
            }
         }
      }

   }

   protected void func_70664_aZ() {
      Random random = new Random();
      if(random.nextInt(15) == 0) {
         this.field_70181_x = 0.41999998688697815D;
         if(this.func_70051_ag()) {
            float f = this.field_70177_z * 0.01745329F;
            this.field_70159_w -= (double)(MathHelper.sin(f) * 0.2F);
            this.field_70179_y += (double)(MathHelper.cos(f) * 0.2F);
         }

         this.field_70160_al = true;
      }

   }

   protected void func_70785_a(Entity entity, float f) {
      if(this.field_70724_aR <= 0 && f < 2.0F && entity.boundingBox.maxY > this.field_70121_D.minY && entity.boundingBox.minY < this.field_70121_D.maxY) {
         this.field_70724_aR = 20;
         this.func_71038_i();
         this.func_70652_k(entity);
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

   public void func_71038_i() {
      if(!this.isSwinging || this.field_110158_av < 0) {
         this.field_110158_av = -1;
         this.isSwinging = true;
      }

   }

}
