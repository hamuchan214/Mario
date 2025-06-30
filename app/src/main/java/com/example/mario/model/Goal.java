// package com.example.mario.model;

package com.example.mario.model;

public class Goal extends GameCharacter {
    private Player player;
    private boolean hasCollided = false; // 衝突済みかどうかのフラグ

    public Goal(int x, int y) {
        this.x = x;
        this.y = y;
        this.xSize = 80; // ゴールのサイズは適宜調整してください
        this.ySize = 120; // ゴールのサイズは適宜調整してください
        this.activeFlag = false; // 最初は非表示
    }

    public void checkCollision() {
        // 既に衝突済みまたは非アクティブなら何もしない
        if (hasCollided || !activeFlag) {
            return;
        }
        
        if (this.overlap(player)) {
            hasCollided = true;
            // プレイヤーを停止
            player.xSpeed = 0;
            player.ySpeed = 0;
        }
    }

    // ゴールが表示されるようにするメソッド
    public void activate() {
        this.activeFlag = true;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean hasCollided() {
        return hasCollided;
    }
}