package com.towerdefense.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.towerdefense.game.TowerDefense;

public class MenuScreen implements Screen, InputProcessor {
    private SpriteBatch batch;
    private TowerDefense game;
    Texture img;
    private Texture backgroundTexture;
    private Texture playButtonTexture;
    private Texture exitButtonTexture;
    private Texture htpButtonTexture;
    private float buttonWidth;
    private boolean htp;
    private float buttonHeight;
    private float buttonSpacing;
    private static final int WORLD_WIDTH = 1280;
    private static final int WORLD_HEIGHT = 720;
    public MenuScreen(SpriteBatch batch, TowerDefense game) {
        this.game=game;
        this.batch = batch;
        htp=false;
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
        float touchX = screenX;
        float touchY = Gdx.graphics.getHeight() - screenY;

        if (htp==false){if (touchX >= Gdx.graphics.getWidth() / 2 - buttonWidth / 2 && touchX <= Gdx.graphics.getWidth() / 2 - buttonWidth / 2 + buttonWidth &&
                touchY >= Gdx.graphics.getHeight() / 2 + buttonSpacing && touchY <= Gdx.graphics.getHeight() / 2 + buttonSpacing + buttonHeight) {
            game.setScreen(new GameScreen(batch,game));
        } else if (touchX >= Gdx.graphics.getWidth() / 2 - buttonWidth / 2 && touchX <= Gdx.graphics.getWidth() / 2 - buttonWidth / 2 + buttonWidth &&
                touchY >= Gdx.graphics.getHeight() / 2 - buttonHeight - 2 * buttonSpacing && touchY <= Gdx.graphics.getHeight() / 2 - buttonHeight - 2 * buttonSpacing + buttonHeight) {
          htp = true;
        } else if (touchX >= Gdx.graphics.getWidth() / 2 - buttonWidth / 2 && touchX <= Gdx.graphics.getWidth() / 2 - buttonWidth / 2 + buttonWidth &&
                touchY >= Gdx.graphics.getHeight() / 4 - buttonHeight - 2 * buttonSpacing && touchY <= Gdx.graphics.getHeight() / 4 - buttonHeight - 2 * buttonSpacing + buttonHeight) {
            Gdx.app.exit();
        }
        }
        else {
            if (touchX >= 0 &&touchX<=1280 &&touchY>=0 &&touchY<=720){
                htp=false;
            }
        }
        return false;
    }
public void howtoplay(){
    batch.begin();
    batch.draw(new Texture("htppage.png"),0,0);
    batch.end();
}



    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        Pixmap cursorPixmap = new Pixmap(Gdx.files.internal("cursor.png"));
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(cursorPixmap, 0, 0));
        cursorPixmap.dispose();
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    @Override
    public void show() {
        img = new Texture("btn1.png");
        batch = new SpriteBatch();
        backgroundTexture = new Texture("bk.png");
        playButtonTexture = new Texture("btn1.png");
        exitButtonTexture = new Texture("btn2.png");
        htpButtonTexture = new Texture("htp.png");
        buttonWidth = Gdx.graphics.getWidth() / 4f;
        buttonHeight = Gdx.graphics.getHeight() / 8f;
        buttonSpacing = Gdx.graphics.getHeight() / 30f;


        Gdx.input.setInputProcessor(this);
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        ScreenUtils.clear(1, 1, 1, 1);
        batch.begin();
        if (htp == false){batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(playButtonTexture, Gdx.graphics.getWidth() / 2 - buttonWidth / 2, Gdx.graphics.getHeight() / 2 + buttonSpacing, buttonWidth, buttonHeight);
        batch.draw(exitButtonTexture, Gdx.graphics.getWidth() / 2 - buttonWidth / 2, Gdx.graphics.getHeight() / 4 - buttonHeight - 2 * buttonSpacing, buttonWidth, buttonHeight);
        batch.draw(htpButtonTexture, Gdx.graphics.getWidth() / 2 - buttonWidth / 2, Gdx.graphics.getHeight() / 2 - buttonHeight - 2 * buttonSpacing, buttonWidth, buttonHeight);
        }
        else {
            batch.draw(new Texture("htppage.png"),0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }
        batch.end();
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