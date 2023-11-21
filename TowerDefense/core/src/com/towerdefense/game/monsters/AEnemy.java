package com.towerdefense.game.monsters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class AEnemy implements IEnemy{

    private Texture texture;
    private float stateTime;
    protected Vector2 position;
    protected int Pathed=0;
    protected boolean isAlive;
    protected int maxPV;
    protected int pv;
    protected int damage;
    protected int speed;
    private Rectangle hitBox;
    protected  int gold;
    protected  boolean fly;
    protected boolean isSlowed = false;
    protected boolean isHavingDoT = false;
    protected int DoTDamage;
    protected int DoTCD;
    protected String monsterType;
    protected boolean BountyAvaible;
    protected ShapeRenderer sr;
    public AEnemy(int pv, int damage, int speed, int gold, boolean fly, float x, float y,Texture texture){
        this.maxPV = pv;
        this.pv = pv;
        this.damage=damage;
        this.speed=speed;
        this.DoTDamage=0;
        this.DoTCD = 0;
        this.gold=gold;
        this.fly=fly;
        this.hitBox = new Rectangle(x, y , texture.getWidth() * 2, texture.getHeight() * 2);
        this.position = new Vector2(x, y);
        this.isAlive = true;
        this.texture=texture;
        this.sr = new ShapeRenderer();
        this.BountyAvaible=true;
    }

    public boolean isBountyAvailable() {
        return BountyAvaible;
    }

    public void GiveBounty() {
        BountyAvaible = false;
    }

    @Override
    public int getDamage() {
        return damage;
    }
    @Override
    public Rectangle getHitBox() {
        return hitBox;
    }
    public int getMaxPV() { return maxPV; }
    @Override
    public int getPV() {
        return pv;
    }
    @Override
    public int getSpeed() {
        return speed;
    }
    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    @Override
    public int getGold() {
        return gold;
    }
    @Override
    public boolean isFly() {
        return fly;
    }

    public void takeDamage(int damage) {
        this.pv -= damage;
        if (this.pv <= 0) {
            isAlive = false;
        }
    }

//    public void draw(SpriteBatch batch) {
//        if (isAlive) {
//            batch.draw(texture, position.x, position.y);
//        }
//    }

    public boolean isAlive() {
        return isAlive;
    }

    public void Dies() {
        isAlive = false;
    }

    public boolean isSlowed() {
        return isSlowed;
    }

    public void slowDown() {
        if (!isSlowed) {
            speed *= 0.6f;
            isSlowed = true;
        }
    }

    public void applyDoTDamage(int damage, int duration) {
        if (!isHavingDoT) {
            this.DoTDamage = damage;
            this.DoTCD = duration;
            isHavingDoT = true;
        }
    }

    public void DoTUpdate(float deltaTime) {
        stateTime += deltaTime;
        DoTCD--;
        if (DoTCD % 50 == 0) {
            takeDamage(DoTDamage);
        }
        if (DoTCD <= 0) {
            DoTCD = 0;
            DoTDamage = 0;
            isHavingDoT = false;
        }
    }

    public Vector2 getPosition() {
        return position;
    }

    public void move(Vector2 destination, float time) {
        if (isAlive) {

            Vector2 direction = new Vector2(destination).sub(position).nor();
            float distanceToTravel = speed * time;


            if (distanceToTravel > position.dst(destination)) {
                distanceToTravel = position.dst(destination);
            }

            position.add(direction.scl(distanceToTravel));
        }
    }


    public int getPathed() {
        return Pathed;
    }

    public void addPathed() {
        Pathed +=1;
    }
    // Faut creer un fonction pour fair apparaitre le mob jpense

    @Override
    public String getMonsterType() {
        return monsterType;
    }
    public float getStateTime() {
        return stateTime;
    }
    public void update(float deltaTime) {
        stateTime += deltaTime;
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    @Override
    public void increaseHP(int hp) {
        this.maxPV += hp;
        this.pv += hp;
    }

    @Override
    public void increaseSpeed(int speed) {
        this.speed += speed;
    }
}