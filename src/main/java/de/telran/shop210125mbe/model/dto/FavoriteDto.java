package de.telran.shop210125mbe.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FavoriteDto {
    private Long favoriteId;
    private UserDto user;
    private ProductDto product;
}
