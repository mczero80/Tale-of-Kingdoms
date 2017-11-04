package mods.aginsun.kingdoms.entities.api;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityNPC extends EntityCreature
{
    private ItemStack defaultHeldItem;
    protected World world;
    protected EntityPlayer player = Minecraft.getMinecraft().thePlayer;

    public EntityNPC(World par1World, ItemStack defaultHeldItem, float i)
    {
        super(par1World);
        this.setHealth(i);
        this.world = par1World;
        this.defaultHeldItem = defaultHeldItem;
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5D);
    }

    @Override
    public ItemStack getHeldItem()
    {
        return this.defaultHeldItem;
    }

    @Override
    protected boolean canDespawn()
    {
        return false;
    }

    @Override
    public boolean canBePushed()
    {
        return true;
    }

    @Override
    protected boolean isMovementCeased()
    {
        return false;
    }

    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        return this.isDead ? false : entityplayer.getDistanceSqToEntity(this) <= 64.0D;
    }
}