package com.example.mario.model;

public class Concrete extends GameCharacter {
    private boolean isHit = false; // 叩かれたかどうかのフラグ
    private int originalY; // 元のY座標 (今回は使わないが、初期Yを記録しておくのは良い)
    private long hitTime; // 叩かれた時間 (今回は演出のY座標を戻さないので、使用しない)
    private Player player;
    private Coin generatedCoin = null; // 生成されたコイン

    public Concrete(int x, int y) {
        this.x = x;
        this.y = y;
        this.xSize = 48;
        this.ySize = 48;
        this.originalY = y; // 初期Y座標を保持
    }

    public void checkHit(){
        if (!isHit && player.getYSpeed() > 0){ // プレイヤーが上向きに移動している時（正の値）
            if (this.overlap(player)){
                // プレイヤーがコンクリートの下から叩いているかチェック
                if (player.getY() < this.y) {
                    hit();
                }
            }
        }
    }

    public void hit() {
        if (!isHit) { // まだ叩かれていない場合のみ反応
            isHit = true; // 叩かれた状態にする

            // 新しいコインを生成し、ブロックの中央から少し上に配置
            generatedCoin = new Coin(this.x + this.xSize / 2 - 16, this.y + this.ySize / 2 - 16);
        }
    }

    public Coin getGeneratedCoin() {
        Coin coin = generatedCoin;
        generatedCoin = null; // 一度取得したらnullにリセット
        return coin;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setPlayer(Player player){
        this.player = player;
    }
}