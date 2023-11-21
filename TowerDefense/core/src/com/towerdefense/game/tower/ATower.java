package com.towerdefense.game.tower;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.towerdefense.game.Manager.MonsterManager;
import com.towerdefense.game.monsters.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public abstract class ATower implements ITower{
    protected int damage;
    protected int range;
    protected float speed;
    protected boolean isAerien;
    protected int level;
    protected int cost;
    protected int targetNumber;
    protected Vector2 position;
    protected Circle circle;
    protected Texture file;
    protected List<Projectile> projectiles;
    protected Texture projectileFile;
    protected float projectileSpeed;

    protected TextureRegion[][] frame;
    protected Animation<TextureRegion> animation;
    protected Animation<TextureRegion> animation2;
    private final float frameDuration = 0.1f;
    protected TextureRegion towerFrame;
    protected TextureRegion towerFrame2;
    protected float stateTime;
    protected ShapeRenderer renderer;
    protected int attackCooldown;
    protected int initCD;
    protected AEnemy currentTarget;


    public ATower(int damage, int range, int targetNumber, int cost, float x, float y, int projectileSpeed, int attackCooldown ,String file, String projectileFile) {
        this.damage = damage;
        this.range = range;
        this.level = 1;
        this.cost = cost;
        this.targetNumber = targetNumber;
        this.position = new Vector2(x, y);
        this.file = new Texture(file);
        circle = new Circle(x + this.file.getWidth() / 2f, y + this.file.getHeight() / 2f, this.range);
        frame = TextureRegion.split(this.file, 45, this.file.getHeight());
        TextureRegion[][] frame2 = TextureRegion.split(this.file, 36, 38);
        animation = new Animation<>(frameDuration, frame[0]);
        animation2 = new Animation<>(frameDuration, frame2[0]);
        stateTime += Gdx.graphics.getDeltaTime();
        towerFrame = animation.getKeyFrame(stateTime, true);
        towerFrame2 = animation2.getKeyFrame(stateTime, true);
        renderer = new ShapeRenderer();
        this.stateTime = 0;
        this.attackCooldown = attackCooldown;
        this.initCD = attackCooldown;
        this.projectiles = new ArrayList<>();
        this.projectileFile = new Texture(projectileFile);
        this.projectileSpeed = projectileSpeed;
    }

    public void updateTime(float deltaTime, MonsterManager enemies) {

        towerFrame = animation.getKeyFrame(deltaTime, true);
        towerFrame2 = animation2.getKeyFrame(deltaTime, true);
        if (initCD > 0) {
            attackCooldown--;
        }
        if (currentTarget != null && !isEnemyInRange(currentTarget)) {
            currentTarget = null;
        }
        if (currentTarget == null || !currentTarget.isAlive()) {
            if (!enemies.getMonster().isEmpty()) {
                currentTarget = findNewTarget(enemies);
            }
        }
    }

    private boolean isReached = false;
    public void updateProjectile(AEnemy enemy) {
        Iterator<Projectile> iterator = projectiles.iterator();
        while (iterator.hasNext()) {
            Projectile projectile = iterator.next();
            projectile.update();
            if (projectile.hasReachedTarget(enemy)) {
                iterator.remove();
                isReached = true;
            }
        }
    }
    public void renderProjectiles(SpriteBatch batch) {
        for (Projectile projectile : projectiles) {
            projectile.render(batch);
        }
    }

    private AEnemy findNewTarget(MonsterManager enemies) {
        if (!enemies.getMonster().isEmpty()) {
            for (AEnemy enemy:enemies.getMonster()) {
                if (isEnemyInRange(enemy) && enemy.isAlive()) {
                    return enemy;
                }
            }
        }
        return null;
    }

    public boolean isEnemyInRange(AEnemy enemy) {
        float distance = calculatedistance(enemy);
        return distance <= range;
    }
    private float calculatedistance(AEnemy enemy) {
        float xdistance = Math.abs(enemy.getPosition().x - getPosition().x);
        float ydistance = Math.abs(enemy.getPosition().y - getPosition().y);
        return (float) Math.sqrt(xdistance * xdistance + ydistance * ydistance);
    }
    public void setPosition(float x, float y) {
        position.set(x, y);
    }

    public void attack(AEnemy enemy) {
        if (attackCooldown <= 0) {
            Projectile projectile = new Projectile(position.x, position.y, enemy, projectileFile, projectileSpeed);
            projectiles.add(projectile);

            if (isReached) {
                enemy.takeDamage(this.damage);
                isReached = false;

                if (this instanceof IceTower) {
                    enemy.slowDown();
                }
            }
            attackCooldown = initCD;
        }
    }

    public TextureRegion getTowerFrame() { return towerFrame; }

    public TextureRegion getTowerFrame2() { return towerFrame2; }
    public float getSpeed() { return projectileSpeed; }
    public boolean getIsAerien() { return  isAerien; }
    public int getLevel() { return  level; }
    public void upgradeLevel() { this.level += 1;}
    public int getCost() { return cost; }
    public void costIncrease() { this.cost += this.cost /2; }
    public int getTargetNumber() {
        return targetNumber;
    }
    public void upgradeDamage() {
        this.damage += this.damage / 2;
    }
    public void upgradeRange() {
        this.range += 50;
    }
    public void upgradeSpeed() {
        this.speed += 10;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
    public int getDamage() {
        return damage;
    }
    public void setRange(int range) {
        this.range = range;
    }
    public int getRange() {
        return range;
    }
    public Vector2 getPosition() {
        return position;
    }

    public AEnemy getCurrentTarget() { return currentTarget; }

    public void showRadius(ShapeRenderer shapeRenderer) {
        float x = position.x;
        float y = position.y;

        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.circle(x + towerFrame.getRegionWidth() / 2, y + towerFrame.getRegionHeight() / 2, range);
        shapeRenderer.end();
    }
}