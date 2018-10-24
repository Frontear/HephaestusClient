package com.frontear.hephaestus.managers;

import com.frontear.hephaestus.helpers.CommandArgs;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.ClientCommandHandler;

import java.util.ArrayList;
import java.util.List;

public class CommandManager extends CommandBase {
    public String commandName;
    public String commandUsage;
    public CommandArgs[] commandArgs;

    public CommandManager(String commandName, String commandUsage, CommandArgs... commandArgs) {
        this.commandName = commandName;
        this.commandUsage = commandUsage;
        this.commandArgs = commandArgs;

        ClientCommandHandler.instance.registerCommand(this);
    }

    @Override
    public String getCommandName() {
        return commandName;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        return autoCompletionArgs(commandArgs);
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        doCommand(sender, args);
    }

    public String getCommandUsage(ICommandSender sender) {
        return formatText(EnumChatFormatting.RED, commandUsage).getChatComponentText_TextValue();
    }

    public void doCommand(ICommandSender sender, String[] args) {}

    public ChatComponentText formatText(EnumChatFormatting color, String message) {
        return (new ChatComponentText(color + message));
    }

    public List<String> autoCompletionArgs(CommandArgs... args) {
        ArrayList<String> arguments = new ArrayList<String>();

        for (CommandArgs commandArgs : args) {
            arguments.add(commandArgs.argument);
        }

        return arguments;
    }
}
