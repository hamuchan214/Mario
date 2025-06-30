package com.example.mario;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MotionEvent;

import com.example.mario.R;
import com.example.mario.helpers.BaseActivity;
import com.example.mario.model.Player;
import com.example.mario.model.World;
import com.example.mario.views.MainView;

public class MainActivity extends BaseActivity {

    World world;

    MainView mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        gravityEnabled = true;
        orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        world = new World();
        mainView = new MainView(this);

    }

    public void update() {
        //---------------
        // プレーヤの移動
        //---------------
        Player player = world.getPlayer();

        // 端末を左右に傾けると横方向に移動
        if (accelerationController.y > 2) {
            player.turnRight();
        } else if (accelerationController.y < -2) {
            player.turnLeft();
        } else {
            player.stop();
        }

        // 端末を上下に傾けるとジャンプ
        if (accelerationController.x > 2) {
            player.jump();
        }

        //---------------
        // モデルの更新
        //---------------
        world.move();

        //---------------
        // ビューの更新
        //---------------
        mainView.draw(world);
    }

    //===========
    // イベントリスナー
    //===========
    public void retry() {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }
}