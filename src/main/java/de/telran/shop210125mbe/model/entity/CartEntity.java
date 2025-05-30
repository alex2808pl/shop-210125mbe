package de.telran.shop210125mbe.model.entity;

import jakarta.persistence.*;
import lombok.*;

//@Data
@EqualsAndHashCode(exclude = "user")
@Getter
@Setter
@ToString(exclude = "user")
@Entity
@Table(name = "Cart")
@NoArgsConstructor
@AllArgsConstructor
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CartID")
    private long cartId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserID", referencedColumnName = "userId")
    private UserEntity user;
}
