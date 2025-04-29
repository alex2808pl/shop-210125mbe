package de.telran.shop210125mbe;

import de.telran.shop210125mbe.pojo.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class Shop210125MBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(Shop210125MBeApplication.class, args);

		Product product = new Product();
		product.setProductId(1L);
		product.setName("Морковка");
		System.out.println(product);
		System.out.println("Я купил "+product.getName());

		Set<Product> productSet = new HashSet<>();
		productSet.add(product);
		System.out.println(productSet);


	}

}
