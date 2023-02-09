package com.javarush.task.task24.HTML_Editor.actions;

import com.javarush.task.task24.HTML_Editor.View;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class UndoAction extends AbstractAction {
    private View view;
    public UndoAction(View view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        view.undo();
    }
}
