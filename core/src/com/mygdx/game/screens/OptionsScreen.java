package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.helpers.AssetsMannager;
import com.mygdx.game.helpers.IniciadorJoc;

public class OptionsScreen implements Screen {

    private final IniciadorJoc game;
    private Stage stage;

    public OptionsScreen(final IniciadorJoc game) {
        this.game = game;
        this.stage = new Stage();
        Gdx.input.setInputProcessor(stage); // Asegúrate de que tu Stage procese los eventos de entrada

        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        Label musicLabel = new Label("Musica: ", skin);
        musicLabel.setPosition(200, 300);

        CheckBox musicCheckBox = new CheckBox("", skin);
        musicCheckBox.setPosition(200 + musicLabel.getWidth(), 300);
        musicCheckBox.setChecked(game.isMusicOn());
        musicCheckBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
                game.setMusicOn(musicCheckBox.isChecked());
            }
        });


        Label difficultyLabel = new Label("Canviar Dificultat: ", skin);
        difficultyLabel.setPosition(200, 260);

        SelectBox<String> difficultySelectBox = new SelectBox<String>(skin);
        difficultySelectBox.setItems("Facil", "Medio", "Difícil");
        difficultySelectBox.setSelected(game.getDifficulty());
        difficultySelectBox.setPosition(200 + difficultyLabel.getWidth(), 260);
        difficultySelectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
                game.setDifficulty(difficultySelectBox.getSelected());
            }
        });

        TextButton backButton = new TextButton("Enrere", skin.get("small", TextButton.TextButtonStyle.class));
        backButton.setPosition(250, 50);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
                game.setScreen(new StartScreen(game));
            }
        });

        stage.addActor(musicLabel);
        stage.addActor(musicCheckBox);
        stage.addActor(difficultyLabel);
        stage.addActor(difficultySelectBox);
        stage.addActor(backButton);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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

    }

    // Asegúrate de llamar a stage.dispose() en el método dispose()
    @Override
    public void dispose() {
        stage.dispose();
    }

    // ... el resto de tus métodos aquí ...
}

