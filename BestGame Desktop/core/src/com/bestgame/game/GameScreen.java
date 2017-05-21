package com.bestgame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class GameScreen implements Screen {

    final GameClass game;

    Texture heroImage;
    Rectangle heroRect;
    OrthographicCamera camera;

    public GameScreen(final GameClass game){
        this.game = game;

        heroImage = new Texture(Gdx.files.internal("billy.png"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);

        heroRect = new Rectangle();
        heroRect.x = 1280/2-64/2;
        heroRect.y = 20;

        heroRect.height = 64;
        heroRect.width = 64;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(heroImage, heroRect.getX(), heroRect.getY(), heroRect.getWidth(), heroRect.getHeight());
        game.batch.end();

        heroMovement();
        heroCollision();
    }

    private void heroMovement(){
        if(Gdx.input.isKeyPressed(Input.Keys.W))
            heroRect.y += 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.S))
            heroRect.y -= 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.A))
            heroRect.x -= 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.D))
            heroRect.x += 200 * Gdx.graphics.getDeltaTime();
    }

    private void heroCollision(){
        if(heroRect.x < 0)
            heroRect.x = 0;
        if(heroRect.x > 1280-64)
            heroRect.x = 1280-64;
        if(heroRect.y < 0)
            heroRect.y = 0;
        if(heroRect.y > 720-64)
            heroRect.y = 720-64;
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
