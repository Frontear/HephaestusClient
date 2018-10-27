package com.frontear.hephaestus.modules;

import com.frontear.hephaestus.managers.GUIManager;
import com.frontear.hephaestus.modules.api.Module;
import org.lwjgl.input.Keyboard;

public class ClickGUI extends Module {
    public ClickGUI() {
        super("ClickGui", Keyboard.KEY_LCONTROL);
    }

    @Override
    public void onGui(int offset) {}

    @Override
    public void onToggle(boolean state) {
        minecraft.displayGuiScreen(new GUIManager());
    }
}
