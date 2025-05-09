package de.telran.shop210125mbe.service;

import de.telran.shop210125mbe.pojo.Product;
import org.springframework.stereotype.Component;

import java.util.List;

public interface ProductServiceInterface {
    List<Product> getAllProducts();

    Product getProductById(Long productId);

    Product insertProduct(Product newProduct);

    Product updateProduct(Long id, Product updateProduct);

    Product updatePartProduct(Long id, Product updateProduct) throws Exception;

    void deleteProductById(Long id);
}
