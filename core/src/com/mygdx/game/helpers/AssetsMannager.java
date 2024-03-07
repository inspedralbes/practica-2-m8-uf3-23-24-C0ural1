package com.mygdx.game.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetsMannager {

    private static AssetManager assetManager = new AssetManager();
    private static Texture planeTexture;
    private static Texture planeTexture2;
    private static Texture planeTexture3;
    private static Texture backgroundTexture;
    private static Texture groundTexture;

    private static Texture enemic;


    private static Texture TorreTexture;

    private static Texture enemyTexture;

    public static Music music;

    // Método para cargar los activos
    public static void load() {
        assetManager.load("plane1.png", Texture.class);
        assetManager.load("plane2.png", Texture.class);
        assetManager.load("plane3.png", Texture.class);
        assetManager.load("background.png", Texture.class);
        assetManager.load("ground.png", Texture.class);
        assetManager.load("torre4.png", Texture.class);
        assetManager.load("enemy.png", Texture.class);
        assetManager.load("enemy3.png", Texture.class);

        assetManager.finishLoading();

        planeTexture = assetManager.get("plane1.png", Texture.class);
        planeTexture2 = assetManager.get("plane2.png", Texture.class);
        planeTexture3 = assetManager.get("plane3.png", Texture.class);
        backgroundTexture = assetManager.get("background.png", Texture.class);
        groundTexture = assetManager.get("ground.png", Texture.class);
        TorreTexture = assetManager.get("torre4.png", Texture.class);
        enemic = assetManager.get("enemy.png", Texture.class);
        enemyTexture = assetManager.get("enemy3.png", Texture.class);


        music = Gdx.audio.newMusic(Gdx.files.internal("musica/music.mp3"));
        music.setVolume(0.2f);


    }

    public static Texture getPlaneTexture() {
        return planeTexture;
    }
    public static Texture getPlaneTexture2() {
        return planeTexture2;
    }
    public static Texture getPlaneTexture3() {
        return planeTexture3;
    }
    public static Texture getBackgroundTexture() {
        return backgroundTexture;
    }

    public static Texture getGroundTexture() {
        return groundTexture;
    }
    public static Texture getEnemicTexture() {
        return enemic;
    }

    public static Texture getTorreTexture() {
        return TorreTexture;
    }

    public static void dispose() {
        assetManager.dispose();
    }

    public static Texture getEnemyTexture() {
        return enemyTexture;
    }
}