package mods.aginsun.kingdoms.handlers;

import cpw.mods.fml.common.FMLCommonHandler;
import mods.aginsun.kingdoms.TaleOfKingdoms;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public final class ItemDropHelper
{
    public static World world = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(0);

    public static boolean isHostileEntity(EntityLivingBase entityLiving)
    {
        return entityLiving instanceof EntityBlaze || entityLiving instanceof EntityCaveSpider || entityLiving instanceof EntityCreeper || entityLiving instanceof EntityDragon || entityLiving instanceof EntityEnderman || entityLiving instanceof EntityGhast || entityLiving instanceof EntityMagmaCube || entityLiving instanceof EntityPigZombie || entityLiving instanceof EntitySilverfish || entityLiving instanceof EntitySkeleton || entityLiving instanceof EntitySpider || entityLiving instanceof EntityWitch || entityLiving instanceof EntityWither || entityLiving instanceof EntityZombie;
    }

    public static void dropCoins(EntityPlayer player, EntityLivingBase entityLiving)
    {
        if(isHostileEntity(entityLiving) && !world.isRemote)
        {
            dropItem(TaleOfKingdoms.coins, 1, entityLiving);
            dropItem(TaleOfKingdoms.coins, 1, entityLiving);
            dropItem(TaleOfKingdoms.coins, 1, entityLiving);
            dropItem(TaleOfKingdoms.coins, 1, entityLiving);
            dropItem(TaleOfKingdoms.coins, 1, entityLiving);
            dropItem(TaleOfKingdoms.coins, 1, entityLiving);
            dropItem(TaleOfKingdoms.coins, 1, entityLiving);
            dropItem(TaleOfKingdoms.coins, 1, entityLiving);
            dropItem(TaleOfKingdoms.coins, 1, entityLiving);
            dropItem(TaleOfKingdoms.coins, 1, entityLiving);
            dropItem(TaleOfKingdoms.coins, 1, entityLiving);
            dropItem(TaleOfKingdoms.coins, 1, entityLiving);
            dropItem(TaleOfKingdoms.coins, 1, entityLiving);
            dropItem(TaleOfKingdoms.coins, 1, entityLiving);
            dropItem(TaleOfKingdoms.coins, 1, entityLiving);
            dropItem(TaleOfKingdoms.coins, 1, entityLiving);
            dropItem(TaleOfKingdoms.coins, 1, entityLiving);
            dropItem(TaleOfKingdoms.coins, 1, entityLiving);
            dropItem(TaleOfKingdoms.coins, 1, entityLiving);
            dropItem(TaleOfKingdoms.coins, 1, entityLiving);
            dropItem(TaleOfKingdoms.coins, 1, entityLiving);
            dropItem(TaleOfKingdoms.coins, 1, entityLiving);
            dropItem(TaleOfKingdoms.coins, 1, entityLiving);
            dropItem(TaleOfKingdoms.coins, 1, entityLiving);
            dropItem(TaleOfKingdoms.coins, 1, entityLiving);
            dropItem(TaleOfKingdoms.coins, 1, entityLiving);
        }
    }

    private static void dropItem(Item item, int meta, EntityLivingBase livingBase)
    {
        livingBase.dropItem(item, meta);
    }
}