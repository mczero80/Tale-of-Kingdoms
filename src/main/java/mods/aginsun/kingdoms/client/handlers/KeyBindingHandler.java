package mods.aginsun.kingdoms.client.handlers;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.aginsun.kingdoms.client.guis.GuiStartConquest;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public final class KeyBindingHandler
{
    private static KeyBinding key = new KeyBinding(I18n.format("keyBinding.conquest"), 21, "Tale Of Kingdoms");

    public KeyBindingHandler()
    {
        ClientRegistry.registerKeyBinding(key);
    }

    @SubscribeEvent
    public void onInput(InputEvent.KeyInputEvent e)
    {
        if (Keyboard.isKeyDown(key.getKeyCode()))
        {
            if (FMLClientHandler.instance().getClient().currentScreen == null)
            {
                FMLCommonHandler.instance().showGuiScreen(new GuiStartConquest(Minecraft.getMinecraft()));
            }
        }
    }
}
