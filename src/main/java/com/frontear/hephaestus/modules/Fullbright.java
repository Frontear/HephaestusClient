package com.frontear.hephaestus.modules;

import com.frontear.hephaestus.modules.api.Module;
import org.lwjgl.input.Keyboard;

public class Fullbright extends Module {
    public Fullbright() {
        super("Fullbright", Keyboard.KEY_B);
    }

    @Override
    public void onToggle(boolean state) {
        super.onToggle(state);

        minecraft.gameSettings.gammaSetting = (state ? 100f : 0.5f);
    }
}
