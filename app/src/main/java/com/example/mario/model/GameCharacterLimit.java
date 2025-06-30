package com.example.mario.model;

import java.util.LinkedList;
import java.util.List;

public class GameCharacterLimit extends GameCharacter {
    // 属性
    protected int xMin=0;
    protected int xMax=World.WIDTH;
    protected int yMin=-100;
    protected int yMax=World.HEIGHT;
    private List<GameCharacter> targets = new LinkedList<>();

    public void addLimitCharacter(GameCharacter target) {
        targets.add(target);
    }

    private int getMaxX() {
        return targets.stream()
                .filter(c -> c.isActive() && overlapY(c) && isToLeftOf(c))
                .map(c -> c.getLeft())
                .min(Integer::compareTo)
                .orElse(World.WIDTH) - xSize;
    }

    private int getMinX() {
        return targets.stream()
                .filter(c -> c.isActive() && overlapY(c) && isToRightOf(c))
                .map(c -> c.getRight())
                .max(Integer::compareTo)
                .orElse(0) + 1;
    }

    private int getMaxY() {
        return targets.stream()
                .filter(c -> c.isActive() && overlapX(c) && isToBottomOf(c))
                .map(c -> c.getBottom())
                .min(Integer::compareTo)
                .orElse(World.HEIGHT) - ySize;
    }

    private int getMinY() {
        return targets.stream()
                .filter(c -> c.isActive() && overlapX(c) && isToTopOf(c))
                .map(c -> c.getTop())
                .max(Integer::compareTo)
                .orElse(-100) + 1;
    }

    protected void correctPosition() {
        if (xMax < x) {
            x = xMax;
            xSpeed = 0;
        }
        if (x < xMin) {
            x = xMin;
            xSpeed = 0;
        }
        if (yMax < y) {
            y = yMax;
            ySpeed = 0;
        }
        if (y < yMin) {
            y = yMin;
            ySpeed = 0;
        }
        xMax = getMaxX();
        xMin = getMinX();
        yMax = getMaxY();
        yMin = getMinY();
    }
}
