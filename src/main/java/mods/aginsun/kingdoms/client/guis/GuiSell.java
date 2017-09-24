package mods.aginsun.kingdoms.client.guis;

import mods.aginsun.kingdoms.entities.TileEntitySell;
import mods.aginsun.kingdoms.handlers.GoldKeeper;
import mods.aginsun.kingdoms.inventory.ContainerSell;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public final class GuiSell extends GuiContainer
{
    public GuiSell(InventoryPlayer player_inventory, TileEntitySell sell)
    {
        super(new ContainerSell(sell, player_inventory));
    }

    @Override
    public void drawScreen(int w, int h, float partial)
    {
        this.fontRendererObj.drawString("Total Money: ", 25, 40, 4210752);
        this.fontRendererObj.drawString(GoldKeeper.getGoldTotal() + " Gold Coins", 30, 50, 4210752);
        this.fontRendererObj.drawString("Inventory", 8, this.ySize - 96 + 2, 4210752);
        this.fontRendererObj.drawString("Sell Menu", 25, 20, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        ResourceLocation resource = new ResourceLocation("taleofkingdoms", "textures/guis/guisell.png");
        this.mc.renderEngine.bindTexture(resource);
        int l = (this.width - this.xSize) / 2;
        int i1 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(l, i1, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected void keyTyped(char p_73869_1_, int p_73869_2_)
    {
        if(p_73869_2_ == 1 || p_73869_2_ == this.mc.gameSettings.keyBindInventory.getKeyCode())
        {
            this.mc.thePlayer.closeScreen();
        }
    }
}