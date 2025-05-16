package de.telran.shop210125mbe.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "Categories")
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@EqualsAndHashCode
//@ToString
public class CategoryEntity {

    @Id
    @Column(name = "CategoryID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(name = "Name")
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<ProductEntity> products = new HashSet<>();

//    public CategoryEntity(Long categoryId, String name) {
//        this.categoryId = categoryId;
//        this.name = name;
//    }

    //    @ManyToMany
//    @JoinTable(name = "category_product",
//            joinColumns = @JoinColumn(name = "CategoryID"),
//            inverseJoinColumns = @JoinColumn(name = "ProductID"))
//    private Set<ProductsEntity> products =  new HashSet<>();

}
