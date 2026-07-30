package com.trevor.game.javafxfirstgame;

import javafx.scene.input.KeyCode;

public class InputManager {
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean jumpPressed;


    public void keyPressed(KeyCode key) {
        switch (key) {
            case A, LEFT -> leftPressed = true;
            case D, RIGHT -> rightPressed = true;
            case W, UP -> jumpPressed = true;
        }
    }

    public void keyReleased(KeyCode key) {
        switch (key) {
            case A, LEFT -> leftPressed = false;
            case D, RIGHT -> rightPressed = false;
            case W, UP -> jumpPressed = false;
        }

    }
    public void clear() {
        this.leftPressed = false;
        this.rightPressed = false;
        this.jumpPressed = false;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isJumpPressed() {
        return jumpPressed;
    }
}
