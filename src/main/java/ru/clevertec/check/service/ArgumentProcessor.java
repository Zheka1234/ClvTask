package ru.clevertec.check.service;

import ru.clevertec.check.util.CSVReader;
import ru.clevertec.check.exception.InvalidCardException;
import ru.clevertec.check.model.DiscountCard;

import java.util.HashMap;
import java.util.Map;



    public class ArgumentProcessor implements IArgumentProcessorInterface {

        public static ArgumentProcessor createAndProcess(String[] args) {
            ArgumentProcessor argumentProcessor = new ArgumentProcessor();
            argumentProcessor.processArguments(args);
            return argumentProcessor;
        }

        @Override
        public void processArguments(String[] args) {

            if (args.length < 3) {
                System.out.println("Использование: java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java id-quantity discountCard=xxxx balanceDebitCard=xxxx");
                return;
            }

            Map<Integer, Integer> productQuantities = new HashMap<>();
            DiscountCard discountCard = null;
            double balance = 0.0;

            for (String arg : args) {

                if (arg.startsWith("discountCard=")) {
                    String cardNumber = arg.split("=")[1];

                    IDiscountService discountService = new DiscountService(CSVReader.readDiscountCards("./src/main/resources/discountCards.csv"));
                    try {
                        discountCard = discountService.getDiscountCard(cardNumber);
                    } catch (InvalidCardException e) {
                        System.out.println(e.getMessage());
                        return;
                    }
                } else if (arg.startsWith("balanceDebitCard=")) {
                    balance = Double.parseDouble(arg.split("=")[1]);
                } else {

                    String[] parts = arg.split("-");
                    int id = Integer.parseInt(parts[0]);
                    int quantity = Integer.parseInt(parts[1]);
                    productQuantities.put(id, productQuantities.getOrDefault(id, 0) + quantity);
                }
            }


            ICheckExecutorInterface checkExecutor = new CheckExecutor();
            checkExecutor.executeCheck(productQuantities, discountCard, balance);
        }
    }

