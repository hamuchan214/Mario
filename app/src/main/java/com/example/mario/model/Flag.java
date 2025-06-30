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

    public void checkcollision(){
        if(this.overlap(player)){
            player.xSpeed = 0;
            player.ySpeed = 0;
            this.isFalling = true;
            this.ySpeed = FALL_SPEED;
            if (this.y == 0){
                this.ySpeed = 0;
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