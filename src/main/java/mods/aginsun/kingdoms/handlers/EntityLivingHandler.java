package mods.aginsun.kingdoms.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public final class EntityLivingHandler
{
    @SubscribeEvent
    public void onEntityLivingDeath(LivingDeathEvent e)
    {
        if(e.source.getDamageType().equals("player"))
        {
            ItemDropHelper.dropCoins((EntityPlayer)e.source.getSourceOfDamage(), e.entityLiving);
        }
    }
}