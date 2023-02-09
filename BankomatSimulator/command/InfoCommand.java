package com.javarush.task.task26.BankomatSimulator.command;

import com.javarush.task.task26.BankomatSimulator.CashMachine;
import com.javarush.task.task26.BankomatSimulator.ConsoleHelper;
import com.javarush.task.task26.BankomatSimulator.CurrencyManipulator;
import com.javarush.task.task26.BankomatSimulator.CurrencyManipulatorFactory;

import java.util.Collection;
import java.util.ResourceBundle;

class InfoCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + ".info");

    @Override
    public void execute() {
        ConsoleHelper.writeMessage(res.getString("before"));
        boolean hasMoney = false;

        Collection<CurrencyManipulator> currencyManipulators = CurrencyManipulatorFactory.getAllCurrencyManipulators();

        for (CurrencyManipulator currencyManipulator : currencyManipulators) {
            if (currencyManipulator.hasMoney()) {
                hasMoney = true;
                System.out.printf("\t%s - %d\n", currencyManipulator.getCurrencyCode(), currencyManipulator.getTotalAmount());
            }
        }
        if (!hasMoney) ConsoleHelper.writeMessage(res.getString("no.money"));
    }
}
