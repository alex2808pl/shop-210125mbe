package de.telran.shop210125mbe.service;

import de.telran.shop210125mbe.pojo.Product;
import org.springframework.stereotype.Component;

import java.util.List;

public interface ProductServiceInterface {
    List<Product> getAllProducts();
}
