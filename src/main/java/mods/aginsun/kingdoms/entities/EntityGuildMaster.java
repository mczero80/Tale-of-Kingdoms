package mods.aginsun.kingdoms.entities;

import java.util.List;
import java.util.Random;
import mods.aginsun.kingdoms.entities.api.EntityNPC;
import mods.aginsun.kingdoms.util.UtilToK;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityGuildMaster extends EntityNPC {

   private World world;
   private EntityPlayer player;
   private int talk;
   private int counterHeal;
   boolean get;
   boolean said;
   public boolean isSwinging;
   public int field_110158_av;
   protected int attackStrength;


   public EntityGuildMaster(World world) {
      super(world, new ItemStack(Items.iron_sword, 1), 100.0F);
      this.field_70146_Z = new Random();
      this.talk = 0;
      this.counterHeal = 0;
      this.get = true;
      this.said = false;
      this.world = world;
      this.field_70178_ae = true;
      this.attackStrength = 10;
   }

   public boolean func_70104_M() {
      return false;
   }

   public boolean func_70085_c(EntityPlayer entityplayer) {
      this.player = entityplayer;
      boolean flag = false;
      int j;
      if(!this.world.loadedEntityList.isEmpty()) {
         for(j = 0; j < this.world.loadedEntityList.size(); ++j) {
            Entity k = (Entity)this.world.loadedEntityList.get(j);
            if(k instanceof EntityReficulGuardian && k.getDistanceSqToEntity(this) <= 2000.0D) {
               flag = true;
            }

            if(k instanceof EntityReficulMage && k.getDistanceSqToEntity(this) <= 2000.0D) {
               flag = true;
            }

            if(k instanceof EntityReficulSoldier && k.getDistanceSqToEntity(this) <= 2000.0D) {
               flag = true;
            }
         }
      }

      j = this.world.getWorldInfo().getSpawnX() + 30;
      int var12 = this.world.getWorldInfo().getSpawnY();
      int l = this.world.getWorldInfo().getSpawnZ() + 40;
      int i1 = (int)this.field_70165_t;
      int j1 = (int)this.field_70163_u;
      int k1 = (int)this.field_70161_v;
      if(this.get) {
         if(j - 50 < i1 && j + 50 > i1 && var12 - 50 < j1 && var12 + 50 > j1 && l - 50 < k1 && l + 50 > k1) {
            if(flag && !UtilToK.guildFightEnded) {
               if(this.talk == 0) {
                  if(!this.world.isRemote) {
                     this.player.addChatMessage("Guild Master: My apprentice! The guild is under attack, and I came here to ask for your help. Please let us hurry back to the guild!");
                  }

                  ItemStack var13 = new ItemStack(267, 1, 0);
                  EntityItem var14 = new EntityItem(this.field_70170_p, this.player.field_70165_t, this.player.field_70163_u, this.player.field_70161_v, var13);
                  this.player.joinEntityItemWithWorld(var14);
               } else if(this.talk > 2 && this.talk < 5 && !this.field_70170_p.isRemote) {
                  this.player.addChatMessage("Master: Keep close and I will heal you.");
               } else if(!this.world.isRemote) {
                  this.player.addChatMessage("Master: Lets take this bastards down. There are still some left.");
               }

               ++this.talk;
            } else {
               this.get = true;
               InventoryPlayer inventoryplayer = entityplayer.inventory;
               if(inventoryplayer.hasItem(17)) {
                  for(int i2 = 0; i2 < inventoryplayer.mainInventory.length; ++i2) {
                     if(inventoryplayer.mainInventory[i2] != null && inventoryplayer.mainInventory[i2].itemID == 17) {
                        ItemStack itemstack1 = inventoryplayer.getStackInSlot(i2);
                        if(itemstack1.stackSize == itemstack1.getMaxStackSize() && this.get) {
                           this.get = false;
                           inventoryplayer.setInventorySlotContents(i2, (ItemStack)null);
                        }
                     }
                  }
               }

               if(!this.get && !this.field_70170_p.isRemote) {
                  this.player.addChatMessage("Master: We have now supplies to build the guild. Talk to me again if you want to repair the guild.");
               } else if(!this.world.isRemote) {
                  this.player.addChatMessage("Master: We did the best we could. Now, we should rebuild the guild and gather 64 wood while the rest do some cleanup and construction.");
               }
            }
         } else if(!this.world.isRemote) {
            this.player.addChatMessage("Master: We are too far from the guild!");
         }
      } else {
         if(!this.world.isRemote) {
            this.player.addChatMessage("Master: Thank you hero, you have proven yourself a worthy leader but your quest for kingship is not over. I will be back at the guild and may you continue this good progress.");
         }

         UtilToK.guildFightEnded = true;
         this.func_70106_y();
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
            if(this.counterHeal == 5 && !UtilToK.guildFightEnded && !this.said && !this.field_70170_p.isRemote) {
               this.player.addChatMessage("Guild Master: My apprentice! The guild is under attack and I came here to ask for your help. Please let us hurry back to the guild!");
               this.said = true;
            }

            if(this.counterHeal > 30) {
               this.player.func_70691_i(2.0F);
               this.counterHeal = 0;
            }

            ++this.counterHeal;
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

   protected void func_70785_a(Entity entity, float f) {
      if((new ItemStack(Item.swordIron, 1)).itemID == 267 && this.field_70724_aR <= 0 && f < 2.0F && entity.boundingBox.maxY > this.field_70121_D.minY && entity.boundingBox.minY < this.field_70121_D.maxY) {
         this.func_71038_i();
         this.field_70724_aR = 20;
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

   public boolean attackEntityFrom(DamageSource damagesource, int i) {
      return true;
   }

   public void func_71038_i() {
      if(!this.isSwinging || this.field_110158_av < 0) {
         this.field_110158_av = -1;
         this.isSwinging = true;
      }

   }
}
