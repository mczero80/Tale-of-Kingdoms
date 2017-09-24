package mods.aginsun.kingdoms.entities;

import cpw.mods.fml.common.FMLCommonHandler;
import mods.aginsun.kingdoms.client.guis.GuiMageHall;
import mods.aginsun.kingdoms.entities.api.EntityNPC;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;

public class EntityMageKeeper extends EntityNPC {

   private World field_70170_p = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(0);


   public EntityMageKeeper(World world) {
      super(world, new ItemStack(Item.stick), 100.0F);
      this.field_70170_p = world;
      this.field_70178_ae = false;
   }

   protected boolean func_70692_ba() {
      return false;
   }

   public boolean canInteractWith(EntityPlayer entityplayer) {
      return this.field_70128_L?false:entityplayer.func_70068_e(this) <= 64.0D;
   }

   public boolean func_70104_M() {
      return false;
   }

   protected void func_70626_be() {
      super.func_70626_be();
   }

   protected boolean func_70780_i() {
      return true;
   }

   public boolean func_70085_c(EntityPlayer entityplayer) {
      if(this.canInteractWith(entityplayer)) {
         this.func_70691_i(100.0F);
         Minecraft minecraft = ModLoader.getMinecraftInstance();
         if(!this.field_70170_p.isRemote) {
            entityplayer.addChatMessage("Head Mage: Welcome to the mage hall, hero.");
         }

         minecraft.displayGuiScreen(new GuiMageHall(entityplayer, this.field_70170_p));
      }

      return true;
   }
}
