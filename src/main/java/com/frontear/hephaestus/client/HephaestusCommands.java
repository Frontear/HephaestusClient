package com.frontear.hephaestus.client;

import com.frontear.hephaestus.Hephaestus;
import com.frontear.hephaestus.helpers.ResponseBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HephaestusCommands {
    private final Minecraft minecraft = Minecraft.getMinecraft();
    private final String commandPrefix = ".";

    private String responsePrefix;

    public HephaestusCommands() {
        responsePrefix = new ResponseBuilder("[").getResponse().getFormattedText()
        + new ResponseBuilder(Hephaestus.client.CLIENT_NAME, new Style().setColor(TextFormatting.GOLD)).getResponse().getFormattedText()
        + new ResponseBuilder("] ").getResponse().getFormattedText();
    }

    @SubscribeEvent
    public void onChat(ClientChatEvent event) {
    }

    private boolean commandIssued(ClientChatEvent event, String command) {
        boolean message = event.getMessage().equals(commandPrefix + command);

        if (event.getMessage().startsWith(commandPrefix)) { event.setMessage(""); }

        return message;
    }

    private void chatResponse(ITextComponent message) {
        minecraft.ingameGUI.getChatGUI().printChatMessage(new TextComponentString(responsePrefix + message.getFormattedText()));
    }
}
