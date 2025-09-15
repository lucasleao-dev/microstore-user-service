package com.microstore.products.service;

import com.microstore.products.dto.ProductRequest;
import com.microstore.products.entity.Category;
import com.microstore.products.entity.Product;
import com.microstore.products.repository.CategoryRepository;
import com.microstore.products.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository,
                          CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    // aceita DTO (recomendado)
    public Product createProduct(ProductRequest dto) {
        Category category = null;
        if (dto.getCategoryId() != null) {
            category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        }
        Product p = new Product();
        p.setName(dto.getName());
        p.setDescription(dto.getDescription());
        p.setPrice(dto.getPrice());
        p.setStock(dto.getStock());
        p.setCategory(category);
        return productRepository.save(p);
    }

    // overload para compatibilidade quando alguém passar um Product direto
    public Product createProduct(Product product) {
        if (product.getCategory() != null && product.getCategory().getId() != null) {
            Category category = categoryRepository.findById(product.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
            product.setCategory(category);
        } else {
            product.setCategory(null);
        }
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
