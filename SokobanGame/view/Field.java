package com.javarush.task.task24.SokobanGame.view;

import com.javarush.task.task24.SokobanGame.controller.EventListener;
import com.javarush.task.task24.SokobanGame.model.Direction;
import com.javarush.task.task24.SokobanGame.model.GameObject;
import com.javarush.task.task24.SokobanGame.model.GameObjects;
import com.javarush.task.task34.SokobanGame.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Field extends JPanel {
    private EventListener eventListener;
    private View view;

    public Field(View view) {

        this.view = view;
        KeyHandler handler = new KeyHandler();
        addKeyListener(handler);
        setFocusable(true);
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, view.getWidth(), view.getWidth());
        GameObjects gameObjects = view.getGameObjects();
        if (gameObjects != null) {
            for (GameObject gameObject : gameObjects.getAll()) {
                gameObject.draw(g);
            }
        }

    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                eventListener.move(Direction.LEFT);
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                eventListener.move(Direction.RIGHT);
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                eventListener.move(Direction.UP);
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                eventListener.move(Direction.DOWN);
            } else if (e.getKeyCode() == KeyEvent.VK_R) {
                eventListener.restart();
            }
        }
    }
}
