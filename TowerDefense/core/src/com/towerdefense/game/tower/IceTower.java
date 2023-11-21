package com.towerdefense.game.tower;

public class IceTower extends ATower {

    public IceTower(float x, float y) {
        super(1, 150, 1, 130, x, y, 90, 80,"tower/ice/tower.png", "tower/ice/bullet.png");
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
