package de.telran.shop210125mbe.controller;

import de.telran.shop210125mbe.model.dto.UserDto;
import de.telran.shop210125mbe.model.dto.UserShortDto;
import de.telran.shop210125mbe.pojo.Product;
import de.telran.shop210125mbe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")  //localhost:8088/user
@RequiredArgsConstructor
public class UserController {

    private final UserService userService; //используем прямой компонент без интерфейса (возможно)

    @GetMapping
    List<UserShortDto> getAll() {
        return userService.getAll();
    }

    // вставку
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserDto create(@RequestBody UserDto newUser) {
        return userService.create(newUser);
    }
}
