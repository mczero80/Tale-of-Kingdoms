package mods.aginsun.kingdoms.client.guis;

import mods.aginsun.kingdoms.client.guis.GuiShopList;
import mods.aginsun.kingdoms.client.guis.GuiStockList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiButtonShop extends GuiButton {

   private GuiShopList gui;
   private GuiStockList gui2;
   private Item item;
   private Integer itemID;
   protected int field_73747_a;
   protected int field_73745_b;
   public int field_73746_c;
   public int field_73743_d;
   private String field_73744_e;
   public int field_73741_f;
   public boolean field_73742_g;
   public boolean enabled2;


   public GuiButtonShop(Integer integer, GuiShopList guishoplist, int i, int j, int k, int l, int i1, String s) {
      super(i, j, k, 200, 20, s);
      this.gui = guishoplist;
      this.itemID = integer;
      Item item1 = (new ItemStack(integer.intValue(), 1, 0)).getItem();
      this.item = item1;
      this.field_73747_a = 200;
      this.field_73745_b = 20;
      this.field_73742_g = true;
      this.enabled2 = true;
      this.field_73741_f = i;
      this.field_73746_c = j;
      this.field_73743_d = k;
      this.field_73747_a = l;
      this.field_73745_b = i1;
      this.field_73744_e = s;
   }

   public GuiButtonShop(Item item1, GuiStockList guistocklist, int i, int j, int k, int l, int i1, String s) {
      super(i, j, k, 200, 20, s);
      this.gui2 = guistocklist;
      this.item = item1;
      this.field_73747_a = 200;
      this.field_73745_b = 20;
      this.field_73742_g = true;
      this.enabled2 = true;
      this.field_73741_f = i;
      this.field_73746_c = j;
      this.field_73743_d = k;
      this.field_73747_a = l;
      this.field_73745_b = i1;
      this.field_73744_e = s;
   }

   protected int func_73738_a(boolean flag) {
      byte byte0 = 1;
      if(!this.field_73742_g) {
         byte0 = 0;
      } else if(flag) {
         byte0 = 2;
      }

      return byte0;
   }

   public void func_73737_a(Minecraft minecraft, int i, int j) {
      if(this.enabled2) {
         FontRenderer fontrenderer = minecraft.fontRenderer;
         ResourceLocation resource = new ResourceLocation("taleofkingdoms", "textures/guis/gui.png");
         minecraft.renderEngine.bindTexture(resource);
         boolean flag = i >= this.field_73746_c && j >= this.field_73743_d && i < this.field_73746_c + this.field_73747_a && j < this.field_73743_d + this.field_73745_b;
         int k = this.func_73738_a(flag);
         if(this.gui != null) {
            if(this.gui.itemSelected == this.itemID) {
               k = 2;
            } else {
               k = 1;
            }
         }

         if(this.gui2 != null) {
            if(this.gui2.itemSelected == this.item) {
               k = 2;
            } else {
               k = 1;
            }
         }

         this.func_73729_b(this.field_73746_c, this.field_73743_d, 0, 46 + k * 20, this.field_73747_a / 2, this.field_73745_b);
         this.func_73729_b(this.field_73746_c + this.field_73747_a / 2, this.field_73743_d, 200 - this.field_73747_a / 2, 46 + k * 20, this.field_73747_a / 2, this.field_73745_b);
         this.func_73739_b(minecraft, i, j);
         if(!this.field_73742_g) {
            this.func_73732_a(fontrenderer, this.field_73744_e, this.field_73746_c + this.field_73747_a / 2 - 20, this.field_73743_d + (this.field_73745_b - 8) / 2, -13312);
         } else if(!flag) {
            this.func_73732_a(fontrenderer, this.field_73744_e, this.field_73746_c + this.field_73747_a / 2 - 20, this.field_73743_d + (this.field_73745_b - 8) / 2, 16777215);
         } else {
            this.func_73732_a(fontrenderer, this.field_73744_e, this.field_73746_c + this.field_73747_a / 2 - 20, this.field_73743_d + (this.field_73745_b - 8) / 2, '\ucc00');
         }

      }
   }
}
