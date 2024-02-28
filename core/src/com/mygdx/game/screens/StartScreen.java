package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.helpers.IniciadorJoc;

public class StartScreen implements Screen {

    private final IniciadorJoc game;
    private Stage stage;
    private SpriteBatch batch;
    private Texture backgroundTexture;
    private Sprite backgroundSprite1, backgroundSprite2;

    public StartScreen(final IniciadorJoc game) {
        this.game = game;
        this.stage = new Stage();
        this.batch = new SpriteBatch();
        Gdx.input.setInputProcessor(stage); // Asegúrate de que tu Stage procese los eventos de entrada

        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        // Carga el fondo
        backgroundTexture = new Texture(Gdx.files.internal("background.png"));
        backgroundSprite1 = new Sprite(backgroundTexture);
        backgroundSprite2 = new Sprite(backgroundTexture);
        backgroundSprite2.setX(backgroundSprite1.getWidth());

        TextButton startButton = new TextButton("Començar joc", skin.get("small", TextButton.TextButtonStyle.class));
        startButton.setPosition(Gdx.graphics.getWidth() / 2 - startButton.getWidth() / 2, Gdx.graphics.getHeight() / 2);

        startButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game));
            }
        });

        TextButton optionsButton = new TextButton("Opcions", skin.get("small", TextButton.TextButtonStyle.class));
        optionsButton.setPosition(Gdx.graphics.getWidth() / 2 - optionsButton.getWidth() / 2, Gdx.graphics.getHeight() / 2 - startButton.getHeight() - 10); // Reduce el espacio entre los botone
        optionsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
                game.setScreen(new OptionsScreen(game));
            }
        });

        stage.addActor(startButton);
        stage.addActor(optionsButton);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Mueve el fondo hacia la derecha
        backgroundSprite1.setX(backgroundSprite1.getX() - 1);
        backgroundSprite2.setX(backgroundSprite2.getX() - 1);

        // Si una imagen se ha movido completamente fuera de la pantalla, la reposiciona al final
        if (backgroundSprite1.getX() + backgroundSprite1.getWidth() < 0) {
            backgroundSprite1.setX(backgroundSprite2.getX() + backgroundSprite2.getWidth());
        }
        if (backgroundSprite2.getX() + backgroundSprite2.getWidth() < 0) {
            backgroundSprite2.setX(backgroundSprite1.getX() + backgroundSprite1.getWidth());
        }

        // Dibuja el fondo
        batch.begin();
        backgroundSprite1.draw(batch);
        backgroundSprite2.draw(batch);
        batch.end();

        stage.act();
        stage.draw();
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
        stage.clear();
    }

    // Asegúrate de llamar a stage.dispose() y batch.dispose() en el método dispose()
    @Override
    public void dispose() {
        stage.dispose();
        stage.clear();

        batch.dispose();
        backgroundTexture.dispose();
    }

    // ... el resto de tus métodos aquí ...
}
