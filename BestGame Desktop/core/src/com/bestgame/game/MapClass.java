package com.bestgame.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricStaggeredTiledMapRenderer;

public class MapClass extends ApplicationAdapter implements InputProcessor {
    TiledMap tiledMap;
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;
    MapProperties prop;
    private float cameraXpos = 0;
    private float cameraYpos = 0;
    private boolean collideTop = false;
    private boolean collideBot = false;
    private boolean collideLeft = false;
    private boolean collideRight = false;

    private int mapWidth;
    private int mapHeight;
    private int tilePixelWidth;
    private int tilePixelHeight;

    private int mapPixelWidth;
    private int mapPixelHeight;

    private float movespeed = 200;

    @Override
    public void create(){
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false,w,h);
        camera.update();
        camera.translate(-128,0);
        cameraXpos += -128;
        camera.zoom = (float) 0.7;
        tiledMap = new TmxMapLoader().load("placeholder123.tmx");
        tiledMapRenderer = new IsometricStaggeredTiledMapRenderer(tiledMap);
        setCameraLimits(tiledMap);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(){
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        heroMovement();
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
    }

    private void heroMovement(){
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            if (cameraYpos < mapPixelHeight / 28) {
                camera.translate(0, movespeed * Gdx.graphics.getDeltaTime());
                cameraYpos += movespeed * Gdx.graphics.getDeltaTime();
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            if(cameraYpos > -mapPixelHeight / 12) {
                camera.translate(0, -movespeed * Gdx.graphics.getDeltaTime());
                cameraYpos += -movespeed * Gdx.graphics.getDeltaTime();
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            if(cameraXpos > -mapPixelWidth / 7) {
                camera.translate(-movespeed * Gdx.graphics.getDeltaTime(), 0);
                cameraXpos += -movespeed * Gdx.graphics.getDeltaTime();
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            if(cameraXpos < mapPixelWidth / 9) {
                camera.translate(movespeed * Gdx.graphics.getDeltaTime(), 0);
                cameraXpos += movespeed * Gdx.graphics.getDeltaTime();
            }
        }
    }

    private void setCameraLimits(TiledMap tiledMap){
        prop = tiledMap.getProperties();
        mapWidth = prop.get("width", Integer.class);
        mapHeight = prop.get("height", Integer.class);
        tilePixelWidth = prop.get("tilewidth", Integer.class);
        tilePixelHeight = prop.get("tileheight", Integer.class);
        mapPixelWidth = mapWidth * tilePixelWidth;
        mapPixelHeight = mapHeight * tilePixelHeight;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.LEFT)
            camera.translate(-32,0);
        if(keycode == Input.Keys.RIGHT)
            camera.translate(32,0);
        if(keycode == Input.Keys.UP)
            camera.translate(0,-32);
        if(keycode == Input.Keys.DOWN)
            camera.translate(0,32);
        if(keycode == Input.Keys.NUM_1)
            tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
        if(keycode == Input.Keys.NUM_2)
            tiledMap.getLayers().get(1).setVisible(!tiledMap.getLayers().get(1).isVisible());
        if(keycode == Input.Keys.NUM_3) {
            tiledMap.getLayers().get(2).setVisible(!tiledMap.getLayers().get(2).isVisible());
            tiledMap.getLayers().get(3).setVisible(!tiledMap.getLayers().get(3).isVisible());
        }
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
