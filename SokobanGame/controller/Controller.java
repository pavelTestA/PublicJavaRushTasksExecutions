package com.javarush.task.task34.SokobanGame.controller;


import com.javarush.task.task34.SokobanGame.model.Direction;
import com.javarush.task.task34.SokobanGame.model.GameObjects;
import com.javarush.task.task34.SokobanGame.model.Model;
import com.javarush.task.task34.SokobanGame.view.View;


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
