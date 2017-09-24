package mods.aginsun.kingdoms.entities;

import cpw.mods.fml.common.registry.EntityRegistry;
import java.awt.Color;
import mods.aginsun.kingdoms.TaleOfKingdoms;
import mods.aginsun.kingdoms.entities.EntityBankerKeeper;
import mods.aginsun.kingdoms.entities.EntityBarracksKeeper;
import mods.aginsun.kingdoms.entities.EntityBuilderKeeper;
import mods.aginsun.kingdoms.entities.EntityDefendArcher;
import mods.aginsun.kingdoms.entities.EntityDefendBandit;
import mods.aginsun.kingdoms.entities.EntityDefendKnight;
import mods.aginsun.kingdoms.entities.EntityDefendMage;
import mods.aginsun.kingdoms.entities.EntityDefendMarker;
import mods.aginsun.kingdoms.entities.EntityDefendPaladin;
import mods.aginsun.kingdoms.entities.EntityDefendPriest;
import mods.aginsun.kingdoms.entities.EntityDefendWarrior;
import mods.aginsun.kingdoms.entities.EntityFarmerKeeper;
import mods.aginsun.kingdoms.entities.EntityFisher;
import mods.aginsun.kingdoms.entities.EntityFoodKeeper;
import mods.aginsun.kingdoms.entities.EntityForgeKeeper;
import mods.aginsun.kingdoms.entities.EntityGuildMaster;
import mods.aginsun.kingdoms.entities.EntityGuildMember;
import mods.aginsun.kingdoms.entities.EntityHeadCommander;
import mods.aginsun.kingdoms.entities.EntityHired;
import mods.aginsun.kingdoms.entities.EntityHunterKeeper;
import mods.aginsun.kingdoms.entities.EntityInnKeeper;
import mods.aginsun.kingdoms.entities.EntityKingdomWorker;
import mods.aginsun.kingdoms.entities.EntityLibraryKeeper;
import mods.aginsun.kingdoms.entities.EntityLoneTraveller;
import mods.aginsun.kingdoms.entities.EntityLostVillager;
import mods.aginsun.kingdoms.entities.EntityLumber;
import mods.aginsun.kingdoms.entities.EntityMageKeeper;
import mods.aginsun.kingdoms.entities.EntityMarker2Keeper;
import mods.aginsun.kingdoms.entities.EntityMarkerKeeper;
import mods.aginsun.kingdoms.entities.EntityPriestKeeper;
import mods.aginsun.kingdoms.entities.EntityQuarry;
import mods.aginsun.kingdoms.entities.EntityReficulGuardian;
import mods.aginsun.kingdoms.entities.EntityReficulMage;
import mods.aginsun.kingdoms.entities.EntityReficulSoldier;
import mods.aginsun.kingdoms.entities.EntityShopKeeper;
import mods.aginsun.kingdoms.entities.EntityStableMaster;
import mods.aginsun.kingdoms.entities.EntityStockKeeper;
import mods.aginsun.kingdoms.entities.EntityTavernKeeper;
import mods.aginsun.kingdoms.entities.EntityVillageMember;
import mods.aginsun.kingdoms.entities.EntityWeaponKeeper;
import mods.aginsun.kingdoms.entities.EntityWorkerMember;

public class EntityRegistryToK {

   public static void registerEntities() {
      registerEntity(EntityStableMaster.class, "StableMaster", 214);
      registerEntity(EntityFisher.class, "Fisher", 215);
      registerEntity(EntityLumber.class, "Lumber", 216);
      registerEntity(EntityWorkerMember.class, "WorkerMember", 217);
      registerEntity(EntityDefendMarker.class, "DefendMark", 218);
      registerEntity(EntityMageKeeper.class, "MageKeeper", 219);
      registerEntity(EntityGuildMaster.class, "GuildMaster", 220);
      registerEntity(EntityInnKeeper.class, "InnKeeper", 221);
      registerEntity(EntityHunterKeeper.class, "GuildKeeper", 222);
      registerEntity(EntityBuilderKeeper.class, "BuilderKeeper", 223);
      registerEntity(EntityBankerKeeper.class, "Banker", 224);
      registerEntity(EntityBarracksKeeper.class, "BarracksKeeper", 225);
      registerEntity(EntityDefendArcher.class, "DefendArcher", 226);
      registerEntity(EntityDefendBandit.class, "DefendBandit", 227);
      registerEntity(EntityDefendKnight.class, "DefendKnight", 228);
      registerEntity(EntityDefendMage.class, "DefendMage", 229);
      registerEntity(EntityDefendPaladin.class, "DefendPaladin", 230);
      registerEntity(EntityDefendPriest.class, "DefendPriest", 231);
      registerEntity(EntityDefendWarrior.class, "DefendWarrior", 232);
      registerEntity(EntityFarmerKeeper.class, "Farmer", 233);
      registerEntity(EntityFoodKeeper.class, "FoodKeeper", 234);
      registerEntity(EntityQuarry.class, "Quarry", 235);
      registerEntity(EntityWeaponKeeper.class, "WeaponKeeper", 236);
      registerEntity(EntityGuildMember.class, "GuildMember", 237);
      registerEntity(EntityHired.class, "Hired", 238);
      registerEntity(EntityForgeKeeper.class, "ForgeKeeper", 239);
      registerEntity(EntityHeadCommander.class, "HeadCommander", 240);
      registerEntity(EntityKingdomWorker.class, "KingdomWorker", 241);
      registerEntity(EntityLibraryKeeper.class, "LibraryKeeper", 242);
      registerEntity(EntityLoneTraveller.class, "LoneTraveller", 243);
      registerEntity(EntityLostVillager.class, "LostVillager", 244);
      registerEntity(EntityMarkerKeeper.class, "Marker", 245);
      registerEntity(EntityMarker2Keeper.class, "Marker2", 246);
      registerEntity(EntityPriestKeeper.class, "PriestKeeper", 247);
      registerEntity(EntityReficulGuardian.class, "ReficulGuardian", 248);
      registerEntity(EntityReficulMage.class, "ReficulMage", 249);
      registerEntity(EntityReficulSoldier.class, "ReficulSoldier", 250);
      registerEntity(EntityShopKeeper.class, "ShopKeeper", 251);
      registerEntity(EntityStockKeeper.class, "StockKeeper", 252);
      registerEntity(EntityTavernKeeper.class, "TavernKeeper", 253);
      registerEntity(EntityVillageMember.class, "VillageMember", 254);
   }

   private static void registerEntity(Class entityClass, String name, int id) {
      EntityRegistry.registerGlobalEntityID(entityClass, name, id, Color.red.getRGB(), Color.green.getRGB());
      EntityRegistry.registerModEntity(entityClass, name, id, TaleOfKingdoms.instance, 250, 2, false);
   }
}
