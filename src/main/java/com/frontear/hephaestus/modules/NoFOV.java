package com.frontear.hephaestus.modules;

import com.frontear.hephaestus.modules.api.Module;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

public class NoFOV extends Module {
    public NoFOV() {
        super("NoFOV", Keyboard.KEY_C);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onFOVUpdate(FOVUpdateEvent event) {
        if(getState()) {
            if (fovModificationPotionEffects(event) && event.entity.isSprinting()) {
                event.newfov = 1.15f;
            }
            else if (fovModificationPotionEffects(event) && !event.entity.isSprinting()) {
                event.newfov = 1f;
            }
        }
    }

    private boolean fovModificationPotionEffects(FOVUpdateEvent event) {
        return (event.entity.isPotionActive(Potion.moveSpeed) || event.entity.isPotionActive(Potion.moveSlowdown));
    }
}
