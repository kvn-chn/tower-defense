package com.towerdefense.game.tower;

public class LaserTower extends ATower{
    public LaserTower(float x, float y) {
        super(3,400, 1, 150, x, y, 170, 50,"tower/laser/tower.png", "tower/laser/bullet.png");
    }

    @Override
    public void upgradeDamage() { this.damage += this.damage / 2;}
    @Override
    public void upgradeRange() {
        this.range += 10;
    }
    @Override
    public void upgradeSpeed() {
        this.projectileSpeed += 10;
        this.attackCooldown -= 3;
    }
}
