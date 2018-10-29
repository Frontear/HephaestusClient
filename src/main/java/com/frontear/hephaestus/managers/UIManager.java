package com.frontear.hephaestus.managers;

import com.frontear.hephaestus.Hephaestus;
import com.frontear.hephaestus.helpers.MultiColor;
import com.frontear.hephaestus.helpers.UIPosition;
import net.minecraft.client.Minecraft;

import java.awt.*;
import java.util.Random;

public class UIManager {
    private final Random random = new Random();
    private Minecraft minecraft;
    public UIPosition uiPosition;

    private MultiColor multiColor;

    public UIPosition.POSITION_ON_SCREEN position_on_screen = UIPosition.POSITION_ON_SCREEN.BOTTOM_RIGHT;
    public float scaleFactor = 1.5f;

    public UIManager() {
        minecraft = Minecraft.getMinecraft();
        uiPosition = new UIPosition();
    }

    public void Draw() {
        multiColor = MultiColor.getRainbow();
        uiPosition.positionText(position_on_screen, Hephaestus.client.CLIENT_NAME, 2, 2, scaleFactor);
        uiPosition.GLScale(scaleFactor);
        minecraft.fontRenderer.drawStringWithShadow(Hephaestus.client.CLIENT_NAME, uiPosition.x_position, uiPosition.y_position, multiColor.getRGB());
        uiPosition.GLScale(1 / scaleFactor);
    }
}