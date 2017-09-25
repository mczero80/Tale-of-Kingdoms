package mods.aginsun.kingdoms.entities;

import mods.aginsun.kingdoms.client.guis.GuiFoodKeeper;
import mods.aginsun.kingdoms.entities.api.EntityNPC;
import mods.aginsun.kingdoms.handlers.GoldKeeper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public final class EntityFarmerKeeper extends EntityNPC
{
    private static ItemStack defaultHeldItem = new ItemStack(Items.iron_hoe, 1);
    public static GoldKeeper gold;

    public EntityFarmerKeeper(World world)
    {
        super(world, defaultHeldItem, 100.0F);
        this.isImmuneToFire = false;
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
        final Minecraft mc = Minecraft.getMinecraft();

        if(this.canInteractWith(entityplayer))
        {
            mc.displayGuiScreen(new GuiFoodKeeper(entityplayer, this.worldObj));
        }
        return true;
    }
}