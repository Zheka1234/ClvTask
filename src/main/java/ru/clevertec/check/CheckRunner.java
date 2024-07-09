package ru.clevertec.check;

import ru.clevertec.check.service.ArgumentProcessor;

public class CheckRunner {
    public static void main(String[] args) {

        ArgumentProcessor.createAndProcess(args);


    }
}

//java -cp out ru.clevertec.check.CheckRunner 1-3 2-5 3-1 discountCard=1111 balanceDebitCard=100.00

//java -cp out ru.clevertec.check.CheckRunner 1-3 2-5 3-1 discountCard=1111 balanceDebitCard=100.00 pathToFile=./src/main/resources/products.csv saveToFile=./result.csv

//java -cp out ru.clevertec.check.CheckRunner 1-3 2-5 3-1 discountCard=1111 balanceDebitCard=100.00  saveToFile=./result.csv