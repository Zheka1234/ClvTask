package ru.clevertec.check.service;

import ru.clevertec.check.exception.InvalidCardException;
import ru.clevertec.check.model.DiscountCard;

import java.util.Map;

public class DiscountService implements IDiscountService {
    private Map<String, DiscountCard> discountCards;

    public DiscountService(Map<String, DiscountCard> discountCards) {
        this.discountCards = discountCards;
    }

    @Override
    public DiscountCard getDiscountCard(String number) throws InvalidCardException {
        if (!discountCards.containsKey(number)) {
            throw new InvalidCardException("Invalid discount card number: " + number);
        }
        return discountCards.get(number);
    }


}
