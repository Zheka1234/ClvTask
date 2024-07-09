import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.check.exception.InvalidCardException;
import ru.clevertec.check.model.DiscountCard;
import ru.clevertec.check.service.DiscountService;

import java.util.HashMap;
import java.util.Map;

public class DiscountServiceTest {

    private DiscountService discountService;
    private Map<String, DiscountCard> discountCards;

    @BeforeEach
    public void setUp() {
        discountCards = new HashMap<>();
        DiscountCard card1 = new DiscountCard("1234567890", 0.1);
        discountCards.put("1234567890", card1);
        discountService = new DiscountService(discountCards);
    }

    @Test
    public void testGetDiscountCard_existingCardNumber_shouldReturnCard() throws InvalidCardException {
        String cardNumber = "1234567890";
        DiscountCard retrievedCard = discountService.getDiscountCard(cardNumber);
        Assertions.assertEquals(cardNumber, retrievedCard.getNumber());
    }

    @Test
    public void testGetDiscountCard_nonExistingCardNumber_shouldThrowInvalidCardException() {
        String nonExistingCardNumber = "0987654321";
        Assertions.assertThrows(InvalidCardException.class, () -> discountService.getDiscountCard(nonExistingCardNumber));
    }
}

