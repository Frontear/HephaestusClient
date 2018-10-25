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
    private float fov = 0.0F;

    public NoFOV() {
        super("NoFOV", Keyboard.KEY_C);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onFOVUpdate(FOVUpdateEvent event) {
        if(getState()) {
            if(event.entity.isPotionActive(Potion.moveSpeed) && event.entity.isSprinting()) {
                event.newfov = 1.15F;
            }

            if(this.fov > 0.0F) {
                event.newfov = 1.0F;
                this.fov = 0.0F;
            }
        }

    }

    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        if(getState()) {
            if(event.entity instanceof EntityPlayer && event.entityLiving.isPotionActive(Potion.moveSpeed) && !event.entity.isSprinting()) {
                this.fov = 1.0F;
            }

            if(event.entity instanceof EntityPlayer && event.entityLiving.isPotionActive(Potion.moveSlowdown)) {
                this.fov = 1.0F;
            }
        }

    }
}
