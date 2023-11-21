package com.towerdefense.game.Renderer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.towerdefense.game.monsters.AEnemy;

public class MonsterRenderer {
    private Animation<TextureRegion> monsterAnimation;
    private ShapeRenderer hitBoxRenderer;
    public void renderMonster(AEnemy monster, float stateTime, SpriteBatch spriteBatch) {

        TextureRegion[][] frameMonster = TextureRegion.split(monster.getTexture(), 48, 48);
        monsterAnimation = new Animation<>(0.1f, frameMonster[0]);
        TextureRegion monsterFrame = monsterAnimation.getKeyFrame(stateTime, true);
        hitBoxRenderer = new ShapeRenderer();

        spriteBatch.begin();
        if (monster.isAlive()) {
            spriteBatch.draw(monsterFrame, monster.getPosition().x - 24, monster.getPosition().y - 24,
                    (monster.getHitBox().width / 6), monster.getHitBox().height);
        }
        spriteBatch.end();
        //renderHitbox(hitBoxRenderer, monster);
    }

    public void renderHealthBar(SpriteBatch batch, ShapeRenderer shapeRenderer, AEnemy enemy) {
        if (enemy.isAlive()) {
            batch.begin();
            float healthBarWidth = 40;
            float healthBarHeight = 5;

            float x = enemy.getPosition().x;
            float y = enemy.getPosition().y + enemy.getHitBox().height / 2 + 5;

            float healthPercentage = (float) enemy.getPV() / enemy.getMaxPV();

            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.rect(x, y, healthBarWidth, healthBarHeight);

            shapeRenderer.setColor(Color.GREEN);
            shapeRenderer.rect(x, y, healthBarWidth * healthPercentage, healthBarHeight);
            shapeRenderer.end();
            batch.end();
        }
    }
//    private void renderHitbox(ShapeRenderer shapeRenderer, AEnemy monster) {
//        if (monster.isAlive()) {
//            float x = monster.getPosition().x;
//            float y = monster.getPosition().y;
//            float width = monster.getHitBox().width / 6; // A CHANGER
//            float height = monster.getHitBox().height; // A CHANGER
//
//            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//            shapeRenderer.setColor(Color.RED);
//            shapeRenderer.rect(x, y, width, height);
//            shapeRenderer.end();
//        }
//    }
}
