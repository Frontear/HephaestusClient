package com.frontear.hephaestus.managers;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.ClientCommandHandler;

import java.util.ArrayList;
import java.util.List;

public class CommandManager extends CommandBase {
    private String commandName;
    private String commandUsage;
    private String[] commandArgs;

    public CommandManager(String commandName, String commandUsage, String... commandArgs) {
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
    public String getCommandUsage(ICommandSender sender) {
        return formatText(EnumChatFormatting.RED, commandUsage).getChatComponentText_TextValue();
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        return autoCompletionArgs(commandArgs);
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    public ChatComponentText formatText(EnumChatFormatting color, String message) {
        return (new ChatComponentText(color + message));
    }
    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        doCommand(sender, args);
    }

    public boolean doCommand(ICommandSender sender, String[] args) {
        boolean hasArgs = (commandArgs.length != 0 && args.length != 0);
        if (!hasArgs) {
            sender.addChatMessage(new ChatComponentText(getCommandUsage(sender)));
        }

        return hasArgs;
    }

    public List<String> autoCompletionArgs(String... args) {
        ArrayList<String> arguments = new ArrayList<String>();

        for (String s : args) {
            arguments.add(s);
        }

        return arguments;
    }
}
