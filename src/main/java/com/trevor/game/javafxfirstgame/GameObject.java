package com.trevor.game.javafxfirstgame;

/*
TODO: x position, y position, mass, size, view,
 */
public abstract class GameObject {
    private double xPosition;
    private double yPosition;
    private double xVelocity;
    private double yVelocity;
    private double mass;
    private double width;
    private double height;

    public GameObject(double xPosition,
                      double yPosition,
                      double xVelocity,
                      double yVelocity,
                      double mass,
                      double width,
                      double height) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.mass = mass;
        this.width = width;
        this.height = height;
    }

    public double getMass() {
        return mass;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getxPosition() {
        return xPosition;
    }

    public void setxPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    public double getyPosition() {
        return yPosition;
    }

    public void setyPosition(double yPosition) {
        this.yPosition = yPosition;
    }

    public double getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    public double getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }



}
