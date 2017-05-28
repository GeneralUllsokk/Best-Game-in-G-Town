package com.bestgame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class GameScreen implements Screen {
    private final float heroMoveSpeed = 200;
    final GameClass game;
    MapClass map;

    Texture heroImage;
    Rectangle heroRect;
    OrthographicCamera camera;

    public GameScreen(final GameClass game){
        this.game = game;
        map = new MapClass();
        map.create();

        heroImage = new Texture(Gdx.files.internal("spriteMed.png"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        heroRect = new Rectangle();
        heroRect.x = Gdx.graphics.getWidth()/2;
        heroRect.y = Gdx.graphics.getHeight()/2;
        heroRect.height = 90;
        heroRect.width = 90;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        map.render();

        game.batch.begin();
        game.batch.draw(heroImage, heroRect.getX(), heroRect.getY(), heroRect.getWidth(), heroRect.getHeight());
        game.batch.end();

        heroMovement();
        heroCollision();
    }

    private void heroMovement(){
        if(Gdx.input.isKeyPressed(Input.Keys.W))
            heroRect.y += heroMoveSpeed * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.S))
            heroRect.y -= heroMoveSpeed * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.A))
            heroRect.x -= heroMoveSpeed * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.D))
            heroRect.x += heroMoveSpeed * Gdx.graphics.getDeltaTime();
    }

    private void heroCollision(){
        if(heroRect.x < 0)
            heroRect.x = 0;
        if(heroRect.x > Gdx.graphics.getWidth()-64)
            heroRect.x = Gdx.graphics.getWidth()-64;
        if(heroRect.y < 0)
            heroRect.y = 0;
        if(heroRect.y > Gdx.graphics.getHeight()-64)
            heroRect.y = Gdx.graphics.getHeight()-64;
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

    }
}
