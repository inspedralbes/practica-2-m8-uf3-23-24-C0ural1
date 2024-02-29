package com.mygdx.game.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.helpers.IniciadorJoc;

public class EndScreen implements Screen {

    private final IniciadorJoc game;
    private Stage stage;
    private SpriteBatch batch;
    private Texture backgroundTexture;
    private Sprite backgroundSprite1, backgroundSprite2;
    private int score;

    public EndScreen(final IniciadorJoc game, int score) {
        this.game = game;
        this.score = score;
        this.stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        this.batch = new SpriteBatch();
        Gdx.input.setInputProcessor(stage);

        // Cargar el fondo
        backgroundTexture = new Texture(Gdx.files.internal("background.png"));
        backgroundSprite1 = new Sprite(backgroundTexture);
        backgroundSprite2 = new Sprite(backgroundTexture);
        backgroundSprite2.setX(backgroundSprite1.getWidth());

        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));


        TextButton retryButton = new TextButton("Jugar de nou", skin.get("small", TextButton.TextButtonStyle.class));
        retryButton.setPosition(Gdx.graphics.getWidth() / 2 - retryButton.getWidth() / 2, Gdx.graphics.getHeight() / 2);

        retryButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
                game.setScreen(new GameScreen(game));
            }
        });

        TextButton IniciButton = new TextButton("Tornar a inici", skin.get("small", TextButton.TextButtonStyle.class));
        IniciButton.setPosition(Gdx.graphics.getWidth() / 2 - IniciButton.getWidth() / 2, Gdx.graphics.getHeight() / 2 - retryButton.getHeight() - 10);

        IniciButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
                game.setScreen(new StartScreen(game));
            }
        });


        stage.addActor(retryButton);
        stage.addActor(IniciButton);
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

        batch.begin();
        //font.draw(batch, "Game Over", Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2 + 50);
        //font.draw(batch, "Score: " + score, Gdx.graphics.getWidth() / 2 - 50, Gdx.graphics.getHeight() / 2);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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

    @Override
    public void dispose() {
        stage.dispose();
        stage.clear();

        batch.dispose();
        backgroundTexture.dispose();
    }

    // ... el resto de tus métodos aquí ...
}
