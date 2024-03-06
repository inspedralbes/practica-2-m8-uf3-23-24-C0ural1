package com.mygdx.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.helpers.AssetsMannager;

public class PlaneActor extends Actor {

    private TextureRegion planeTexture;
    private Rectangle planeRectangle;
    private long lastJumpTime;
    private boolean isJumping;

    private Animation<TextureRegion> planeAnimation;
    private float elapsedTime;

    public PlaneActor() {
        TextureRegion planeTexture = new TextureRegion(AssetsMannager.getPlaneTexture());
        planeRectangle = new Rectangle(50, 240, planeTexture.getRegionWidth(), planeTexture.getRegionHeight()); // Ajusta el tamaño y la posición del avión
        lastJumpTime = System.currentTimeMillis();
        isJumping = false;

        Texture frame1 = AssetsMannager.getPlaneTexture();
        Texture frame2 = AssetsMannager.getPlaneTexture2();
        Texture frame3 = AssetsMannager.getPlaneTexture3();

        // Crear los TextureRegion utilizando las texturas cargadas
        TextureRegion planeTextureRegion = new TextureRegion(planeTexture);
        TextureRegion frame1Region = new TextureRegion(frame1);
        TextureRegion frame2Region = new TextureRegion(frame2);
        TextureRegion frame3Region = new TextureRegion(frame3);

        // Establecer filtro para los TextureRegion
        planeTextureRegion.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        frame1Region.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        frame2Region.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        frame3Region.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        // Configurar la animación
        planeAnimation = new Animation<TextureRegion>(0.05f,
                frame1Region,
                frame2Region,
                frame3Region,
                frame2Region
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






