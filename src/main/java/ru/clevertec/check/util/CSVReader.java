package ru.clevertec.check.util;


import ru.clevertec.check.model.DiscountCard;
import ru.clevertec.check.model.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class CSVReader {
    public static Map<Integer, Product> readProducts(String dbUrl, String dbUsername, String dbPassword) {
        Map<Integer, Product> products = new HashMap<>();

        try (DBReader dbReader = new DBReader(dbUrl, dbUsername, dbPassword)) {
            Connection connection = dbReader.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM public.product");

            while (rs.next()) {
                int id = rs.getInt("id");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                boolean isWholesale = rs.getBoolean("wholesale_product");
                int quantityInStock = rs.getInt("quantity_in_stock");
                products.put(id, new Product(id, description, price, isWholesale, quantityInStock));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    public static Map<String, DiscountCard> readDiscountCards(String dbUrl, String dbUsername, String dbPassword) {
        Map<String, DiscountCard> discountCards = new HashMap<>();

        try (DBReader dbReader = new DBReader(dbUrl, dbUsername, dbPassword)) {
            Connection connection = dbReader.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM public.discount_card");

            while (rs.next()) {
                String number = rs.getString("number");
                double discountPercentage = rs.getDouble("amount");
                discountCards.put(number, new DiscountCard(number, discountPercentage));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return discountCards;
    }
}