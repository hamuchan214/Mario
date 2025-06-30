// package com.example.mario.model;

package com.example.mario.model;

public class Goal extends GameCharacter {
    public Goal(int x, int y) {
        this.x = x;
        this.y = y;
        this.xSize = 80; // ゴールのサイズは適宜調整してください
        this.ySize = 120; // ゴールのサイズは適宜調整してください
        this.activeFlag = false; // 最初は非表示
    }

    // ゴールが表示されるようにするメソッド
    public void activate() {
        this.activeFlag = true;
    }
}