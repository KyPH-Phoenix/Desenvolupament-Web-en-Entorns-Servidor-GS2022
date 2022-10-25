package com.liceu.geom.model;

public class Figure {
    private int xCord;
    private int yCord;
    private int size;
    enum type {CIRCLE, SQUARE, TRIANGLE, PENTAGON, STAR}
    enum color {BLACK, GREEN, RED, BLUE, YELLOW, GRAY}

    public int getxCord() {
        return xCord;
    }

    public void setxCord(int xCord) {
        this.xCord = xCord;
    }

    public int getyCord() {
        return yCord;
    }

    public void setyCord(int yCord) {
        this.yCord = yCord;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
