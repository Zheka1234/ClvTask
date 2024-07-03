package ru.clevertec.check.service;

import ru.clevertec.check.exception.InvalidCardException;
import ru.clevertec.check.model.DiscountCard;


public interface IDiscountService {
    DiscountCard getDiscountCard(String number) throws InvalidCardException;


}