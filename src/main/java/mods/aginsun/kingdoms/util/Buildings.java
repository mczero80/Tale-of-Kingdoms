package mods.aginsun.kingdoms.util;

import java.util.ArrayList;

public final class Buildings
{
    private static ArrayList buildingList = new ArrayList();
    public static boolean
            createGuild, kingdomCreated, smallhouse1, smallhouse2, largehouse1, well, itemshop, stockmarket, isTier2,
            smallhouse3, smallhouse4, largehouse2, builderhouse, barracks, foodshop, blockshop, isTier3, smallhouse5,
            smallhouse6, smallhouse7, largehouse3, tavern, chapel, library, magehall, isTier4, bridge, castle, colloseum,
            easternTower, fishHut, lightHouse, mill, observerPost, smallhouse8, smallhouse9, smallhouse10, smallhouse11,
            largehouse4, northernTower1, northernTower2, stables, zeppelin;

    public static void registerBuildings()
    {
        addBuilding(createGuild);
        addBuilding(kingdomCreated);
        addBuilding(smallhouse1);
        addBuilding(smallhouse2);
        addBuilding(largehouse1);
        addBuilding(well);
        addBuilding(itemshop);
        addBuilding(stockmarket);
        addBuilding(isTier2);
        addBuilding(smallhouse3);
        addBuilding(largehouse2);
        addBuilding(builderhouse);
        addBuilding(smallhouse4);
        addBuilding(barracks);
        addBuilding(foodshop);
        addBuilding(blockshop);
        addBuilding(isTier3);
        addBuilding(tavern);
        addBuilding(smallhouse5);
        addBuilding(smallhouse6);
        addBuilding(smallhouse7);
        addBuilding(chapel);
        addBuilding(largehouse3);
        addBuilding(library);
        addBuilding(magehall);
        addBuilding(isTier4);
        addBuilding(bridge);
        addBuilding(castle);
        addBuilding(colloseum);
        addBuilding(easternTower);
        addBuilding(fishHut);
        addBuilding(largehouse4);
        addBuilding(lightHouse);
        addBuilding(mill);
        addBuilding(northernTower1);
        addBuilding(northernTower2);
        addBuilding(observerPost);
        addBuilding(smallhouse8);
        addBuilding(smallhouse9);
        addBuilding(smallhouse10);
        addBuilding(smallhouse11);
        addBuilding(stables);
        addBuilding(zeppelin);
        addBuilding(stables);
        addBuilding(stables);
    }

    public static void addBuilding(boolean x)
    {
        buildingList.add(x);
    }

    public static void setBuildingTrue(int number)
    {
        boolean h = (Boolean) buildingList.get(number);
        h = true;
        buildingList.set(number, h);
    }

    public static void setBuildingState(boolean x, int number)
    {
        boolean h = (Boolean) buildingList.get(number);
        buildingList.set(number, x);
    }

    public static boolean getBuilding(int number)
    {
        return (Boolean) getBuildingList().get(number);
    }

    public static ArrayList getBuildingList()
    {
        return buildingList;
    }

    public void setBuildingList(ArrayList buildingList)
    {
        this.buildingList = buildingList;
    }
}