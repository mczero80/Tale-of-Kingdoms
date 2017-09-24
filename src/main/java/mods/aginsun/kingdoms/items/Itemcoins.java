package mods.aginsun.kingdoms.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public final class Itemcoins extends Item
{
    public Itemcoins()
    {
        this.setCreativeTab(CreativeTabs.tabMaterials);
        this.maxStackSize = 1;
    }

    @Override
    public void registerIcons(IIconRegister iconRegister)
    {
        this.itemIcon = iconRegister.registerIcon("taleofkingdoms:coins");
    }
}