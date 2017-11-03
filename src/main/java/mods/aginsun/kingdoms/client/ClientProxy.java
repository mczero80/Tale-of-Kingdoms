package mods.aginsun.kingdoms.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import mods.aginsun.kingdoms.client.guis.GuiSell;
import mods.aginsun.kingdoms.client.handlers.ClientTickHandler;
import mods.aginsun.kingdoms.client.handlers.KeyBindingHandler;
import mods.aginsun.kingdoms.client.render.RenderBipedToK;
import mods.aginsun.kingdoms.core.CommonProxy;
import mods.aginsun.kingdoms.entities.*;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public final class ClientProxy extends CommonProxy
{
    public void registerRenderers()
    {
        MinecraftForge.EVENT_BUS.register(new ClientTickHandler());
        FMLCommonHandler.instance().bus().register(new ClientTickHandler());
        FMLCommonHandler.instance().bus().register(new KeyBindingHandler());
        setRender(EntityGuildMaster.class, "head");
        setRender(EntityInnKeeper.class, "inn");
        setRender(EntityBuilderKeeper.class, "builder");
        setRender(EntityHunterKeeper.class, "head");
        setRender(EntityBankerKeeper.class, "banker");
        setRender(EntityBarracksKeeper.class, "guardelite");
        setRender(EntityDefendArcher.class, "hunter");
        setRender(EntityDefendBandit.class, "bandit");
        setRender(EntityDefendKnight.class, "knight");
        setRender(EntityDefendMage.class, "wizard");
        setRender(EntityDefendMarker.class, "");
        setRender(EntityDefendPaladin.class, "paladin");
        setRender(EntityDefendPriest.class, "priest");
        setRender(EntityDefendWarrior.class, "warrior");
        setRender(EntityFarmerKeeper.class, "inn");
        setRender(EntityFoodKeeper.class, "food");
        setRender(EntityLumber.class, "foremanlumber");
        setRender(EntityWeaponKeeper.class, "smith");
        setRender(EntityGuildMember.class, "member");
        setRender(EntityHired.class, "guild");
        setRender(EntityForgeKeeper.class, "forge");
        setRender(EntityHeadCommander.class, "headcommander");
        setRender(EntityKingdomWorker.class, "worker");
        setRender(EntityLibraryKeeper.class, "librarian");
        setRender(EntityLoneTraveller.class, "lone");
        setRender(EntityLostVillager.class, "ac");
        setRender(EntityMarkerKeeper.class, "");
        setRender(EntityMarker2Keeper.class, "");
        setRender(EntityPriestKeeper.class, "headpriest");
        setRender(EntityReficulGuardian.class, "reficulGuardian");
        setRender(EntityReficulMage.class, "reficulMage");
        setRender(EntityReficulSoldier.class, "reficulSoldier");
        setRender(EntityShopKeeper.class, "forge");
        setRender(EntityStockKeeper.class, "stock");
        setRender(EntityTavernKeeper.class, "tavern");
        setRender(EntityVillageMember.class, "man1");
        setRender(EntityWorkerMember.class, "worker");
        setRender(EntityMageKeeper.class, "headmage");
        setRender(EntityQuarry.class, "foremanquarry");
        setRender(EntityStableMaster.class, "");
        setRender(EntityFisher.class, "fisher");
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        if (id == 1)
        {
            return new GuiSell(player.inventory, new TileEntitySell());
        }
        return super.getClientGuiElement(id, player, world, x, y, z);
    }

    private void setRender(Class<? extends Entity> entity, String location)
    {
        RenderingRegistry.registerEntityRenderingHandler(entity, new RenderBipedToK(new ModelBiped(), 0.4F, location));
    }
}