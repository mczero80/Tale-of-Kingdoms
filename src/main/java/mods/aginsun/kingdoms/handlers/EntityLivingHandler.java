package mods.aginsun.kingdoms.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public final class EntityLivingHandler
{
    @SubscribeEvent
    public void onEntityLivingDeath(LivingDeathEvent e)
    {
        if(e.source.getDamageType().equals("player"))
        {
            ItemDropHelper.dropCoins(e.entityLiving);
        }
    }
}