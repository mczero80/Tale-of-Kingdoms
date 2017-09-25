package mods.aginsun.kingdoms.entities;

import mods.aginsun.kingdoms.client.guis.GuiFisher;
import mods.aginsun.kingdoms.entities.api.EntityNPC;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public final class EntityFisher extends EntityNPC
{
    public EntityFisher(World par1World)
    {
        super(par1World, new ItemStack(Items.fishing_rod), 40.0F);
    }

    public boolean canBePushed()
    {
        return false;
    }

    protected boolean isMovementCeased()
    {
        return true;
    }

    public boolean interact(EntityPlayer entityplayer)
    {
        if(this.canInteractWith(entityplayer))
        {
            Minecraft.getMinecraft().displayGuiScreen(new GuiFisher(entityplayer));
        }
        return true;
    }
}