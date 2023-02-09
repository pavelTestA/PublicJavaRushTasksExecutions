package com.javarush.task.task26.BankomatSimulator.command;

import com.javarush.task.task26.BankomatSimulator.CashMachine;
import com.javarush.task.task26.BankomatSimulator.ConsoleHelper;
import com.javarush.task.task26.BankomatSimulator.exception.InterruptOperationException;

import java.util.ResourceBundle;

class ExitCommand implements Command{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + ".exit");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("exit.question.y.n"));
        String result = ConsoleHelper.readString();
        if(result!=null && result.toLowerCase().equals("y")){
            ConsoleHelper.writeMessage(res.getString("thank.message"));
        }


    }
}
