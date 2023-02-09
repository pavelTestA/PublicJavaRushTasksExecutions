package com.javarush.task.task24.BankomatSimulator;

import java.util.*;

public class CurrencyManipulatorFactory {
    private static Map<String, CurrencyManipulator> map = new HashMap<>();
    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode){
        if(map.containsKey(currencyCode.toLowerCase())){
            return map.get(currencyCode.toLowerCase());
        }else {
            CurrencyManipulator currencyManipulator = new CurrencyManipulator(currencyCode);
            map.put(currencyCode.toLowerCase(), currencyManipulator);
            return currencyManipulator;
        }
    }

    private CurrencyManipulatorFactory() {
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators(){
        List<CurrencyManipulator> currencyManipulators = new ArrayList<>();
        for (String str: map.keySet()){
            currencyManipulators.add(map.get(str));
        }
        return currencyManipulators;
    }
}
