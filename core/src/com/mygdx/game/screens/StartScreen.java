package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.helpers.IniciadorJoc;

public class StartScreen implements Screen {

    private final IniciadorJoc game;

    public StartScreen(final IniciadorJoc game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) || Gdx.input.justTouched()) {

            game.setScreen(new GameScreen(game));
            dispose();
        }
        game.getBatch().begin();
        game.getFont().draw(game.getBatch(), "Premi la pantalla per començar", 100, 150);
        game.getBatch().end();
    }

    @Override
    public void show() {
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        // Dispose dels recursos si cal
    }
}