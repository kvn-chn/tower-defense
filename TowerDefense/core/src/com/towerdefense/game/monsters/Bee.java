package com.towerdefense.game.monsters;

import com.badlogic.gdx.graphics.Texture;

public class Bee extends AEnemy {

    public Bee( float x, float y) {
        super(30, 10, 60,15, true,x,y,new Texture("monster/B_Walk.png"));
    }

}