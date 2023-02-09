package com.javarush.task.task26.BankomatSimulator.command;

import com.javarush.task.task26.BankomatSimulator.CashMachine;
import com.javarush.task.task26.BankomatSimulator.ConsoleHelper;
import com.javarush.task.task26.BankomatSimulator.CurrencyManipulator;
import com.javarush.task.task26.BankomatSimulator.CurrencyManipulatorFactory;
import com.javarush.task.task26.BankomatSimulator.exception.InterruptOperationException;
import com.javarush.task.task26.BankomatSimulator.exception.NotEnoughMoneyException;

import java.util.*;

class WithdrawCommand implements Command{
    private ResourceBundle res =  ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + ".withdraw");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));

        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);

        while (true) {
            try {
                ConsoleHelper.writeMessage(res.getString("specify.amount"));
                String s = ConsoleHelper.readString();
                if (s == null) {
                    ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                } else {
                    try {
                        int amount = Integer.parseInt(s);
                        boolean isAmountAvailable = manipulator.isAmountAvailable(amount);
                        if (isAmountAvailable) {
                            Map<Integer, Integer> denominations = manipulator.withdrawAmount(amount);
                            for (Integer item : denominations.keySet()) {
                                ConsoleHelper.writeMessage("\t" + item + " - " + denominations.get(item));
                            }

                            ConsoleHelper.writeMessage(String.format(res.getString("success.format"), amount, currencyCode));
                            break;
                        } else {
                            ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                        }
                    } catch (NumberFormatException e) {
                        ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                    }
                }
            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
            }
        }
    }
}
