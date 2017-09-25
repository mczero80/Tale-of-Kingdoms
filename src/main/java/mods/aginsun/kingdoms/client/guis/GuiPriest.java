package mods.aginsun.kingdoms.client.guis;

import cpw.mods.fml.common.FMLCommonHandler;
import mods.aginsun.kingdoms.handlers.GoldKeeper;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public final class GuiPriest extends GuiScreenToK {

   private World worldObj = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(0);
   public EntityPlayer entityplayer;
   boolean goldchecker = false;


   public GuiPriest(EntityPlayer entityplayer1, World world) {
      this.entityplayer = entityplayer1;
      this.worldObj = world;
   }

   public void initGui() {
      String s = "";
      this.buttonList.clear();
      this.buttonList.add(new GuiButton(1, this.width / 2 + 100, 160, 110, 20, s));
      this.buttonList.add(new GuiButton(2, this.width / 2 + 100, 180, 110, 20, "Recruit a Priestess"));
      this.buttonList.add(new GuiButton(4, this.width / 2 + 100, 200, 110, 20, "Rejuvinate"));
      this.buttonList.add(new GuiButton(3, this.width / 2 + 100, 220, 110, 20, "Exit"));
   }

   protected void actionPerformed(GuiButton guibutton) {
      if(guibutton.id == 1) {
         ;
      }

      if(guibutton.id == 2) {
         if(2000 <= GoldKeeper.getGoldTotal()) {
            EntityLiving entityliving = (EntityLiving)EntityList.createEntityByName("DefendPriest", this.worldObj);
            entityliving.setLocationAndAngles(this.entityplayer.posX, this.entityplayer.posY, this.entityplayer.posZ, 0.0F, 0.0F);
            this.worldObj.spawnEntityInWorld(entityliving);
            GoldKeeper.decreaseGold(2000);
         } else {
            this.goldchecker = true;
         }
      }

      if(guibutton.id == 3) {
         this.mc.displayGuiScreen((GuiScreen)null);
         this.goldchecker = false;
      }

      if(guibutton.id == 4) {
         this.entityplayer.getFoodStats().setFoodLevel(20);
         this.entityplayer.heal(20.0F);
         if(!this.worldObj.isRemote) {
            this.entityplayer.addChatMessage(new ChatComponentText("Head Priest: You are now rejuvinated."));
         }
      }

   }

   public void onGuiClosed() {
      if(!this.worldObj.isRemote) {
         this.entityplayer.addChatMessage(new ChatComponentText("Head Priest: May the light be with you."));
      }
   }

   public void drawScreen(int i, int j, float f)
   {
      for(int k = 0; k < this.buttonList.size(); ++k) {
         GuiButton guibutton = (GuiButton)this.buttonList.get(k);
         guibutton.drawButton(this.mc, i, j);
      }

      if(this.goldchecker) {
         this.drawString(this.fontRendererObj, "The Chapel Total Money: " + GoldKeeper.getGoldTotal() + " Gold Coins - NOT ENOUGH GOLD", this.width / 2, 20, 16763904);
      } else {
         this.drawString(this.fontRendererObj, "The Chapel Total Money: " + GoldKeeper.getGoldTotal() + " Gold Coins", this.width / 2, 10, 16763904);
      }

      this.drawString(this.fontRendererObj, "Note: Recruiting a priest cost 2000", this.width / 2, 20, 16763904);
   }
}
