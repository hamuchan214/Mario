package com.example.mario.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.mario.MainActivity;
import com.example.mario.R;
import com.example.mario.helpers.BaseView;
import com.example.mario.model.GameCharacter;
import com.example.mario.model.Player;
import com.example.mario.model.World;


public class MainView extends BaseView {

    MainActivity mainActivity;
    ConstraintLayout constraintLayout;
    Context context;

    // 定数
    final int BLOCK_COUNT = 5;

    // 画像用変数
    Bitmap backGroundImage;
    Bitmap playerRightImage;
    Bitmap playerLeftImage;
    Bitmap bridgeImage;
    Bitmap pipeImage;
    Bitmap blockImage;
    Bitmap concreteImage;
    Bitmap poleImage;
    Bitmap flagImage;
    Bitmap enemyImage;
    Bitmap goalImage;
    Bitmap concreteHitImage;
    Bitmap coinImage;

    // ビュー用変数
    ImageViewBuilder imageViewBuilder;
    TextView gameClearTextView;
    TextView gameOverTextView;

    public MainView(Context context) {
        super(context);
        this.context = context;
        this.mainActivity = (MainActivity) context;

        // 画像の読み込み
        backGroundImage = loadImage(R.drawable.sea);
        playerRightImage = loadImage(R.drawable.player_rigft);
        playerLeftImage = loadImage(R.drawable.player_left);
        bridgeImage = loadImage(R.drawable.bridges);
        pipeImage = loadImage(R.drawable.pipe_up);
        blockImage = loadImage(R.drawable.block1);
        concreteImage = loadImage(R.drawable.concretex);
        concreteHitImage = loadImage(R.drawable.concrete);
        poleImage = loadImage(R.drawable.pole);
        flagImage = loadImage(R.drawable.flag);
        enemyImage = loadImage(R.drawable.enemy);
        goalImage = loadImage(R.drawable.goal);
        coinImage = loadImage(R.drawable.coin);

        // ビューの生成・登録
        constraintLayout = new ConstraintLayout(context);
        baseActivity.setContentView(constraintLayout);
        imageViewBuilder = new ImageViewBuilder(constraintLayout, context);

    }

    public void draw(World world) {
        Player player = world.getPlayer();

        if (player.isDead()) {
            drawGameOver();
        }

        if (world.isGameClear()) {
            drawGameClear();
        }

        // 横スクロール
        if (player.getX() < 750) {
            canvasBaseX = 0;
        } else if (player.getX() < 3850+750) {
            canvasBaseX = player.getX() - 750;
        } else {
            canvasBaseX = 3850;
        }

        // ImageViewBuilderリセット
        imageViewBuilder.reset();

       // 背景の表示
        ImageView imageView = imageViewBuilder.getImageView();
        drawImage(0, 0, World.WIDTH, World.HEIGHT, backGroundImage, imageView);

        // オブジェクトの表示
        world.getBridges().forEach(bridge -> drawCharacter(bridge, bridgeImage));
        world.getPipes().forEach(pipe -> drawCharacter(pipe,pipeImage));
        world.getBlocks().forEach(block -> drawCharacter(block,blockImage));

        world.getConcretes().forEach(concrete -> {
            Bitmap imageToDraw = concrete.isHit() ? concreteHitImage : concreteImage;
            drawCharacter(concrete, imageToDraw);
        });

        drawCharacter(world.getPole(), poleImage);
        drawCharacter(world.getFlag(), flagImage);
        world.getEnemies().forEach(enemy -> drawCharacter(enemy, enemyImage));
        drawCharacter(world.getGoal(), goalImage);
        world.getCoins().forEach(coin -> drawCharacter(coin, coinImage));

        drawPlayer(player);


    }

    //======================
    // テキストビュー表示用の関数
    //======================
    public void drawGameClear() {
        if (gameClearTextView == null) {
            gameClearTextView = new TextView(context);
            constraintLayout.addView(gameClearTextView);
        }
        gameClearTextView.setTextSize(32);
        gameClearTextView.setTextColor(Color.WHITE);
        gameClearTextView.setText("Game Clear !!");
        drawTextViewCenter(canvasBaseX + 750, 350, gameClearTextView);
    }

    public void drawGameOver() {
        if (gameOverTextView == null) {
            gameOverTextView = new TextView(context);
            constraintLayout.addView(gameOverTextView);
        }
        gameOverTextView.setTextSize(32);
        gameOverTextView.setTextColor(Color.RED);
        gameOverTextView.setText("Game Over !!");
        drawTextViewCenter(canvasBaseX + 750, 350, gameOverTextView);
    }


    //======================
    // キャラクター表示用の関数
    //======================
    private void drawPlayer(Player player) {
        Bitmap playerImage = (player.getXSpeed() >= 0) ? playerLeftImage : playerRightImage;
        drawCharacter(player, playerImage);
    }

    private void drawCharacter(GameCharacter c, Bitmap image) {
        if (c.isActive()) {
            ImageView imageView = imageViewBuilder.getImageView();
            int x = c.getX();
            int y = c.getY();
            int xSize = c.getxSize();
            int ySize = c.getySize();
            drawImage(x, y, xSize, ySize, image, imageView);
        }
    }
}


