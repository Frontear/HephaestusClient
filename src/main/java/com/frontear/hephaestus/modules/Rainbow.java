package com.frontear.hephaestus.modules;

import com.frontear.hephaestus.modules.api.Module;
import org.lwjgl.input.Keyboard;

public class Rainbow extends Module {
    public Rainbow() {
        super("Rainbow", Keyboard.KEY_NONE);
    }

    @Override
    public void onGui(int offset) {}
}
