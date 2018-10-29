package com.frontear.hephaestus.client;

import com.frontear.hephaestus.Hephaestus;
import com.frontear.hephaestus.helpers.ResponseBuilder;
import com.frontear.hephaestus.modules.api.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;

public class HephaestusCommands {
    private final Minecraft minecraft = Minecraft.getMinecraft();
    private final String commandPrefix = ".";

    public ArrayList<ResponseBuilder> moduleArguments = new ArrayList<ResponseBuilder>();
    private String responsePrefix;

    public HephaestusCommands() {
        for (Module module : Hephaestus.client.moduleManager.moduleList) {
            moduleArguments.add(new ResponseBuilder(module.name.toLowerCase()));
        }
        responsePrefix = new ResponseBuilder("[").getResponse().getFormattedText()
        + new ResponseBuilder(Hephaestus.client.CLIENT_NAME, new Style().setColor(TextFormatting.GOLD)).getResponse().getFormattedText()
        + new ResponseBuilder("] ").getResponse().getFormattedText();
    }

    @SubscribeEvent
    public void onChat(ClientChatEvent event) {
        boolean commandIssued = false;

        if (Hephaestus.client.moduleManager.getModule(event.getMessage().replace(commandPrefix, "")) != null) {
            Hephaestus.client.moduleManager.getModule(event.getMessage().replace(commandPrefix, "")).Toggle();
            commandIssued = true;
        }

        else if (event.getMessage().equalsIgnoreCase(commandPrefix + "clear")) {
            minecraft.ingameGUI.getChatGUI().clearChatMessages(false);
            hephaestusResponse(new ResponseBuilder("Messages cleared!").getResponse());
            commandIssued = true;
        }

        else if (event.getMessage().equalsIgnoreCase(commandPrefix + "version")) {
            hephaestusResponse(new ResponseBuilder("You are using version " + Hephaestus.client.CLIENT_VERSION).getResponse());
            commandIssued = true;
        }

        else if (event.getMessage().startsWith(commandPrefix + "say") && !event.getMessage().equalsIgnoreCase(commandPrefix + "say")) {
            event.setMessage(event.getMessage().replaceFirst(commandPrefix + "say ", ""));
        }

        else if (event.getMessage().startsWith(commandPrefix)) {
            hephaestusResponse(new ResponseBuilder("Unknown command. Try "
                    + new ResponseBuilder(".help", new Style().setColor(TextFormatting.GOLD)).getResponse().getFormattedText()
                    + new ResponseBuilder(" for a list of commands").getResponse().getFormattedText()).getResponse());
            commandIssued = true;
        }

        if (commandIssued) { event.setMessage(""); }
    }

    private void hephaestusResponse(ITextComponent message) {
        minecraft.ingameGUI.getChatGUI().printChatMessage(new TextComponentString(responsePrefix + message.getFormattedText()));
    }
}
