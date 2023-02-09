package com.javarush.task.task24.HTML_Editor;

import com.javarush.task.task24.HTML_Editor.listeners.FrameListener;
import com.javarush.task.task24.HTML_Editor.listeners.TabbedPaneChangeListener;
import com.javarush.task.task24.HTML_Editor.listeners.UndoListener;

import javax.swing.*;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener {
    private UndoManager undoManager = new UndoManager();
    private UndoListener undoListener = new UndoListener(undoManager);
    private Controller controller;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTextPane htmlTextPane = new JTextPane();
    private JEditorPane plainTextPane = new JEditorPane();

    public boolean isHtmlTabSelected(){

        return tabbedPane.getSelectedIndex()==0;
    }

    public void selectHtmlTab(){
        tabbedPane.setSelectedIndex(0);
        resetUndo();
    }

    public void update(){
        htmlTextPane.setDocument(controller.getDocument());
    }

    public void showAbout(){
        JOptionPane.showMessageDialog(this, "Нечто", "О программе", JOptionPane.INFORMATION_MESSAGE);

    }

    public View() throws HeadlessException {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    public void undo(){
        try {
            undoManager.undo();
        } catch (CannotUndoException e){
            ExceptionHandler.log(e);
        }


    }

    public void redo(){
        try {
            undoManager.redo();
        } catch (CannotRedoException e){
            ExceptionHandler.log(e);
        }
    }

    public void resetUndo(){
        undoManager.discardAllEdits();
    }

    public boolean canUndo(){
        return undoManager.canUndo();
    }

    public boolean canRedo(){
        return undoManager.canRedo();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String command = actionEvent.getActionCommand();
        switch (command){
            case "Новый": controller.createNewDocument(); break;
            case "Открыть": controller.openDocument(); break;
            case "Сохранить": controller.saveDocument(); break;
            case "Сохранить как...": controller.saveDocumentAs(); break;
            case "Выход": controller.exit(); break;
            case "О программе": showAbout();
        }
    }
    public void selectedTabChanged(){
        switch (tabbedPane.getSelectedIndex()){
            case 0: controller.setPlainText(plainTextPane.getText());break;
            case 1: plainTextPane.setText(controller.getPlainText());
        }
        resetUndo();
    }

    public UndoListener getUndoListener() {
        return undoListener;
    }

    public UndoManager getUndoManager() {
        return undoManager;
    }

    public void initMenuBar(){
        JMenuBar jMenuBar = new JMenuBar();
        MenuHelper.initFileMenu(this, jMenuBar);
        MenuHelper.initEditMenu(this, jMenuBar);
        MenuHelper.initStyleMenu(this, jMenuBar);
        MenuHelper.initAlignMenu(this, jMenuBar);
        MenuHelper.initColorMenu(this, jMenuBar);
        MenuHelper.initFontMenu(this, jMenuBar);
        MenuHelper.initHelpMenu(this, jMenuBar);
        getContentPane().add(jMenuBar, BorderLayout.NORTH);
    }

    public void initEditor(){
        htmlTextPane.setContentType("text/html");
        JScrollPane jScrollPanehtml = new JScrollPane(htmlTextPane);
        tabbedPane.add("Текст", jScrollPanehtml);
        JScrollPane jScrollPanePlain = new JScrollPane(plainTextPane);
        tabbedPane.add("HTML", jScrollPanePlain);
        tabbedPane.setPreferredSize(new Dimension(200,200));
        tabbedPane.addChangeListener(new TabbedPaneChangeListener(this));
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }

    public void initGui(){
        initMenuBar();
        initEditor();
        pack();
    }

    public void init(){
        initGui();
        addWindowListener(new FrameListener(this));
        setVisible(true);
    }

    public void exit(){
        controller.exit();
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
