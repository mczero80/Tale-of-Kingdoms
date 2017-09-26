package mods.aginsun.kingdoms.entities;

import java.util.List;
import mods.aginsun.kingdoms.client.guis.GuiWorker;
import mods.aginsun.kingdoms.entities.api.EntityNPC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.src.ModLoader;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityWorkerMember extends EntityNPC {

   private World field_70170_p;
   private EntityPlayer player;
   public static ItemStack defaultHeldItem;
   private boolean markerExist;
   public boolean follow;
   private int hit3;
   public int worktype;
   public boolean freeze;
   public boolean isMining;
   public Entity marker2;
   public boolean isSwinging;
   public int field_110158_av;


   public EntityWorkerMember(World world) {
      super(world, defaultHeldItem, 30.0F);
      defaultHeldItem = null;
      this.markerExist = false;
      this.follow = true;
      this.hit3 = 0;
      this.worktype = 0;
      this.freeze = false;
      this.isMining = false;
      this.marker2 = null;
      this.field_70170_p = world;
      this.isImmuneToFire = false;
      Minecraft minecraft = Minecraft.getMinecraft();
      this.player = minecraft.thePlayer;
   }

   protected boolean isMovementCeased() {
      return this.freeze;
   }

   public boolean interact(EntityPlayer entityplayer) {
      boolean flag = false;
      if(this.worktype == 0) {
         Minecraft j = Minecraft.getMinecraft();
         j.displayGuiScreen(new GuiWorker(entityplayer, this.field_70170_p, this));
      }

      int var10;
      if(!this.field_70170_p.loadedEntityList.isEmpty()) {
         for(var10 = 0; var10 < this.field_70170_p.loadedEntityList.size(); ++var10) {
            Entity k = (Entity)this.field_70170_p.loadedEntityList.get(var10);
            if(k instanceof EntityMarkerKeeper) {
               k.setDead();
            }
         }
      }

      this.freeze = false;
      if(this.isMining) {
         this.isMining = false;
      }

      if(this.worktype == 1) {
         var10 = (int)this.field_70163_u;
         if(this.follow) {
            for(int var11 = (int)this.field_70165_t - 5; (double)var11 < this.field_70165_t + 5.0D; ++var11) {
               for(int l = 0; l < 10; ++l) {
                  for(int i1 = (int)this.field_70161_v - 5; (double)i1 < this.field_70161_v + 5.0D; ++i1) {
                     if(this.field_70170_p.getBlockId(var11, var10, i1) == 17 && !flag) {
                        for(int entity2 = var11 - 5; entity2 <= var11 + 5; ++entity2) {
                           for(int k1 = var10 - 5; k1 <= var10 + 5; ++k1) {
                              for(int l1 = i1 - 5; l1 <= i1 + 5; ++l1) {
                                 if(this.field_70170_p.getBlockId(entity2, k1, l1) == 18) {
                                    this.field_70170_p.setBlock(entity2, k1, l1, 0);
                                 }
                              }
                           }
                        }

                        Entity var12 = EntityList.createEntityByName("Marker", this.field_70170_p);
                        var12.setLocationAndAngles((double)var11, (double)var10, (double)i1, 0.0F, 0.0F);
                        if(!this.field_70170_p.isRemote) {
                           this.field_70170_p.spawnEntityInWorld(var12);
                        }

                        System.out.println("newMarker");
                        flag = true;
                        this.markerExist = true;
                     } else {
                        this.markerExist = false;
                     }
                  }
               }
            }
         }

         if(this.follow && flag && this.worktype == 1 && !this.field_70170_p.isRemote) {
            this.player.addChatMessage(new ChatComponentText("Worker: Chopping it down sir!"));
         }

         if(this.follow && !flag && this.worktype == 1 && !this.field_70170_p.isRemote) {
            this.player.addChatMessage(new ChatComponentText("Worker: Direct me to a tree and I will start cutting!"));
         }

         this.player = entityplayer;
      }

      if(this.worktype == 2 && !this.isMining) {
         if(this.field_70163_u < 50.0D && !this.world.isRemote) {
            this.player.addChatMessage(new ChatComponentText("Worker: Mining the stone sir!"));
            this.createMine();
            this.isMining = true;
         } else if(!this.world.isRemote) {
            this.player.addChatMessage(new ChatComponentText("Worker: We must go further underground sir!"));
         }
      }

      this.player = entityplayer;
      return true;
   }

   protected void func_70664_aZ() {
      if(this.follow && !this.freeze) {
         this.field_70181_x = 0.41999998688697815D;
         if(this.func_70051_ag()) {
            float f = this.field_70177_z * 0.01745329F;
            this.field_70159_w -= (double)(MathHelper.sin(f) * 0.2F);
            this.field_70179_y += (double)(MathHelper.cos(f) * 0.2F);
         }

         this.field_70160_al = true;
      }

   }

   public void func_71038_i() {
      if(!this.isSwinging || this.field_110158_av < 0) {
         this.field_110158_av = -1;
         this.isSwinging = true;
      }

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
      if(this.worktype == 1) {
         List f = this.field_70170_p.getEntitiesWithinAABB(EntityMarkerKeeper.class, AxisAlignedBB.getBoundingBox(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0D, this.field_70163_u + 1.0D, this.field_70161_v + 1.0D).expand(16.0D, 16.0D, 16.0D));
         if(!f.isEmpty()) {
            Entity j = (Entity)f.get(this.field_70170_p.rand.nextInt(f.size()));
            this.field_70789_a = j;
         } else {
            this.follow = true;
         }

         for(int var8 = (int)this.field_70165_t - 3; var8 <= 3; ++var8) {
            for(int k = (int)this.field_70163_u - 3; k <= 3; ++k) {
               for(int l = (int)this.field_70161_v - 3; l <= 3; ++l) {
                  if(this.field_70170_p.getBlockId(var8, k, l) == 18) {
                     this.field_70170_p.setBlock(var8, k, l, 0);
                  }
               }
            }
         }
      }

      if(this.follow && this.worktype == 2 && this.marker2 != null) {
         Entity var6 = this.marker2;
         this.field_70789_a = var6;
      }

      if(this.field_70789_a != null) {
         float var7 = this.field_70789_a.getDistanceToEntity(this);
         if(this.field_70789_a.isEntityAlive()) {
            this.func_70785_a(this.field_70789_a, var7);
         }

         this.func_70625_a(this.field_70789_a, 30.0F, 30.0F);
      }

   }

   protected void func_70785_a(Entity entity, float f) {
      Object obj;
      if(entity instanceof EntityMarkerKeeper) {
         obj = (EntityMarkerKeeper)entity;
      } else {
         obj = entity;
      }

      if(this.field_70724_aR <= 0 && f < 2.0F && ((Entity)obj).boundingBox.maxY + 20.0D > this.field_70121_D.minY && ((Entity)obj).boundingBox.minY < this.field_70121_D.maxY + 20.0D && this.worktype == 1) {
         this.field_70724_aR = 20;
         this.func_71038_i();
         this.follow = false;
      }

      if(this.field_70724_aR <= 0 && f < 2.0F && ((Entity)obj).boundingBox.maxY > this.field_70121_D.minY && ((Entity)obj).boundingBox.minY < this.field_70121_D.maxY && this.worktype == 2) {
         this.field_70724_aR = 20;
         this.func_71038_i();
         this.freeze = true;
         if(this.player != null && this.player.func_70068_e(this) <= 3000.0D && this.hit3 > 8) {
            ItemStack itemstack = new ItemStack(4, 1, 0);
            EntityItem entityitem = new EntityItem(this.field_70170_p, this.player.field_70165_t, this.player.field_70163_u, this.player.field_70161_v, itemstack);
            this.player.joinEntityItemWithWorld(entityitem);
            this.hit3 = 0;
         }

         ++this.hit3;
      }

   }

   public void func_70636_d() {
      super.func_70636_d();
      if(this.follow && !this.markerExist && this.player != null) {
         float f = this.player.func_70032_d(this);
         PathEntity pathentity;
         if(f > 5.0F && f < 18.0F) {
            pathentity = this.field_70170_p.getPathEntityToEntity(this, this.player, 16.0F, true, false, false, true);
         } else {
            pathentity = null;
         }

         this.func_70778_a(pathentity);
      }

   }

   public boolean attackEntityFrom(DamageSource damagesource, int i) {
      Entity entity = damagesource.getSourceOfDamage();
      if(entity instanceof EntityPlayer || entity instanceof EntityPlayerSP) {
         defaultHeldItem = null;
         this.follow = false;
         this.worktype = 0;
         Minecraft minecraft = Minecraft.getMinecraft();
         minecraft.displayGuiScreen(new GuiWorker((EntityPlayer)entity, this.field_70170_p, this));
      }

      return true;
   }

   private void createMine() {
      int i = (int)this.field_70165_t + 1;
      int j = (int)this.field_70163_u;
      int k = (int)this.field_70161_v - 3;

      int i1;
      int k1;
      for(i1 = 0; i1 < 5; ++i1) {
         for(k1 = 0; k1 < 3; ++k1) {
            for(int l1 = 1; l1 < 5; ++l1) {
               this.field_70170_p.setBlock(i + i1, j + k1, k + l1, Blocks.air);
            }
         }
      }

      for(i1 = 1; i1 < 5; ++i1) {
         for(k1 = 1; k1 < 7; ++k1) {
            this.field_70170_p.setBlock(i + i1, j - 2, k + k1, Blocks.stone);
            this.field_70170_p.setBlock(i + i1, j - 1, k + k1, Blocks.gravel);
         }
      }

      this.field_70170_p.setBlock(i + 1, j, k + 1, Blocks.stone);
      this.field_70170_p.setBlock(i + 1, j, k + 2, Blocks.fence);
      this.field_70170_p.setBlock(i + 1, j, k + 5, Blocks.fence);
      this.field_70170_p.setBlock(i + 1, j, k + 6, Blocks.cobblestone);
      this.field_70170_p.setBlock(i + 1, j + 1, k + 1, Blocks.cobblestone);
      this.field_70170_p.setBlock(i + 1, j + 1, k + 2, Blocks.fence);
      this.field_70170_p.setBlock(i + 1, j + 1, k + 5, Blocks.fence);
      this.field_70170_p.setBlock(i + 1, j + 1, k + 6, Blocks.stone);
      this.field_70170_p.setBlock(i + 1, j + 2, k + 1, Blocks.stone);
      this.field_70170_p.setBlock(i + 1, j + 2, k + 2, Blocks.planks);
      this.field_70170_p.setBlock(i + 1, j + 2, k + 3, Blocks.planks);
      this.field_70170_p.setBlock(i + 1, j + 2, k + 4, Blocks.planks);
      this.field_70170_p.setBlock(i + 1, j + 2, k + 5, Blocks.planks);
      this.field_70170_p.setBlock(i + 1, j + 2, k + 6, Blocks.stone);
      this.field_70170_p.setBlock(i + 1, j + 3, k + 2, Blocks.cobblestone);
      this.field_70170_p.setBlock(i + 1, j + 3, k + 3, Blocks.cobblestone);
      this.field_70170_p.setBlock(i + 1, j + 3, k + 4, Blocks.stone);
      this.field_70170_p.setBlock(i + 1, j + 3, k + 5, Blocks.cobblestone);
      this.field_70170_p.setBlock(i + 2, j, k + 1, Blocks.cobblestone);
      this.field_70170_p.setBlock(i + 2, j, k + 2, Blocks.crafting_table);
      this.field_70170_p.setBlock(i + 2, j, k + 6, Blocks.cobblestone);
      this.field_70170_p.setBlock(i + 2, j + 1, k + 1, Blocks.stone);
      this.field_70170_p.setBlock(i + 2, j + 1, k + 6, Blocks.stone);
      this.field_70170_p.setBlock(i + 2, j + 2, k + 1, Blocks.cobblestone);
      this.field_70170_p.setBlock(i + 2, j + 2, k + 6, Blocks.cobblestone);
      this.field_70170_p.setBlock(i + 2, j + 3, k + 2, Blocks.cobblestone);
      this.field_70170_p.setBlock(i + 2, j + 3, k + 3, Blocks.stone);
      this.field_70170_p.setBlock(i + 2, j + 3, k + 4, Blocks.cobblestone);
      this.field_70170_p.setBlock(i + 2, j + 3, k + 5, Blocks.cobblestone);
      this.field_70170_p.setBlock(i + 3, j, k + 1, Blocks.stone);
      this.field_70170_p.setBlock(i + 3, j, k + 5, Blocks.cobblestone);
      this.field_70170_p.setBlock(i + 3, j, k + 6, Blocks.stone);
      this.field_70170_p.setBlock(i + 3, j + 1, k + 1, Blocks.cobblestone);
      this.field_70170_p.setBlock(i + 3, j + 1, k + 6, Blocks.cobblestone);
      this.field_70170_p.setBlock(i + 3, j + 2, k + 1, Blocks.stone);
      this.field_70170_p.setBlock(i + 3, j + 2, k + 6, Blocks.stone);
      this.field_70170_p.setBlock(i + 3, j + 3, k + 2, Blocks.stone);
      this.field_70170_p.setBlock(i + 3, j + 3, k + 3, Blocks.cobblestone);
      this.field_70170_p.setBlock(i + 3, j + 3, k + 4, Blocks.cobblestone);
      this.field_70170_p.setBlock(i + 3, j + 3, k + 5, Blocks.stone);
      this.field_70170_p.setBlock(i + 4, j, k + 1, Blocks.stone);
      this.field_70170_p.setBlock(i + 4, j, k + 2, Blocks.cobblestone);
      this.field_70170_p.setBlock(i + 4, j, k + 6, Blocks.stone);
      this.field_70170_p.setBlock(i + 4, j + 1, k + 1, Blocks.cobblestone);
      this.field_70170_p.setBlock(i + 4, j + 1, k + 6, Blocks.stone);
      this.field_70170_p.setBlock(i + 4, j + 2, k + 1, Blocks.stone);
      this.field_70170_p.setBlock(i + 4, j + 2, k + 6, Blocks.cobblestone);
      this.field_70170_p.setBlock(i + 4, j + 3, k + 2, Blocks.stone);
      this.field_70170_p.setBlock(i + 4, j + 3, k + 3, Blocks.stone);
      this.field_70170_p.setBlock(i + 4, j + 3, k + 4, Blocks.cobblestone);
      this.field_70170_p.setBlock(i + 4, j + 3, k + 5, Blocks.stone);
      this.field_70170_p.setBlockMetadataWithNotify(i + 3, j + 2, k + 2, 50, 3);
      this.marker2 = EntityList.createEntityByName("Marker2", this.field_70170_p);
      this.marker2.setLocationAndAngles((double)(i + 4), (double)(j + 1), (double)(k + 3), 0.0F, 0.0F);
      if(!this.world.isRemote) {
         this.world.spawnEntityInWorld(this.marker2);
      }

   }
}
