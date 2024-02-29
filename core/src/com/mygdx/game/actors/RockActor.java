package com.mygdx.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class RockActor extends Actor {

    private TextureRegion rockTexture;
    private Rectangle rockRectangle;
    private boolean isDown;

    public RockActor(float x, float y, boolean isDown) {
        this.isDown = isDown;
        rockTexture = new TextureRegion(new Texture("torre4.png"));
        if (isDown) {
            rockTexture.flip(false, true); // Hace un flip de la textura de la roca si viene de arriba
        }
        // Ajusta la hitbox para que se adapte mejor a la forma de la roca
        rockRectangle = new Rectangle(x, y, rockTexture.getRegionWidth(), rockTexture.getRegionHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        rockRectangle.x -= 2;
        // Actualiza la posición de la hitbox junto con la roca
        rockRectangle.setPosition(rockRectangle.x, rockRectangle.y);
    }

    public Rectangle getRockRectangle() {
        return rockRectangle;
    }

    public TextureRegion getRockTexture() {
        return rockTexture;
    }
}



