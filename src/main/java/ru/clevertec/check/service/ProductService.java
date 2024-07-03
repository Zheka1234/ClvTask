package ru.clevertec.check.service;

import ru.clevertec.check.exception.InsufficientStockException;
import ru.clevertec.check.exception.ProductNotFoundException;
import ru.clevertec.check.model.Product;

import java.util.Map;

public class ProductService implements IProductService {
    private Map<Integer, Product> products;

    public ProductService(Map<Integer, Product> products) {
        this.products = products;
    }

    @Override
    public Product getProductById(int id) throws ProductNotFoundException {
        if (!products.containsKey(id)) {
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }
        return products.get(id);
    }

    @Override
    public void checkProductStock(int id, int quantity) throws ProductNotFoundException, InsufficientStockException {
        Product product = getProductById(id);
        if (product.getQuantityInStock() < quantity) {
            throw new InsufficientStockException("Insufficient stock for product: " + product.getName());
        }
    }

    @Override
    public void reduceProductStock(int id, int quantity) throws ProductNotFoundException, InsufficientStockException {
        Product product = getProductById(id);
        checkProductStock(id, quantity);
        product.setQuantityInStock(product.getQuantityInStock() - quantity);
    }


}