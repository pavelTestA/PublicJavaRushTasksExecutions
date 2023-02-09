package com.javarush.task.task24.SokobanGame.SokobanGame.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class LevelLoader {
    private Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level) {
        if (level > getMaxLevel()) level = level % getMaxLevel();
        StringBuilder levelInfoSB = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(levels.toFile()))) {
            while (reader.ready()) {
                levelInfoSB.append(reader.readLine() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String levelsInfo = levelInfoSB.toString();

        int beginIndex = levelsInfo.indexOf(String.format("Maze: %d", level));
        int firstIndex = levelsInfo.indexOf("\n\n", beginIndex);
        int endIndex = levelsInfo.indexOf("******", beginIndex);
        String levelBody = levelsInfo.substring(firstIndex, endIndex);
        while (true){
            if (levelBody.charAt(0)=='\n'){
                levelBody = levelBody.substring(1);
            } else break;
        }
        while (true){
            if (levelBody.charAt(levelBody.length()-1)=='\n'){
                levelBody = levelBody.substring(0,levelBody.length()-1);
            } else break;
        }
        String[] levelBodyString = levelBody.split("\n");

        int x0 = Model.FIELD_CELL_SIZE/2;
        int y0 = Model.FIELD_CELL_SIZE/2;

        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = new Player(-1,-1);

        for (int j = 0; j < levelBodyString.length; j++) {
                String newLine = levelBodyString[j];
                if(newLine!=null) {
                    for (int i = 0; i < newLine.length(); i++) {
                        int y = y0 + Model.FIELD_CELL_SIZE * j;
                        int x = x0 + Model.FIELD_CELL_SIZE * i;
                        char charAt = newLine.charAt(i);
                        switch (charAt) {
                            case 'X':
                                walls.add(new Wall(x, y));
                                break;
                            case '*':
                                boxes.add(new Box(x, y));
                                break;
                            case '.':
                                homes.add(new Home(x, y));
                                break;
                            case '&':
                                boxes.add(new Box(x, y));
                                homes.add(new Home(x, y));
                                break;
                            case '@':
                                player = new Player(x, y);
                                break;
                        }
                    }
                }

            }
        GameObjects newLevelGameObjects = new GameObjects(walls, boxes, homes, player);
        return newLevelGameObjects;
    }

    public int getMaxLevel() {
        int amountOflevels = 1;
        try (BufferedReader reader = new BufferedReader(new FileReader(levels.toFile()))) {
            while (reader.ready()) {
                String str = reader.readLine();
                if (str.contains("Maze: ")) {
                    if (amountOflevels < Integer.parseInt(str.substring(str.indexOf(" ")).trim())) {
                        amountOflevels = Integer.parseInt(str.substring(str.indexOf(" ")).trim());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return amountOflevels;
    }
}
