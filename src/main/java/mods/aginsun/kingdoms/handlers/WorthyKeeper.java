package mods.aginsun.kingdoms.handlers;

public final class WorthyKeeper
{
    private float worthy = 0.0F;
    private static WorthyKeeper instance = new WorthyKeeper();

    public static WorthyKeeper getInstance()
    {
        return instance;
    }

    public float getWorthy()
    {
        return this.worthy;
    }

    public void setWorthy(float i)
    {
        this.worthy = i;
    }

    public void addWorthy(float i)
    {
        this.worthy += i;
    }

    public void decreaseWorthy(float i)
    {
        this.worthy -= i;
    }
}