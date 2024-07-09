package ru.clevertec.check.service;

import ru.clevertec.check.model.DiscountCard;
import ru.clevertec.check.model.Product;
import ru.clevertec.check.util.CSVReader;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class CheckExecutor implements ICheckExecutorInterface {
    @Override
    public void executeCheck(Map<Integer, Integer> productQuantities, DiscountCard discountCard, double balance, String dbUrl, String dbUsername, String dbPassword, String saveToFile) {
        Map<Integer, Product> products = CSVReader.readProducts(dbUrl, dbUsername, dbPassword);

        double totalCost = 0.0;
        for (Map.Entry<Integer, Integer> entry : productQuantities.entrySet()) {
            int productId = entry.getKey();
            int quantity = entry.getValue();

            if (products.containsKey(productId)) {
                Product product = products.get(productId);
                totalCost += product.getPrice() * quantity;
            } else {
                System.out.println("Product ID " + productId + " not found in the database.");
                return;
            }
        }

        if (discountCard != null) {
            totalCost *= (1 - discountCard.getDiscountPercentage() / 100);
        }

        if (balance < totalCost) {
            System.out.println("Insufficient balance.");
            return;
        }

        if (saveToFile != null) {
            try (FileWriter writer = new FileWriter(saveToFile)) {
                writer.write("Total cost: " + totalCost);
                System.out.println("Check saved to " + saveToFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Total cost: " + totalCost);
        }
    }
}