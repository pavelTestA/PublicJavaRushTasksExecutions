package com.javarush.task.task26.BankomatSimulator;

import com.javarush.task.task26.BankomatSimulator.exception.NotEnoughMoneyException;

import java.util.*;

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations = new HashMap<>();

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        int newCount = count;
        if (denominations.containsKey(denomination)) {
            newCount = denominations.get(denomination) + count;
        }
        denominations.put(denomination, newCount);
    }

    public int getTotalAmount() {
        int totalAmount = 0;
        for (int denomination : denominations.keySet()) {
            totalAmount += denomination * denominations.get(denomination);
        }
        return totalAmount;
    }

    public boolean hasMoney() {
        return !denominations.isEmpty();
    }

    public boolean isAmountAvailable(int expectedAmount) {
        boolean isAmountAvailable = getTotalAmount() >= expectedAmount ? true : false;
        return isAmountAvailable;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        Map<Integer, Integer> usedBanknots = new HashMap<>();
        ArrayList<Integer> sortedNominals = new ArrayList(denominations.keySet());
        Collections.sort(sortedNominals, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        ConsoleHelper.writeMessage(sortedNominals.toString());

        int currentAmount = 0;
        int skipNominal;
        Outter:
        for (skipNominal = 0; skipNominal < sortedNominals.size(); skipNominal++) {
            usedBanknots = new HashMap<>();
            currentAmount = 0;
            for (int nominal = skipNominal; nominal < sortedNominals.size(); nominal++) {
                int usedBanknot = 0;
                for (int i = 1; i <= denominations.get(sortedNominals.get(nominal)); i++) {
                    if (expectedAmount - currentAmount >= sortedNominals.get(nominal) * i) {
                        usedBanknot = i;
                    } else {
                        break;
                    }

                }

                if (usedBanknot > 0) {
                    currentAmount += sortedNominals.get(nominal) * usedBanknot;
                    usedBanknots.put(sortedNominals.get(nominal), usedBanknot);
                    if (currentAmount == expectedAmount) break Outter;
                }

            }
        }
        if (currentAmount == expectedAmount) {
            for (int nominal : usedBanknots.keySet()) {
                int newBanknotCount = denominations.get(nominal) - usedBanknots.get(nominal);
                if (newBanknotCount == 0) {
                    denominations.remove(nominal);
                } else {
                    denominations.put(nominal, newBanknotCount);
                }
            }
            return usedBanknots;
        } else {
            throw new NotEnoughMoneyException();
        }
    }
}
