package mods.aginsun.kingdoms.entities;

import mods.aginsun.kingdoms.client.guis.GuiWardenMenu;
import mods.aginsun.kingdoms.entities.api.EntityNPC;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public final class EntityBarracksKeeper extends EntityNPC
{
    private static ItemStack defaultHeldItem = new ItemStack(Items.iron_sword, 1);

    public EntityBarracksKeeper(World world)
    {
        super(world, defaultHeldItem, 20.0F);
    }

    @Override
    public boolean canBePushed()
    {
        return false;
    }

    @Override
    public boolean interact(EntityPlayer entityplayer)
    {
        if(!this.world.isRemote)
        {
            entityplayer.addChatMessage(new ChatComponentText("Warden: The guild\'s warden at your service."));
        }

        if(this.canInteractWith(entityplayer))
        {
            Minecraft.getMinecraft().displayGuiScreen(new GuiWardenMenu(entityplayer, this.worldObj));
        }
        return true;
    }
}