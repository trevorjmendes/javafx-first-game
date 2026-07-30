package com.trevor.game.javafxfirstgame;

public class GameWorld {
    private double width;
    private double height;
    private double gravity;

    public GameWorld(double width, double height, double gravity) {
        this.width = width;
        this.height = height;
        this.gravity = gravity;
    }
    public void update(double width, double height, double gravity) {
        this.width = width;
        this.height = height;
        this.gravity = gravity;
    }
    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getGravity() {
        return gravity;
    }
    public void setGravity(double gravity) {
        this.gravity = gravity;
    }
}
