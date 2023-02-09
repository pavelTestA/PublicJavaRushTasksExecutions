package com.javarush.task.task24.BankomatSimulator.command;

import com.javarush.task.task24.BankomatSimulator.exception.InterruptOperationException;

interface Command {
    void execute() throws InterruptOperationException;
}
