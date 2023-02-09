package com.javarush.task.task24.HTML_Editor;

import com.javarush.task.task24.HTML_Editor.listeners.UndoListener;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

public class Controller {

    private View view;
    private HTMLDocument document;
    private File currentFile;

    public Controller(View view) {
        this.view = view;
    }

    public void resetDocument(){
        UndoListener undoListener = view.getUndoListener();
        if(document != null){
            document.removeUndoableEditListener(undoListener);
        }
        document = (HTMLDocument)new HTMLEditorKit().createDefaultDocument();
        document.addUndoableEditListener(undoListener);
        view.update();
    }

    public void createNewDocument(){
        view.selectHtmlTab();
        resetDocument();
        view.setTitle("HTML редактор");
        currentFile = null;

    }
    public void openDocument(){
        view.selectHtmlTab();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new HTMLFileFilter());
        if((fileChooser.showOpenDialog(view) == JFileChooser.APPROVE_OPTION)){
            currentFile = fileChooser.getSelectedFile();
            resetDocument();
            view.setTitle(currentFile.getName());
            try {
            FileReader reader = new FileReader(currentFile);
            new HTMLEditorKit().read(reader, document, 0);
            view.resetUndo();
            } catch (IOException | BadLocationException e){
                ExceptionHandler.log(e);
            }
        }
    }
    public void saveDocument(){
        view.selectHtmlTab();
        if(currentFile == null){
            saveDocumentAs();
        } else{
            view.setTitle(currentFile.getName());
            try {
                FileWriter fileWriter = new FileWriter(currentFile);
                new HTMLEditorKit().write(fileWriter, document, 0, document.getLength());
            } catch (IOException | BadLocationException e){
                ExceptionHandler.log(e);
            }
        }
    }
    public void saveDocumentAs(){
        view.selectHtmlTab();
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new HTMLFileFilter());

        if (jFileChooser.showSaveDialog(view) == JFileChooser.APPROVE_OPTION) {
            currentFile = jFileChooser.getSelectedFile();
            view.setTitle(currentFile.getName());
            try {
                FileWriter fileWriter = new FileWriter(currentFile);
                new HTMLEditorKit().write(fileWriter, document, 0, document.getLength());
            }catch (IOException | BadLocationException e){
                ExceptionHandler.log(e);
            }
        }
    }

    public void setPlainText(String text){
        resetDocument();
        StringReader reader = new StringReader(text);
        try {

            new HTMLEditorKit().read(reader, document,document.getLength());
        }catch (IOException| BadLocationException e){
            ExceptionHandler.log(e);
        }
    }

    public String getPlainText(){
        StringWriter stringWriter = new StringWriter();
        try {

            new HTMLEditorKit().write(stringWriter, document, 0, document.getLength());
        } catch (IOException | BadLocationException e){
            ExceptionHandler.log(e);
        }
        return stringWriter.toString();
    }

    public void init(){
        createNewDocument();
    }
    public void exit(){
        System.exit(0);
    }

    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();
    }

    public HTMLDocument getDocument() {
        return document;
    }
}
