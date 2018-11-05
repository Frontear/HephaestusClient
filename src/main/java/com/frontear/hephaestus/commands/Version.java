package com.frontear.hephaestus.commands;

import com.frontear.hephaestus.Hephaestus;
import com.frontear.hephaestus.commands.api.Command;

public class Version extends Command {
    public Version() {
        super("version", "gets the version of the current client");
    }

    @Override
    public boolean DoCommand(String[] commandArgs) {
        clientResponse(responseBuilder("You are running " + Hephaestus.client.CLIENT_NAME + " " + Hephaestus.client.CLIENT_VERSION));

        return true;
    }
}
