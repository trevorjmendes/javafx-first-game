package com.trevor.game.javafxfirstgame;

public class EnemyData {
    private final EnemyType type;
    private final double gravityMult;
    private final double restitution;
    private final double startingXVelocity;
    private final double mass;
    private final double width;
    private final double height;
    public EnemyData(EnemyType type, double gravityMult, double restitution, double startingXVelocity, double mass, double width, double height) {
        this.type = type;
        this.gravityMult = gravityMult;
        this.restitution = restitution;
        this.startingXVelocity = startingXVelocity;
        this.mass = mass;
        this.width = width;
        this.height = height;
    }

    public EnemyType getType() {
        return type;
    }


    public double getGravityMult() {return gravityMult;}

    public double getRestitution() {
        return restitution;
    }

    public double getStartingXVelocity() {return startingXVelocity;}
    public double getMass() {return mass;}
    public double getWidth() {return width;}
    public double getHeight() {return height;}


}
