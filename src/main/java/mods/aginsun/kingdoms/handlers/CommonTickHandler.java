package mods.aginsun.kingdoms.handlers;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.world.WorldServer;

public final class CommonTickHandler
{
    @SubscribeEvent
    public void onTicks(TickEvent.ServerTickEvent e)
    {
        if (e.phase.equals(TickEvent.Phase.END))
        {
            if (e.type.equals(TickEvent.Type.SERVER))
            {
                WorldServer world = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(0);
                if (world != null)
                {
                    SchematicHandler.getInstance().update(world);
                }
            }
        }
    }
}