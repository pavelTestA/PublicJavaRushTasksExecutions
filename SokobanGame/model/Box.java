package com.javarush.task.task24.SokobanGame.model;

import java.awt.*;

public class Box extends CollisionObject implements Movable{
    public Box(int x, int y) {
        super(x, y);
    }

    @Override
    public void move(int x, int y) {
        this.setX(this.getX()+x);
        this.setY(this.getY()+y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.ORANGE);
        graphics.drawRect(getX()-getWidth()/2, getY()-getHeight()/2, getWidth(), getHeight());
        graphics.drawLine(getX()-getWidth()/2, getY()-getHeight()/2, getX()+getWidth()/2, getY()+getHeight()/2);
        graphics.drawLine(getX()-getWidth()/2, getY()+getHeight()/2, getX()+getWidth()/2, getY()-getHeight()/2);
    }
}
