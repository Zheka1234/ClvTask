import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.check.model.DiscountCard;
import ru.clevertec.check.service.CheckExecutor;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckExecutorTest {
    private CheckExecutor checkExecutor;

    @BeforeEach
    void setUp() {
        checkExecutor = new CheckExecutor();
    }

    @Test
    void testExecuteCheck() {
        Map<Integer, Integer> productQuantities = new HashMap<>();
        productQuantities.put(1, 2);
        productQuantities.put(2, 1);

        DiscountCard discountCard = new DiscountCard("1234", 10.0);
        double balance = 1000.0;
        String dbUrl = "jdbc:postgresql://localhost:5432/check";
        String dbUsername = "postgres";
        String dbPassword = "postgres";
        String saveToFile = "result.csv";

        checkExecutor.executeCheck(productQuantities, discountCard, balance, dbUrl, dbUsername, dbPassword, saveToFile);

        File file = new File(saveToFile);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);

        file.delete();
    }
}