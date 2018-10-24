package com.frontear.hephaestus;

import com.frontear.hephaestus.client.HephaestusClient;
import com.frontear.hephaestus.client.HephaestusCommand;
import com.frontear.hephaestus.helpers.CommandArgs;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

@Mod(
        modid = "hephaestus",
        version = "0,2",
        acceptedMinecraftVersions = "[1.8.9]"
)
public class Hephaestus
{
    public static HephaestusClient client;
    public static HephaestusCommand command;
    public static CommandArgs[] commandArgs;

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(client = new HephaestusClient());

        commandArgs = new CommandArgs[] {
                new CommandArgs("help", "usage: list all commands"),
                new CommandArgs("bind", "usage: bind <module> [key]"),
                new CommandArgs("version","usage: display " + client.CLIENT_NAME.toLowerCase() + " version")
        };
        ClientCommandHandler.instance.registerCommand(command = new HephaestusCommand("hephaestus", "Usage: " + " /hephaestus " + " <command> ", commandArgs));
    }
}
