package com.mygdx.game.helpers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.StartScreen;

public class IniciadorJoc extends Game {

    private SpriteBatch batch;
    private BitmapFont font;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        AssetsMannager.load();
        setScreen(new StartScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetsMannager.dispose();
    }

    public SpriteBatch getBatch() {
        return batch;
    }
    public BitmapFont getFont() {
        return font;
    }
}
