package com.frontear.hephaestus.modules;

import com.frontear.hephaestus.modules.api.Module;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import org.lwjgl.input.Keyboard;

public class Fullbright extends Module {
    private float previousGamma;
    public Fullbright() {
        super("Fullbright", Keyboard.KEY_B);
        previousGamma = minecraft.gameSettings.gammaSetting;
    }

    @Override
    public void onToggle(boolean state) {
        super.onToggle(state);

        minecraft.gameSettings.gammaSetting = (state ? 100f : previousGamma);
    }
}
