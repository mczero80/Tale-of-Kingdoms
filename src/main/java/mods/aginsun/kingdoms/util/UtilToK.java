package mods.aginsun.kingdoms.util;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

public final class UtilToK
{
    public static boolean guildFightEnded, guildFightStarted;
    public static int libraryInvestment, townX, townY, townZ, burningVillages, reficulHoles, bindLight;

    public static void spawnEntity(World world, String name, ChunkCoordinates position)
    {
        if (!world.isRemote)
        {
            EntityLivingBase entityliving = (EntityLivingBase)EntityList.createEntityByName(name, world);
            entityliving.setLocationAndAngles((double) position.posX, (double) position.posY, (double) position.posZ, 0.0F, 0.0F);
            world.spawnEntityInWorld(entityliving);
        }
    }
}