package com.mygdx.game.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.math.Rectangle;


public class EnemyActor extends Actor {
    private TextureRegion enemyTexture;
    private Rectangle enemyRectangle;
    private float speed;

    public EnemyActor(int x, int y, float speed) {
        this.speed = speed;

        enemyTexture = new TextureRegion(new Texture("enemy3.png"));

        enemyRectangle = new Rectangle(x, y, enemyTexture.getRegionWidth(), enemyTexture.getRegionHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        enemyRectangle.x -= speed * delta;
        enemyRectangle.setPosition(enemyRectangle.x, enemyRectangle.y);
    }

    public Rectangle getEnemyRectangle() {
        return enemyRectangle;
    }

    public TextureRegion getEnemyTextureRegion() {
        return enemyTexture;
    }
}






