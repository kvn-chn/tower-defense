package com.towerdefense.game.monsters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public interface IEnemy {
    int getPV();
    int getSpeed();
    int getDamage();
    void increaseSpeed(int speed);
    void increaseHP(int hp);
    Rectangle getHitBox();
    void setSpeed(int speed);
    int getGold();
    boolean isFly();
    String getMonsterType();
    Texture getTexture();
}