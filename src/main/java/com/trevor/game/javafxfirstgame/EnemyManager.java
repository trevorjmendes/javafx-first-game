package com.trevor.game.javafxfirstgame;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

//TODO: Make explode function
//TODO: Make bigger enemies fall faster

// Made enemies able to pass through ceiling no problem
// Fixed enemies sinking thru floor (stopped gravity making enemies accelerate thru floor)
// Made ground impact penetration angularly consistent
// Made gravity always apply to enemies/player

public class EnemyManager {
    private ArrayList<Enemy> enemies;
    private final Pane gamePane;
    private final WaveManager waveManager;
    private final GameWorld world;

    public EnemyManager(Pane gamePane, WaveManager waveManager, GameWorld world) {
        this.gamePane = gamePane;
        this.waveManager = waveManager;
        enemies = new ArrayList<>();
        this.world = world;
    }
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public Enemy createEnemy(EnemyData data) {
        double x = Math.random() * (world.getWidth() - data.getWidth());
        double y = -(Math.random() * 100 + data.getHeight());

        double xVelocity = data.getStartingXVelocity();
        double yVelocity = data.getStartingYVelocity();
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
        enemy.getView().render();
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
            double gravity = world.getGravity() * enemy.getGravityMult() * dt;
            if (!enemy.onGround(world)) {
                enemy.setyVelocity(
                        enemy.getyVelocity() + gravity);
            }



            // position equals velocity times change in time
            double nextY = enemy.getyPosition() + enemy.getyVelocity() * dt;
            double nextX = enemy.getxPosition() + enemy.getxVelocity() * dt;
            // --- Y collisions ---
            // enemy hits ground
            if (nextY >= floor) {
                double newYVelocity = enemy.getyVelocity() * (1 - enemy.getRestitution());
                double newXVelocity = enemy.getxVelocity() * (1 - enemy.getRestitution());

                enemy.setyVelocity(newYVelocity); // finalizing velocities
                enemy.setxVelocity(newXVelocity);
            }
//            } else if (nextY <= ceiling) {
//                double newYVelocity = Math.abs(enemy.getyVelocity() * enemy.getRestitution());
//                enemy.setyVelocity(newYVelocity); // bounce down
//                nextY = ceiling;
//            }


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
