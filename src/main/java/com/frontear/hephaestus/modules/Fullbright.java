package com.frontear.hephaestus.modules;

import com.frontear.hephaestus.modules.api.Module;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import org.lwjgl.input.Keyboard;

public class Fullbright extends Module {
    public Fullbright() {
        super("Fullbright", Keyboard.KEY_B);
    }

    @Override
    public void onToggle(boolean state) {
        super.onToggle(state);

        if (state) {
            minecraft.thePlayer.addPotionEffect(new PotionEffect(Potion.nightVision.id, Integer.MAX_VALUE, 1));
        }
        else {
            minecraft.thePlayer.removePotionEffect(Potion.nightVision.id);
        }
    }
}
