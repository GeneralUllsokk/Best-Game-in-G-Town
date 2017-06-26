package com.bestgame.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameClass extends Game {
	private final String VERSION = "0.00.03";
	public SpriteBatch batch;
	public BitmapFont font;

	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		this.setScreen(new MainMenuScreen(this));
	}

	public void render(){
		super.render();
		batch.begin();
		font.draw(batch, "Version: " + VERSION, 0, 720);
		batch.end();
	}

	public void dispose(){
		batch.dispose();
		font.dispose();
		super.dispose();
	}
}
