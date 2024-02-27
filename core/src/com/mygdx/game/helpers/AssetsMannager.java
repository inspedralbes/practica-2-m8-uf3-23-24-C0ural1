package com.mygdx.game.helpers;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

public class AssetsMannager {

    private static final AssetsMannager instance = new AssetsMannager();

    public AssetsMannager() {
    }

    public static void load() {/*
        instance.manager.load("sonic.png", Texture.class);
        instance.manager.load("monstruo.png", Texture.class);
        instance.manager.load("fons.png", Texture.class);
        instance.manager.load("sonicMusica.mp3", Music.class);
        instance.manager.load("perdervida.mp3", Music.class);


        instance.manager.finishLoading(); //*/
    }

    public static void dispose() {
    }

    /*public static Texture getPersonajeTexture() {
        return instance.manager.get("sonic.png", Texture.class);
    }

    public static Texture getMonsterTexture() {
        return instance.manager.get("monstruo.png", Texture.class);
    }

    public static Texture getFons() {
        return instance.manager.get("fons.png", Texture.class);
    }

    public static Music getMusicaSonic() {
        return instance.manager.get("sonicMusica.mp3", Music.class);
    }

    public static Music getPerdreVida() {
        return instance.manager.get("perdervida.mp3", Music.class);
    }*/

}
