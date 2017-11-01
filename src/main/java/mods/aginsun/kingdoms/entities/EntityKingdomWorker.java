package mods.aginsun.kingdoms.entities;

import java.util.List;

import mods.aginsun.kingdoms.entities.api.EntityNPC;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityKingdomWorker extends EntityNPC {

   public static ItemStack defaultHeldItem = new ItemStack(Items.iron_axe, 1);
   public Entity marker2 = null;
   private World field_70170_p;
   private int counter = 0;
   public boolean move = false;
   public boolean isSwinging;
   public int field_110158_av;


   public EntityKingdomWorker(World world) {
      super(world, defaultHeldItem, 30.0F);
      this.field_70170_p = world;
      this.field_70178_ae = false;
   }

   protected boolean func_70780_i() {
      return this.move;
   }

   public boolean func_70085_c(EntityPlayer entityplayer) {
      return true;
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
      if(this.field_70170_p.rand.nextInt(5) == 0) {
         this.func_71038_i();
      }

      if(this.counter > 200) {
         List list = this.field_70170_p.getEntitiesWithinAABB(EntityMarker2Keeper.class, AxisAlignedBB.getBoundingBox(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0D, this.field_70163_u + 1.0D, this.field_70161_v + 1.0D).expand(16.0D, 16.0D, 16.0D));
         if(!list.isEmpty()) {
            this.marker2 = (Entity)list.get(this.field_70170_p.rand.nextInt(list.size()));
            this.field_70789_a = this.marker2;
         }

         this.counter = 0;
      } else {
         ++this.counter;
      }

   }

   public void createMarker() {
      this.marker2 = EntityList.createEntityByName("Marker2", this.field_70170_p);
      this.marker2.setLocationAndAngles(this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0F, 0.0F);
      this.field_70170_p.spawnEntityInWorld(this.marker2);
      defaultHeldItem = new ItemStack(Item.pickaxeIron, 1);
      System.out.println("marker");
   }

}
