package mods.aginsun.kingdoms.util;

import net.minecraft.block.Block;

public final class FakeBlock
{
    public Block block;
    public int metadata, posX, posY, posZ;

    public FakeBlock(int block, int metadata, int posX, int posY, int posZ)
    {
        this.block = Block.getBlockById(block);
        this.metadata = metadata;
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
    }
}