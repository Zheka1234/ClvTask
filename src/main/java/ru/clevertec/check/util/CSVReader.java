package ru.clevertec.check.util;

import ru.clevertec.check.model.DiscountCard;
import ru.clevertec.check.model.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CSVReader {
    public static Map<Integer, Product> readProducts(String csvFilePath) {
        Map<Integer, Product> products = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int id = Integer.parseInt(values[0]);
                String name = values[1];
                double price = Double.parseDouble(values[2]);
                boolean isWholesale = Boolean.parseBoolean(values[3]);
                int quantityInStock = Integer.parseInt(values[4]);
                products.put(id, new Product(id, name, price, isWholesale, quantityInStock));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    public static Map<String, DiscountCard> readDiscountCards(String csvFilePath) {
        Map<String, DiscountCard> discountCards = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String number = values[0];
                double discountPercentage = Double.parseDouble(values[1]);
                discountCards.put(number, new DiscountCard(number, discountPercentage));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return discountCards;
    }
}