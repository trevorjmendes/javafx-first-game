package com.trevor.game.javafxfirstgame;

//TODO: Make onGround field and logic
public class Enemy extends GameObject {
    private final EnemyType TYPE;

    private final EnemyView view;

    private double gravityMult;
    private double restitution;


    public Enemy(double xPosition,
                 double yPosition,
                 double xVelocity,
                 double yVelocity,
                 double mass,
                 double width,
                 double height,
                 double gravityMult,
                 double restitution,
                 EnemyType type) {
        super(xPosition,
                yPosition,
                xVelocity,
                yVelocity,
                mass,
                width,
                height);
        this.gravityMult = gravityMult;
        this.restitution = restitution;
        this.TYPE = type;
        this.view = new EnemyView(this);

    }
    public boolean onGround(GameWorld world) {
        return getxPosition() + getHeight() >= world.getHeight();
    }
    public EnemyType getTYPE() {
        return TYPE;
    }

    public EnemyView getView() {
        return view;
    }

    public double getGravityMult() {
        return gravityMult;
    }

    public double getRestitution() {
        return restitution;
    }

    public void setGravityMult(double gravityMult) {
        this.gravityMult = gravityMult;
    }



}
