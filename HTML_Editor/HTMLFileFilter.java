package com.javarush.task.task24.HTML_Editor;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class HTMLFileFilter extends FileFilter {
    @Override
    public boolean accept(File f) {
        if (f.isDirectory() || f.getName().toLowerCase().endsWith(".html") || f.getName().toLowerCase().endsWith(".htm"))
            return true;

        else return false;
    }

    @Override
    public String getDescription() {
        return "HTML и HTM файлы";
    }
}

