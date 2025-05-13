package de.telran.shop210125mbe.jdbc;

import de.telran.shop210125mbe.pojo.Category;

public interface CategoryDbInterface {
    Category findById(long id);
}
