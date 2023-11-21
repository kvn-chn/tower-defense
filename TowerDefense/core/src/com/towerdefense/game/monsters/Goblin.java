package com.towerdefense.game.monsters;

import com.badlogic.gdx.graphics.Texture;

public class Goblin extends AEnemy{
    public Goblin(float x,float y) {
        super(40, 20, 50, 40,false,x,y,new Texture("monster/G_Walk.png"));
    }
}
