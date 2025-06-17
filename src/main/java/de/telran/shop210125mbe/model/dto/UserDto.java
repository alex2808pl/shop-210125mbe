package de.telran.shop210125mbe.model.dto;

import de.telran.shop210125mbe.model.entity.FavoriteEntity;
import de.telran.shop210125mbe.model.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
//@EqualsAndHashCode(exclude = "cart")
//@Getter
//@Setter
//@ToString(exclude = "cart")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long userId;

    @Size(min=2, max=30, message = "Invalid name: Must be of 2 - 30 characters")
    private String name;

    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Invalid Phone number: Empty number")
    @Pattern(regexp = "^\\d{12}$", message = "Invalid phone number")
    private String phoneNumber;

    @Pattern(regexp = "^\\w{4}$", message = "Invalid password")
    private String passwordHash;

//    @NotNull
    private String role;

    @EqualsAndHashCode.Exclude
    private Set<FavoriteDto> favorites = new HashSet<>();

//private Set<FavoritesDto> favorites = new HashSet<>();
    private CartDto cart;
}
