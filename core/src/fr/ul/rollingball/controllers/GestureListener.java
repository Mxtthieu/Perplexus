package fr.ul.rollingball.controllers;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

public class GestureListener implements GestureDetector.GestureListener{
    private static float coefaccel = 5.0f;
    private boolean geste;
    private Vector2 accel;

    public GestureListener(){
        accel = new Vector2(0, 0);
        geste = false;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        geste = true;
        accel.x = velocityX * coefaccel;
        accel.y = -velocityY * coefaccel;
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }

    public boolean isGeste() {
        return geste;
    }

    public Vector2 getAccel() {
        if (geste){
            geste = false;
        }
        Vector2 buff= new Vector2(accel);
        accel.x =0;
        accel.y =0;
        return buff;
    }

    public void stopGeste(){
        geste = false;
    }
}
