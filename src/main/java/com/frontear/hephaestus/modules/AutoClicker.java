package com.frontear.hephaestus.modules;

import com.frontear.hephaestus.helpers.DelayTimer;
import com.frontear.hephaestus.modules.api.Module;
import net.minecraft.network.play.client.C03PacketPlayer;
import org.lwjgl.input.Keyboard;

import java.util.Random;

public class AutoClicker extends Module {
    private final Random random = new Random();
    private DelayTimer delayTimer = new DelayTimer();

    private int min_cps = 9, max_cps = 13;

    public AutoClicker() {
        super("AutoClicker", Keyboard.KEY_F);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        MouseClick();
    }

    private void MouseClick() {
        if (minecraft.gameSettings.keyBindAttack.isKeyDown()) {
            int _a = 1000 / (random.nextInt(max_cps - min_cps) + min_cps);
            if (delayTimer.hasTimeElapsed(_a, true)) {
            	minecraft.thePlayer.swingItem();
            	switch (minecraft.objectMouseOver.typeOfHit) {
                    case ENTITY:
                        minecraft.playerController.attackEntity(minecraft.thePlayer, minecraft.objectMouseOver.entityHit);
                        break;
                    case BLOCK:
                        minecraft.playerController.clickBlock(minecraft.objectMouseOver.getBlockPos(), minecraft.objectMouseOver.sideHit);
                        break;
                }
            }
        }
        else {
            delayTimer.resetTime();
        }
    }
}
