package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.actors.EnemyActor;
import com.mygdx.game.actors.PlaneActor;
import com.mygdx.game.actors.RockActor;
import com.mygdx.game.helpers.IniciadorJoc;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.helpers.InputHandler;
import com.mygdx.game.helpers.AssetsMannager;


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

    private static final float ENEMY_SPAWN_INTERVAL_MIN = 1.5f;
    private static final float ENEMY_SPAWN_INTERVAL_MAX = 3.0f;

    private static final float BACKGROUND_SCROLL_SPEED = 80; // Velocidad de desplazamiento del fondo


    float enemySpawnTimer = 0;

    private static final float ENEMY_SPEED = 200; // Ajusta esto a la velocidad que desees
    private Array<EnemyActor> enemies;

    private boolean gameStarted = false;

    private float backgroundX;

    private int towersPassed = 0;

    private static final float ENEMY_SPAWN_INTERVAL_EASY = 2.5f;
    private static final float ENEMY_SPAWN_INTERVAL_MEDIUM = 1.7f;
    private static final float ENEMY_SPAWN_INTERVAL_HARD = 1.0f;

    private int lives = 3;

    private static final float INVULNERABILITY_TIME = 1.5f; // 1 segundo de invulnerabilidad
    private float invulnerabilityTimer = 0;



    BitmapFont font;

    public GameScreen(final IniciadorJoc game) {
        this.game = game;
        rockSpawnTimer = MathUtils.random(ROCK_SPAWN_INTERVAL_MIN, ROCK_SPAWN_INTERVAL_MAX);
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        AssetsMannager.load();
        backgroundTexture = new TextureRegion(AssetsMannager.getBackgroundTexture());
        ground = new TextureRegion(AssetsMannager.getGroundTexture());
        planeActor = new PlaneActor();
        //Input
        InputHandler inputHandler = new InputHandler(planeActor);
        Gdx.input.setInputProcessor(inputHandler);

        rocks = new Array<RockActor>();
        score = 0;
        ceiling = new TextureRegion(ground);
        ceiling.flip(true, true);
        enemies = new Array<EnemyActor>();

        backgroundX = 0;

        score = 0;
        font = new BitmapFont();

        if (game.isMusicOn()) {
            // Si la música está activada, la reproduce
            AssetsMannager.music.play();
        } else {
            // Si la música no está activada, la detiene
            AssetsMannager.music.stop();
        }
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        // Mover el fondo hacia la izquierda
        backgroundX -= BACKGROUND_SCROLL_SPEED * delta;

        if (invulnerabilityTimer > 0) {
            invulnerabilityTimer -= delta;
        }

        // Si el fondo se ha movido completamente fuera de la pantalla, lo reposiciona
        if (backgroundX <= -backgroundTexture.getRegionWidth()) {
            backgroundX = 0;
        }

        // Dibuja el fondo
        batch.draw(backgroundTexture, backgroundX, 0);
        batch.draw(backgroundTexture, backgroundX + backgroundTexture.getRegionWidth(), 0);

        // Check for rock spawn and generate a new rock
        if (rockSpawnTimer <= 0) {
            boolean isDown = MathUtils.randomBoolean();
            RockActor newRock = new RockActor(Gdx.graphics.getWidth(), isDown ? 0 : Gdx.graphics.getHeight() - ROCK_SPAWN_MIN_HEIGHT - planeActor.getPlaneTexture().getRegionHeight(), !isDown);
            rocks.add(newRock);
            rockSpawnTimer = MathUtils.random(ROCK_SPAWN_INTERVAL_MIN, ROCK_SPAWN_INTERVAL_MAX);
        } else {
            rockSpawnTimer -= delta;
        }

        if (enemySpawnTimer <= 0) {
            int x = Gdx.graphics.getWidth(); // Aparece desde la derecha
            int y = MathUtils.random(0, Gdx.graphics.getHeight() - new Texture("enemy.png").getHeight());
            EnemyActor newEnemy = new EnemyActor(x, y, ENEMY_SPEED);
            enemies.add(newEnemy);

            // Ajusta el intervalo de aparición de los enemigos basándote en la dificultad seleccionada
            if (game.getDifficulty().equals("Facil")) {
                enemySpawnTimer = ENEMY_SPAWN_INTERVAL_EASY;
            } else if (game.getDifficulty().equals("Medio")) {
                enemySpawnTimer = ENEMY_SPAWN_INTERVAL_MEDIUM;
            } else if (game.getDifficulty().equals("Difícil")) {
                enemySpawnTimer = ENEMY_SPAWN_INTERVAL_HARD;
            }
        } else {
            enemySpawnTimer -= delta;
        }

        // Update and draw rocks
        for (RockActor rock : rocks) {
            rock.act(delta);
            batch.draw(rock.getRockTexture(), rock.getRockRectangle().x, rock.getRockRectangle().y);
        }

        for (EnemyActor enemy : enemies) {
            enemy.act(delta);
            batch.draw(enemy.getEnemyTextureRegion(), enemy.getEnemyRectangle().x, enemy.getEnemyRectangle().y);
        }

        // Update and draw plane
        planeActor.act(delta);
        batch.draw(planeActor.getPlaneTexture(), planeActor.getPlaneRectangle().x, planeActor.getPlaneRectangle().y);

        // Check for collisions
        // Increment score when plane passes a rock
        for (RockActor rock : rocks) {
            if (!rock.isScored() && rock.getRockRectangle().x + rock.getRockTexture().getRegionWidth() < planeActor.getPlaneRectangle().x) {
                score++;
                rock.setScored(true);
            }
            if (rock.getRockRectangle().overlaps(planeActor.getPlaneRectangle()) && invulnerabilityTimer <= 0) {
                lives--; // Disminuye las vidas
                invulnerabilityTimer = INVULNERABILITY_TIME; // Establece el temporizador de invulnerabilidad
                if (lives <= 0) {
                    game.setScreen(new EndScreen(game, score)); // Cambia a EndScreen si no quedan vidas
                    break;
                }
            }
            }

        for (EnemyActor enemy : enemies) {
            if (enemy.getEnemyRectangle().overlaps(planeActor.getPlaneRectangle()) && invulnerabilityTimer <= 0) {
                lives--; // Disminuye las vidas
                invulnerabilityTimer = INVULNERABILITY_TIME; // Establece el temporizador de invulnerabilidad
                if (lives <= 0) {
                    game.setScreen(new EndScreen(game, score)); // Cambia a EndScreen si no quedan vidas
                    break;
                }
            }
        }

        // Increment score when plane passes a rock
        RockActor lastRock = rocks.size > 0 ? rocks.peek() : null;
        if (lastRock != null && lastRock.getRockRectangle().x + lastRock.getRockTexture().getRegionWidth() < planeActor.getPlaneRectangle().x && !lastRock.isScored()) {
            score++;
            lastRock.setScored(true);
        }

        // Draw ground and ceiling
        batch.draw(ground, 0, 0);
        batch.draw(ceiling, 0, Gdx.graphics.getHeight() - ceiling.getRegionHeight());

        // Draw score
        font.draw(batch, "Score: " + score, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 65);
        // Draw lives
        font.draw(batch, "Vidas: " + lives, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 85);



        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        //stage.getViewport().update(width, height, true)
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        AssetsMannager.music.stop();
    }

    @Override
    public void dispose() {
        AssetsMannager.music.stop();
        batch.dispose();
        backgroundTexture.getTexture().dispose();
        planeActor.getPlaneTexture().getTexture().dispose();
        for (RockActor rock : rocks) {
            rock.getRockTexture().getTexture().dispose();
        }
        ground.getTexture().dispose();
        ceiling.getTexture().dispose();
        AssetsMannager.dispose();
    }
}








