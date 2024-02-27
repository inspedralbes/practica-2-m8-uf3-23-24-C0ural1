package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.actors.PlaneActor;
import com.mygdx.game.actors.RockActor;
import com.mygdx.game.helpers.IniciadorJoc;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class GameScreen implements Screen {

    private final IniciadorJoc game;
    private SpriteBatch batch;
    private TextureRegion backgroundTexture;
    private PlaneActor planeActor;
    private Array<RockActor> rocks;
    private int score;
    private float rockSpawnTimer;
    private TextureRegion ground;
    private TextureRegion ceiling;

    // Constantes para el control de juego
    private static final float ROCK_SPAWN_INTERVAL_MIN = 1.5f;
    private static final float ROCK_SPAWN_INTERVAL_MAX = 3.0f;
    private static final int ROCK_SPAWN_MIN_HEIGHT = 100;
    private static final int ROCK_SPAWN_MAX_HEIGHT = 300;

    public GameScreen(final IniciadorJoc game) {
        this.game = game;
        rockSpawnTimer = MathUtils.random(ROCK_SPAWN_INTERVAL_MIN, ROCK_SPAWN_INTERVAL_MAX);
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        backgroundTexture = new TextureRegion(new Texture("background.png"));
        planeActor = new PlaneActor();
        rocks = new Array<RockActor>();
        score = 0;
        ground = new TextureRegion(new Texture("ground.png"));
        ceiling = new TextureRegion(ground);
        ceiling.flip(true, true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(backgroundTexture, 0, 0);

        // Check for rock spawn and generate a new rock
        if (rockSpawnTimer <= 0) {
            RockActor newRock = new RockActor(Gdx.graphics.getWidth(), MathUtils.random(ROCK_SPAWN_MIN_HEIGHT, ROCK_SPAWN_MAX_HEIGHT));
            rocks.add(newRock);
            rockSpawnTimer = MathUtils.random(ROCK_SPAWN_INTERVAL_MIN, ROCK_SPAWN_INTERVAL_MAX);
        } else {
            rockSpawnTimer -= delta;
        }

        // Update and draw rocks
        for (RockActor rock : rocks) {
            rock.act(delta);
            batch.draw(rock.getRockTexture(), rock.getRockRectangle().x, rock.getRockRectangle().y);
        }

        // Update and draw plane
        planeActor.act(delta);
        batch.draw(planeActor.getPlaneTexture(), planeActor.getPlaneRectangle().x, planeActor.getPlaneRectangle().y);

        // Check for collisions
        for (RockActor rock : rocks) {
            if (rock.getRockRectangle().overlaps(planeActor.getPlaneRectangle())) {
                game.setScreen(new EndScreen(game, score));
                break;
            }
        }

        // Draw ground and ceiling
        batch.draw(ground, 0, 0);
        batch.draw(ceiling, 0, Gdx.graphics.getHeight() - ceiling.getRegionHeight());

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // Not implemented
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
        batch.dispose();
        backgroundTexture.getTexture().dispose();
        planeActor.getPlaneTexture().getTexture().dispose();
        for (RockActor rock : rocks) {
            rock.getRockTexture().getTexture().dispose();
        }
        ground.getTexture().dispose();
        ceiling.getTexture().dispose();
    }
}






