package com.example.mario.model;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class World {
    // 定数
    public static final int HEIGHT=720; // 世界の高さ
    public static final int WIDTH=5400; // 世界の幅

    // オブジェクト用変数
    private Player player;
    private List<Bridge> bridges;
    private List<com.example.mario.model.Pipe> pipes;
    private List<Block> blocks;
    private List<Concrete> concretes;
    private Pole pole;
    private Flag flag;
    private List<Enemy> enemies;
    private Goal goal;
    private List<Coin> coins;

    private boolean flagReachedGround = false; // フラグが地面に到達したかどうかのフラグ
    private boolean gameClear = false; // ★追加：ゲームクリアフラグ

    public World() {
        // オブジェクトの生成
        player = new Player(5000,600);

        bridges = new LinkedList<>();

        // 最初の12個（240間隔）
        IntStream.range(0, 12)
                .mapToObj(i -> new Bridge(i * 240, 0))
                .forEach(bridges::add);

        // 次の10個（240間隔、200スペース空けて）
        int offset = 12 * 240 + 200; // = 2880 + 200 = 3080
        IntStream.range(0, 10)
                .mapToObj(i -> new Bridge(offset + i * 240, 0))
                .forEach(bridges::add);

        pipes = new LinkedList<>();

        pipes.add(new com.example.mario.model.Pipe(1800, 0));
        pipes.add(new com.example.mario.model.Pipe(2200, 0));
        pipes.add(new com.example.mario.model.Pipe(2640, 0));
        pipes.add(new com.example.mario.model.Pipe(3100, 0));
        pipes.add(new com.example.mario.model.Pipe(3620, -60));

        blocks = new LinkedList<>();

        blocks.add(new Block(1392, 256));
        blocks.add(new Block(1488, 256));
        blocks.add(new Block(1584, 256));
        blocks.add(new Block(3377, 256));
        blocks.add(new Block(3472, 256));
        blocks.add(new Block(3568, 256));

        int startX = 3748;
        int startY = 48;
        int blockSize = 48;

        for (int step = 0; step < 10; step++) {      // 0～9段目
            int blocksInStep = step + 1;            // 1段目は1個、2段目は2個…
            for (int block = 0; block < blocksInStep; block++) {
                int x = startX + blockSize * step;                     // 横は段数分右に
                int y = startY + blockSize * block;                    // 縦はブロック数分上に
                blocks.add(new Block(x, y));
            }
        }

        concretes = new LinkedList<>();

        concretes.add(new Concrete(1536, 256));
        concretes.add(new Concrete(1488, 496));
        concretes.add(new Concrete(1440, 256));
        concretes.add(new Concrete(3424, 256));
        concretes.add(new Concrete(3520, 256));

        pole = new Pole(4400, 0);
        flag = new Flag(4456, 420);

        enemies = new LinkedList<>();
        enemies.add(new Enemy(2300, 48));
        enemies.add(new Enemy(1900, 48));
        enemies.add(new Enemy(2400, 48));
        enemies.add(new Enemy(2540, 48));
        enemies.add(new Enemy(3400, 48));

        goal = new Goal(4800, 48);

        coins = new LinkedList<>();


        //object connection

        enemies.forEach(enemy -> enemy.setPlayer(player));
        concretes.forEach(concrete -> concrete.setPlayer(player));
        flag.setPlayer(player);
        pole.setPlayer(player);
        pole.setFlag(flag);
        goal.setPlayer(player);

        //object collision detection
        bridges.forEach(bridge -> player.addLimitCharacter(bridge));
        pipes.forEach(pipe -> player.addLimitCharacter(pipe));
        blocks.forEach(block -> player.addLimitCharacter(block));
        concretes.forEach(concrete -> player.addLimitCharacter(concrete));
    }

    public void move() {
        // オブジェクトの更新
        player.move();
        enemies.forEach(enemy -> enemy.move());
        enemies.forEach(enemy -> enemy.checkCollision());
        
        // コンクリートの衝突判定とコイン生成
        concretes.forEach(concrete -> {
            concrete.checkHit();
            Coin newCoin = concrete.getGeneratedCoin();
            if (newCoin != null) {
                coins.add(newCoin);
            }
        });
        
        pole.checkCollision(); // ポールの衝突判定
        flag.move(); // フラグの移動処理
        goal.checkCollision(); // ゴールの衝突判定

        // フラグが地面に到達したらゴールを表示
        if (flag.isDowned() && !goal.isActive()) {
            goal.activate();
        }

        // プレイヤーがゴールに触れたらゲームクリア
        if (goal.hasCollided() && !gameClear) {
            gameClear = true;
        }

        coins.forEach(coin -> coin.move());
        coins.removeIf(coin -> !coin.isActive());
    }

    //Getter

    public Player getPlayer() {
        return player;
    }

    public List<Bridge> getBridges() {
        return bridges;
    }
    public List<Pipe> getPipes(){
        return pipes;
    }
    public List<Block> getBlocks(){
        return blocks;
    }
    public List<Concrete> getConcretes(){
        return concretes;
    }
    public Pole getPole(){
        return pole;
    }
    public Flag getFlag(){
        return flag;
    }
    public List<Enemy> getEnemies(){
        return enemies;
    }
    public Goal getGoal(){ return goal;}
    public boolean isGameClear(){ return gameClear; }
    public List<Coin> getCoins() { return coins; }
}
