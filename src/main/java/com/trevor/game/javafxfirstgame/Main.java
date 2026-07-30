package com.trevor.game.javafxfirstgame;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneHandler app = new SceneHandler(stage);
        app.changeState(GameState.MENU);
    }

    public static void main(String[] args) {
        launch();
    }
}