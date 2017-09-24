package mods.aginsun.kingdoms.client.guis;

import cpw.mods.fml.common.FMLCommonHandler;
import mods.aginsun.kingdoms.client.guis.GuiScreenToK;
import mods.aginsun.kingdoms.handlers.GoldKeeper;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiPriest extends GuiScreenToK {

   private World worldObj = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(0);
   public EntityPlayer entityplayer;
   boolean goldchecker = false;


   public GuiPriest(EntityPlayer entityplayer1, World world) {
      this.entityplayer = entityplayer1;
      this.worldObj = world;
   }

   public void func_73866_w_() {
      String s = "";
      this.field_73887_h.clear();
      this.field_73887_h.add(new GuiButton(1, this.field_73880_f / 2 + 100, 160, 110, 20, s));
      this.field_73887_h.add(new GuiButton(2, this.field_73880_f / 2 + 100, 180, 110, 20, "Recruit a Priestess"));
      this.field_73887_h.add(new GuiButton(4, this.field_73880_f / 2 + 100, 200, 110, 20, "Rejuvinate"));
      this.field_73887_h.add(new GuiButton(3, this.field_73880_f / 2 + 100, 220, 110, 20, "Exit"));
   }

   protected void func_73875_a(GuiButton guibutton) {
      if(guibutton.id == 1) {
         ;
      }

      if(guibutton.id == 2) {
         if(2000 <= GoldKeeper.getGoldTotal()) {
            EntityLiving entityliving = (EntityLiving)EntityList.createEntityByName("DefendPriest", this.worldObj);
            entityliving.func_70012_b(this.entityplayer.field_70165_t, this.entityplayer.field_70163_u, this.entityplayer.field_70161_v, 0.0F, 0.0F);
            this.worldObj.spawnEntityInWorld(entityliving);
            GoldKeeper.decreaseGold(2000);
         } else {
            this.goldchecker = true;
         }
      }

      if(guibutton.id == 3) {
         this.field_73882_e.displayGuiScreen((GuiScreen)null);
         this.goldchecker = false;
      }

      if(guibutton.id == 4) {
         this.entityplayer.getFoodStats().setFoodLevel(20);
         this.entityplayer.func_70691_i(20.0F);
         if(!this.worldObj.isRemote) {
            this.entityplayer.addChatMessage("Head Priest: You are now rejuvinated.");
         }
      }

   }

   public void func_73874_b() {
      if(!this.worldObj.isRemote) {
         this.entityplayer.addChatMessage("Head Priest: May the light be with you.");
      }

   }

   public void func_73863_a(int i, int j, float f) {
      for(int k = 0; k < this.field_73887_h.size(); ++k) {
         GuiButton guibutton = (GuiButton)this.field_73887_h.get(k);
         guibutton.drawButton(this.field_73882_e, i, j);
      }

      if(this.goldchecker) {
         this.func_73732_a(this.field_73886_k, "The Chapel Total Money: " + GoldKeeper.getGoldTotal() + " Gold Coins - NOT ENOUGH GOLD", this.field_73880_f / 2, 20, 16763904);
      } else {
         this.func_73732_a(this.field_73886_k, "The Chapel Total Money: " + GoldKeeper.getGoldTotal() + " Gold Coins", this.field_73880_f / 2, 10, 16763904);
      }

      this.func_73732_a(this.field_73886_k, "Note: Recruiting a priest cost 2000", this.field_73880_f / 2, 20, 16763904);
   }
}
