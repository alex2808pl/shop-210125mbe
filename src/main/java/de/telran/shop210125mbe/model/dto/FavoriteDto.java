package de.telran.shop210125mbe.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
//@EqualsAndHashCode(exclude = "user")
//@Getter
//@Setter
//@ToString(exclude = "user")

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FavoriteDto {
    private Long favoriteId;

    @EqualsAndHashCode.Exclude
    private UserDto user;

    private ProductDto product;
}
