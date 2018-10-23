package com.frontear.hephaestus.modules.api;

import com.frontear.hephaestus.Hephaestus;
import com.frontear.hephaestus.managers.CommandManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.input.Keyboard;

import java.awt.*;

public class Module extends CommandManager {
    private String name;
    public KeyBinding module;
    private boolean state;

    private float scaleFactor = 1.2f;
    protected Minecraft minecraft = Minecraft.getMinecraft();

    public Module(String name, int keyCode) {
        super(name.toLowerCase(), "Usage: " + name.toLowerCase() + " <command>", "toggle", "state");

        module = new KeyBinding(name, keyCode, "");

        this.name = name + " " + "[" + Keyboard.getKeyName(module.getKeyCode()) + "]";
    }

    @Override
    public boolean doCommand(ICommandSender sender, String[] args) {
        boolean hasArgs = super.doCommand(sender, args);

        if (hasArgs) {
            if (args[0].equalsIgnoreCase("toggle")) {
                Toggle();
            }
            else if (args[0].equalsIgnoreCase("state")) {
                sender.addChatMessage(formatText(EnumChatFormatting.GRAY, name + " is currently " + state));
            }
        }

        return true;
    }

    public void onGui(int offset) {
        Hephaestus.client.uiManager.uiPosition.positionText(Hephaestus.client.uiManager.position_on_screen, name, 2, 8, 1.2f);
        Hephaestus.client.uiManager.uiPosition.GLScale(scaleFactor);
        minecraft.fontRendererObj.drawStringWithShadow(name, Hephaestus.client.uiManager.uiPosition.x_position, (Hephaestus.client.uiManager.uiPosition.y_position - 10) - (offset * 10), new Color(255, 255, 255).getRGB());
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
}
