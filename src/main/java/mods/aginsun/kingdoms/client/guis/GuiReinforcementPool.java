package mods.aginsun.kingdoms.client.guis;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public final class GuiReinforcementPool extends GuiScreenToK {

   private GuiPriceBar knightPool;
   private float soldierNumber;


   public GuiReinforcementPool(EntityPlayer entityplayer1, World world, EntityCreature entitycreature) {}

   public void initGui() {
      this.buttonList.clear();
      this.knightPool = new GuiPriceBar(0, this.width / 2 - 100, 40, 90, 12, 1.0F, "red");
      this.knightPool.setBar(this.soldierNumber / 80.0F);
   }

   protected void actionPerformed(GuiButton guibutton) {
      if(guibutton.id == 0) {
         ;
      }

      if(guibutton.id == 1) {
         ;
      }

      if(guibutton.id == 2) {
         this.mc.displayGuiScreen(null);
      }

   }

   public void drawScreen(int i, int j, float f) {
      this.drawDefaultBackground();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      short l = 255;
      short guibutton = 255;
      ResourceLocation resource = new ResourceLocation("taleofkingdoms", "textures/guis/crafting.png");
      this.mc.renderEngine.bindTexture(resource);
      int i1 = (this.width - l) / 2;
      this.drawTexturedModalRect(i1, 0, 0, 0, l, guibutton);

      for(int var8 = 0; var8 < this.buttonList.size(); ++var8) {
         if(this.buttonList.get(var8) instanceof GuiButton) {
            GuiButton var9 = (GuiButton)this.buttonList.get(var8);
            var9.drawButton(this.mc, i, j);
         }
      }

      this.drawString(this.fontRendererObj, "Reinforcement Pool", this.width / 2, 15, 16777215);
      this.knightPool.drawBar();
      super.drawScreen(i, j, f);
   }
}
