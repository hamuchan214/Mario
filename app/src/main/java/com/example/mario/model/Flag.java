// package com.example.mario.model;

package com.example.mario.model;

public class Flag extends GameCharacter{

    private boolean isFalling = false; // フラグが落下中かどうかのフラグ
    private final int FALL_SPEED = -2; // フラグの落下速度
    private boolean isDowned = false;
    private boolean hasCollided = false; // 衝突済みかどうかのフラグ
    private Player player;

    public Flag(int x, int y){
        this.x = x;
        this.y = y;
        this.xSize = 100;
        this.ySize = 90;
    }

    public void checkcollision(){
        // 既に衝突済みまたは落下中なら何もしない
        if (hasCollided || isFalling) {
            return;
        }
        
        if(this.overlap(player)){
            player.xSpeed = 0;
            player.ySpeed = 0;
            this.isFalling = true;
            this.hasCollided = true;
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