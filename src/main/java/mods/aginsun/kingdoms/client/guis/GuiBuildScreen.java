package mods.aginsun.kingdoms.client.guis;

import mods.aginsun.kingdoms.handlers.GoldKeeper;
import mods.aginsun.kingdoms.handlers.ResourceHandler;
import mods.aginsun.kingdoms.handlers.SchematicHandler;
import mods.aginsun.kingdoms.handlers.SchematicRegistry;
import mods.aginsun.kingdoms.util.Buildings;
import mods.aginsun.kingdoms.util.UtilToK;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public final class GuiBuildScreen extends GuiScreenToK
{
    private World worldObj;
    private EntityPlayer player;
    private GuiPriceBar woodBar, cobblestoneBar;
    private ResourceLocation resource = new ResourceLocation("taleofkingdoms", "textures/guis/crafting.png");

    public GuiBuildScreen(EntityPlayer player, World world)
    {
        this.player = player;
        this.worldObj = world;
    }

    @Override
    public void initGui()
    {
        this.buttonList.clear();

        if(UtilToK.townX != 0 && UtilToK.townY != 0 && UtilToK.townZ != 0)
        {
            SchematicRegistry.registerAllBuildings(UtilToK.townX, UtilToK.townY, UtilToK.townZ);
        }
        else
        {
            SchematicRegistry.registerAllBuildings((int) this.mc.thePlayer.posX, (int) this.mc.thePlayer.posY, (int) this.mc.thePlayer.posZ);
        }

        this.woodBar = new GuiPriceBar(0, this.width / 2 - 100, 40, 90, 12, 1.0F, "red");
        this.woodBar.setBar((float)ResourceHandler.getInstance().getwoodResource() / 320.0F);
        this.cobblestoneBar = new GuiPriceBar(1, this.width / 2 - 100, 60, 90, 12, 1.0F, "red");
        this.cobblestoneBar.setBar((float)ResourceHandler.getInstance().getcobbleResource() / 320.0F);

        this.buttonList.add(new GuiButton(1, this.width / 2 - 25, 220, 50, 20, I18n.format("gui.exit")));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 110, 80, 110, 20, I18n.format("gui.build.give.logs")));
        this.buttonList.add(new GuiButton(3, this.width / 2 + 5, 80, 110, 20, I18n.format("gui.build.give.cobble")));

        if(!Buildings.getBuilding(1))
        {
            this.addButton(4, this.width / 2 - 55, 130, I18n.format("gui.build.buildings.stage_1"));
        }
        else if(!Buildings.getBuilding(8) && Buildings.getBuilding(1))
        {
            if(!Buildings.getBuilding(2))
            {
                this.addButton(5, this.width / 2 - 120, 120,  I18n.format("gui.build.buildings.smallHouse"));
            }

            if(!Buildings.getBuilding(3))
            {
                this.addButton(6, this.width / 2 + 5, 120, I18n.format("gui.build.buildings.smallHouse"));
            }

            if(!Buildings.getBuilding(4))
            {
                this.addButton(7, this.width / 2 - 120, 180, I18n.format("gui.build.buildings.largeHouse"));
            }

            if(!Buildings.getBuilding(6))
            {
                this.addButton(8, this.width / 2 - 120, 140, I18n.format("gui.build.buildings.itemsShop"));
            }

            if(!Buildings.getBuilding(7))
            {
                this.addButton(9, this.width / 2 - 120, 160, I18n.format("gui.build.buildings.stockMarket"));
            }

            if(!Buildings.getBuilding(8) && this.buttonList.size() == 3)
            {
                this.addButton(10, this.width / 2 - 55, 130, I18n.format("gui.build.buildings.stage_2"));
            }
        }
        else if(Buildings.getBuilding(8) && !Buildings.getBuilding(16))
        {
            if(!Buildings.getBuilding(9))
            {
                this.addButton(11, this.width / 2 - 120, 120, I18n.format("gui.build.buildings.smallHouse"));
            }

            if(!Buildings.getBuilding(10))
            {
                this.addButton(12, this.width / 2 - 120, 180, I18n.format("gui.build.buildings.smallHouse"));
            }

            if(!Buildings.getBuilding(11))
            {
                this.addButton(13, this.width / 2 - 120, 140, I18n.format("gui.build.buildings.largeHouse"));
            }

            if(!Buildings.getBuilding(12))
            {
                this.addButton(14, this.width / 2 - 120, 160, I18n.format("gui.build.buildings.builderHouse"));
            }

            if(!Buildings.getBuilding(13))
            {
                this.addButton(15, this.width / 2 + 5, 120, I18n.format("gui.build.buildings.barracks"));
            }

            if(!Buildings.getBuilding(14))
            {
                this.addButton(16, this.width / 2 + 5, 140, I18n.format("gui.build.buildings.foodShop"));
            }

            if(!Buildings.getBuilding(15))
            {
                this.addButton(17, this.width / 2 + 5, 160, I18n.format("gui.build.buildings.blocksShop"));
            }

            if(!Buildings.getBuilding(16) && this.buttonList.size() == 3)
            {
                this.addButton(18, this.width / 2 - 55, 130, I18n.format("gui.build.buildings.stage_3"));
            }
        }
        else if(Buildings.getBuilding(16) && !Buildings.getBuilding(25))
        {
            if(!Buildings.getBuilding(17))
            {
                this.addButton(19, this.width / 2 - 120, 140, I18n.format("gui.build.buildings.smallHouse"));
            }

            if(!Buildings.getBuilding(18))
            {
                this.addButton(20, this.width / 2 - 120, 160, I18n.format("gui.build.buildings.smallHouse"));
            }

            if(!Buildings.getBuilding(19))
            {
                this.addButton(21, this.width / 2 - 120, 180, I18n.format("gui.build.buildings.smallHouse"));
            }

            if(!Buildings.getBuilding(20))
            {
                this.addButton(22, this.width / 2 + 5, 160, I18n.format("gui.build.buildings.largeHouse"));
            }

            if(!Buildings.getBuilding(21))
            {
                this.addButton(23, this.width / 2 - 120, 120, I18n.format("gui.build.buildings.tavern"));
            }

            if(!Buildings.getBuilding(22))
            {
                this.addButton(24, this.width / 2 + 5, 140, I18n.format("gui.build.buildings.chapel"));
            }
            if(!Buildings.getBuilding(23))
            {
                this.addButton(25, this.width / 2 + 5, 180, I18n.format("gui.build.buildings.library"));
            }

            if(!Buildings.getBuilding(24))
            {
                this.addButton(26, this.width / 2 + 5, 120, I18n.format("gui.build.buildings.mageHall"));
            }

            if(!Buildings.getBuilding(25) && this.buttonList.size() == 3)
            {
                this.addButton(27, this.width / 2 - 55, 130, I18n.format("gui.build.buildings.stage_4"));
            }
        }
        else if(Buildings.getBuilding(25) && !Buildings.getBuilding(26))
        {
            if(!Buildings.getBuilding(31))
            {
                this.addButton(28, this.width / 2 - 120, 120, I18n.format("gui.build.buildings.lightHouse"));
            }

            if(!Buildings.getBuilding(29))
            {
                this.addButton(29, this.width / 2 - 120, 140, I18n.format("gui.build.buildings.easternTower"));
            }

            if(!Buildings.getBuilding(30))
            {
                this.addButton(30, this.width / 2 - 120, 160, I18n.format("gui.build.buildings.fishermanHut"));
            }

            if(!Buildings.getBuilding(32))
            {
                this.addButton(31, this.width / 2 + 5, 140, I18n.format("gui.build.buildings.windmill"));
            }

            if(!Buildings.getBuilding(33))
            {
                this.addButton(32, this.width / 2 + 5, 120, I18n.format("gui.build.buildings.observerPost"));
            }

            if(!Buildings.getBuilding(26) && this.buttonList.size() == 3)
            {
                this.buttonList.add(new GuiButton(33, this.width / 2 - 65, 130, 140, 20, I18n.format("gui.build.buildings.stage_5")));
            }
        }
        else if(Buildings.getBuilding(26) && !Buildings.getBuilding(27))
        {
            if(!Buildings.getBuilding(34))
            {
                this.addButton(34, this.width / 2 - 120, 120, I18n.format("gui.build.buildings.smallHouse"));
            }

            if(!Buildings.getBuilding(35))
            {
                this.addButton(35, this.width / 2 - 120, 140, I18n.format("gui.build.buildings.smallHouse"));
            }

            if(!Buildings.getBuilding(36))
            {
                this.addButton(36, this.width / 2 - 120, 160, I18n.format("gui.build.buildings.smallHouse"));
            }

            if(!Buildings.getBuilding(37))
            {
                this.addButton(37, this.width / 2 - 120, 180, I18n.format("gui.build.buildings.smallHouse"));
            }

            if(!Buildings.getBuilding(38))
            {
                this.addButton(38, this.width / 2 - 120, 200, I18n.format("gui.build.buildings.largeHouse"));
            }

            if(!Buildings.getBuilding(39))
            {
                this.addButton(39, this.width / 2 + 5, 120, I18n.format("gui.build.buildings.centerTower"));
            }

            if(!Buildings.getBuilding(40))
            {
                this.addButton(40, this.width / 2 + 5, 140, I18n.format("gui.build.buildings.northernTower"));
            }

            if(!Buildings.getBuilding(28))
            {
                this.addButton(41, this.width / 2 + 5, 160, I18n.format("gui.build.buildings.coliseum"));
            }

            if(!Buildings.getBuilding(41))
            {
                this.addButton(42, this.width / 2 + 5, 180, I18n.format("gui.build.buildings.stables"));
            }

            if(!Buildings.getBuilding(42))
            {
                this.addButton(43, this.width / 2 + 5, 200, I18n.format("gui.build.buildings.zeppelin"));
            }

            if(!Buildings.getBuilding(27) && this.buttonList.size() == 3)
            {
                this.addButton(44, this.width / 2 - 65, 130, I18n.format("gui.build.buildings.castle"));
            }
        }
    }

    @Override
    public void actionPerformed(GuiButton guibutton)
    {
        short speed = 200;
        int j;

        switch(guibutton.id)
        {
            case 1:
                this.mc.displayGuiScreen(null);
                break;
            case 2:
                j = getInventorySlotContainItem(Item.getItemFromBlock(Blocks.log), mc.thePlayer.inventory.mainInventory);

                if(j >= 0 && this.mc.thePlayer.inventory.getStackInSlot(j).stackSize == 64)
                {
                    this.mc.thePlayer.inventory.setInventorySlotContents(j, null);
                    ResourceHandler.getInstance().addwoodResource(64);
                }
                break;
            case 3:
                j = getInventorySlotContainItem(Item.getItemFromBlock(Blocks.cobblestone), mc.thePlayer.inventory.mainInventory);

                if(j >= 0 && this.mc.thePlayer.inventory.getStackInSlot(j).stackSize == 64)
                {
                    this.mc.thePlayer.inventory.setInventorySlotContents(j, null);
                    ResourceHandler.getInstance().addcobbleResource(64);
                }
                break;
            case 4:
                UtilToK.townX = (int)this.mc.thePlayer.posX;
                UtilToK.townY = (int)this.mc.thePlayer.posY;
                UtilToK.townZ = (int)this.mc.thePlayer.posZ;
                this.createBuilding(1, 100, 0, 0);
                break;
            case 5:
                if(this.x(128, 192))
                {
                    this.createBuilding(2, speed, 128, 192);
                }
                break;
            case 6:
                if(this.x(128, 192))
                {
                    this.createBuilding(3, speed, 128, 192);
                }
                break;
            case 7:
                if(this.x(320, 192))
                {
                    this.createBuilding(4, speed, 320, 192);
                }
                break;
            case 8:
                if(this.x(256, 256))
                {
                    this.createBuilding(6, speed, 256, 256);
                }
                break;
            case 9:
                if(this.x(128, 128))
                {
                    this.createBuilding(7, speed, 192, 192);
                }
                break;
            case 10:
                if(this.x(128, 128))
                {
                    this.createBuilding(8, speed, 128, 128);
                }
                break;
            case 11:
                if(this.x(128, 192))
                {
                    this.createBuilding(9, speed, 128, 192);
                }
                break;
            case 12:
                if(this.x(128, 192))
                {
                    this.createBuilding(10, speed, 128, 192);
                }
                break;
            case 13:
                if(this.x(320, 192))
                {
                    this.createBuilding(11, speed, 320, 192);
                }
                break;
            case 14:
                if(this.x(128, 128))
                {
                    this.createBuilding(12, speed, 128, 128);
                }
                break;
            case 15:
                if(this.x(320, 320))
                {
                    this.createBuilding(13, speed, 320, 320);
                }
                break;
            case 16:
                if(this.x(256, 192))
                {
                    this.createBuilding(14, speed, 256, 192);
                }
                break;
            case 17:
                if(this.x(320, 256))
                {
                    this.createBuilding(15, speed, 320, 256);
                }
                break;
            case 18:
                if(this.x(128, 128))
                {
                    this.createBuilding(16, speed, 128, 128);
                }
                break;
            case 19:
                if(this.x(128, 192))
                {
                    this.createBuilding(17, speed, 128, 192);
                }
                break;
            case 20:
                if(this.x(128, 192))
                {
                    this.createBuilding(18, speed, 128, 192);
                }
                break;
            case 21:
                if(this.x(128, 192))
                {
                    this.createBuilding(19, speed, 128, 192);
                }
                break;
            case 22:
                if(this.x(320, 192))
                {
                    this.createBuilding(20, speed, 320, 192);
                }
                break;
            case 23:
                if(this.x(128, 320))
                {
                    this.createBuilding(21, speed, 128, 320);
                }
                break;
            case 24:
                if(this.x(320, 320))
                {
                    this.createBuilding(22, speed, 320, 320);
                }
                break;
            case 25:
                if(this.x(256, 256))
                {
                    this.createBuilding(23, speed, 256, 256);
                }
                break;
            case 26:
                if(this.x(320, 256))
                {
                    this.createBuilding(24, speed, 320, 256);
                }
                break;
            case 27:
                if(this.x(256, 256))
                {
                    this.createBuilding(25, 800, 256, 256);
                }
                break;
            case 28:
                if(this.x(128, 128))
                {
                    this.createBuilding(31, speed, 128, 128);
                }
                break;
            case 29:
                if(this.x(256, 128))
                {
                    this.createBuilding(29, speed, 256, 128);
                }
                break;
            case 30:
                if(this.x(128, 192))
                {
                    this.createBuilding(30, speed, 128, 192);
                }
                break;
            case 31:
                if(this.x(128, 128))
                {
                    this.createBuilding(32, speed, 128, 128);
                }
                break;
            case 32:
                if(this.x(64, 128))
                {
                    this.createBuilding(33, speed, 64, 128);
                }
                break;
            case 33:
                if(this.x(64, 0))
                {
                    this.createBuilding(26, 5, 64, 0);
                }
                break;
            case 34:
                if(this.x(320, 192))
                {
                    this.createBuilding(34, speed, 320, 192);
                }
                break;
            case 35:
                if(this.x(320, 192))
                {
                    this.createBuilding(35, speed, 320, 192);
                }
                break;
            case 36:
                if(this.x(128, 192))
                {
                    this.createBuilding(36, speed, 128, 192);
                }
                break;
            case 37:
                if(this.x(128, 192))
                {
                    this.createBuilding(37, speed, 128, 192);
                }
                break;
            case 38:
                if(this.x(320, 256))
                {
                    this.createBuilding(38, speed, 320, 256);
                }
                break;
            case 39:
                if(this.x(256, 256))
                {
                    this.createBuilding(39, speed, 256, 256);
                }
                break;
            case 40:
                if(this.x(256, 256))
                {
                    this.createBuilding(40, speed, 256, 256);
                }
                break;
            case 41:
                if(this.x(320, 320))
                {
                    this.createBuilding(28, speed, 320, 320);
                }
                break;
            case 42:
                if(this.x(320, 256))
                {
                    this.createBuilding(41, speed, 320, 256);
                    this.createBuilding(43, speed, 0, 0);
                    this.createBuilding(44, speed, 0, 0);
                }
                break;
            case 43:
                if(this.x(256, 320))
                {
                    this.createBuilding(42, speed, 256, 320);
                }
                break;
            case 44:
                if(this.x(320, 320))
                {
                    this.createBuilding(27, speed, 320, 320);
                }
                break;
        }

        this.initGui();
    }

    private void createBuilding(int buildingNumber, int speed, int resource1, int resource2)
    {
        SchematicHandler.getInstance().addBuilding(SchematicRegistry.getSchematic(buildingNumber).setSpeed(speed));
        ResourceHandler.getInstance().decreasecobbleResource(resource1);
        ResourceHandler.getInstance().decreasewoodResource(resource2);
        Buildings.setBuildingTrue(buildingNumber);
    }

    private boolean x(int i, int j)
    {
        return ResourceHandler.getInstance().getcobbleResource() >= i && ResourceHandler.getInstance().getwoodResource() >= j;
    }

    private void addButton(int id, int posX, int posZ, String name)
    {
        this.buttonList.add(new GuiButton(id, posX, posZ, 120, 20, name));
    }

    private int getInventorySlotContainItem(Item item, ItemStack[] inv)
    {
        for (int i = 0; i < inv.length; ++i)
        {
            if (inv[i] != null && inv[i].getItem() == item)
            {
                return i;
            }
        }
        return -1;
    }

    public void drawScreen(int i, int j, float f)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        if(resource != null)
            this.mc.renderEngine.bindTexture(resource);

        this.drawTexturedModalRect((this.width - 255) / 2, 0, 0, 0, 255, 255);

        super.drawScreen(i, j, f);

        if(!Buildings.isTier2)
        {
            this.drawString(this.fontRendererObj, I18n.format("gui.build.stage_1", GoldKeeper.getGoldTotal()), (this.width / 2) - fontRendererObj.getStringWidth(I18n.format("gui.build.stage_1", GoldKeeper.getGoldTotal())) / 2, 15, 16763904);
        }

        if(Buildings.isTier2 && !Buildings.isTier3)
        {
            this.drawString(this.fontRendererObj, I18n.format("gui.build.stage_2", GoldKeeper.getGoldTotal()), (this.width / 2) - fontRendererObj.getStringWidth(I18n.format("gui.build.stage_2", GoldKeeper.getGoldTotal())) / 2, 15, 16763904);
        }

        if(Buildings.isTier3)
        {
            this.drawString(this.fontRendererObj, I18n.format("gui.build.stage_3", GoldKeeper.getGoldTotal()), (this.width / 2) - fontRendererObj.getStringWidth(I18n.format("gui.build.stage_3", GoldKeeper.getGoldTotal())) / 2, 15, 16763904);
        }

        if(this.woodBar != null)
        {
            this.woodBar.drawBar();
        }

        if(this.cobblestoneBar != null)
        {
            this.cobblestoneBar.drawBar();
        }

        this.drawString(this.fontRendererObj, "0       160     320", this.width / 2 - 100, 30, 16777215);
        this.drawString(this.fontRendererObj, I18n.format("gui.build.resources.logs"), this.width / 2, 40, 16763904);
        this.drawString(this.fontRendererObj, I18n.format("gui.build.resources.cobble"), this.width / 2, 60, 16763904);
    }
}