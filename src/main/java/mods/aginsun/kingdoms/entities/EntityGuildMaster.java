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
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public final class EntityGuildMaster extends EntityNPC {

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
      this.rand = new Random();
      this.talk = 0;
      this.counterHeal = 0;
      this.get = true;
      this.said = false;
      this.world = world;
      this.isImmuneToFire = true;
      this.attackStrength = 10;
   }

   public boolean canBePushed() {
      return false;
   }

   public boolean interact(EntityPlayer entityplayer) {
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
      int i1 = (int)this.posX;
      int j1 = (int)this.posY;
      int k1 = (int)this.posZ;
      if(this.get) {
         if(j - 50 < i1 && j + 50 > i1 && var12 - 50 < j1 && var12 + 50 > j1 && l - 50 < k1 && l + 50 > k1) {
            if(flag && !UtilToK.guildFightEnded) {
               if(this.talk == 0) {
                  if(!this.world.isRemote) {
                     this.player.addChatMessage(new ChatComponentText("Guild Master: My apprentice! The guild is under attack, and I came here to ask for your help. Please let us hurry back to the guild!"));
                  }

                  ItemStack var13 = new ItemStack(Item.getItemById(267), 1, 0);
                  EntityItem var14 = new EntityItem(this.world, this.player.posX, this.player.posY, this.player.posZ, var13);
                  this.player.joinEntityItemWithWorld(var14);
               } else if(this.talk > 2 && this.talk < 5 && !this.world.isRemote) {
                  this.player.addChatMessage(new ChatComponentText("Master: Keep close and I will heal you."));
               } else if(!this.world.isRemote) {
                  this.player.addChatMessage(new ChatComponentText("Master: Lets take this bastards down. There are still some left."));
               }

               ++this.talk;
            } else {
               this.get = true;
               InventoryPlayer inventoryplayer = entityplayer.inventory;
               if(inventoryplayer.hasItem(Item.getItemById(17))) {
                  for(int i2 = 0; i2 < inventoryplayer.mainInventory.length; ++i2) {
                     if(inventoryplayer.mainInventory[i2] != null && inventoryplayer.mainInventory[i2].getItem() == Item.getItemById(17)) {
                        ItemStack itemstack1 = inventoryplayer.getStackInSlot(i2);
                        if(itemstack1.stackSize == itemstack1.getMaxStackSize() && this.get) {
                           this.get = false;
                           inventoryplayer.setInventorySlotContents(i2, null);
                        }
                     }
                  }
               }

               if(!this.get && !this.world.isRemote) {
                  this.player.addChatMessage(new ChatComponentText("Master: We have now supplies to build the guild. Talk to me again if you want to repair the guild."));
               } else if(!this.world.isRemote) {
                  this.player.addChatMessage(new ChatComponentText("Master: We did the best we could. Now, we should rebuild the guild and gather 64 wood while the rest do some cleanup and construction."));
               }
            }
         } else if(!this.world.isRemote) {
            this.player.addChatMessage(new ChatComponentText("Master: We are too far from the guild!"));
         }
      } else {
         if(!this.world.isRemote) {
            this.player.addChatMessage(new ChatComponentText("Master: Thank you hero, you have proven yourself a worthy leader but your quest for kingship is not over. I will be back at the guild and may you continue this good progress."));
         }

         UtilToK.guildFightEnded = true;
         this.setDead();
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
      for(int list = 0; list < this.worldObj.loadedEntityList.size(); ++list) {
         entity1 = (Entity)this.worldObj.loadedEntityList.get(list);
         if(entity1 instanceof EntityPlayer) {
            this.player = (EntityPlayer)entity1;
            if(this.counterHeal == 5 && !UtilToK.guildFightEnded && !this.said && !this.world.isRemote) {
               this.player.addChatMessage(new ChatComponentText("Guild Master: My apprentice! The guild is under attack and I came here to ask for your help. Please let us hurry back to the guild!"));
               this.said = true;
            }

            if(this.counterHeal > 30) {
               this.player.heal(2.0F);
               this.counterHeal = 0;
            }

            ++this.counterHeal;
         }
      }

      if(!this.hasAttacked && !this.hasPath() && this.ridingEntity == null && this.player != null) {
         float var4 = this.player.getDistanceToEntity(this);
         if(var4 > 5.0F) {
            this.getPathOrWalkableBlock(this.player, var4);
         }
      }

      if(this.entityToAttack == null && !this.hasPath()) {
         List var5 = this.world.getEntitiesWithinAABB(EntityCreature.class, AxisAlignedBB.getBoundingBox(this.posX, this.posY, this.posZ, this.posX + 1.0D, this.posY + 1.0D, this.posZ + 1.0D).expand(16.0D, 4.0D, 16.0D));
         if(!var5.isEmpty()) {
            entity1 = (Entity)var5.get(this.world.rand.nextInt(var5.size()));
            if(this.canEntityBeSeen(entity1) && (entity1 instanceof EntityMob || entity1 instanceof EntityReficulSoldier || entity1 instanceof EntityReficulGuardian || entity1 instanceof EntityReficulMage)) {
               this.entityToAttack = entity1;
            }
         }
      }

   }

   private void getPathOrWalkableBlock(Entity entity, float f) {
      PathEntity pathentity = this.world.getPathEntityToEntity(this, entity, 16.0F, true, false, false, true);
      if(pathentity == null && f > 12.0F) {
         int i = MathHelper.floor_double(entity.posX) - 2;
         int j = MathHelper.floor_double(entity.posZ) - 2;
         int k = MathHelper.floor_double(entity.boundingBox.minY);

         for(int l = 0; l <= 4; ++l) {
            for(int i1 = 0; i1 <= 4; ++i1) {
               if((l < 1 || i1 < 1 || l > 3 || i1 > 3) && this.world.isBlockNormalCubeDefault(i + l, k - 1, j + i1, false) && !this.world.isBlockNormalCubeDefault(i + l, k, j + i1, false) && !this.world.isBlockNormalCubeDefault(i + l, k + 1, j + i1, false)) {
                  this.setLocationAndAngles((double)((float)(i + l) + 0.5F), (double)k, (double)((float)(j + i1) + 0.5F), this.rotationYaw, this.rotationPitch);
                  return;
               }
            }
         }
      } else {
         this.setPathToEntity(pathentity);
      }

   }

   protected void attackEntity(Entity entity, float f) {
      if((new ItemStack(Items.iron_sword, 1)).getItem() == Item.getItemById(267) && this.attackTime <= 0 && f < 2.0F && entity.boundingBox.maxY > this.boundingBox.minY && entity.boundingBox.minY < this.boundingBox.maxY) {
         this.swingItem();
         this.attackTime = 20;
         this.attackEntityAsMob(entity);
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

   public boolean attackEntityFrom(DamageSource damagesource, int i) {
      return true;
   }

   public void swingItem() {
      if(!this.isSwinging || this.field_110158_av < 0) {
         this.field_110158_av = -1;
         this.isSwinging = true;
      }

   }
}
