package de.telran.shop210125mbe.model.dto;

import de.telran.shop210125mbe.model.entity.FavoriteEntity;
import de.telran.shop210125mbe.model.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    private String name;
    private String email;
    private String phoneNumber;
    private String passwordHash;
    private String role;

    @EqualsAndHashCode.Exclude
    private Set<FavoriteDto> favorites = new HashSet<>();

//private Set<FavoritesDto> favorites = new HashSet<>();
    private CartDto cart;
}
