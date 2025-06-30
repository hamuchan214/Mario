package com.example.mario.model;

public class Pole extends GameCharacter{
    private Player player;
    private Flag flag;
    private boolean hasCollided = false; // 衝突済みかどうかのフラグ

    public Pole(int x, int y){
        this.x = x;
        this.y = y;
        this.xSize = 100;
        this.ySize = 600;
    }

    public void checkCollision() {
        // 既に衝突済みなら何もしない
        if (hasCollided) {
            return;
        }
        
        if (this.overlap(player)) {
            hasCollided = true;
            // プレイヤーを停止
            player.xSpeed = 0;
            player.ySpeed = 0;
            // フラグを落下開始
            flag.startFalling();
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }
}
