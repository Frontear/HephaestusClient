package com.frontear.hephaestus.managers;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CommandManager {
    private Minecraft minecraft = Minecraft.getMinecraft();

    public CommandManager() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onChat(ClientChatEvent event) {
        if (chatMessage(event).equals("Hello")) {
            chatResponse("Hi");
        }
    }

    private String chatMessage(ClientChatEvent event) {
        String message = event.getMessage();

        System.out.println(message);
        return message;
    }

    private void chatResponse(String message) {
        //minecraft.ingameGUI.getChatGUI().printChatMessage(null);
    }
}
