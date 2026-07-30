package com.trevor.game.javafxfirstgame;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

// TODO: Make x collisions reduce speed
// TODO: FIX Y Velocity stuck around 10 when enemies on ground
public class EnemyManager {
    private ArrayList<Enemy> enemies;
    private final Pane gamePane;
    private final WaveManager waveManager;
    private final GameWorld world;
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
    public EnemyManager(Pane gamePane, WaveManager waveManager, GameWorld world) {
        this.gamePane = gamePane;
        this.waveManager = waveManager;
        enemies = new ArrayList<>();
        this.world = world;
    }

    public Enemy createEnemy(EnemyData data) {
        double x = Math.random() * (world.getWidth() - data.getWidth());
        double y = Math.random() * (world.getHeight() / 10) + data.getWidth();
        double xVelocity = data.getStartingXVelocity() * (Math.random() < 0.5 ? -1 : 1);
        double yVelocity = 0;
        return new Enemy(
                x,
                y,
                xVelocity,
                yVelocity,
                data.getMass(),
                data.getWidth(),
                data.getHeight(),
                data.getGravityMult(),
                data.getRestitution(),
                data.getType());
    }

    public void spawnWave() {
        for (EnemyData data : waveManager.getCurrentWave()) {
            Enemy enemy = createEnemy(data);
            addEnemy(enemy);
        }
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
        gamePane.getChildren().add(enemy.getView().getNode());
    }
    public void update(
            double dt,
            GameWorld gameWorld
    )
    {
        for (Enemy enemy : enemies) {
            double left = 0;
            double right = gameWorld.getWidth() - enemy.getWidth();
            double ceiling = 0;
            double floor = gameWorld.getHeight() - enemy.getHeight();
            // Integrate gravity
            enemy.setyVelocity(
                    enemy.getyVelocity()
                            + gameWorld.getGravity() * enemy.getGravityMult() * dt);


            double nextY = enemy.getyPosition() + enemy.getyVelocity() * dt;
            double nextX = enemy.getxPosition() + enemy.getxVelocity() * dt;
            // --- Y collisions ---
            if (nextY >= floor) {
                double newYVelocity = enemy.getyVelocity() * enemy.getRestitution();
                nextY = floor;
                if (enemy.getyVelocity() >= 0) {
                    nextY = enemy.getyPosition();
                    double newXVelocity = enemy.getxVelocity() * 0.99;
                    enemy.setxVelocity(newXVelocity);
                }
                enemy.setyVelocity(-Math.abs(newYVelocity)); // bounce up
            } else if (nextY <= ceiling) {
                double newYVelocity = Math.abs(enemy.getyVelocity() * enemy.getRestitution());
                enemy.setyVelocity(newYVelocity); // bounce down
                nextY = ceiling;
            }


            // --- X collisions ---
            if (nextX >= right) {
                double newXVelocity = -Math.abs(enemy.getxVelocity() * enemy.getRestitution());
                nextX = right;
                enemy.setxVelocity(newXVelocity);
            } else if (nextX <= left) {
                double newXVelocity = Math.abs(enemy.getxVelocity() * enemy.getRestitution());
                nextX = left;
                enemy.setxVelocity(newXVelocity);
            }

            // finalizing enemy position
            enemy.setxPosition(nextX);
            enemy.setyPosition(nextY);

            enemy.getView().render();
            // Check to see if its on the ground
            enemy.onGround(world);

        }
    }

    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
        gamePane.getChildren().remove(enemy.getView().getNode());

    }
    public void restart() {
        for (Enemy enemy : enemies) {
            gamePane.getChildren().remove(enemy.getView().getNode());

        }
        enemies.clear();
        waveManager.getWaveQueue().clear();
    }
}
