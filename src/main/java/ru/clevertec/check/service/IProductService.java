package ru.clevertec.check.service;


import ru.clevertec.check.exception.InsufficientStockException;
import ru.clevertec.check.exception.ProductNotFoundException;
import ru.clevertec.check.model.Product;


public interface IProductService {
    Product getProductById(int id) throws ProductNotFoundException;

    void checkProductStock(int id, int quantity) throws ProductNotFoundException, InsufficientStockException;

    void reduceProductStock(int id, int quantity) throws ProductNotFoundException, InsufficientStockException;


}
