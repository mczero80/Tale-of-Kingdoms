package mods.aginsun.kingdoms.entities;

import java.util.List;
import java.util.Random;
import mods.aginsun.kingdoms.entities.api.EntityNPC;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityVillageMember extends EntityNPC {

   private boolean hasAxe = false;
   private boolean hasPick = false;


   public EntityVillageMember(World world) {
      super(world, (ItemStack)null, 30.0F);
      this.field_70170_p = world;
      this.field_70178_ae = false;
   }

   public boolean func_70104_M() {
      return false;
   }

   public boolean func_70085_c(EntityPlayer entityplayer) {
      ItemStack itemstack = entityplayer.inventory.getCurrentItem();
      if(itemstack != null && !this.hasAxe && (itemstack.getItem() == Items.wooden_axe || itemstack.getItem() == Items.stone_axe || itemstack.getItem() == Items.iron_axe)) {
         entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, (ItemStack)null);
         this.hasAxe = true;
      }

      if(itemstack != null && !this.hasPick && (itemstack.getItem() == Items.iron_pickaxe || itemstack.getItem() == Items.wooden_pickaxe || itemstack.getItem() == Items.stone_pickaxe)) {
         entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, (ItemStack)null);
         this.hasPick = true;
      }

      if(!this.hasPick && !this.hasAxe && !super.world.isRemote) {
         entityplayer.addChatMessage(new ChatComponentText("Villager: My king! Do you need a worker? Give me an axe and a pickaxe and I will work for you."));
      }

      if(!this.hasPick && this.hasAxe && !super.world.isRemote) {
         entityplayer.addChatMessage(new ChatComponentText("Villager: I still need a pickaxe sir."));
      }

      if(this.hasPick && !this.hasAxe && !super.world.isRemote) {
         entityplayer.addChatMessage(new ChatComponentText("Villager: I still need an axe sir."));
      }

      if(this.hasPick && this.hasAxe && !super.world.isRemote) {
         entityplayer.addChatMessage(new ChatComponentText("Worker: I am now a worker sir! Lead the way!"));
         EntityLiving entityliving = (EntityLiving)EntityList.createEntityByName("WorkerMember", this.field_70170_p);
         entityliving.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0F, 0.0F);
         this.field_70170_p.spawnEntityInWorld(entityliving);
         this.func_70106_y();
      }

      return true;
   }

   protected void func_70626_be() {
      super.func_70626_be();
      List list = this.field_70170_p.getEntitiesWithinAABB(EntityMob.class, AxisAlignedBB.getBoundingBox(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0D, this.field_70163_u + 1.0D, this.field_70161_v + 1.0D).expand(20.0D, 4.0D, 20.0D));
      if(!list.isEmpty()) {
         Entity entity = (Entity)list.get(this.field_70170_p.rand.nextInt(list.size()));
         entity.setDead();
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
}
