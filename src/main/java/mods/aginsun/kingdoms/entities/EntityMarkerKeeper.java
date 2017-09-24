package mods.aginsun.kingdoms.entities;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;

public class EntityMarkerKeeper extends Entity {

   public int treelife = 8;
   public EntityPlayer player;
   private World world;


   public EntityMarkerKeeper(World world1) {
      super(world1);
      this.world = world1;
      this.func_70105_a(5.0E-6F, 5.0E-6F);
      Minecraft minecraft = ModLoader.getMinecraftInstance();
      this.player = minecraft.thePlayer;
   }

   public boolean func_70104_M() {
      return false;
   }

   public void func_70088_a() {
      --this.treelife;
      System.out.println(this.treelife);
      this.onLivingUpdate();
   }

   public void onLivingUpdate() {
      int i = (int)this.field_70163_u;
      int j = (int)this.field_70163_u - 1;
      if(this.treelife == 0) {
         while(this.field_70170_p.getBlockId((int)this.field_70165_t, i, (int)this.field_70161_v) == 17 && this.player != null) {
            this.field_70170_p.setBlock((int)this.field_70165_t, i, (int)this.field_70161_v, 0);
            ItemStack k = new ItemStack(17, 1, 0);
            EntityItem itemstack1 = new EntityItem(this.field_70170_p, this.player.field_70165_t, this.player.field_70163_u, this.player.field_70161_v, k);
            this.world.spawnEntityInWorld(itemstack1);
            k = new ItemStack(17, 1, 0);
            itemstack1 = new EntityItem(this.field_70170_p, this.player.field_70165_t, this.player.field_70163_u, this.player.field_70161_v, k);
            this.world.spawnEntityInWorld(itemstack1);
            ++i;
         }

         for(int var6 = j; this.field_70170_p.getBlockId((int)this.field_70165_t, var6, (int)this.field_70161_v) == 17 && this.player != null; --var6) {
            this.field_70170_p.setBlock((int)this.field_70165_t, var6, (int)this.field_70161_v, 0);
            ItemStack var7 = new ItemStack(17, 1, 0);
            EntityItem entityitem1 = new EntityItem(this.field_70170_p, this.player.field_70165_t, this.player.field_70163_u, this.player.field_70161_v, var7);
            this.world.spawnEntityInWorld(entityitem1);
            var7 = new ItemStack(17, 1, 0);
            entityitem1 = new EntityItem(this.field_70170_p, this.player.field_70165_t, this.player.field_70163_u, this.player.field_70161_v, var7);
            this.world.spawnEntityInWorld(entityitem1);
         }

         this.func_70106_y();
      }

   }

   public void func_70014_b(NBTTagCompound nbttagcompound) {}

   public void func_70037_a(NBTTagCompound nbttagcompound) {}
}
