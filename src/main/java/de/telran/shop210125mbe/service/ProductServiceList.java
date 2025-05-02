package de.telran.shop210125mbe.service;

import de.telran.shop210125mbe.pojo.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component("serviceProductList")
public class ProductServiceList implements ProductServiceInterface {
    List<Product> localStorage = new ArrayList<>();

    @PostConstruct
    void init() {
        Product product = new Product();
        product.setProductId(1L);
        product.setName("Морковка");

        Product product2 = new Product();
        product2.setProductId(2L);
        product2.setName("Свекла");

        localStorage.add(product);
        localStorage.add(product2);

     }

    @Override
    public List<Product> getAllProducts() {
        return localStorage;
    }
}
