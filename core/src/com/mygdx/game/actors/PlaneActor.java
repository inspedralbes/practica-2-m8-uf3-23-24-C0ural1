package com.mygdx.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PlaneActor extends Actor {

    private TextureRegion planeTexture;
    private Rectangle planeRectangle;
    private long lastJumpTime;
    private boolean isJumping;

    Rectangle rect1 = new Rectangle();
    Rectangle rect2 = new Rectangle();


    public PlaneActor() {
        planeTexture = new TextureRegion(new Texture("plane1.png"));
        planeRectangle = new Rectangle(50, 240, planeTexture.getRegionWidth(), planeTexture.getRegionHeight()); // Ajusta el tamaño y la posición del avión
        lastJumpTime = System.currentTimeMillis();
        isJumping = false;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (isJumping) {
            planeRectangle.y += 5; // Cambiar la cantidad de movimiento vertical aquí
            if (System.currentTimeMillis() - lastJumpTime > 300) {
                isJumping = false;
            }
        }

        if (Gdx.input.justTouched() || Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            isJumping = true;
            lastJumpTime = System.currentTimeMillis();
        }

        planeRectangle.y -= 1; // Cambiar la cantidad de movimiento vertical aquí
        if (planeRectangle.y < 0) {
            planeRectangle.y = 0;
        }
        if (planeRectangle.y > 480 - planeTexture.getRegionHeight()) {
            planeRectangle.y = 480 - planeTexture.getRegionHeight();
        }
    }

    public Rectangle getPlaneRectangle() {
        return planeRectangle;
    }

    public TextureRegion getPlaneTexture() {
        return planeTexture;
    }
}






