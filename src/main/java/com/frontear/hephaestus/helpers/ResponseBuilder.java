package com.frontear.hephaestus.helpers;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class ResponseBuilder {
    private String message;
    private Style style;

    public ResponseBuilder(String message) {
        this.message = message;
        this.style = new Style().setColor(TextFormatting.GRAY);
    }

    public ResponseBuilder(String message, Style style) {
        this.message = message;
        this.style = style;
    }

    public ITextComponent getResponse() {
        return new TextComponentString(message).setStyle(style);
    }
}
