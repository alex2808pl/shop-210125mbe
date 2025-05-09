package de.telran.shop210125mbe.service;

import de.telran.shop210125mbe.pojo.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.*;

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

    @Override
    public Product getProductById(Long productId) {
        for (Product product : localStorage) {
            if(product.getProductId().equals(productId))
                return product;
        }
        return null;
    }

    @Override
    public Product insertProduct(Product newProduct) {
        if (localStorage.add(newProduct)) { // если добавление успешно
            return getProductById(newProduct.getProductId()); //возвращаем объект.
        }
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product updateProduct) {
        for (int i = 0; i < localStorage.size(); i++) {
            Product product = localStorage.get(i);
            if(product.getProductId().equals(id)) {
                localStorage.set(i, updateProduct);
                return localStorage.get(i);
            }
        }
        // если объекта не существует, мы должны создать новый
        return insertProduct(updateProduct);
    }

    @Override
    public Product updatePartProduct(Long id, Product updateProduct) throws Exception {
        for (Product product : localStorage) {
            if(product.getProductId().equals(id)) {
                if (!product.getName().equals(updateProduct.getName()))
                    product.setName(updateProduct.getName());
                if (product.getDescription() == null ||
                        !product.getDescription().equals(updateProduct.getDescription()))
                    product.setDescription(updateProduct.getDescription());
                return product;
            }
        }
        // throw new Exception("При update не нашли продукт с id="+id);
        throw new ArithmeticException("При update не нашли продукт с id="+id);
    }

    @Override
    public void deleteProductById(Long id) {

        if(getProductById(id)==null) //нечего удалять
            throw new IllegalArgumentException("Отсутствует элемент с Id="+id);

            //localStorage.removeIf(product -> product.getProductId().equals(id));
        Iterator<Product> iterator = localStorage.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if(product.getProductId().equals(id)) {
                iterator.remove();
                break;
            }
        }
    }
}
