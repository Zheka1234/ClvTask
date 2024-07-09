import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.check.service.ArgumentProcessor;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class ArgumentProcessorTest {

    private ArgumentProcessor argumentProcessor;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        argumentProcessor = new ArgumentProcessor();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testProcessArguments_validArguments_shouldExecuteCheck() throws IOException {
        String[] args = {
                "1-5",
                "2-3",
                "discountCard=1234567890",
                "balanceDebitCard=100.0",
                "saveToFile=result.csv",
                "datasource.url=jdbc:mysql://localhost:3306/mydb",
                "datasource.username=root",
                "datasource.password=secret"
        };

        argumentProcessor.processArguments(args);


        Assertions.assertFalse(outputStreamCaptor.toString().contains("BAD REQUEST"));


        File resultFile = new File("result.csv");
        Assertions.assertTrue(resultFile.exists());
        resultFile.delete();
    }

    @Test
    public void testProcessArguments_missingDatabaseArgs_shouldPrintErrorMessage() {
        String[] args = {
                "1-5",
                "2-3",
                "discountCard=1234567890",
                "balanceDebitCard=100.0",
                "saveToFile=result.csv"

        };

        argumentProcessor.processArguments(args);


        Assertions.assertTrue(outputStreamCaptor.toString().contains("BAD REQUEST: Missing database connection arguments"));
    }

    @Test
    public void testProcessArguments_invalidDiscountCard_shouldPrintErrorMessage() {
        String[] args = {
                "1-5",
                "2-3",
                "discountCard=invalidCardNumber",
                "balanceDebitCard=100.0",
                "saveToFile=result.csv",
                "datasource.url=jdbc:mysql://localhost:3306/mydb",
                "datasource.username=root",
                "datasource.password=secret"
        };

        argumentProcessor.processArguments(args);


        Assertions.assertTrue(outputStreamCaptor.toString().contains("Invalid discount card number: invalidCardNumber"));
    }
}

