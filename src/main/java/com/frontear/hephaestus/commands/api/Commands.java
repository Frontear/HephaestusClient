package com.frontear.hephaestus.commands.api;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class Commands extends CommandBase {
    private String commandName;
    private String commandUsage;

    public Commands(String commandName, String commandUsage) {
        this.commandName = commandName;
        this.commandUsage = commandUsage;
    }

    @Override
    public String getCommandName() {
        return commandName;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return formatText(EnumChatFormatting.RED, commandUsage).getChatComponentText_TextValue();
    }

    public void processCommand(ICommandSender sender, String[] args) throws CommandException {}

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    public ChatComponentText formatText(EnumChatFormatting color, String message) {
        return (new ChatComponentText(color + message));
    }
}
