package com.mygdx.game.helpers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.StartScreen;

public class IniciadorJoc extends Game {

    private SpriteBatch batch;
    private BitmapFont font;
    private boolean musicOn = true; // Añade un campo para el estado de la música
    private String difficulty = "Fácil"; // Añade un campo para la dificultad

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

    // Añade los métodos para obtener y establecer el estado de la música
    public boolean isMusicOn() {
        return musicOn;
    }

    public void setMusicOn(boolean musicOn) {
        this.musicOn = musicOn;
    }

    // Añade los métodos para obtener y establecer la dificultad
    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}

