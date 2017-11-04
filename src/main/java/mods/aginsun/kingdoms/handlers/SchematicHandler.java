package mods.aginsun.kingdoms.handlers;

import mods.aginsun.kingdoms.util.Buildings;
import mods.aginsun.kingdoms.util.FakeBlock;
import mods.aginsun.kingdoms.util.FakeEntity;
import mods.aginsun.kingdoms.util.UtilToK;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Iterator;

public final class SchematicHandler
{
    private int index;
    private ArrayList torchList = new ArrayList();
    private ArrayList buildingList = new ArrayList();
    private static SchematicHandler instance = new SchematicHandler();

    public static SchematicHandler getInstance()
    {
        return instance;
    }

    public void addBuilding(Schematic schematic)
    {
        this.buildingList.add(schematic);
    }

    public ArrayList getBuildingList()
    {
        return this.buildingList;
    }

    public void update(World world)
    {
        if(!this.buildingList.isEmpty()) {
            Schematic x = (Schematic)this.buildingList.get(0);
            if(x != null) {
                ArrayList arrayList = x.getBlockList();
                ArrayList arrayList1 = x.getEntityList();
                if(arrayList.isEmpty() && arrayList1.isEmpty()) {
                    System.out.println("EMPTY LISTS");
                } else {
                    for(int i = 0; i < x.speed; ++i) {
                        if(this.index < arrayList.size()) {
                            FakeBlock i$ = (FakeBlock)arrayList.get(this.index);
                            if(i$ != null && world.getBlock(x.x + i$.posX, x.y + i$.posY, x.z + i$.posZ) != i$.block) {
                                if(i$.block == Blocks.air) {
                                    world.setBlockToAir(x.x + i$.posX, x.y + i$.posY, x.z + i$.posZ);
                                }

                                if(i$.block != Blocks.torch && i$.block != Blocks.wooden_door && i$.block != Blocks.ladder && i$.block != Blocks.trapdoor) {
                                    world.setBlock(x.x + i$.posX, x.y + i$.posY, x.z + i$.posZ, i$.block, i$.metadata, 3);
                                } else {
                                    this.torchList.add(i$);
                                }
                            }

                            ++this.index;
                        } else if(this.index < arrayList.size() + arrayList1.size()) {
                            FakeEntity var8 = (FakeEntity)arrayList1.get(this.index - arrayList.size());
                            EntityLiving block = (EntityLiving)EntityList.createEntityByName(var8.entityName, world);
                            if(block != null) {
                                if(Buildings.getBuilding(1)) {
                                    block.setPosition((double)UtilToK.townX + var8.posX, (double)UtilToK.townY + var8.posY, (double)UtilToK.townZ + var8.posZ);
                                } else {
                                    block.setPosition((double)x.x + var8.posX, (double)x.y + var8.posY + 1.5D, (double)x.z + var8.posZ);
                                }

                                world.spawnEntityInWorld(block);
                            } else {
                                System.out.println("ERROR " + var8.entityName);
                            }

                            ++this.index;
                        } else {

                            for (Object aTorchList : this.torchList) {
                                FakeBlock var10 = (FakeBlock) aTorchList;
                                world.setBlock(x.x + var10.posX, x.y + var10.posY, x.z + var10.posZ, var10.block, var10.metadata, 3);
                            }

                            this.index = 0;
                            if(!this.buildingList.isEmpty()) {
                                this.buildingList.remove(0);
                            }

                            this.torchList.clear();
                            System.out.println("REMOVED BUILDING");
                        }
                    }
                }
            } else {
                System.out.println("CANNOT FIND SCHEMATIC");
            }
        }
    }

    public float getProgressCurrentBuilding()
    {
        if(this.buildingList.isEmpty())
        {
            return 0.0F;
        }
        else
        {
            ArrayList arrayList = ((Schematic)this.buildingList.get(0)).getBlockList();
            float index = (float)this.index;
            float f = index / (float)arrayList.size() * 100.0F;
            return f;
        }
    }
}