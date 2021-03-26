package fr.ul.rollingball.controllers;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;


public class KeyboardListener implements InputProcessor {
    private static int coefaccel = 500;
    private boolean exit;
    private boolean debugmode;
    private Vector2 accel;

    public KeyboardListener(){
        exit = false;
        debugmode = false;
        accel = new Vector2(0, 0);
    }
    @Override
    public boolean keyDown(int keycode) {
        if (keycode == 131 || keycode == 45){ // Escape / Q
            exit = true;
        }
        if (keycode == 22){ // Right
            accel.x = coefaccel;
        }
        if (keycode == 21){ // Left
            accel.x = -coefaccel;
        }
        if (keycode == 20){ // Down
            accel.y = -coefaccel;
        }
        if (keycode == 19){ // Up
            accel.y = coefaccel;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == 32) { // D
            debugmode = !debugmode;
        }
        if (keycode == 22 || keycode == 21 || keycode == 20 || keycode == 19){ // Fleches
            accel.x = 0;
            accel.y = 0;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isDebugmode() {
        return debugmode;
    }

    public Vector2 getAccel() {
        return accel;
    }

    public void resetAccel(){
        accel.setZero();
    }
}
