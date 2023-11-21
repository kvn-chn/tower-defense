package com.towerdefense.game.tower;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.towerdefense.game.monsters.AEnemy;

public class Projectile {
    private Vector2 position;
    private Vector2 velocity;
    private Texture projectileFile;
    private float speed;
    private AEnemy target;
    private Rectangle hitbox;
    private float rotation;

    public Projectile(float x, float y, AEnemy target, Texture projectileFile, float speed) {
        this.position = new Vector2(x, y);
        this.target = target;
        this.projectileFile = projectileFile;
        this.speed = speed;
        this.hitbox = new Rectangle(x, y, projectileFile.getWidth(), projectileFile.getHeight());
        Vector2 direction = new Vector2(target.getPosition().x - x, target.getPosition().y - y);
        direction.nor(); // Normalize the direction vector
        this.velocity = calculateVelocity(speed);
        this.rotation = direction.angle();

        updateVelocity();
        Vector2 initialDirection = new Vector2(target.getPosition().x - position.x, target.getPosition().y - position.y);
        initialDirection.nor();
        this.rotation = initialDirection.angle();
    }

    public void update() {
        updateVelocity();
        position.x += velocity.x * Gdx.graphics.getDeltaTime();
        position.y += velocity.y * Gdx.graphics.getDeltaTime();
        hitbox.setPosition(position.x, position.y);

        // Update rotation based on the current direction
        rotation = velocity.angle();
    }

    private Vector2 calculateVelocity(float speed) {
        float angle = (float) Math.atan2(target.getPosition().y - position.y, target.getPosition().x - position.x);
        return new Vector2((float) Math.cos(angle) * speed, (float) Math.sin(angle) * speed);
    }

    private void updateVelocity() {
        Vector2 direction = new Vector2(target.getPosition().x - position.x, target.getPosition().y - position.y);
        direction.nor(); // Normalize the direction vector
        this.velocity = new Vector2(direction.x * speed, direction.y * speed);
    }

    public Rectangle getHitbox() {
        return hitbox;
    }


    public void render(SpriteBatch batch) {
        batch.draw(projectileFile, position.x + 12, position.y + 3,
                projectileFile.getWidth() / 2f, projectileFile.getHeight() / 2f, // originX, originY (center of the projectile)
                projectileFile.getWidth(), projectileFile.getHeight(),
                1.5f, 1.5f,
                rotation, // rotation
                0, 0,
                projectileFile.getWidth(), projectileFile.getHeight(),
                false, false);
    }

    public boolean hasReachedTarget(AEnemy enemy) {
        return position.dst(target.getPosition()) < 10;
    }

    public AEnemy getTarget() {
        return target;
    }
}