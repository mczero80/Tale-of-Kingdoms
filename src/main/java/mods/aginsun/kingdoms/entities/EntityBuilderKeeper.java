package mods.aginsun.kingdoms.entities;

import cpw.mods.fml.common.FMLCommonHandler;
import mods.aginsun.kingdoms.client.guis.GuiBuildScreen;
import mods.aginsun.kingdoms.entities.api.EntityNPC;
import mods.aginsun.kingdoms.handlers.WorthyKeeper;
import mods.aginsun.kingdoms.util.Buildings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;

public class EntityBuilderKeeper extends EntityNPC {

   private boolean follow = true;
   private static ItemStack defaultHeldItem = null;


   public EntityBuilderKeeper(World world) {
      super(world, defaultHeldItem, 100.0F);
      this.field_70178_ae = false;
      if(Buildings.kingdomCreated) {
         this.follow = true;
      }

   }

   public boolean func_70104_M() {
      return false;
   }

   protected boolean func_70780_i() {
      return this.follow;
   }

   public boolean func_70085_c(EntityPlayer entityplayer) {
      if(this.canInteractWith(entityplayer) && WorthyKeeper.getInstance().getWorthy() < 10000.0F && !Buildings.kingdomCreated && !super.world.isRemote) {
         entityplayer.addChatMessage("City Builder: My king! You are still not worthy of creating a kingdom. The Guild Master will prepare you.");
      }

      if(!this.follow || WorthyKeeper.getInstance().getWorthy() >= 10000.0F && Buildings.kingdomCreated && !super.world.isRemote) {
         entityplayer.addChatMessage("City Builder: Lets start building!");
         FMLCommonHandler.instance().showGuiScreen(new GuiBuildScreen(entityplayer, super.world));
      }

      if(this.canInteractWith(entityplayer) && WorthyKeeper.getInstance().getWorthy() >= 10000.0F && this.follow && !Buildings.kingdomCreated && !super.world.isRemote) {
         entityplayer.addChatMessage("City Builder: Let us build your city my liege! Lead the way!");
         this.follow = false;
      }

      return true;
   }

   public void func_70636_d() {
      super.func_70636_d();
      if(!this.follow && !Buildings.kingdomCreated) {
         Minecraft minecraft = ModLoader.getMinecraftInstance();
         EntityClientPlayerMP entityplayersp = minecraft.thePlayer;
         if(entityplayersp != null) {
            float f = entityplayersp.func_70032_d(this);
            PathEntity pathentity;
            if(f > 5.0F && f < 18.0F) {
               pathentity = super.world.getPathEntityToEntity(this, entityplayersp, 16.0F, true, false, false, true);
            } else {
               pathentity = null;
            }

            this.func_70778_a(pathentity);
         }
      }

   }

}
