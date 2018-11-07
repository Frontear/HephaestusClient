package com.frontear.hephaestus.commands;

import com.frontear.hephaestus.Hephaestus;
import com.frontear.hephaestus.commands.api.Command;
import com.frontear.hephaestus.modules.api.Module;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

import java.lang.reflect.Field;

public class Bind extends Command {
    public Bind() {
        super("bind", "bind a module to a different key");
    }

    @Override
    public boolean DoCommand(String[] commandArgs) {
        if (commandArgs.length > 1) {
            Module module = Hephaestus.client.moduleManager.getModule(commandArgs[1]);
            if (module != null && commandArgs.length > 2) {
                Class<Keyboard> keys = Keyboard.class;
                Field key;

                try {
                    key = keys.getField("KEY_" + commandArgs[2].toUpperCase());
                    module.module.setKeyCode(key.getInt(null));
                    KeyBinding.resetKeyBindingArrayAndHash();
                    clientResponse(responseBuilder(module.name + " has been binded to " + Keyboard.getKeyName(module.module.getKeyCode())));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    clientResponse(responseBuilder("Key not found."));
                }
            }
        }

        return true;
    }
}
