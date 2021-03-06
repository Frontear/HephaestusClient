package com.frontear.hephaestus.client;

import com.frontear.hephaestus.managers.ModuleManager;
import com.frontear.hephaestus.managers.UIManager;
import com.frontear.hephaestus.modules.api.Module;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.opengl.Display;

public class HephaestusClient {
    public final String CLIENT_NAME = "Hephaestus";
    public final double CLIENT_VERSION = 0.6;

    public ModuleManager moduleManager;
    public UIManager uiManager;

    public HephaestusClient() {
        moduleManager = new ModuleManager();
        uiManager = new UIManager();

        Display.setTitle(CLIENT_NAME + " " + "Client" + " " + CLIENT_VERSION);
    }

    @SubscribeEvent
    public void onGui(RenderGameOverlayEvent event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            uiManager.Draw();
            for (int i = 0; i < moduleManager.getEnabledModules().size(); i++) {
                moduleManager.getEnabledModules().get(i).onGui(i);
            }
        }
    }

    @SubscribeEvent
    public void onUpdate(TickEvent.PlayerTickEvent event) {
        if (event.type == TickEvent.Type.PLAYER) {
            for (Module module : moduleManager.getEnabledModules()) {
                module.onUpdate();
            }
        }
    }

    @SubscribeEvent
    public void onRender(RenderHandEvent event) {
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
