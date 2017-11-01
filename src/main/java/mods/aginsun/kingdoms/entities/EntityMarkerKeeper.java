package mods.aginsun.kingdoms.entities;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public final class EntityMarkerKeeper extends Entity {

   public int treelife = 8;
   public EntityPlayer player;
   private World world;


   public EntityMarkerKeeper(World world1) {
      super(world1);
      this.world = world1;
      this.setSize(5.0E-6F, 5.0E-6F);
      Minecraft minecraft = Minecraft.getMinecraft();
      this.player = minecraft.thePlayer;
   }

   public boolean canBePushed() {
      return false;
   }

   public void entityInit() {
      --this.treelife;
      System.out.println(this.treelife);
      this.onLivingUpdate();
   }

   public void onLivingUpdate() {
      int i = (int)this.posY;
      int j = (int)this.posY - 1;
      if(this.treelife == 0) {
         while(this.worldObj.getBlock((int)this.posX, i, (int)this.posZ) == Block.getBlockById(17) && this.player != null) {
            this.worldObj.setBlock((int)this.posX, i, (int)this.posZ, Blocks.air);
            ItemStack k = new ItemStack(Item.getItemById(17), 1, 0);
            EntityItem itemstack1 = new EntityItem(this.worldObj, this.player.posX, this.player.posY, this.player.posZ, k);
            this.world.spawnEntityInWorld(itemstack1);
            k = new ItemStack(Item.getItemById(17), 1, 0);
            itemstack1 = new EntityItem(this.worldObj, this.player.posX, this.player.posY, this.player.posZ, k);
            this.world.spawnEntityInWorld(itemstack1);
            ++i;
         }

         for(int var6 = j; this.worldObj.getBlock((int)this.posX, var6, (int)this.posZ) == Block.getBlockById(17) && this.player != null; --var6) {
            this.worldObj.setBlock((int)this.posX, var6, (int)this.posZ, Blocks.air);
            ItemStack var7 = new ItemStack(Item.getItemById(17), 1, 0);
            EntityItem entityitem1 = new EntityItem(this.worldObj, this.player.posX, this.player.posY, this.player.posZ, var7);
            this.world.spawnEntityInWorld(entityitem1);
            var7 = new ItemStack(Item.getItemById(17), 1, 0);
            entityitem1 = new EntityItem(this.worldObj, this.player.posX, this.player.posY, this.player.posZ, var7);
            this.world.spawnEntityInWorld(entityitem1);
         }

         this.setDead();
      }

   }

   public void writeEntityToNBT(NBTTagCompound nbttagcompound) {}

   public void readEntityFromNBT(NBTTagCompound nbttagcompound) {}
}
