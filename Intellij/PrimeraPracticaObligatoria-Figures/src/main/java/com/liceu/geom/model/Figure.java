package com.liceu.geom.model;

public class Figure {
    private int userId;
    private int figureId;
    private int xCord;
    private int yCord;
    private int size;
    private String name;
    private String shape;
    private String color;

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFigureId() {
        return figureId;
    }

    public void setFigureId(int figureId) {
        this.figureId = figureId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    @Override
    public String toString() {
        return "Figure{" +
                "userId=" + userId +
                ", figureId=" + figureId +
                ", xCord=" + xCord +
                ", yCord=" + yCord +
                ", size=" + size +
                ", name='" + name + '\'' +
                ", shape='" + shape + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
