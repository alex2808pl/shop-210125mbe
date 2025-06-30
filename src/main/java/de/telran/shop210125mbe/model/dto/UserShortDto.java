package de.telran.shop210125mbe.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Сущность пользователя")
public class UserShortDto {
    @Schema(description = "Уникальный идентификатор пользователя", example = "1", accessMode = Schema.AccessMode.AUTO)
    private Long userId;
    @Schema(description = "ФИО", example = "Иванов Иван Иванович")
    private String name;
    private String email;
    private String phoneNumber;
}
