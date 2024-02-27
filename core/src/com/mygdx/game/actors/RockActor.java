package com.mygdx.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class RockActor extends Actor {

    private TextureRegion rockTexture;
    private Rectangle rockRectangle;

    public RockActor(float x, float y) {
        rockTexture = new TextureRegion(new Texture("rock.png"));
        rockRectangle = new Rectangle(x, y, rockTexture.getRegionWidth(), rockTexture.getRegionHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        rockRectangle.x -= 2;
    }

    public Rectangle getRockRectangle() {
        return rockRectangle;
    }

    public TextureRegion getRockTexture() {
        return rockTexture;
    }
}

