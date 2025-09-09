package com.microstore.products;

import com.microstore.products.entity.Category;
import com.microstore.products.entity.Product;
import com.microstore.products.repository.CategoryRepository;
import com.microstore.products.repository.ProductRepository;
import com.microstore.products.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@Transactional
class ProductServiceIntegrationTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private Category category;

    @BeforeEach
    void setUp() {
        // Cria uma categoria de teste
        category = new Category();
        category.setName("Eletrônicos");
        categoryRepository.save(category);
    }

    @Test
    void testCreateProductWithValidCategory() {
        Product product = new Product();
        product.setName("Smartphone");
        product.setPrice(new BigDecimal("1999.90"));
        product.setCategory(category);

        Product saved = productService.createProduct(product);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo("Smartphone");
        assertThat(saved.getCategory().getName()).isEqualTo("Eletrônicos");
    }

    @Test
    void testCreateProductWithInvalidCategory() {
        Product product = new Product();
        product.setName("Tablet");
        product.setPrice(new BigDecimal("999.90"));
        product.setCategory(new Category()); // Categoria não salva

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            productService.createProduct(product);
        });

        assertThat(exception.getMessage()).isEqualTo("Categoria não encontrada");
    }

    @Test
    void testFindProductById() {
        Product product = new Product();
        product.setName("Notebook");
        product.setPrice(new BigDecimal("3500.00"));
        product.setCategory(category);
        Product saved = productService.createProduct(product);

        Optional<Product> found = productService.getProductById(saved.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Notebook");
    }

    @Test
    void testUpdateProduct() {
        Product product = new Product();
        product.setName("Fone de Ouvido");
        product.setPrice(new BigDecimal("199.90"));
        product.setCategory(category);
        Product saved = productService.createProduct(product);

        saved.setPrice(new BigDecimal("179.90"));
        Product updated = productService.updateProduct(saved.getId(), saved);

        assertThat(updated.getPrice()).isEqualByComparingTo("179.90");
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setName("Smartwatch");
        product.setPrice(new BigDecimal("499.90"));
        product.setCategory(category);
        Product saved = productService.createProduct(product);

        productService.deleteProduct(saved.getId());

        Optional<Product> deleted = productRepository.findById(saved.getId());
        assertThat(deleted).isEmpty();
    }
}
