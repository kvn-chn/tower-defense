package com.towerdefense.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.towerdefense.game.Screens.GameScreen;
import com.towerdefense.game.Screens.MenuScreen;

public class TowerDefense extends Game {
	private SpriteBatch batch;
	private MenuScreen menuScreen;
	@Override
	public void create() {
		batch = new SpriteBatch();
		menuScreen = new MenuScreen(batch,this);
		setScreen(menuScreen);
	}
	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		super.render();
		batch.end();
	}
	private boolean shouldSwitchToGameScreen() {
		return Gdx.input.isTouched();
	}
	@Override
	public void dispose() {
		batch.dispose();
	}
}