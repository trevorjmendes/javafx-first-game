package com.trevor.game.javafxfirstgame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class MenuController {
    private SceneHandler app;
    private final GameState state = GameState.MENU;
    public void setApp(SceneHandler app) {
        this.app = app;
    }
    @FXML
    public void onStartClick(ActionEvent event) throws IOException {
        app.changeState(GameState.IN_GAME);
    }
}
