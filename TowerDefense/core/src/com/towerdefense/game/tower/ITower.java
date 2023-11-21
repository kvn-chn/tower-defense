package com.towerdefense.game.tower;

import com.badlogic.gdx.math.Vector2;

public interface ITower {

    void upgradeDamage();
    void upgradeRange();
    int getLevel();
    int getCost();
    int getTargetNumber();
    void setDamage(int damage);
    int getDamage();
    void setRange(int range);
    int getRange();
    Vector2 getPosition();

}
