package com.d43.tbs;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.d43.tbs.utils.Assets;
import com.d43.tbs.view.GameScreen;

public class TurnBaseStrategy extends Game {

	private Screen gameScreen;
	private Assets assets;
	
	@Override
	public void create() {
		assets = new Assets();
		gameScreen = new GameScreen();
		((GameScreen)gameScreen).setTextureAtlas(assets.getManager().get("atlas.atlas", TextureAtlas.class));
		this.setScreen(gameScreen);
	}
	
	public void dispose() {
		super.dispose();
		gameScreen.dispose();
		assets.dispose();
	}
	
//	SpriteBatch batch;
//	Texture img;
//	
//	@Override
//	public void create () {
//		batch = new SpriteBatch();
//		img = new Texture("badlogic.jpg");
//	}
//
//	@Override
//	public void render () {
//		Gdx.gl.glClearColor(1, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();
//	}
//	
//	@Override
//	public void dispose () {
//		batch.dispose();
//		img.dispose();
//	}
}
