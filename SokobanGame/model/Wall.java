package com.javarush.task.task34.SokobanGame.model;

import java.awt.*;

public class Wall extends CollisionObject{
    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.BLUE);
        graphics.fillRect(getX()-getWidth()/2, getY() - getHeight()/2, getWidth(), getHeight());
    }
}
