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

        if (canSprint()) {
            minecraft.thePlayer.setSprinting(true);
        }
    }

    private boolean canSprint() {
        return (!minecraft.thePlayer.isBlocking() && !minecraft.thePlayer.isOnLadder() && !minecraft.thePlayer.isCollidedHorizontally && minecraft.thePlayer.getFoodStats().getFoodLevel() > 6);
    }
}
