package com.javarush.task.task26.BankomatSimulator.command;

import com.javarush.task.task26.BankomatSimulator.exception.InterruptOperationException;

interface Command {
    void execute() throws InterruptOperationException;
}
