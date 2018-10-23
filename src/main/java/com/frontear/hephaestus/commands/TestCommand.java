package com.frontear.hephaestus.commands;

import com.frontear.hephaestus.commands.api.Commands;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public class TestCommand extends Commands {

    public TestCommand(String commandName, String commandUsage) {
        super(commandName, commandUsage);
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 0) {
            sender.addChatMessage(new ChatComponentText(getCommandUsage(sender)));
            return;
        }

        if (args[0].equals("0")) {
            sender.addChatMessage(formatText(EnumChatFormatting.GOLD, "You've issued a big yeet!"));
        }
        else if (args[0].equals("1")) {
            sender.addChatMessage(formatText(EnumChatFormatting.GOLD, "You've issued a fat dab!"));
        }
        else {
            sender.addChatMessage(formatText(EnumChatFormatting.LIGHT_PURPLE, "You just used /testcommand" + " " + args[0] + "." + " " + "While this was not supposed to be an option, we still consider it a success!"));
        }
    }
}
