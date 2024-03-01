package com.mygdx.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PlaneActor extends Actor {

    private TextureRegion planeTexture;
    private Rectangle planeRectangle;
    private long lastJumpTime;
    private boolean isJumping;

    private Animation<TextureRegion> planeAnimation;
    private float elapsedTime;

    public PlaneActor() {
        planeTexture = new TextureRegion(new Texture("plane1.png"));
        planeRectangle = new Rectangle(50, 240, planeTexture.getRegionWidth(), planeTexture.getRegionHeight()); // Ajusta el tamaño y la posición del avión
        lastJumpTime = System.currentTimeMillis();
        isJumping = false;

        Texture frame1 = new Texture("plane1.png");
        frame1.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Texture frame2 = new Texture("plane2.png");
        frame2.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Texture frame3 = new Texture("plane3.png");
        frame3.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        planeAnimation = new Animation<TextureRegion>(0.05f,
                new TextureRegion(frame1),
                new TextureRegion(frame2),
                new TextureRegion(frame3),
                new TextureRegion(frame2)
        );

        planeAnimation.setPlayMode(Animation.PlayMode.LOOP);
        elapsedTime = 0;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        elapsedTime += delta;
        planeTexture = planeAnimation.getKeyFrame(elapsedTime, true);

        if (isJumping) {
            planeRectangle.y += 3; // Cambiar la cantidad de movimiento vertical aquí
            if (System.currentTimeMillis() - lastJumpTime > 300) {
                isJumping = false;
            }
        }

        planeRectangle.y -= 1; // Cambiar la cantidad de movimiento vertical aquí
        if (planeRectangle.y < 0) {
            planeRectangle.y = 0;
        }
        if (planeRectangle.y > 480 - planeTexture.getRegionHeight()) {
            planeRectangle.y = 480 - planeTexture.getRegionHeight();
        }
    }


    public void jump() {
        isJumping = true;
        lastJumpTime = System.currentTimeMillis();
    }

    public Rectangle getPlaneRectangle() {
        return planeRectangle;
    }

    public TextureRegion getPlaneTexture() {
        return planeTexture;
    }
}






