package com.example.mario.model;

public class Coin extends GameCharacter {
    private static final int INITIAL_JUMP_SPEED = 15;
    private static final float GRAVITY = 1.0f;

    public Coin(int x, int y) {
        this.x = x;
        this.y = y;
        this.xSize = 32;
        this.ySize = 32;
        this.ySpeed = -INITIAL_JUMP_SPEED;
        this.activeFlag = true;
    }

    @Override
    public void move() {
        ySpeed += GRAVITY;
        y += ySpeed;

        if (y < -ySize || y > World.HEIGHT) {
            this.activeFlag = false;
        }
    }
}