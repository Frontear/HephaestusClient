package com.frontear.hephaestus.modules;

import com.frontear.hephaestus.modules.api.Module;
import org.lwjgl.input.Keyboard;

import java.util.Random;

public class AutoClicker extends Module {

    private final int cps_max_ms = 10;
    private final int cps_min_ms = 5;
    private int ticks_ms; // 1 tick = 5ms
    private int random_ms;

    private final Random random = new Random();

    public AutoClicker() {
        super("AutoClicker", Keyboard.KEY_F);
        random_ms = random.nextInt(cps_max_ms - cps_min_ms) + cps_min_ms; // random value between cps_max_ms and cps_min_ms
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        ticks_ms += 5;
        MouseClick();
    }

    private void MouseClick() {
        if (minecraft.gameSettings.keyBindAttack.isKeyDown()) {
            if (ticks_ms > random_ms) {
                minecraft.thePlayer.swingItem();
                switch (minecraft.objectMouseOver.typeOfHit) {
                    case ENTITY:
                        minecraft.playerController.attackEntity(minecraft.thePlayer, minecraft.objectMouseOver.entityHit);
                        break;
                    case BLOCK:
                        minecraft.playerController.clickBlock(minecraft.objectMouseOver.getBlockPos(), minecraft.objectMouseOver.sideHit);
                        break;
                }

                ticks_ms = 0;
                random_ms = random.nextInt(cps_max_ms);
            }
        }
    }
}
