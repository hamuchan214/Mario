package com.example.mario.model;

import java.util.LinkedList;
import java.util.List;

public class GameCharacter {
    // 属性
    protected int x = 0;
    protected int y = 0;
    protected int xSize = 0;
    protected int ySize = 0;
    protected int xSpeed = 0;
    protected float ySpeed = 0;
    protected boolean activeFlag = true;

    // メソッド
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getxSize() {
        return xSize;
    }

    public int getySize() {
        return ySize;
    }

    public boolean isActive() {
        return activeFlag;
    }

    // キャラクターの右端のｘ座標を取得する
    public int getRight() {
        return x + xSize - 1;
    }

    // キャラクターの左端のｘ座標を取得する
    public int getLeft() {
        return x;
    }

    // キャラクターの上端のｙ座標を取得する
    public int getTop() {
        return y + ySize - 1;
    }

    // キャラクターの下端のｙ座標を取得する
    public int getBottom() {
        return y;
    }

    public boolean isToRightOf(GameCharacter target) {
        return target.getRight() < getLeft();
    }

    public boolean isToLeftOf(GameCharacter target) {return getRight() < target.getLeft();

    }

    public boolean isToBottomOf(GameCharacter target) {
        return getTop() < target.getBottom();
    }

    public boolean isToTopOf(GameCharacter target) {
        return target.getTop() < getBottom();
    }

    public boolean isOverOnTop(GameCharacter target) {
        return getBottom() < target.getBottom();
    }

    public boolean isOverOnBottom(GameCharacter target) {
        return target.getTop() < getTop();
    }

    public boolean isOverOnLeft(GameCharacter target) {
        return target.getRight() < getRight();
    }

    public boolean isOverOnRight(GameCharacter target) {
        return getLeft() < target.getLeft();

    }


    // 横方向で重なっていないか判定する
    public boolean overlapX(GameCharacter target) {
        return !isToRightOf(target) && !isToLeftOf(target);
    }

    // 縦方向で重なっていないか判定する
    public boolean overlapY(GameCharacter target) {
        return !isToTopOf(target) && !isToBottomOf(target);
    }

    // 衝突していないか判定する
    public boolean overlap(GameCharacter target) {
        return overlapX(target) && overlapY(target);
    }

    public boolean fullOverlap(GameCharacter target){
        return ( getBottom() <= target.getBottom() && target.getTop()<getTop()
        && getLeft() <= target.getLeft() && target.getRight() <= getRight());
    }
    public void move() {
    }
}
