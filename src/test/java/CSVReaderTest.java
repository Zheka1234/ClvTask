import org.junit.jupiter.api.Test;
import ru.clevertec.check.model.DiscountCard;
import ru.clevertec.check.model.Product;
import ru.clevertec.check.util.CSVReader;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CSVReaderTest {

    private final String dbUrl = "jdbc:postgresql://localhost:5432/check";
    private final String dbUsername = "postgres";
    private final String dbPassword = "postgres";

    @Test
    void testReadProducts() {
        Map<Integer, Product> products = CSVReader.readProducts(dbUrl, dbUsername, dbPassword);
        assertNotNull(products);
        assertFalse(products.isEmpty());
    }

    @Test
    void testReadDiscountCards() {
        Map<String, DiscountCard> discountCards = CSVReader.readDiscountCards(dbUrl, dbUsername, dbPassword);
        assertNotNull(discountCards);
        assertFalse(discountCards.isEmpty());
    }
}

