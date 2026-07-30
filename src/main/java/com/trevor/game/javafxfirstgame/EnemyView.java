package com.trevor.game.javafxfirstgame;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class EnemyView extends GameObjectView {
    public EnemyView(Enemy enemy) {
        super(enemy, null);
        switch (enemy.getTYPE()) {
            case SQUARE -> setNode(new Rectangle(enemy.getHeight(), enemy.getWidth()));
            case CIRCLE -> setNode(new Circle(enemy.getWidth() / 2));
            case BOSS -> setNode(new ImageView());
            default -> setNode(new Rectangle(enemy.getHeight(), enemy.getWidth()));
        }
        super.setStyleClass("enemy");
        if (getNode() instanceof Circle circle) {
            circle.setCenterX(enemy.getWidth() / 2);
            circle.setCenterY(enemy.getWidth() / 2);
        }
    }
}
