package de.telran.shop210125mbe.service;

import de.telran.shop210125mbe.pojo.Category;

import java.util.List;

public interface CategoryServiceInterface {
    List<Category> getAll();

    Category getById(Long id);

    Category create(Category category);
}
