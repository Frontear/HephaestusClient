package com.frontear.hephaestus.client;

import com.frontear.hephaestus.Hephaestus;
import com.frontear.hephaestus.helpers.CommandArgs;
import com.frontear.hephaestus.managers.CommandManager;
import com.frontear.hephaestus.modules.api.Module;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.input.Keyboard;

import javax.swing.text.JTextComponent;
import java.lang.reflect.Field;

public class HephaestusCommand extends CommandManager {
    private String hephaestusPrefix;
    private String hephaestusInformation;

    private EnumChatFormatting mainColor;
    private EnumChatFormatting secondaryColor;
    private EnumChatFormatting tertiaryColor;

    public HephaestusCommand(String commandName, String commandUsage, CommandArgs... commandArgs) {
        super(commandName, commandUsage, commandArgs);

        mainColor = EnumChatFormatting.GOLD;
        secondaryColor = EnumChatFormatting.YELLOW;
        tertiaryColor = EnumChatFormatting.GRAY;

        hephaestusPrefix = new ChatComponentText(tertiaryColor + "[" + mainColor + Hephaestus.client.CLIENT_NAME + tertiaryColor + "]" + " " + secondaryColor).getChatComponentText_TextValue();
        hephaestusInformation = new ChatComponentText("Use " + mainColor + "/hephaestus help" + secondaryColor + " for more information.").getChatComponentText_TextValue();
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return hephaestusResponse(sender, "Command not found! " + hephaestusInformation);
    }

    @Override
    public void doCommand(ICommandSender sender, String[] args) {
        if (args.length != 0) {
            if (args[0].equalsIgnoreCase("help")) {
                getHelp(sender);
            }
            else if (args[0].equalsIgnoreCase("version")) {
                hephaestusResponse(sender, "You are using version " + Hephaestus.client.CLIENT_VERSION);
            }
            else if (args[0].equalsIgnoreCase("bind")) {
                getBinds(sender, args);
            }
            else {
                getCommandUsage(sender);
            }
        }
        else {
            hephaestusResponse(sender, "No arguments specified! " + hephaestusInformation);
        }
    }

    private void getHelp(ICommandSender sender) {
        String hephaestusHelp = "";
        for (CommandArgs theArgs : commandArgs) {
            hephaestusHelp += System.getProperty("line.separator") + new ChatComponentText(tertiaryColor + "- " + secondaryColor + theArgs.argument + " " + theArgs.description).getChatComponentText_TextValue();
        }

        hephaestusResponse(sender, "Commands include: " + hephaestusHelp);
    }

    private void getBinds(ICommandSender sender, String[] args) {
        if (args[1] != null) {
            for (Module module : Hephaestus.client.moduleManager.moduleList) {
                if (args[1].equalsIgnoreCase(module.name)) {
                    if (args[2] != null) {
                        Class<Keyboard> keys = Keyboard.class;
                        Field key = null;
                        try {
                            key = keys.getDeclaredField("KEY_" + args[2].toUpperCase());
                        } catch (NoSuchFieldException e) {
                            hephaestusResponse(sender, "No such key found! Please try again.");
                        }

                        try {
                            module.module.setKeyCode(key.getInt(null));
                            KeyBinding.resetKeyBindingArrayAndHash();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        hephaestusResponse(sender, module.name + " keybind changed to " + Keyboard.getKeyName(module.module.getKeyCode()));
                    }
                }
            }
        }
    }

    private String hephaestusResponse(ICommandSender sender, String response) {
        ChatComponentText message = new ChatComponentText(hephaestusPrefix + response);
        sender.addChatMessage(message);

        return message.getChatComponentText_TextValue();
    }
}
