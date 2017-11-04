package mods.aginsun.kingdoms.entities;

import mods.aginsun.kingdoms.client.guis.GuiBank;
import mods.aginsun.kingdoms.entities.api.EntityNPC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public final class EntityBankerKeeper extends EntityNPC
{
    public EntityBankerKeeper(World world)
    {
        super(world, new ItemStack(Items.book), 40.0F);
    }

    @Override
    public boolean canBePushed()
    {
        return false;
    }

    @Override
    protected boolean isMovementCeased()
    {
        return true;
    }

    @Override
    public boolean interact(EntityPlayer entityplayer)
    {
        if(this.canInteractWith(entityplayer))
        {
            this.heal(100.0F);

            if(!this.world.isRemote)
            {
                entityplayer.addChatMessage(new ChatComponentText(I18n.format("npc.banker.dialog.welcome")));
            }

            Minecraft.getMinecraft().displayGuiScreen(new GuiBank(entityplayer, super.world));
        }
        return true;
    }
}