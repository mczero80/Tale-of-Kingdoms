package mods.aginsun.kingdoms.entities;

import cpw.mods.fml.common.FMLCommonHandler;
import java.util.List;

import mods.aginsun.kingdoms.entities.api.EntityNPC;
import mods.aginsun.kingdoms.handlers.WorthyKeeper;
import mods.aginsun.kingdoms.util.UtilToK;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class EntityLoneTraveller extends EntityNPC {

   private World field_70170_p = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(0);
   private static ItemStack defaultHeldItem = new ItemStack(Items.iron_sword, 1);


   public EntityLoneTraveller(World world) {
      super(world, defaultHeldItem, 20.0F);
      this.field_70170_p = world;
      this.field_70178_ae = true;
   }

   protected boolean func_70780_i() {
      return true;
   }

   public boolean func_70085_c(EntityPlayer entityplayer) {
      boolean flag1 = false;
      List list = this.field_70170_p.getEntitiesWithinAABB(EntityCreature.class, AxisAlignedBB.getBoundingBox(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0D, this.field_70163_u + 1.0D, this.field_70161_v + 1.0D).expand(16.0D, 4.0D, 16.0D));
      if(!list.isEmpty()) {
         for(int i = 0; i < list.size(); ++i) {
            Entity entity = (Entity)list.get(i);
            if(this.func_70685_l(entity) && entity instanceof EntityLostVillager) {
               entity.setDead();
               WorthyKeeper.getInstance().addWorthy(400.0F);
               flag1 = true;
            }
         }
      }

      if(flag1 && !this.field_70170_p.isRemote) {
         entityplayer.addChatMessage(new ChatComponentText("Survivor: My king! Thank you for saving them! I will let the guild master know your efforts"));
      } else if(!this.field_70170_p.isRemote) {
         entityplayer.addChatMessage(new ChatComponentText("Survivor: I am gravely lost, my king. I survived the attack but many of our villages burned down. There are still survivors left, hurry and rescue them!"));
         entityplayer.addChatMessage(new ChatComponentText("Guild Master: Your quest has started, find the village and save them!"));
      }

      UtilToK.burningVillages = 1;
      return true;
   }

   public void func_70636_d() {
      super.func_70636_d();
   }

}
