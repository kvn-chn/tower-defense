package com.towerdefense.game.monsters;

import com.badlogic.gdx.graphics.Texture;

public class Wolf extends AEnemy{
    public Wolf(float x ,float y) {
        super(40, 30, 65, 35, false,x,y,new Texture("monster/W_Walk.png"));
    }
}
