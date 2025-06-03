package de.telran.shop210125mbe.model.entity;

import jakarta.persistence.*;
import lombok.*;

//@Data
@EqualsAndHashCode(exclude = {"user","product"})
@Getter
@Setter
@ToString(exclude = {"user","product"})
@Entity
@Table(name = "Favorites")
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteEntity {
    @Id
    @Column(name = "FavoriteID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long favoriteId;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "ProductID")
    private ProductEntity product;
}
