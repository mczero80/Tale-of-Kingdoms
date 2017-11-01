package mods.aginsun.kingdoms.entities;

import cpw.mods.fml.common.FMLCommonHandler;
import mods.aginsun.kingdoms.client.guis.GuiMageHall;
import mods.aginsun.kingdoms.entities.api.EntityNPC;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class EntityMageKeeper extends EntityNPC {

   private World field_70170_p = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(0);


   public EntityMageKeeper(World world) {
      super(world, new ItemStack(Items.stick), 100.0F);
      this.field_70170_p = world;
      this.isImmuneToFire = false;
   }

   protected boolean canDespawn() {
      return false;
   }

   public boolean canInteractWith(EntityPlayer entityplayer) {
      return this.isDead?false:entityplayer.getDistanceSqToEntity(this) <= 64.0D;
   }

   public boolean canBePushed() {
      return false;
   }

   protected void updateEntityActionState() {
      super.updateEntityActionState();
   }

   protected boolean isMovementCeased() {
      return true;
   }

   public boolean interact(EntityPlayer entityplayer) {
      if(this.canInteractWith(entityplayer)) {
         this.heal(100.0F);
         Minecraft minecraft = Minecraft.getMinecraft();
         if(!this.field_70170_p.isRemote) {
            entityplayer.addChatMessage(new ChatComponentText("Head Mage: Welcome to the mage hall, hero."));
         }

         minecraft.displayGuiScreen(new GuiMageHall(entityplayer, this.field_70170_p));
      }

      return true;
   }
}
