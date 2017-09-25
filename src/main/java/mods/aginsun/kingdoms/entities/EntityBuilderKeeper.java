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
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public final class EntityBuilderKeeper extends EntityNPC {

   private boolean follow = true;
   private static ItemStack defaultHeldItem = null;


   public EntityBuilderKeeper(World world) {
      super(world, defaultHeldItem, 100.0F);
      this.isImmuneToFire = false;
      if(Buildings.kingdomCreated) {
         this.follow = true;
      }

   }

   public boolean canBePushed() {
      return false;
   }

   protected boolean isMovementCeased() {
      return this.follow;
   }

   public boolean interact(EntityPlayer entityplayer) {
      if(this.canInteractWith(entityplayer) && WorthyKeeper.getInstance().getWorthy() < 10000.0F && !Buildings.kingdomCreated && !super.world.isRemote) {
         entityplayer.addChatMessage(new ChatComponentText("City Builder: My king! You are still not worthy of creating a kingdom. The Guild Master will prepare you."));
      }

      if(!this.follow || WorthyKeeper.getInstance().getWorthy() >= 10000.0F && Buildings.kingdomCreated && !super.world.isRemote) {
         entityplayer.addChatMessage(new ChatComponentText("City Builder: Lets start building!"));
         FMLCommonHandler.instance().showGuiScreen(new GuiBuildScreen(entityplayer, super.world));
      }

      if(this.canInteractWith(entityplayer) && WorthyKeeper.getInstance().getWorthy() >= 10000.0F && this.follow && !Buildings.kingdomCreated && !super.world.isRemote) {
         entityplayer.addChatMessage(new ChatComponentText("City Builder: Let us build your city my liege! Lead the way!"));
         this.follow = false;
      }

      return true;
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      if(!this.follow && !Buildings.kingdomCreated) {
         Minecraft minecraft = Minecraft.getMinecraft();
         EntityClientPlayerMP entityplayersp = minecraft.thePlayer;
         if(entityplayersp != null) {
            float f = entityplayersp.getDistanceToEntity(this);
            PathEntity pathentity;
            if(f > 5.0F && f < 18.0F) {
               pathentity = super.world.getPathEntityToEntity(this, entityplayersp, 16.0F, true, false, false, true);
            } else {
               pathentity = null;
            }

            this.setPathToEntity(pathentity);
         }
      }

   }

}
