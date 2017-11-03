package mods.aginsun.kingdoms.util;

public final class FakeEntity
{
    public String entityName;
    public double posX, posY, posZ;

    public FakeEntity(String entityName, double posX, double posY, double posZ)
    {
        this.entityName = entityName;
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
    }
}