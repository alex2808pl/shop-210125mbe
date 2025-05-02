package de.telran.shop210125mbe;

import de.telran.shop210125mbe.pojo.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class Shop210125MBeApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(Shop210125MBeApplication.class, args);

		// поиск по типу bean
		Product product = applicationContext.getBean(Product.class);
		// Product product = new Product();
		product.setProductId(1L);
		product.setName("Морковка");
		System.out.println(product);
		System.out.println("Я купил "+product.getName());

		// поиск по имени bean
		Product productName = (Product) applicationContext.getBean("product");
		productName.setProductId(2L);
		productName.setName("Свекла");
		System.out.println(productName);
		System.out.println("Я купил "+productName.getName());

		Set<Product> productSet = new HashSet<>();
		productSet.add(product);
		System.out.println(productSet);


	}

}
