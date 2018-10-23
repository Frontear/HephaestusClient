package com.frontear.hephaestus;

import com.frontear.hephaestus.client.HephaestusClient;
import com.frontear.hephaestus.commands.TestCommand;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

@Mod(
        modid = "hephaestus",
        version = "1.0",
        acceptedMinecraftVersions = "[1.8.9]"
)
public class Hephaestus
{
    public static HephaestusClient client;

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(client = new HephaestusClient());
        ClientCommandHandler.instance.registerCommand(new TestCommand("testcommand", "Usage: /testcommand <value>"));
    }
}
