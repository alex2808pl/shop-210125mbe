package de.telran.shop210125mbe.service;

import de.telran.shop210125mbe.pojo.Category;
import de.telran.shop210125mbe.pojo.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceList implements CategoryServiceInterface{
    List<Category> localStorage = new ArrayList<>();

    @PostConstruct
    void init() {
        localStorage.add(new Category(1L, "Продукты"));
        localStorage.add(new Category(2L, "Быт.химия"));
        localStorage.add(new Category(3L, "Молочные продукты"));
    }

    @Override
    public List<Category> getAll() {
        return localStorage;
    }

    @Override
    public Category getById(Long id) {
        for (Category category : localStorage) {
            if(category.getCategoryId().equals(id)) {
                return category;
            }
        }
        return null;
    }

    @Override
    public Category create(Category category) {
        localStorage.add(category);
        return  localStorage.add(category) ? category : null;
    }
}
