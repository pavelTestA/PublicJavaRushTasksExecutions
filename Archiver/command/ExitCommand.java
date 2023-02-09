package com.javarush.task.task24.Archiver.command;

import com.javarush.task.task24.Archiver.ConsoleHelper;

public class ExitCommand implements Command {
    @Override
    public void execute() throws Exception {
        ConsoleHelper.writeMessage("До встречи!");
    }
}
