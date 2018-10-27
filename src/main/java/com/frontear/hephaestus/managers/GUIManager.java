package com.frontear.hephaestus.managers;

import com.frontear.hephaestus.Hephaestus;
import com.frontear.hephaestus.helpers.UIPosition;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import java.awt.*;
import java.io.IOException;

public class GUIManager extends GuiScreen {
    private UIPosition uiPosition;
    private final float scaleFactor = 1.5f;
    public GUIManager() {
        uiPosition = new UIPosition();
    }
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        uiPosition.GLScale(scaleFactor);
        drawRect(2, 2, 80, mc.fontRendererObj.FONT_HEIGHT * 2, new Color(128, 128, 128).getRGB());
        mc.fontRendererObj.drawString("Hacks", 25, (4 + mc.fontRendererObj.FONT_HEIGHT) / 2, new Color(255, 255, 255).getRGB());
        uiPosition.GLScale(1 / scaleFactor);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void initGui() {
        for (int i = 0; i < Hephaestus.client.moduleManager.moduleList.size(); i++) {
            if (Hephaestus.client.moduleManager.moduleList.get(i).name != "ClickGui") {
                buttonList.add(new GuiButton(i, 2, (12 + mc.fontRendererObj.FONT_HEIGHT * 2) + (20 * i), 119, mc.fontRendererObj.FONT_HEIGHT * 2, Hephaestus.client.moduleManager.moduleList.get(i).name));
            }
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        for (int i = 0; i < Hephaestus.client.moduleManager.moduleList.size(); i++) {
            if (button.id == i) {
                Hephaestus.client.moduleManager.moduleList.get(i).Toggle();
            }
        }
    }
}
