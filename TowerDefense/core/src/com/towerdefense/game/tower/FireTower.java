package com.towerdefense.game.tower;

public class FireTower extends ATower {
    public FireTower(float x, float y) {
        super(4,170, 2, 100, x, y, 80, 50,"tower/flame/tower.png", "tower/flame/bullet.png");
    }

    @Override
    public void upgradeDamage() { this.damage += this.damage / 2;}
    @Override
    public void upgradeRange() {
        this.range += 20;
    }
    @Override
    public void upgradeSpeed() { this.projectileSpeed += 10; this.attackCooldown -= 3; }
}
