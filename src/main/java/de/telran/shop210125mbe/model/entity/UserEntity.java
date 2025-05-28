package de.telran.shop210125mbe.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import de.telran.shop210125mbe.model.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id //эта колонка должны быть уникальной в таблице и не повторяться
    @GeneratedValue(strategy = GenerationType.IDENTITY) // БД генерит уникальный Id
    @Column(name = "UserID")
    private Long userId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Email")
    private String email;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @Column(name = "PasswordHash")
    private String passwordHash;

    @Enumerated(EnumType.STRING) //что будет сохраняться в таблице БД
    @Column(name = "Role")
    private Role role;

    @OneToOne(mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private CartEntity cart;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<FavoriteEntity> favorites = new HashSet<>();

//    @ManyToMany
//    @JoinTable(name = "UsersAddresses",
//            joinColumns = @JoinColumn(name = "UserID"),
//            inverseJoinColumns = @JoinColumn(name = "AddressID"))
//    private Set<AddressEntity> addresses =  new HashSet<>();

}
