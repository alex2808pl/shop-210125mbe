package de.telran.shop210125mbe.model.dto;

import de.telran.shop210125mbe.model.entity.FavoriteEntity;
import de.telran.shop210125mbe.model.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
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
    private Set<FavoriteDto> favorites = new HashSet<>();
//private Set<FavoritesDto> favorites = new HashSet<>();
    private CartDto cart;
}
