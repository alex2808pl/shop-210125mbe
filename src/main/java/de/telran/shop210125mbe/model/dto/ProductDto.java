package de.telran.shop210125mbe.model.dto;

import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class ProductDto {

    private Long productId;

    private String name;

    private String description;

    private Double price;

    private String imageUrl;

    private Double discountPrice;

    private Timestamp createdAt;

    private Timestamp updatedAt;

}
