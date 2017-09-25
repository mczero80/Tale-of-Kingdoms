package mods.aginsun.kingdoms.client.guis;

import mods.aginsun.kingdoms.handlers.GoldKeeper;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public final class GuiFoodKeeper extends GuiScreenToK
{
    private World worldObj;
    public EntityPlayer entityplayer;
    private boolean goldchecker = false, freebread = true;
    public Item item;

    public GuiFoodKeeper(EntityPlayer entityplayer1, World world)
    {
        this.entityplayer = entityplayer1;
        this.worldObj = world;
    }

    @Override
    public void initGui()
    {
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(2, this.width / 2 + 110, 160, 120, 20, "Give me some bread!"));
        this.buttonList.add(new GuiButton(3, this.width / 2 + 110, 180, 120, 20, "Exit."));
    }

    @Override
    public void actionPerformed(GuiButton guibutton)
    {
        if(guibutton.id == 1)
        {
            this.initGui();
        }

        if(guibutton.id == 2)
        {
            if(this.freebread)
            {
                if(!this.worldObj.isRemote)
                {
                    this.entityplayer.addChatMessage(new ChatComponentText("Farmer: Here, take a bread!"));
                }

                ItemStack itemstack = new ItemStack(Items.bread, 1, 0);
                EntityItem entityitem = new EntityItem(this.worldObj, this.entityplayer.posX, this.entityplayer.posY, this.entityplayer.posZ, itemstack);
                this.worldObj.spawnEntityInWorld(entityitem);
                this.freebread = false;
            }
            else if(!this.worldObj.isRemote)
            {
                this.entityplayer.addChatMessage(new ChatComponentText("Farmer: You got your bread for now!"));
            }
        }

        if(guibutton.id == 3)
        {
            this.mc.displayGuiScreen(null);
        }
    }

    @Override
    public void drawScreen(int i, int j, float f)
    {
        for(int k = 0; k < this.buttonList.size(); ++k)
        {
            GuiButton guibutton = (GuiButton)this.buttonList.get(k);
            guibutton.drawButton(this.mc, i, j);
        }

        if(this.goldchecker)
        {
            this.drawString(this.fontRendererObj, "The Guild Order  Total Money: " + GoldKeeper.getGoldTotal() + " Gold Coins - NOT ENOUGH GOLD", this.width / 2, 20, 16772608);
        }
        else
        {
            this.drawString(this.fontRendererObj, "Total Money: " + GoldKeeper.getGoldTotal() + " Gold Coins", this.width / 2, 0, 16772608);
        }
    }

    @Override
    protected void keyTyped(char par1, int par2)
    {
        if(par2 == 1 || par2 == this.mc.gameSettings.keyBindInventory.getKeyCode())
        {
            this.mc.thePlayer.closeScreen();
        }
    }
}