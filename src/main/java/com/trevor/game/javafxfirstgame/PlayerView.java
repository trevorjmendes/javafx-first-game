package com.trevor.game.javafxfirstgame;

import javafx.scene.shape.Rectangle;

public class PlayerView extends GameObjectView {
    public PlayerView(Player player) {
        super(player, new Rectangle(player.getWidth(), player.getHeight()));
        super.setStyleClass("player");
    }

    public Player getPlayer() {
        return (Player) getGameObject();
    }
}
