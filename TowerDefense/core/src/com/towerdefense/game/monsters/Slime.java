package com.towerdefense.game.monsters;

import com.badlogic.gdx.graphics.Texture;

public class Slime extends AEnemy {
    public Slime( float x, float y) {
        super(35, 15, 55, 30, false,x,y,new Texture("monster/S_Walk.png"));
    }
}
