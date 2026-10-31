package com.trevor.game.javafxfirstgame;

//TODO: Make start startingVelocity field, a vector (two elements, x and y)
public class EnemyData {
    private final EnemyType type;
    private final double gravityMult;
    private final double restitution;
    private final double[] startingVelocity;
    private final double mass;
    private final double width;
    private final double height;
    public EnemyData(EnemyType type, double gravityMult, double restitution, double[] startingVelocity, double mass, double width, double height) {
        this.type = type;
        this.gravityMult = gravityMult;
        this.restitution = restitution;
        this.startingVelocity = startingVelocity;
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

    public double[] getStartingVelocity() {return startingVelocity;}
    public double getStartingXVelocity() {return startingVelocity[0];}
    public double getStartingYVelocity() {return startingVelocity[1];}

    public double getMass() {return mass;}
    public double getWidth() {return width;}
    public double getHeight() {return height;}


}
