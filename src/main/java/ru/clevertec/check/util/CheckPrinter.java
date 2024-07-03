package ru.clevertec.check.util;

import ru.clevertec.check.model.DiscountCard;
import ru.clevertec.check.model.Product;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class CheckPrinter {
    public static void printCheck(Map<Product, Integer> products, DiscountCard discountCard, double total, double balance) {
        StringBuilder check = new StringBuilder();


        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy;HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        check.append("Date;Time\n")
                .append(dtf.format(now))
                .append("\n\n");


        check.append("QTY;DESCRIPTION;PRICE;DISCOUNT;TOTAL\n");

        double totalBeforeDiscount = 0.0;
        double totalDiscount = 0.0;

        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            double productTotal = product.getPrice() * quantity;
            totalBeforeDiscount += productTotal;

            double discount = 0.0;
            if (product.isWholesale() && quantity >= 5) {
                discount = productTotal * 0.1;
            } else if (discountCard != null) {
                discount = productTotal * discountCard.getDiscountPercentage() / 100;
            }
            totalDiscount += discount;
            double totalWithDiscount = productTotal - discount;

            check.append(quantity)
                    .append(";")
                    .append(product.getName())
                    .append(";")
                    .append(String.format("%.2f$", product.getPrice()))
                    .append(";")
                    .append(String.format("%.2f$", discount))
                    .append(";")
                    .append(String.format("%.2f$", totalWithDiscount))
                    .append("\n");
        }

        check.append("\nDISCOUNT CARD;DISCOUNT PERCENTAGE\n")
                .append(discountCard != null ? discountCard.getNumber() : "None")
                .append(";")
                .append(discountCard != null ? String.format("%.0f%%", discountCard.getDiscountPercentage()) : "0%")
                .append("\n\n");

        check.append("TOTAL PRICE;TOTAL DISCOUNT;TOTAL WITH DISCOUNT\n")
                .append(String.format("%.2f$", totalBeforeDiscount))
                .append(";")
                .append(String.format("%.2f$", totalDiscount))
                .append(";")
                .append(String.format("%.2f$", total))
                .append("\n");

        try (FileWriter writer = new FileWriter("result.csv")) {
            writer.write(check.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(check.toString());
    }
}