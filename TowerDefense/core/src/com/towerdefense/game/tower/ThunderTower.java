package com.towerdefense.game.tower;

public class ThunderTower extends ATower{

    public ThunderTower(float x, float y) {
        super(5, 200, 1, 150, x, y, 100,40,"tower/thunder/tower.png", "tower/thunder/bullet.png");
    }

    @Override
    public void upgradeDamage() { this.damage += this.damage / 2;}
    @Override
    public void upgradeRange() {
        this.range += 20;
    }
    @Override
    public void upgradeSpeed() {
        this.projectileSpeed += 10;
        this.attackCooldown -= 3;
    }
}
