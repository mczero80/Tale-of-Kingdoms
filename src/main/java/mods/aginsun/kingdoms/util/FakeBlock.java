package mods.aginsun.kingdoms.util;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public final class FakeBlock extends Block
{
   public int blockID;
   public int metadata;
   public int posX;
   public int posY;
   public int posZ;

   public FakeBlock(int id, int metadata, int posX, int posY, int posZ)
   {
      super(Material.anvil);
      this.blockID = id;
      this.metadata = metadata;
      this.posX = posX;
      this.posY = posY;
      this.posZ = posZ;
   }
}
