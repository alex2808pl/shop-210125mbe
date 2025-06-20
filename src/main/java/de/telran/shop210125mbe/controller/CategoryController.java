package de.telran.shop210125mbe.controller;

import de.telran.shop210125mbe.aspect.LogTimeAnnotation;
import de.telran.shop210125mbe.pojo.Category;
import de.telran.shop210125mbe.pojo.Product;
import de.telran.shop210125mbe.service.CategoryServiceInterface;
import jakarta.annotation.PostConstruct;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/category") //localhost:8080/category
@RequiredArgsConstructor
public class CategoryController {

//    public CategoryController(@Qualifier("categoryJdbc") CategoryServiceInterface categoryServiceInterface) {
//        this.categoryServiceInterface = categoryServiceInterface;
//    }

    //@Autowired
    @Qualifier("categoryJdbc")//("categoryServiceDbJdbc")//("categoryServiceList")
    private final CategoryServiceInterface categoryServiceInterface;

    @PostConstruct
    void init() {
        System.out.println("создается объект CategoryController");
    }

    @LogTimeAnnotation
    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Category> categories = categoryServiceInterface.getAll();
        ResponseEntity<?> responseEntity = new ResponseEntity<>(categories, HttpStatusCode.valueOf(222));
        return responseEntity;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable Long id) {
        Category category = categoryServiceInterface.getById(id);
        if(category==null) {
            //return new ResponseEntity<>(category, HttpStatus.NOT_FOUND); //через конструктор
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // через builder
        } else {
            //return new ResponseEntity<>(category, HttpStatus.OK); //через конструктор
            return ResponseEntity.ok(category); // через static метод
        }

    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category category)  {
        Category response = categoryServiceInterface.create(category);
        return (response!=null) ? ResponseEntity.status(HttpStatus.CREATED).body(response)
                : ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }

}
