package com.javarush.task.task26.BankomatSimulator;

import com.javarush.task.task26.BankomatSimulator.command.CommandExecutor;
import com.javarush.task.task26.BankomatSimulator.exception.InterruptOperationException;

import java.util.*;


public class CashMachine {
    public static final String RESOURCE_PATH = CashMachine.class.getPackage().getName() + ".resources.";

    public static void main(String[] args) {

        System.out.println(CashMachine.class.getPackage().getName() + ".resources.verifiedCards");
        try {
            Locale.setDefault(Locale.ENGLISH);
            CommandExecutor.execute(Operation.LOGIN);
            Operation operation;
            do {
                operation = ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            } while (operation != Operation.EXIT);
        } catch (InterruptOperationException e){
            ConsoleHelper.printExitMessage();
        }
    }
}
