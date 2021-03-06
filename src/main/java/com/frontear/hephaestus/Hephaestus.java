package com.frontear.hephaestus;

import com.frontear.hephaestus.client.HephaestusClient;
import com.frontear.hephaestus.client.HephaestusCommands;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

@Mod(
        modid = "hephaestus",
        version = "0.6",
        acceptedMinecraftVersions = "[1.12.2]"
)
public class Hephaestus
{
    public static HephaestusClient client;
    public static HephaestusCommands commands;

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(client = new HephaestusClient());
        MinecraftForge.EVENT_BUS.register(commands = new HephaestusCommands());
    }
}
