package com.bestgame.game.Entities;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.bestgame.game.GameClass;

public class Entity implements ApplicationListener {
    public final GameClass game;
    public Rectangle entityRect;
    public Animation animation;
    public float movespeed = 0;
    private TextureAtlas textureAtlas;

    private float elapsedTime = 0;

    public Entity(GameClass game, String spriteSheetpos){
        this.game = game;
        entityRect = new Rectangle();
        textureAtlas = new TextureAtlas(Gdx.files.internal(spriteSheetpos));
    }

    @Override
    public void create() {
    }

    @Override
    public void render() {
    }

    @Override
    public void dispose() {
        textureAtlas.dispose();
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

    public TextureAtlas getTextureAtlas() {
        return textureAtlas;
    }

    public float getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(float elapsedTime) {
        this.elapsedTime = elapsedTime;
    }
}
