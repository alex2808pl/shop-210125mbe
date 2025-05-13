package de.telran.shop210125mbe.controller;

import de.telran.shop210125mbe.pojo.Product;
import de.telran.shop210125mbe.service.ProductServiceInterface;
import de.telran.shop210125mbe.service.ProductServiceList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
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

    // получение информации
    @GetMapping("/find/{id}") // http://localhost:8080/product/find/1
    public Product getProductById(@PathVariable(name = "id") Long productId) {
        System.out.println("Поиск информации по id");
        return serviceProductList.getProductById(productId);
    }

    // вставку
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Product insertProduct(@RequestBody Product newProduct) {
        System.out.println("Произошла вставка");
        return serviceProductList.insertProduct(newProduct);
    }

    // обновление всего объекта, если объекта не существует, мы должны создать новый
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updateProduct) {
        System.out.println("Произошло редактирование");
        return serviceProductList.updateProduct(id, updateProduct);
    }

    // обновление части информации, если объекта не существует, новый не создаем
    @PatchMapping("/{id}")
    public Product updatePartProduct(@PathVariable Long id, @RequestBody Product updateProduct) throws Exception {
        System.out.println("Произошло редактирование части информации");
        return serviceProductList.updatePartProduct(id, updateProduct);
    }

    // удаление
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        System.out.println("Произошло удаление");
        serviceProductList.deleteProductById(id);
    }

    @ExceptionHandler({IllegalArgumentException.class,FileNotFoundException.class})
    //@ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException exception) {
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("ProductController: "+exception.getMessage());

    }

//    @ResponseStatus(HttpStatus.CONFLICT)
//    @ExceptionHandler(Exception.class) // обработчик для всех остальніх типов исключений
//    public String handleException(Exception exception) {
//        return  "ProductController(Exception): "+exception.getMessage();
//
//    }
}
