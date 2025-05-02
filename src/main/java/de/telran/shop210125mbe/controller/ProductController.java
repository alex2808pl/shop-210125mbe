package de.telran.shop210125mbe.controller;

import de.telran.shop210125mbe.pojo.Product;
import de.telran.shop210125mbe.service.ProductServiceInterface;
import de.telran.shop210125mbe.service.ProductServiceList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/product")  //localhost:8080/product
public class ProductController {

    // Получить из контейнера связанный объект
    @Autowired
    private ProductServiceInterface serviceProductList; //productServiceInterface; // = new ProductServiceList();


    // получение информации
    @GetMapping
    public List<Product> getAllProducts() {
        System.out.println("Поиск информации");
        return serviceProductList.getAllProducts();
    }

    // вставку
    @PostMapping
    public void insertProduct() {
        System.out.println("Произошла вставка");
    }

    // вставку
    @PutMapping
    public void updateProduct() {
        System.out.println("Произошло редактирование");
    }

    // вставку
    @DeleteMapping
    public void deleteProduct() {
        System.out.println("Произошло удаление");
    }


}
