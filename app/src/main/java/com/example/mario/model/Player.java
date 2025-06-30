package com.example.mario.model;

/**
 * Created by t010318 on 2017/06/21.
 */

public class Player extends GameCharacterLimit {
    private int yAccel = -1;

    private boolean deadFlag = false;
    private boolean jumpFlag = false;
    private int previousY = 0;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.xSize = 48;
        this.ySize = 48;
        this.xSpeed = 0;
        this.ySpeed = 0;
    }

    public float getXSpeed() {
        return xSpeed;
    }

    public float getYSpeed() { return ySpeed; }

    public void turnRight() {
        xSpeed = 3;
    }

    public void turnLeft() {
        xSpeed = -3;
    }

    public void stop() {
        xSpeed = 0;
    }

    public void jump() {
        if (jumpFlag == false) {
            ySpeed = 24;
            jumpFlag = true;
        }
    }

    public void move() {
        previousY = y;

        // 位置の更新
        x += xSpeed;
        y += ySpeed;
        ySpeed += yAccel;

        // 着地したらジャンプ終了
        if (y < yMin) {
            ySpeed = 0;
            jumpFlag = false;
        }

        correctPosition();
    }


    public void setDeadFlag(boolean deadFlag){
        this.deadFlag = deadFlag;
    }

    public boolean isDead(){
        return deadFlag;
    }


}
