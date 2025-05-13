package de.telran.shop210125mbe.service;

import de.telran.shop210125mbe.jdbc.CategoryDbInterface;
import de.telran.shop210125mbe.pojo.Category;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service("categoryJdbc") //новое имя этого компонента
// имя компонента в контейнере по умолчанию "categoryServiceDbJdbc"
public class CategoryServiceDbJdbc implements CategoryServiceInterface{

    @Autowired
    @Qualifier("categoryDbJdbcTemplate")//("categoryDbJdbc")
    CategoryDbInterface categoryDbInterface;

    @Override
    public List<Category> getAll() {
        return List.of();
    }

    @Override
    public Category getById(Long id) {
        return categoryDbInterface.findById(id);
    }

    @Override
    public Category create(Category category) {
        return null;
    }

}
