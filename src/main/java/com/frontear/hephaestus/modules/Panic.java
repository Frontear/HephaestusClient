package com.frontear.hephaestus.modules;

import com.frontear.hephaestus.Hephaestus;
import com.frontear.hephaestus.modules.api.Module;
import org.lwjgl.input.Keyboard;

public class Panic extends Module {
    public Panic() {
        super("Name", Keyboard.KEY_P);
    }

    @Override
    public void onGui(int offset) {} // panic is not meant to display to the gui. It's meant to clear it

    @Override
    public void onToggle(boolean state) {
        super.onToggle(state);

        if (Hephaestus.client.moduleManager.getEnabledModules().size() > 1) {
            for (Module module : Hephaestus.client.moduleManager.getEnabledModules()) {
                module.Toggle();
            }
        }
    }
}
