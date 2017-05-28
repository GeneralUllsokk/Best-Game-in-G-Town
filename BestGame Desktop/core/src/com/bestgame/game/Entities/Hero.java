package com.bestgame.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bestgame.game.GameClass;

public class Hero extends Entity implements InputProcessor {
    public Hero(GameClass game) {
        super(game, "SpriteSheets/HeroSpritePack.atlas");
    }

    @Override
    public void create(){
        this.entityRect.setX(200);
        this.entityRect.setY(180);
        this.entityRect.setWidth(128);
        this.entityRect.setHeight(128);

        movespeed = 200;
        animation = new Animation(1, getTextureAtlas().getRegions());

    }

    @Override
    public void render(){
        this.game.batch.begin();
        this.setElapsedTime(getElapsedTime() + Gdx.graphics.getDeltaTime());
        this.game.batch.draw((TextureRegion)animation.getKeyFrame(getElapsedTime(), true), entityRect.getX(), entityRect.getY(), entityRect.getWidth(), entityRect.getHeight());
        this.game.batch.end();

        heroMovement();
        heroCollision();
    }

    private void heroMovement(){
        if(Gdx.input.isKeyPressed(Input.Keys.W))
            entityRect.y += movespeed * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.S))
            entityRect.y -= movespeed * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.A))
            entityRect.x -= movespeed * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.D))
            entityRect.x += movespeed * Gdx.graphics.getDeltaTime();
    }

    private void heroCollision() {
        if (entityRect.x < -64)
            entityRect.x = -64;
        if (entityRect.x > Gdx.graphics.getWidth() - 64)
            entityRect.x = Gdx.graphics.getWidth() - 64;
        if (entityRect.y < -64)
            entityRect.y = -64;
        if (entityRect.y > Gdx.graphics.getHeight() - 64)
            entityRect.y = Gdx.graphics.getHeight() - 64;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
