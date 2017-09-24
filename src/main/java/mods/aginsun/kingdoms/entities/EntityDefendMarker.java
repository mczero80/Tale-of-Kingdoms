package mods.aginsun.kingdoms.entities;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public final class EntityDefendMarker extends Entity {

   public EntityDefendMarker(World world) {
      super(world);
      this.setSize(5.0E-6F, 5.0E-6F);
   }

   public boolean canBePushed() {
      return false;
   }

   public void entityInit() {}

   public void writeEntityToNBT(NBTTagCompound nbttagcompound) {}

   public void readEntityFromNBT(NBTTagCompound nbttagcompound) {}
}
