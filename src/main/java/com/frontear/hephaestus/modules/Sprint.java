package com.frontear.hephaestus.modules;

import com.frontear.hephaestus.modules.api.Module;
import org.lwjgl.input.Keyboard;

public class Sprint extends Module {
    public Sprint() {
        super("Sprint", Keyboard.KEY_V);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (minecraft.thePlayer.moveForward > 0) {
            if (!minecraft.thePlayer.isSprinting()) {
                minecraft.thePlayer.setSprinting(true);
            }
        }
    }
}
