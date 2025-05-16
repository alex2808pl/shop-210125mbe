package de.telran.shop210125mbe.model.entity;

import de.telran.shop210125mbe.model.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Users")
@Data
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

    @ManyToMany
    @JoinTable(name = "UsersAddresses",
            joinColumns = @JoinColumn(name = "UserID"),
            inverseJoinColumns = @JoinColumn(name = "AddressID"))
    private Set<AddressEntity> addresses =  new HashSet<>();

}
