package com.towerdefense.game.tower;

import com.towerdefense.game.Manager.MonsterManager;
import com.towerdefense.game.monsters.AEnemy;

public class PoisonTower extends ATower{
    private int continuousDamage;
    public PoisonTower(float x, float y) {
        super(1, 170, 1, 140, x, y, 80,70,"tower/poison/tower.png", "tower/poison/bullet.png");
        this.continuousDamage = 1;
    }

    public int getContinuousDamage() {
        return continuousDamage;
    }

    public void setContinuousDamage(int continuousDamage) {
        this.damage = continuousDamage;
    }

    @Override
    public void updateTime(float deltaTime, MonsterManager enemies) {
        super.updateTime(deltaTime, enemies);
    }

    @Override
    public void attack(AEnemy enemy) {
        super.attack(enemy);
        enemy.applyDoTDamage(getContinuousDamage(), 350);
    }

    @Override
    public void upgradeDamage() { this.damage += this.damage / 2; this.continuousDamage += this.continuousDamage /2; }
    @Override
    public void upgradeRange() {
        this.range += 20;
    }
    @Override
    public void upgradeSpeed() { this.projectileSpeed += 10; this.attackCooldown -= 3; }

}
