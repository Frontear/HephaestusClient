package com.frontear.hephaestus.managers;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

public class CommandManager {
    private Minecraft minecraft = Minecraft.getMinecraft();

    public CommandManager() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if (chatMessage(event).equals("Hello")) {
            chatResponse("Hi");
        }
    }

    private String chatMessage(ClientChatReceivedEvent event) {
        String message = event.message.getUnformattedText().replace("<"  + minecraft.thePlayer.getName() + "> ", "");

        System.out.println(message);
        return message;
    }

    private void chatResponse(String message) {
        minecraft.ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(message));
    }
}
