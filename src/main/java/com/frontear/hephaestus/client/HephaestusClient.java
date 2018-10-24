package com.frontear.hephaestus.client;

import com.frontear.hephaestus.managers.*;
import com.frontear.hephaestus.modules.AutoClicker;
import com.frontear.hephaestus.modules.api.Module;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.opengl.Display;

public class HephaestusClient {
    public final String CLIENT_NAME = "Hephaestus";
    public final double CLIENT_VERSION = 0.2;

    public UIManager uiManager;
    public ModuleManager moduleManager;

    public HephaestusClient() {
        uiManager = new UIManager();
        moduleManager = new ModuleManager();

        Display.setTitle(CLIENT_NAME + " " + "Client" + " " + CLIENT_VERSION);
    }

    @SubscribeEvent
    public void onGui(RenderGameOverlayEvent.Post event) {
        if (event.type == RenderGameOverlayEvent.ElementType.TEXT) {
            uiManager.Draw();
            for (int i = 0; i < moduleManager.getEnabledModules().size(); i++) {
                moduleManager.getEnabledModules().get(i).onGui(i);
            }
        }
    }

    @SubscribeEvent
    public void onUpdate(TickEvent.PlayerTickEvent event) {
        for (Module module : moduleManager.getEnabledModules()) {
            module.onUpdate();
        }
    }

    @SubscribeEvent
    public void onRender(EntityViewRenderEvent event) {
        for (Module module : moduleManager.getEnabledModules()) {
            module.onRender();
        }
    }

    @SubscribeEvent
    public void onKey(InputEvent.KeyInputEvent event) {
        for (Module module : moduleManager.moduleList) {
            module.onKey();
        }
    }
}
