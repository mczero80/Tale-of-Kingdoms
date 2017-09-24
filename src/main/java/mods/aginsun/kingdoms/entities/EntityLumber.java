package mods.aginsun.kingdoms.entities;

import cpw.mods.fml.common.FMLCommonHandler;
import mods.aginsun.kingdoms.client.guis.GuiLumber;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;

public class EntityLumber extends EntityCreature {

   private World field_70170_p = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(0);


   public EntityLumber(World world) {
      super(world);
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

   protected boolean func_70780_i() {
      return true;
   }

   public boolean func_70085_c(EntityPlayer entityplayer) {
      if(this.canInteractWith(entityplayer)) {
         this.func_70691_i(100.0F);
         Minecraft minecraft = ModLoader.getMinecraftInstance();
         if(!this.field_70170_p.isRemote) {
            entityplayer.addChatMessage("Foreman: Do you need resources sir?");
         }

         minecraft.displayGuiScreen(new GuiLumber(entityplayer, this.field_70170_p));
      }

      return true;
   }
}
