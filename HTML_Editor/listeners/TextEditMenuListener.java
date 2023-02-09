package com.javarush.task.task24.HTML_Editor.listeners;

import com.javarush.task.task24.HTML_Editor.View;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;

public class TextEditMenuListener implements MenuListener {
    private View view;
    public TextEditMenuListener(View view) {
        this.view = view;
    }



    @Override
    public void menuSelected(MenuEvent menuEvent) {
        Component[] components = ((JMenu)menuEvent.getSource()).getMenuComponents();
        for (int i = 0; i < components.length; i++) {
            components[i].setEnabled(view.isHtmlTabSelected());
        }
    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }
}
