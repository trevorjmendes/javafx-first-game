package com.trevor.game.javafxfirstgame;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneHandler {
    private GameState currentState;
    private Stage stage;

    public SceneHandler(Stage stage) {
        this.stage = stage;
    }

    public void changeState(GameState newState) throws IOException {

        currentState = newState;

        switch (currentState) {
            case MENU -> showMenu();
            case IN_GAME -> showPlaying();
        }
    }

    private void showMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuScene.fxml"));
        Parent root = loader.load();
        MenuController controller = loader.getController();
        controller.setApp(this);
        Scene scene = new Scene(root);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    private void showPlaying() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameScene.fxml"));
        Parent root = loader.load();
        GameController controller = loader.getController();
        controller.setSceneHandler(this);
        Scene scene = new Scene(root);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        controller.onSceneEnter();
    }
}
