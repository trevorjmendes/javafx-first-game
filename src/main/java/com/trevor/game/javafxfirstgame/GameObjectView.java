package com.trevor.game.javafxfirstgame;

import javafx.scene.Node;

public abstract class GameObjectView {
    private final GameObject gameObject;

    private Node node;
    public GameObjectView(GameObject gameObject, Node node) {
        this.gameObject = gameObject;
        this.node = node;
    }

    public void render() {
        node.setTranslateX(gameObject.getxPosition());
        node.setTranslateY(gameObject.getyPosition());
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    public void setStyleClass(String style) {
        node.getStyleClass().add(style);
    }

    public Node getNode() {
        return node;
    }
    public void setNode(Node node) {
        this.node = node;
    }
}
