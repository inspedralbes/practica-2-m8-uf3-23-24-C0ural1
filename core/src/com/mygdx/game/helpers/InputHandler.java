package com.mygdx.game.helpers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.mygdx.game.actors.PlaneActor;

public class InputHandler extends InputAdapter {

    private PlaneActor planeActor;

    public InputHandler(PlaneActor planeActor) {
        this.planeActor = planeActor;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.SPACE) {
            planeActor.jump();
            return true;
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        planeActor.jump();
        return true;
    }
}

