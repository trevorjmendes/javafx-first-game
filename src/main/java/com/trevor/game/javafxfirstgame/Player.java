package com.trevor.game.javafxfirstgame;

//TODO: conserve jump speed
public class Player extends GameObject {
    private double gravityMult;
    private double speed;
    private double jumpSpeed;
    private boolean onGround;
    public Player() {
        super(0, 0, 0, 0, 80,  60, 180);
        gravityMult = 1;
        speed = 50;
        jumpSpeed = 500;
    }
    public void setGravityMult(double gravityMult) {this.gravityMult = gravityMult;}
    public void update(double dt, InputManager inputManager, GameWorld world) {
        // input

        if (inputManager.isLeftPressed()) {
            setxVelocity(getxVelocity() - speed);
        }
        if (inputManager.isRightPressed()) {
            setxVelocity(getxVelocity() + speed);
        }
        if (inputManager.isJumpPressed() && onGround) {
            setyVelocity(-jumpSpeed);
        }
        double newXVelocity;
        // Integrate gravity
        setyVelocity(getyVelocity() + world.getGravity() * gravityMult * dt);
        if (!onGround) {
            double newAirVelocity = getxVelocity() * 0.92;
            setxVelocity(newAirVelocity);
        }

        double nextY = getyPosition() + getyVelocity() * dt;
        double nextX = getxPosition() + getxVelocity() * dt;

        // --- Y collisions ---
        double floor = world.getHeight() - getHeight();
        double ceiling = 0;
        double right = world.getWidth() - getWidth();
        double left = 0;
        if (nextY >= floor) {
            setyVelocity(0);
            setxVelocity(getxVelocity() * 0.93);
            nextY = floor;
            onGround = true;
        } else {
            onGround = false;
        }
        if (nextY <= ceiling) {
            double newYVelocity = Math.abs(getyVelocity() + world.getGravity());
            setyVelocity(newYVelocity); // bounce down
            nextY = ceiling;
        }


        // --- X collisions ---
        if (nextX >= right) {
            newXVelocity = 0;
            nextX = right;
            setxVelocity(newXVelocity);
        } else if (nextX <= left) {
            newXVelocity = 0;
            nextX = left;
            setxVelocity(newXVelocity);
        }

        // finalizing enemy position
        setxPosition(nextX);
        setyPosition(nextY);
    }

}
