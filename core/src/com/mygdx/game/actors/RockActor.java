package com.mygdx.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.helpers.AssetsMannager;


public class RockActor extends Actor {

    private TextureRegion rockTexture;
    private Rectangle rockRectangle;
    private boolean isDown;
    private boolean scored;


    public RockActor(float x, float y, boolean isDown) {
        this.isDown = isDown;
        rockTexture = new TextureRegion(AssetsMannager.getTorreTexture());

        if (isDown) {
            rockTexture.flip(false, true); // Hace un flip de la textura de la roca si viene de arriba
        }
        // Ajusta la hitbox para que se adapte mejor a la forma de la roca
        rockRectangle = new Rectangle(x, y, rockTexture.getRegionWidth(), rockTexture.getRegionHeight());
        this.scored = false;
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

    public boolean isScored() {
        return scored;
    }

    public void setScored(boolean scored) {
        this.scored = scored;
    }
}



