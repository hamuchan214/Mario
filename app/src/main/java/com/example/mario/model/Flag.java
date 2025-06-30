// package com.example.mario.model;

package com.example.mario.model;

public class Flag extends GameCharacter{

    private boolean isFalling = false; // フラグが落下中かどうかのフラグ
    private final int FALL_SPEED = -2; // フラグの落下速度
    private boolean isDowned = false;
    private Player player;

    public Flag(int x, int y){
        this.x = x;
        this.y = y;
        this.xSize = 100;
        this.ySize = 90;
    }

    public void startFalling() {
        if (!isFalling) {
            this.isFalling = true;
            this.ySpeed = FALL_SPEED;
        }
    }

    @Override
    public void move() {
        // 落下中の場合のみ位置を更新
        if (isFalling) {
            y += ySpeed;
            
            // 地面に到達したら停止
            if (y <= 0) {
                y = 0;
                ySpeed = 0;
                this.isDowned = true;
            }
        }
    }

    // フラグが落下中かどうかを返す
    public boolean isFalling() {
        return isFalling;
    }

    public boolean isDowned(){
        return isDowned;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}