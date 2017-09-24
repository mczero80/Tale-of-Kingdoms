package mods.aginsun.kingdoms.client.guis;

import cpw.mods.fml.common.FMLCommonHandler;
import mods.aginsun.kingdoms.client.guis.GuiButtonShop;
import mods.aginsun.kingdoms.client.guis.GuiPriceBar;
import mods.aginsun.kingdoms.client.guis.GuiScreenToK;
import mods.aginsun.kingdoms.handlers.GoldKeeper;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringTranslate;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class GuiStockList extends GuiScreenToK {

   private World worldObj = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(0);
   private EntityPlayer entityplayer;
   private StringTranslate st = new StringTranslate();
   int checkBounty = 0;
   private Item[] item = new Item[9];
   boolean reachedend = false;
   public Item itemSelected;
   boolean goldchecker = false;
   private GuiPriceBar[] loadbar = new GuiPriceBar[8];
   public int shopcounter = 20;


   public GuiStockList(EntityPlayer entityplayer1, World world) {
      this.entityplayer = entityplayer1;
      this.worldObj = world;
      this.setItemList();
      this.itemSelected = (new ItemStack(Item.flint.itemID, 1, 0)).getItem();
   }

   public void setItemList() {
      this.item[1] = (new ItemStack(Item.flint.itemID, 1, 0)).getItem();
      this.item[2] = (new ItemStack(Item.clay.itemID, 1, 0)).getItem();
      this.item[3] = (new ItemStack(Item.ingotIron.itemID, 1, 0)).getItem();
      this.item[4] = (new ItemStack(Item.diamond.itemID, 1, 0)).getItem();
      this.item[5] = (new ItemStack(Item.fishRaw.itemID, 1, 0)).getItem();
      this.item[6] = (new ItemStack(Item.appleRed.itemID, 1, 0)).getItem();
      this.item[7] = (new ItemStack(Item.silk.itemID, 1, 0)).getItem();
      this.item[8] = (new ItemStack(Item.feather.itemID, 1, 0)).getItem();
      this.func_73866_w_();
   }

   public void func_73866_w_() {
      this.field_73887_h.clear();
      this.loadbar[0] = new GuiPriceBar(0, this.field_73880_f / 2 - 100, 43, 90, 12, 1.0F, "red");
      this.loadbar[1] = new GuiPriceBar(0, this.field_73880_f / 2 - 100, 63, 90, 12, 1.0F, "red");
      this.loadbar[2] = new GuiPriceBar(0, this.field_73880_f / 2 - 100, 83, 90, 12, 1.0F, "red");
      this.loadbar[3] = new GuiPriceBar(0, this.field_73880_f / 2 - 100, 103, 90, 12, 1.0F, "red");
      this.loadbar[4] = new GuiPriceBar(0, this.field_73880_f / 2 - 100, 123, 90, 12, 1.0F, "red");
      this.loadbar[5] = new GuiPriceBar(0, this.field_73880_f / 2 - 100, 143, 90, 12, 1.0F, "red");
      this.loadbar[6] = new GuiPriceBar(0, this.field_73880_f / 2 - 100, 163, 90, 12, 1.0F, "red");
      this.loadbar[7] = new GuiPriceBar(0, this.field_73880_f / 2 - 100, 183, 90, 12, 1.0F, "red");
      this.loadbar[0].setBar(GoldKeeper.flint / 200.0F);
      this.loadbar[1].setBar(GoldKeeper.clay / 200.0F);
      this.loadbar[2].setBar(GoldKeeper.iron / 200.0F);
      this.loadbar[3].setBar(GoldKeeper.diamond / 200.0F);
      this.loadbar[4].setBar(GoldKeeper.fish / 200.0F);
      this.loadbar[5].setBar(GoldKeeper.apple / 200.0F);
      this.loadbar[6].setBar(GoldKeeper.string / 200.0F);
      this.loadbar[7].setBar(GoldKeeper.feather / 200.0F);
      String s7;
      if(this.item[1] != null) {
         s7 = this.item[1].getUnlocalizedName() + ".name";
         s7 = this.st.translateKey(s7);
         this.field_73887_h.add(new GuiButtonShop(this.item[1], this, 8, this.field_73880_f / 2 + 20, 40, 90, 20, s7));
      }

      if(this.item[2] != null) {
         s7 = this.item[2].getUnlocalizedName() + ".name";
         s7 = this.st.translateKey(s7);
         this.field_73887_h.add(new GuiButtonShop(this.item[2], this, 9, this.field_73880_f / 2 + 20, 60, 90, 20, s7));
      }

      if(this.item[3] != null) {
         s7 = this.item[3].getUnlocalizedName() + ".name";
         s7 = this.st.translateKey(s7);
         this.field_73887_h.add(new GuiButtonShop(this.item[3], this, 10, this.field_73880_f / 2 + 20, 80, 90, 20, s7));
      }

      if(this.item[4] != null) {
         s7 = this.item[4].getUnlocalizedName() + ".name";
         s7 = this.st.translateKey(s7);
         this.field_73887_h.add(new GuiButtonShop(this.item[4], this, 11, this.field_73880_f / 2 + 20, 100, 90, 20, s7));
      }

      if(this.item[5] != null) {
         s7 = this.item[5].getUnlocalizedName() + ".name";
         s7 = this.st.translateKey(s7);
         this.field_73887_h.add(new GuiButtonShop(this.item[5], this, 12, this.field_73880_f / 2 + 20, 120, 90, 20, s7));
      }

      if(this.item[6] != null) {
         s7 = this.item[6].getUnlocalizedName() + ".name";
         s7 = this.st.translateKey(s7);
         this.field_73887_h.add(new GuiButtonShop(this.item[6], this, 13, this.field_73880_f / 2 + 20, 140, 90, 20, s7));
      }

      if(this.item[7] != null) {
         s7 = this.item[7].getUnlocalizedName() + ".name";
         s7 = this.st.translateKey(s7);
         this.field_73887_h.add(new GuiButtonShop(this.item[7], this, 14, this.field_73880_f / 2 + 20, 160, 90, 20, s7));
      }

      if(this.item[8] != null) {
         s7 = this.item[8].getUnlocalizedName() + ".name";
         s7 = this.st.translateKey(s7);
         this.field_73887_h.add(new GuiButtonShop(this.item[8], this, 15, this.field_73880_f / 2 + 20, 180, 90, 20, s7));
      }

      this.field_73887_h.add(new GuiButton(18, this.field_73880_f / 2 + 130, 160, 80, 20, "Buy Item"));
      this.field_73887_h.add(new GuiButton(19, this.field_73880_f / 2 + 130, 220, 80, 20, "Exit"));
      this.field_73887_h.add(new GuiButton(21, this.field_73880_f / 2 + 130, 200, 80, 20, "Buy 16 Items"));
   }

   public boolean func_73868_f() {
      return false;
   }

   public void func_73874_b() {
      if(this.worldObj.isRemote) {
         this.entityplayer.addChatMessage("Stock Keeper: Keep a look out on your stock supplies!");
      }

   }

   protected void func_73875_a(GuiButton guibutton) {
      if(guibutton.id == 8) {
         this.itemSelected = this.item[1];
         this.goldchecker = false;
      }

      if(guibutton.id == 9) {
         this.itemSelected = this.item[2];
         this.goldchecker = false;
      }

      if(guibutton.id == 10) {
         this.itemSelected = this.item[3];
         this.goldchecker = false;
      }

      if(guibutton.id == 11) {
         this.itemSelected = this.item[4];
         this.goldchecker = false;
      }

      if(guibutton.id == 12) {
         this.itemSelected = this.item[5];
         this.goldchecker = false;
      }

      if(guibutton.id == 13) {
         this.itemSelected = this.item[6];
         this.goldchecker = false;
      }

      if(guibutton.id == 14) {
         this.itemSelected = this.item[7];
         this.goldchecker = false;
      }

      if(guibutton.id == 15) {
         this.itemSelected = this.item[8];
         this.goldchecker = false;
      }

      ItemStack itemstack1;
      Item item2;
      String s1;
      int j;
      float f1;
      if(guibutton.id == 18) {
         itemstack1 = new ItemStack(this.itemSelected.itemID, 1, 0);
         item2 = itemstack1.getItem();
         s1 = item2.getUnlocalizedName();
         j = GoldKeeper.priceItem(s1);
         f1 = 0.0F;
         if(this.itemSelected.itemID == Item.flint.itemID) {
            f1 = GoldKeeper.flint;
         }

         if(this.itemSelected.itemID == Item.clay.itemID) {
            f1 = GoldKeeper.clay;
         }

         if(this.itemSelected.itemID == Item.ingotIron.itemID) {
            f1 = GoldKeeper.iron;
         }

         if(this.itemSelected.itemID == Item.diamond.itemID) {
            f1 = GoldKeeper.diamond;
         }

         if(this.itemSelected.itemID == Item.fishRaw.itemID) {
            f1 = GoldKeeper.fish;
         }

         if(this.itemSelected.itemID == Item.appleRed.itemID) {
            f1 = GoldKeeper.apple;
         }

         if(this.itemSelected.itemID == Item.silk.itemID) {
            f1 = GoldKeeper.string;
         }

         if(this.itemSelected.itemID == Item.feather.itemID) {
            f1 = GoldKeeper.feather;
         }

         f1 /= 100.0F;
         j = (int)((float)j + (float)j * f1);
         if(j <= GoldKeeper.getGoldTotal()) {
            EntityItem entityitem = new EntityItem(this.worldObj, this.entityplayer.field_70165_t, this.entityplayer.field_70163_u, this.entityplayer.field_70161_v, itemstack1);
            this.entityplayer.joinEntityItemWithWorld(entityitem);
            if(FMLCommonHandler.instance().getEffectiveSide().isServer()) {
               GoldKeeper.decreaseGold(j);
            }
         } else {
            this.goldchecker = true;
         }
      }

      if(guibutton.id == 21 && this.shopcounter >= 16) {
         itemstack1 = new ItemStack(this.itemSelected.itemID, 1, 0);
         item2 = itemstack1.getItem();
         s1 = item2.getUnlocalizedName();
         j = GoldKeeper.priceItem(s1);
         f1 = 0.0F;
         if(this.itemSelected.itemID == Item.flint.itemID) {
            f1 = GoldKeeper.flint;
         }

         if(this.itemSelected.itemID == Item.clay.itemID) {
            f1 = GoldKeeper.clay;
         }

         if(this.itemSelected.itemID == Item.ingotIron.itemID) {
            f1 = GoldKeeper.iron;
         }

         if(this.itemSelected.itemID == Item.diamond.itemID) {
            f1 = GoldKeeper.diamond;
         }

         if(this.itemSelected.itemID == Item.fishRaw.itemID) {
            f1 = GoldKeeper.fish;
         }

         if(this.itemSelected.itemID == Item.appleRed.itemID) {
            f1 = GoldKeeper.apple;
         }

         if(this.itemSelected.itemID == Item.silk.itemID) {
            f1 = GoldKeeper.string;
         }

         if(this.itemSelected.itemID == Item.feather.itemID) {
            f1 = GoldKeeper.feather;
         }

         f1 /= 100.0F;
         j = (int)((float)j + (float)j * f1);
         if(j * 16 <= GoldKeeper.getGoldTotal()) {
            this.shopcounter = 0;
         } else {
            this.goldchecker = true;
         }
      }

      if(guibutton.id == 19) {
         this.field_73882_e.displayGuiScreen((GuiScreen)null);
         this.goldchecker = false;
      }

      if(guibutton.id == 20) {
         this.goldchecker = false;
      }

   }

   public void func_73863_a(int i, int j, float f) {
      int f1;
      if(this.shopcounter < 16) {
         ItemStack s = new ItemStack(this.itemSelected.itemID, 1, 0);
         Item s1 = s.getItem();
         String i1 = s1.getUnlocalizedName();
         f1 = GoldKeeper.priceItem(i1);
         float l1 = 0.0F;
         if(this.itemSelected.itemID == Item.flint.itemID) {
            l1 = GoldKeeper.flint;
         }

         if(this.itemSelected.itemID == Item.clay.itemID) {
            l1 = GoldKeeper.clay;
         }

         if(this.itemSelected.itemID == Item.ingotIron.itemID) {
            l1 = GoldKeeper.iron;
         }

         if(this.itemSelected.itemID == Item.diamond.itemID) {
            l1 = GoldKeeper.diamond;
         }

         if(this.itemSelected.itemID == Item.fishRaw.itemID) {
            l1 = GoldKeeper.fish;
         }

         if(this.itemSelected.itemID == Item.appleRed.itemID) {
            l1 = GoldKeeper.apple;
         }

         if(this.itemSelected.itemID == Item.silk.itemID) {
            l1 = GoldKeeper.string;
         }

         if(this.itemSelected.itemID == Item.feather.itemID) {
            l1 = GoldKeeper.feather;
         }

         l1 /= 100.0F;
         f1 = (int)((float)f1 + (float)f1 * l1);
         if(f1 <= GoldKeeper.getGoldTotal()) {
            EntityItem entityitem = new EntityItem(this.worldObj, this.entityplayer.field_70165_t, this.entityplayer.field_70163_u, this.entityplayer.field_70161_v, s);
            this.entityplayer.joinEntityItemWithWorld(entityitem);
            GoldKeeper.decreaseGold(f1);
         }

         ++this.shopcounter;
      }

      this.func_73873_v_();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      short var10 = 255;
      short var12 = 255;
      ResourceLocation var16 = new ResourceLocation("taleofkingdoms", "textures/guis/crafting.png");
      this.field_73882_e.renderEngine.bindTexture(var16);
      f1 = (this.field_73880_f - var10) / 2;
      this.func_73729_b(f1, 0, 0, 0, var10, var12);

      for(int var11 = 0; var11 < this.field_73887_h.size(); ++var11) {
         if(this.field_73887_h.get(var11) instanceof GuiButtonShop) {
            GuiButtonShop var14 = (GuiButtonShop)this.field_73887_h.get(var11);
            var14.func_73737_a(this.field_73882_e, i, j);
         }

         if(this.field_73887_h.get(var11) instanceof GuiButton) {
            GuiButton var15 = (GuiButton)this.field_73887_h.get(var11);
            var15.drawButton(this.field_73882_e, i, j);
         }
      }

      this.func_73732_a(this.field_73886_k, "Stock Menu - Total Money: " + GoldKeeper.getGoldTotal() + " Gold Coins", this.field_73880_f / 2, 15, 16763904);
      String var13 = this.itemSelected.getUnlocalizedName() + ".name";
      String var17 = this.st.translateKey(var13);
      int var18 = GoldKeeper.priceItem(String.valueOf(this.itemSelected.getUnlocalizedName()));
      float var19 = 0.0F;
      if(this.itemSelected.itemID == Item.flint.itemID) {
         var19 = GoldKeeper.flint;
      }

      if(this.itemSelected.itemID == Item.clay.itemID) {
         var19 = GoldKeeper.clay;
      }

      if(this.itemSelected.itemID == Item.ingotIron.itemID) {
         var19 = GoldKeeper.iron;
      }

      if(this.itemSelected.itemID == Item.diamond.itemID) {
         var19 = GoldKeeper.diamond;
      }

      if(this.itemSelected.itemID == Item.fishRaw.itemID) {
         var19 = GoldKeeper.fish;
      }

      if(this.itemSelected.itemID == Item.appleRed.itemID) {
         var19 = GoldKeeper.apple;
      }

      if(this.itemSelected.itemID == Item.silk.itemID) {
         var19 = GoldKeeper.string;
      }

      if(this.itemSelected.itemID == Item.feather.itemID) {
         var19 = GoldKeeper.feather;
      }

      var19 /= 100.0F;
      var18 = (int)((float)var18 + (float)var18 * var19);
      if(this.goldchecker) {
         this.func_73732_a(this.field_73886_k, "Selected Item Cost: " + var17 + " - NOT ENOUGH GOLD", this.field_73880_f / 2, 30, 16763904);
      } else {
         this.func_73732_a(this.field_73886_k, "Selected Item Cost: " + var17 + " - " + var18 + " Gold coins", this.field_73880_f / 2, 30, 16763904);
      }

      this.func_73732_a(this.field_73886_k, "Note: Full bar means full cost!", this.field_73880_f / 2, 200, 16763904);

      for(int var20 = 0; var20 < 8; ++var20) {
         this.loadbar[var20].drawBar();
      }

      super.func_73863_a(i, j, f);
   }

   protected void func_73869_a(char par1, int par2) {
      if(par2 == 1 || par2 == this.field_73882_e.gameSettings.keyBindInventory.keyCode) {
         this.field_73882_e.thePlayer.closeScreen();
      }

   }
}
