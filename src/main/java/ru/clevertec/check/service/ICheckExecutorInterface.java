package ru.clevertec.check.service;

import ru.clevertec.check.model.DiscountCard;

import java.util.Map;

public interface ICheckExecutorInterface {
    void executeCheck(Map<Integer, Integer> productQuantities, DiscountCard discountCard, double balance);
}