package com.javarush.task.task24.SokobanGame.controller;

import com.javarush.task.task24.SokobanGame.model.Direction;

public interface EventListener {
    void move(Direction direction);
    void restart();
    void startNextLevel();
    void levelCompleted(int level);

}
