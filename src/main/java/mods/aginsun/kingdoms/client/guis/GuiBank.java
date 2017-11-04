package mods.aginsun.kingdoms.client.guis;

import mods.aginsun.kingdoms.handlers.GoldKeeper;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public final class GuiBank extends GuiScreenToK
{
    private World worldObj;
    private EntityPlayer player;
    private boolean check = false;
    private ResourceLocation resource = new ResourceLocation("taleofkingdoms", "textures/guis/crafting.png");

    public GuiBank(EntityPlayer player, World world)
    {
        this.player = player;
        this.worldObj = world;
    }

    @Override
    public void initGui()
    {
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(1, this.width / 2 - 95, 55, 90, 20, I18n.format("gui.bank.deposit") + " 1 G"));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 95, 77, 90, 20, I18n.format("gui.bank.deposit") + " 10 G"));
        this.buttonList.add(new GuiButton(3, this.width / 2 - 95, 99, 90, 20, I18n.format("gui.bank.deposit") + " 100 G"));
        this.buttonList.add(new GuiButton(4, this.width / 2 - 95, 121, 90, 20, I18n.format("gui.bank.deposit") + " 1000 G"));
        this.buttonList.add(new GuiButton(5, this.width / 2 - 95, 143, 90, 20, I18n.format("gui.bank.deposit") + " 10000 G"));
        this.buttonList.add(new GuiButton(6, this.width / 2 - 95, 165, 90, 20, I18n.format("gui.bank.deposit.all")));
        this.buttonList.add(new GuiButton(7, this.width / 2 + 5, 55, 90, 20, I18n.format("gui.bank.withdraw") + " 1 G"));
        this.buttonList.add(new GuiButton(8, this.width / 2 + 5, 77, 90, 20, I18n.format("gui.bank.withdraw") + " 10 G"));
        this.buttonList.add(new GuiButton(9, this.width / 2 + 5, 99, 90, 20, I18n.format("gui.bank.withdraw") + " 100 G"));
        this.buttonList.add(new GuiButton(10, this.width / 2 + 5, 121, 90, 20, I18n.format("gui.bank.withdraw") + " 1000 G"));
        this.buttonList.add(new GuiButton(11, this.width / 2 + 5, 143, 90, 20, I18n.format("gui.bank.withdraw") + " 10000 G"));
        this.buttonList.add(new GuiButton(12, this.width / 2 + 5, 165, 90, 20, I18n.format("gui.bank.withdraw.all")));
        this.buttonList.add(new GuiButton(13, this.width / 2 - 45, 197, 90, 20, I18n.format("gui.cancel")));
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        switch (button.id)
        {
            case 1:
                this.check = false;
                depositGold((short) 1);
                break;
            case 2:
                this.check = false;
                depositGold((short) 10);
                break;
            case 3:
                this.check = false;
                depositGold((short) 100);
                break;
            case 4:
                this.check = false;
                depositGold((short) 1000);
                break;
            case 5:
                this.check = false;
                depositGold((short) 10000);
                break;
            case 6:
                this.check = false;
                GoldKeeper.addBankGold(GoldKeeper.getGoldTotal());
                GoldKeeper.decreaseGold(GoldKeeper.getGoldTotal());
                break;
            case 7:
                this.check = false;
                withdrawGold((short) 1);
                break;
            case 8:
                this.check = false;
                withdrawGold((short) 10);
                break;
            case 9:
                this.check = false;
                withdrawGold((short) 100);
                break;
            case 10:
                this.check = false;
                withdrawGold((short) 1000);
                break;
            case 11:
                this.check = false;
                withdrawGold((short) 10000);
                break;
            case 12:
                this.check = false;
                GoldKeeper.addGold(GoldKeeper.getBankGold());
                GoldKeeper.setBankGold(0);
                break;
            case 13:
                if(!this.worldObj.isRemote)
                {
                    this.player.addChatMessage(new ChatComponentText(I18n.format("npc.banker.dialog.bye")));
                }
                this.mc.displayGuiScreen(null);
                break;
        }
    }

    @Override
    public void drawScreen(int i, int j, float f)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(resource);
        this.drawTexturedModalRect((this.width - 255) / 2, 0, 0, 0, 255, 255);
        this.drawCenteredString(this.fontRendererObj, I18n.format("gui.bank.title"), this.width / 2, 15, 16777215);
        this.drawCenteredString(this.fontRendererObj, I18n.format("gui.bank.totalMoney.player", GoldKeeper.getGoldTotal()), this.width / 2, 25, 16777215);
        this.drawCenteredString(this.fontRendererObj, I18n.format("gui.bank.totalMoney.bank", GoldKeeper.getBankGold()), this.width / 2, 35, 16777215);

        if(this.check)
        {
            this.drawCenteredString(this.fontRendererObj, I18n.format("gui.notEnough"), this.width / 2, 45, 16777215);
        }

        super.drawScreen(i, j, f);
    }

    @Override
    protected void keyTyped(char character, int key)
    {
        if(key == 1 || key == this.mc.gameSettings.keyBindInventory.getKeyCode())
        {
            this.mc.thePlayer.closeScreen();
        }
    }

    private void depositGold(short gold)
    {
        if(GoldKeeper.getGoldTotal() >= gold)
        {
            GoldKeeper.decreaseGold(gold);
            GoldKeeper.addBankGold(gold);
        }
        else
        {
            this.check = true;
        }
    }

    private void withdrawGold(short gold)
    {
        if(GoldKeeper.getBankGold() >= gold)
        {
            GoldKeeper.addGold(gold);
            GoldKeeper.decreaseBankGold(gold);
        }
        else
        {
            this.check = true;
        }
    }
}