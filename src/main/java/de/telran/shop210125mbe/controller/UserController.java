package de.telran.shop210125mbe.controller;

import de.telran.shop210125mbe.model.dto.UserDto;
import de.telran.shop210125mbe.model.dto.UserShortDto;
import de.telran.shop210125mbe.pojo.Category;
import de.telran.shop210125mbe.pojo.Product;
import de.telran.shop210125mbe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")  //localhost:8088/user
@RequiredArgsConstructor
public class UserController {

    private final UserService userService; //используем прямой компонент без интерфейса (возможно)

    // Для Клиента
    @GetMapping
    List<UserShortDto> getAll() {
        return userService.getAll();
    }

    // Для Админа
    @GetMapping("/{id}")  // http://localhost:8088/user/2
    public  UserDto getById(@PathVariable Long id) {
        UserDto responseUser = userService.getById(id);
        return responseUser;
    }

    @GetMapping("/email/{valueEmail}")  // http://localhost:8088/user/email/a@i.com
    public  UserDto getByEmail(@PathVariable String valueEmail) {
        UserDto responseUser = userService.getByEmail(valueEmail);
        return responseUser;
    }

    @GetMapping("/name/{valueName}")  // http://localhost:8088/user/email/a@i.com
    public  List<UserDto> getByName(@PathVariable String valueName) {
         return userService.getByName(valueName);
    }

    @GetMapping("/find")  // http://localhost:8088/user/find?name='Дуня'&email='d@i.com'
    public  List<UserShortDto> getByNameAndEmail(@RequestParam String name, @RequestParam(name = "email") String valueEmail) {
        List<UserShortDto> responseUsers = userService.getByNameAndEmail(name, valueEmail);
        return responseUsers;
    }

    // вставку
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserDto create(@RequestBody UserDto newUser) {
        return userService.create(newUser);
    }

    // обновление части информации, если объекта не существует, новый не создаем
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("/{id}") //http://localhost:8088/user/1?phone=+4901239876543&name=Odarka
    public UserShortDto updatePartProduct(@PathVariable Long id, @RequestParam String phone) throws Exception {
        return userService.updatePhoneNumber(id, phone);
    }
}
