import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.check.exception.InsufficientStockException;
import ru.clevertec.check.exception.ProductNotFoundException;
import ru.clevertec.check.model.Product;
import ru.clevertec.check.service.ProductService;

import java.util.HashMap;
import java.util.Map;

public class ProductServiceTest {

    private ProductService productService;
    private Map<Integer, Product> products;

    @BeforeEach
    public void setUp() {
        products = new HashMap<>();
        Product product1 = new Product(1, "1", 10,true,10);
        products.put(1, product1);
        productService = new ProductService(products);
    }

    @Test
    public void testGetProductById_existingId_shouldReturnProduct() throws ProductNotFoundException {
        int productId = 1;
        Product retrievedProduct = productService.getProductById(productId);
        Assertions.assertEquals(productId, retrievedProduct.getId());
    }

    @Test
    public void testGetProductById_nonExistingId_shouldThrowProductNotFoundException() {
        int nonExistingId = 2;
        Assertions.assertThrows(ProductNotFoundException.class, () -> productService.getProductById(nonExistingId));
    }



    @Test
    public void testCheckProductStock_insufficientStock_shouldThrowInsufficientStockException() {
        int productId = 1;
        int quantityToCheck = 15;
        Assertions.assertThrows(InsufficientStockException.class, () -> productService.checkProductStock(productId, quantityToCheck));
    }

    @Test
    public void testCheckProductStock_nonExistingProduct_shouldThrowProductNotFoundException() {
        int nonExistingId = 2;
        int quantityToCheck = 5;
        Assertions.assertThrows(ProductNotFoundException.class, () -> productService.checkProductStock(nonExistingId, quantityToCheck));
    }
}

