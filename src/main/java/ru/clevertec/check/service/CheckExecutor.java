package ru.clevertec.check.service;

import ru.clevertec.check.util.CSVReader;
import ru.clevertec.check.util.CheckPrinter;
import ru.clevertec.check.exception.InsufficientBalanceException;
import ru.clevertec.check.exception.InsufficientStockException;
import ru.clevertec.check.exception.ProductNotFoundException;
import ru.clevertec.check.model.DiscountCard;
import ru.clevertec.check.model.Product;

import java.util.HashMap;
import java.util.Map;

public class CheckExecutor implements ICheckExecutorInterface {

    @Override
    public void executeCheck(Map<Integer, Integer> productQuantities, DiscountCard discountCard, double balance) {

        IProductService productService = new ProductService(CSVReader.readProducts("./src/main/resources/products.csv"));
        Map<Product, Integer> purchasedProducts = new HashMap<>();
        double total = 0.0;

        try {

            for (Map.Entry<Integer, Integer> entry : productQuantities.entrySet()) {
                int productId = entry.getKey();
                int quantity = entry.getValue();
                productService.checkProductStock(productId, quantity);
                Product product = productService.getProductById(productId);
                productService.reduceProductStock(productId, quantity);

                double productTotal = product.getPrice() * quantity;
                if (product.isWholesale() && quantity >= 5) {
                    productTotal *= 0.9;
                } else if (discountCard != null) {
                    productTotal *= (1 - discountCard.getDiscountPercentage() / 100);
                }

                purchasedProducts.put(product, quantity);
                total += productTotal;
            }

            if (balance < total) {
                throw new InsufficientBalanceException("Недостаточно средств на дебетовой карте");
            }

            // Вывод чека с использованием CheckPrinter
            CheckPrinter.printCheck(purchasedProducts, discountCard, total, balance - total);
        } catch (ProductNotFoundException | InsufficientStockException | InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }
    }
}