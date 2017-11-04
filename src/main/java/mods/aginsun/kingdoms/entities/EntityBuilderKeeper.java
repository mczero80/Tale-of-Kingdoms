package mods.aginsun.kingdoms.entities;

import cpw.mods.fml.common.FMLCommonHandler;
import mods.aginsun.kingdoms.client.guis.GuiBuildScreen;
import mods.aginsun.kingdoms.entities.api.EntityNPC;
import mods.aginsun.kingdoms.handlers.WorthyKeeper;
import mods.aginsun.kingdoms.util.Buildings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public final class EntityBuilderKeeper extends EntityNPC
{
    private boolean follow = true, setted = false;

    public EntityBuilderKeeper(World world)
    {
        super(world, new ItemStack(Items.stone_axe), 100.0F);
        this.isImmuneToFire = false;
    }

    @Override
    public boolean canBePushed()
    {
        return false;
    }

    @Override
    protected boolean isMovementCeased()
    {
        return this.follow;
    }

    @Override
    public boolean interact(EntityPlayer entityplayer)
    {
        if (!super.world.isRemote)
        {
            if(this.canInteractWith(entityplayer) && WorthyKeeper.getInstance().getWorthy() < 10000.0F && !Buildings.kingdomCreated)
            {
                entityplayer.addChatMessage(new ChatComponentText(I18n.format("npc.cityBuilder.dialog_1")));
            }

            if(!this.follow || setted || WorthyKeeper.getInstance().getWorthy() >= 10000.0F && Buildings.kingdomCreated)
            {
                entityplayer.addChatMessage(new ChatComponentText(I18n.format("npc.cityBuilder.dialog_2")));
                FMLCommonHandler.instance().showGuiScreen(new GuiBuildScreen(entityplayer, super.world));
                this.follow = true;
                this.setted = true;
            }
            else if(this.canInteractWith(entityplayer) && WorthyKeeper.getInstance().getWorthy() >= 10000.0F && this.follow && !Buildings.kingdomCreated && !setted)
            {
                entityplayer.addChatMessage(new ChatComponentText(I18n.format("npc.cityBuilder.dialog_3")));
                this.follow = false;
            }
        }
        return true;
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        if (!this.follow && !Buildings.kingdomCreated)
        {
            EntityPlayer player = Minecraft.getMinecraft().thePlayer;

            if (player != null)
            {
                PathEntity pathentity;
                if(player.getDistanceToEntity(this) > 3.5F && player.getDistanceToEntity(this) < 25.0F)
                {
                    pathentity = super.world.getPathEntityToEntity(this, player, 16.0F, true, false, false, true);
                }
                else
                {
                    pathentity = null;
                }
                this.setPathToEntity(pathentity);
            }
        }
    }
}
