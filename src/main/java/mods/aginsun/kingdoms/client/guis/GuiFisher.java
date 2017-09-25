package mods.aginsun.kingdoms.client.guis;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.ChatComponentText;

import java.awt.*;

public final class GuiFisher extends GuiScreen
{
    EntityPlayer player;

    public GuiFisher(EntityPlayer player)
    {
        this.player = player;
    }

    public void initGui()
    {
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(1, this.width / 2 - 60, this.height / 2 - 10, 120, 20, "Get Fishing Rod!"));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 60, this.height / 2 + 10, 120, 20, "Exit!"));
    }

    public void actionPerformed(GuiButton guibutton)
    {
        if(guibutton.id == 1)
        {
            this.player.dropItem(Items.fishing_rod, 1);
            this.player.addChatMessage(new ChatComponentText("Here ya go, your new beautiful fishing rod!"));
        }
        else if(guibutton.id == 2)
        {
            this.mc.setIngameFocus();
        }
    }

    public void drawScreen(int i, int j, float f)
    {
        this.drawString(this.fontRendererObj, "Get your best fishing rods here lad.", this.width / 2 - this.fontRendererObj.getStringWidth("Get your best fishing rods here lad.") / 2, this.height / 2 - 30, Color.ORANGE.getRGB());
        super.drawScreen(i, j, f);
    }
}