package com.trevor.game.javafxfirstgame;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

//TODO: Enemies start in top left corner for first frame FIX IT
public class GameController {
    private SceneHandler sceneHandler;
    private GameState gameState;
    private enum InGameState {
        PLAYING,
        PAUSED,
        GAME_OVER,
        WIN
    }
    private EnemyManager enemyManager;
    private WaveManager waveManager;

    private InGameState inGameState;
    private GameWorld gameWorld;
    private InputManager inputManager;
    private Player player;
    private PlayerView playerView;
    @FXML
    private Pane gamePane;
    @FXML
    private VBox gameOverPane;
    @FXML
    private VBox winPane;
    @FXML
    private VBox pausePane;
    @FXML
    private StackPane overlayLayer;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label finalScoreLabel;
    @FXML
    private Label clicksLabel;
    @FXML
    private Label winScoreLabel;
    private AnimationTimer gameLoop;
    private int score;

    int numClicks;
    private static final double DELTA_TIME = 1.0 / 120.0; // 120 Hz physics
    private double accumulator = 0.0;
    private long lastTime = 0;

    public void setSceneHandler(SceneHandler sceneHandler) {
        this.sceneHandler = sceneHandler;
    }

    @FXML
    public void initialize() {
        gameState = GameState.IN_GAME;
        // basically each loop of animation timer is a "frame"
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (lastTime == 0) {
                    lastTime = now;
                    accumulator = 0;
                    return;
                }
                double frameTime = (now - lastTime) / 1_000_000_000.0;
                lastTime = now;
                // clamp
                frameTime = Math.min(frameTime, 0.25);
                accumulator += frameTime;

                while (accumulator >= DELTA_TIME) {
                    gameWorld.update(gamePane.getWidth(), gamePane.getHeight(), gameWorld.getGravity());
                    enemyManager.update(DELTA_TIME, gameWorld);
                    player.update(DELTA_TIME, inputManager, gameWorld);
                    accumulator -= DELTA_TIME;
                }
                playerView.render();

            }
        };
    }
    public void addPlayer() {
        player = new Player();
        player.setxPosition(gamePane.getWidth() / 2);
        player.setyPosition(gamePane.getHeight() - player.getHeight());
        playerView = new PlayerView(player);
        playerView.render();
        gamePane.getChildren().add(playerView.getNode());

    }
    public void removePlayer() {
        gamePane.getChildren().remove(playerView.getNode());
    }

    public void onSceneEnter() {
        setInGameState(InGameState.PLAYING);
        score = 0;
        numClicks = 60;
        clicksLabel.setText("Clicks left: " + numClicks);
        scoreLabel.setText("Score: " + score);

        gameWorld = new GameWorld(gamePane.getWidth(), gamePane.getHeight(),2000);
        waveManager = new WaveManager();
        enemyManager = new EnemyManager(gamePane, waveManager, gameWorld);
        inputManager = new InputManager();
        addPlayer();

        gamePane.setOnKeyPressed(e -> inputManager.keyPressed(e.getCode()));
        gamePane.setOnKeyReleased(e -> inputManager.keyReleased(e.getCode()));
        gamePane.setOnMouseClicked(e -> {
            onMiss();
        });

        for (int i = 1; i < 3; i++) {
            waveManager.createCircleWave(i, 1,0.65, 900, 50, 1);
        }
        //waveManager.addToWave(0,1, 0.65,900, 75, 1);
        waveManager.nextWave();
        enemyManager.spawnWave();
        makeEnemiesHittable();

        gamePane.requestFocus();

        lastTime = 0;
        gameLoop.start();

    }
    private void setInGameState(InGameState inGameState) {
        this.inGameState = inGameState;
        switch (inGameState) {
            case GAME_OVER -> {
                overlayLayer.setMouseTransparent(false);
                gameLoop.stop();
                finalScoreLabel.setText("Score: " + score);
                gameOverPane.setVisible(true);
            }
            case PLAYING -> {
                overlayLayer.setMouseTransparent(true);
                gameOverPane.setVisible(false);
                pausePane.setVisible(false);
                gamePane.requestFocus();

                lastTime = 0;
                gameLoop.start();
            }
            case PAUSED -> {
                inputManager.clear();
                overlayLayer.setMouseTransparent(false);
                gameLoop.stop();
                pausePane.setVisible(true);
            }
            case WIN -> {
                overlayLayer.setMouseTransparent(false);
                gameLoop.stop();
                winScoreLabel.setText("Score: " + score);
                winPane.setVisible(true);
            }
        }
    }

    public void makeEnemiesHittable() {
        for (Enemy enemy : enemyManager.getEnemies()) {
            enemy.getView().getNode().setOnMousePressed(e -> {
                onHit(enemy);
                e.consume();
            });
        }
    }
    public void onHit(Enemy enemy) {
        score += Math.round(10 + 1000 / enemy.getWidth());
        scoreLabel.setText("Score: " + score);
        enemyManager.removeEnemy(enemy);
        if (enemyManager.getEnemies().size() == 0) {
            waveManager.removeWave();
            if (waveManager.nextWave()) {
                enemyManager.spawnWave();
                makeEnemiesHittable();
            } else if (waveManager.isEmpty()) {
                win();
            }
        }
    }

    public void win() {
        setInGameState(InGameState.WIN);
    }
    public void onMiss() {
        numClicks -= 1;
        clicksLabel.setText("Clicks left: " + numClicks);
        if (numClicks <= 0) {
            setInGameState(InGameState.GAME_OVER);
        }
    }
    public void exitToMenu() throws IOException {
        gameLoop.stop();
        sceneHandler.changeState(GameState.MENU);
    }
    public void restart() {
        removePlayer();
        enemyManager.restart();
        gameOverPane.setVisible(false);
        winPane.setVisible(false);
        onSceneEnter();
    }
    public void pause() {
        setInGameState(InGameState.PAUSED);
    }
    public void resume() {
        setInGameState(InGameState.PLAYING);
    }
}
