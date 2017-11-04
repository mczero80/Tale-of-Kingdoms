package mods.aginsun.kingdoms.client.guis;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.ChatComponentText;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public final class GuiFisher extends GuiScreen
{
    private final EntityPlayer player;

    public GuiFisher(final EntityPlayer player)
    {
        this.player = player;
    }

    @Override
    public void initGui()
    {
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(1, this.width / 2 - 60, this.height / 2 - 10, 120, 20, I18n.format("gui.fishingRod")));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 60, this.height / 2 + 10, 120, 20, I18n.format("gui.exit")));
    }

    @Override
    public void actionPerformed(final GuiButton guibutton)
    {
        if(guibutton.id == 1)
        {
            this.mc.setIngameFocus();
            this.player.dropItem(Items.fishing_rod, 1);
            this.player.addChatMessage(new ChatComponentText(I18n.format("npc.fisher.dialog.fishingRod")));
        }
        else if(guibutton.id == 2)
        {
            this.mc.setIngameFocus();
        }
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }

    @Override
    public void drawScreen(int i, int j, float f)
    {
        this.drawString(this.fontRendererObj, I18n.format("gui.fisher.dialog.for"), this.width / 2 - this.fontRendererObj.getStringWidth(I18n.format("gui.fisher.dialog.for")) / 2, this.height / 2 - 30, 0xFFC800);
        super.drawScreen(i, j, f);
    }
}