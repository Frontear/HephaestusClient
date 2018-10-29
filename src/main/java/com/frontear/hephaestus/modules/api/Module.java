package com.frontear.hephaestus.modules.api;

import com.frontear.hephaestus.Hephaestus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

import java.awt.*;

public class Module {
    public String name;
    public KeyBinding module;
    private boolean state;

    private float scaleFactor = 1.2f;
    protected Minecraft minecraft = Minecraft.getMinecraft();

    public Module(String name, int keyCode) {
        module = new KeyBinding(name, keyCode, "");
        ClientRegistry.registerKeyBinding(module);

        this.name = name;
        getModuleName();
    }

    public void onGui(int offset) {
        Hephaestus.client.uiManager.uiPosition.positionText(Hephaestus.client.uiManager.position_on_screen, getModuleName(), 2, 8, 1.2f);
        Hephaestus.client.uiManager.uiPosition.GLScale(scaleFactor);
        minecraft.fontRenderer.drawStringWithShadow(getModuleName(), Hephaestus.client.uiManager.uiPosition.x_position, (Hephaestus.client.uiManager.uiPosition.y_position - 10) - (offset * 10), new Color(255, 255, 255).getRGB());
        Hephaestus.client.uiManager.uiPosition.GLScale( 1 / scaleFactor);
    }

    public void onToggle(boolean state){}
    public void onUpdate(){}
    public void onRender(){}
    public void onKey(){
        if (module.isKeyDown()) {
            Toggle();
        }
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state){
        this.state = state;
    }

    public void Toggle() {
        setState(!getState());
        onToggle(getState());
    }

    private String getModuleName() {
        return name + " " + "[" + Keyboard.getKeyName(module.getKeyCode()) + "]";
    }
}
