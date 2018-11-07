package com.frontear.hephaestus.modules;

import com.frontear.hephaestus.modules.api.Module;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Trail extends Module {
    ArrayList<Vec3d> positions = new ArrayList<>();
    public Trail() {
        super("Trail", Keyboard.KEY_Z);
    }

    @Override
    public void onToggle(boolean state) {
        super.onToggle(state);

        if (!state) {
            positions.clear();
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (minecraft.player.moveStrafing != 0 || minecraft.player.moveForward != 0) {
            positions.add(minecraft.player.getPositionVector());
        }
    }

    @Override
    public void onRender() {
        super.onRender();

        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(770, 771);
        GL11.glLineWidth(2);
        GL11.glColor4f(0.3f, 1f, 1f, 0.7f);
        GL11.glBegin(3);
        for (Vec3d vec : positions) {
            setVertex3d(getRenderPositions(vec.x, vec.y + 0.3, vec.z));
        }
        GL11.glEnd();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glPopMatrix();
    }

    private Vec3d getRenderPositions(double x, double y, double z) {
        Field _x = null;
        Field _y = null;
        Field _z = null;
        try {
            _x = RenderManager.class.getDeclaredField("renderPosX");
            _x.setAccessible(true);
            _y = RenderManager.class.getDeclaredField("renderPosY");
            _y.setAccessible(true);
            _z = RenderManager.class.getDeclaredField("renderPosZ");
            _z.setAccessible(true);
        }
        catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        Vec3d vec = null;
        try {
            double xx = (x - _x.getDouble(null));
            double yy = (y - _y.getDouble(null));
            double zz = (z - _z.getDouble(null));

            vec = new Vec3d(xx, yy, zz);
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return vec;
    }

    private void setVertex3d(Vec3d v) {
        GL11.glVertex3d(v.x, v.y, v.z);
    }
}
