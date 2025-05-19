package de.telran.shop210125mbe.service;

import de.telran.shop210125mbe.model.entity.CategoryEntity;
import de.telran.shop210125mbe.model.entity.ProductEntity;
import de.telran.shop210125mbe.pojo.Product;
import de.telran.shop210125mbe.repository.CategoryRepository;
import de.telran.shop210125mbe.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceJpa implements ProductServiceInterface{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostConstruct
    void init() {
        // создадим категорию
        //Builder
        CategoryEntity category1 = CategoryEntity.builder()
                .name("Молочные продукты")
                .build();
        category1 = categoryRepository.save(category1);

        CategoryEntity category2 = new CategoryEntity();
        category2.setName("Продукты");
        category2 = categoryRepository.save(category2);

        CategoryEntity category3 = new CategoryEntity(null, "Бытовая химия", null);
        category3 = categoryRepository.save(category3);


        //заполним таблицу Products тестовыми данными
        ProductEntity product1 = new ProductEntity();
        product1.setName("Молоко");
        product1.setPrice(1.83);
        product1.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        product1.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        product1.setDiscountPrice(1.00);
        product1.setDescription("Наш первый продукт");
        product1.setCategory(category1); //пока без связи
        //.setProductId(1L); //это поле должно быть сгенерировано БД
        ProductEntity productNew1 = productRepository.save(product1); //сохраняем в БД
        System.out.println("productId = "+productNew1);

        ProductEntity product2 = new ProductEntity();
        product2.setName("Кефир");
        product2.setPrice(1.23);
        product2.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        product2.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        product2.setDiscountPrice(1.01);
        product2.setDescription("Наш второй продукт");
        product2.setCategory(category1); //пока без связи
        //.setProductId(1L); //это поле должно быть сгенерировано БД
        ProductEntity productNew2 = productRepository.save(product2); //сохраняем в БД
        System.out.println("productId = "+productNew2);


    }

    @Override
    public List<Product> getAllProducts() {
        List<ProductEntity> productEntities = productRepository.findAll();
        List<Product> result = new ArrayList<>();
        for (ProductEntity productEntity : productEntities) {
            Product product = new Product(productEntity.getProductId(),
                    productEntity.getName(),
                    productEntity.getDescription(),
                    productEntity.getPrice(),
                    productEntity.getImageUrl(),
                    productEntity.getDiscountPrice(),
                    productEntity.getCreatedAt(),
                    productEntity.getUpdatedAt(),
                    productEntity.getCategory().getCategoryId()
                    );
            result.add(product);
        }
        return result ;
    }

    @Override
    public Product getProductById(Long productId) {
        return null;
    }

    @Override
    public Product insertProduct(Product newProduct) {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product updateProduct) {
        return null;
    }

    @Override
    public Product updatePartProduct(Long id, Product updateProduct) throws Exception {
        return null;
    }

    @Override
    public void deleteProductById(Long id) {

    }
}
