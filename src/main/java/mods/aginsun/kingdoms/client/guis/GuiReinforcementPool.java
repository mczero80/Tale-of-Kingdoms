package mods.aginsun.kingdoms.client.guis;

import mods.aginsun.kingdoms.client.guis.GuiPriceBar;
import mods.aginsun.kingdoms.client.guis.GuiScreenToK;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class GuiReinforcementPool extends GuiScreenToK {

   private GuiPriceBar knightPool;
   private float soldierNumber;


   public GuiReinforcementPool(EntityPlayer entityplayer1, World world, EntityCreature entitycreature) {}

   public void func_73866_w_() {
      this.field_73887_h.clear();
      this.knightPool = new GuiPriceBar(0, this.field_73880_f / 2 - 100, 40, 90, 12, 1.0F, "red");
      this.knightPool.setBar(this.soldierNumber / 80.0F);
   }

   protected void func_73875_a(GuiButton guibutton) {
      if(guibutton.id == 0) {
         ;
      }

      if(guibutton.id == 1) {
         ;
      }

      if(guibutton.id == 2) {
         this.field_73882_e.displayGuiScreen((GuiScreen)null);
      }

   }

   public void func_73863_a(int i, int j, float f) {
      this.func_73873_v_();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      short l = 255;
      short guibutton = 255;
      ResourceLocation resource = new ResourceLocation("taleofkingdoms", "textures/guis/crafting.png");
      this.field_73882_e.renderEngine.bindTexture(resource);
      int i1 = (this.field_73880_f - l) / 2;
      this.func_73729_b(i1, 0, 0, 0, l, guibutton);

      for(int var8 = 0; var8 < this.field_73887_h.size(); ++var8) {
         if(this.field_73887_h.get(var8) instanceof GuiButton) {
            GuiButton var9 = (GuiButton)this.field_73887_h.get(var8);
            var9.drawButton(this.field_73882_e, i, j);
         }
      }

      this.func_73732_a(this.field_73886_k, "Reinforcement Pool", this.field_73880_f / 2, 15, 16777215);
      this.knightPool.drawBar();
      super.func_73863_a(i, j, f);
   }
}
