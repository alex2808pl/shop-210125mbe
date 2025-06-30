package de.telran.shop210125mbe.controller;

import de.telran.shop210125mbe.model.dto.UserDto;
import de.telran.shop210125mbe.model.dto.UserShortDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "Users", description = "Контроллер для работы с пользователями",
        externalDocs = @ExternalDocumentation(
                description = "Ссылка на общую документацию по пользователям",
                url = "https://confirmed-baron-2e5.notion.site/REST-API-f186cf63a46c4020b2237f73093922ab#b6accb46d1e04c3992cfcc2c7e5b4b2d"
        )
)
public interface UserControllerInterface {
    @Operation(
            summary = "Все клиенты",
            description = "Позволяет получить информацию о всех клиентах"
    )
    List<UserShortDto> getAll();

    @Operation(
            summary = "Ищем информацию по пользователю",
            description = "Позволяет получить информацию по ID пользователя"
    )
    UserDto getById(@Parameter(description = "Идентификатор пользователя", required = true, example = "1")
                    Long id);
    UserDto getByEmail(@PathVariable /*@Email*/ String valueEmail);
}
