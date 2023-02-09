package com.javarush.task.task31.Archiver;

public enum Operation {


    CREATE("упаковать файл в архив"),
    ADD("добавить файл в архив"),
    REMOVE("удалить файл из архива"),
    EXTRACT("распаковать архив"),
    CONTENT("просмотреть содержимое архива"),
    EXIT("выход");

    String opisanie;
    Operation(String opisanie){
        this.opisanie = opisanie;
    }
}
