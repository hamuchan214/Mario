package com.example.mario.model;

public class Enemy extends GameCharacter {

    private int xSpeed = 2;
    private final int startX;
    private final int endX;

    private Player player;

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
        this.xSize = 48;
        this.ySize = 48;

        this.startX = x;
        this.endX = x + 80;
    }

    public void move() {
        x += xSpeed;

        if (x >= endX - xSize) {
            xSpeed = -2;
        } else if (x <= startX) {
            xSpeed = 2;
        }
    }

    public void checkCollision() {
        // 敵またはプレイヤーが非アクティブなら何もしない
        if (!this.isActive() || !player.isActive()) {
            return;
        }

        // プレイヤーが落下中（ySpeed > 0）の場合のみ、以下の処理を行う
        // Y座標が上に行くほど値が大きくなる座標系では、ySpeed > 0 が落下を意味します。
        if (player.getYSpeed() < 0) {
            // プレイヤーが落下中であり、かつ敵とオーバーラップしているか判定
            if (this.overlap(player)) {
                // 落下中にオーバーラップしたので、敵を消す
                this.activeFlag = false; // 敵を非アクティブにする
                player.jump(); // プレイヤーを跳ねさせる
                return; // 処理を終える
            }
        } else {
            // プレイヤーが落下中でない（上昇中または静止中）場合で、かつ敵とオーバーラップしている場合
            if (this.overlap(player)) {
                // プレイヤーを死亡させる
                if (!player.isDead()) {
                    player.setDeadFlag(true);
                    // 必要であれば、ここでゲームオーバー効果音を鳴らす
                    // soundManager.playSound("gameover"); // Worldで鳴らす場合は不要
                }
            }
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}