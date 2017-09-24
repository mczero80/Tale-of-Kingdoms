package mods.aginsun.kingdoms.util;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

public final class UtilToK {

   public static final String Version = "1.5-Pre Release";
   public static final String modid = "taleofkingdoms";
   public static int libraryInvestment;
   public static boolean guildFightEnded;
   public static boolean guildFightStarted;
   public static int townX;
   public static int townY;
   public static int townZ;
   public static int burningVillages;
   public static int reficulHoles;
   public static int bindLight;


   public static void spawnEntity(World world, String name, ChunkCoordinates position) {
      EntityLivingBase entityliving = (EntityLivingBase)EntityList.createEntityByName(name, world);
      entityliving.setLocationAndAngles((double)position.posX, (double)position.posY, (double)position.posZ, 0.0F, 0.0F);
      world.spawnEntityInWorld(entityliving);
   }
}
