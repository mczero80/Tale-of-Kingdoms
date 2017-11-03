package mods.aginsun.kingdoms.client.guis;

import mods.aginsun.kingdoms.handlers.Schematic;
import mods.aginsun.kingdoms.handlers.SchematicHandler;
import mods.aginsun.kingdoms.util.Buildings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;

public final class GuiStartConquest extends GuiScreenToK
{
    private Minecraft mc;
    public EntityPlayer player;

    public GuiStartConquest(Minecraft minecraft)
    {
        this.mc = minecraft;
    }

    @Override
    public void initGui()
    {
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(1, this.width / 2 - 70, this.height / 2 + 40, 140, 20, I18n.format("gui.conquest.start")));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 70, this.height / 2 + 70, 140, 20, I18n.format("gui.cancel")));
    }

    @Override
    protected void actionPerformed(GuiButton guibutton)
    {
        if(guibutton.id == 1)
        {
            this.player = this.mc.thePlayer;
            Schematic schematic = (new Schematic("/mods/aginsun/kingdoms/schematics/GuildCastle")).setPosition((int)this.player.posX, (int)this.player.posY, (int)this.player.posZ).setSpeed(75);
            SchematicHandler.getInstance().addBuilding(schematic);
            Buildings.setBuildingTrue(0);
            this.mc.displayGuiScreen(null);
            this.mc.displayGuiScreen(new GuiToKLoading());
        }
        else if(guibutton.id == 2)
        {
            this.mc.displayGuiScreen(null);
        }
    }

    @Override
    public void drawScreen(int i, int j, float f)
    {
        this.drawDefaultBackground();
        this.drawString(this.fontRendererObj, "The Tale of Kingdoms", this.width / 2 - fontRendererObj.getStringWidth("The Tale of Kingdoms") / 2, this.height / 2 - 80, 16777215);
        this.drawString(this.fontRendererObj, I18n.format("gui.startConquest.line_1"), this.width / 2 - fontRendererObj.getStringWidth(I18n.format("gui.startConquest.line_1")) / 2, this.height / 2 - 70, 16777215);
        this.drawString(this.fontRendererObj, I18n.format("gui.startConquest.line_2"), this.width / 2 - fontRendererObj.getStringWidth(I18n.format("gui.startConquest.line_2")) / 2, this.height / 2 - 60, 16777215);
        this.drawString(this.fontRendererObj, I18n.format("gui.startConquest.line_3"), this.width / 2 - fontRendererObj.getStringWidth(I18n.format("gui.startConquest.line_3")) / 2, this.height / 2 - 50, 16777215);
        this.drawString(this.fontRendererObj, I18n.format("gui.startConquest.line_4"), this.width / 2 - fontRendererObj.getStringWidth(I18n.format("gui.startConquest.line_4")) / 2, this.height / 2 - 40, 16777215);
        this.drawString(this.fontRendererObj, I18n.format("gui.startConquest.line_5"), this.width / 2 - fontRendererObj.getStringWidth(I18n.format("gui.startConquest.line_5")) / 2, this.height / 2, 16777215);
        this.drawString(this.fontRendererObj, I18n.format("gui.startConquest.line_6"), this.width / 2 - fontRendererObj.getStringWidth(I18n.format("gui.startConquest.line_6")) / 2, this.height / 2 + 10, 16777215);

        for (Object aButtonList : this.buttonList)
        {
            GuiButton guibutton = (GuiButton) aButtonList;
            guibutton.drawButton(this.mc, i, j);
        }
    }
}