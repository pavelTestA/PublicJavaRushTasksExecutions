package com.javarush.task.task24.SokobanGame.SokobanGame.controller;


import com.javarush.task.task24.SokobanGame.SokobanGame.model.Direction;
import com.javarush.task.task24.SokobanGame.SokobanGame.model.GameObjects;
import com.javarush.task.task24.SokobanGame.SokobanGame.view.View;
import com.javarush.task.task24.SokobanGame.SokobanGame.model.Model;


public class Controller implements EventListener{

    public static void main(String[] args) {
        Controller controller = new Controller();

    }


    private View view;
    private Model model;

    public Controller() {
        view = new View(this);
        this.model = new Model();
        model.restart();
        view.init();

        model.setEventListener(this);
        view.setEventListener(this);


    }

    public GameObjects getGameObjects(){
        return model.getGameObjects();
    }

    @Override
    public void move(Direction direction) {
        model.move(direction);
        view.update();
    }

    @Override
    public void restart() {
        model.restart();
        view.update();
    }

    @Override
    public void startNextLevel() {
        model.startNextLevel();
        view.update();
    }

    @Override
    public void levelCompleted(int level) {
        view.completed(level);
    }
}
