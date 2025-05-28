package de.telran.shop210125mbe.service;

import de.telran.shop210125mbe.model.dto.UserDto;
import de.telran.shop210125mbe.model.dto.UserShortDto;
import de.telran.shop210125mbe.model.entity.UserEntity;
import de.telran.shop210125mbe.model.enums.Role;
import de.telran.shop210125mbe.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // будет создан конструктор, аргументами которого будут переменные класса private final
public class UserService { //имя компонента по умолчанию userService

//    @Autowired // DI через конструктор (в SpringBoot 3.0 и выше эту анотацию указывать не обязательно)
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    // @Autowired // DI через поле (переменная) (обязательно)
    private final UserRepository userRepository;


//    @Autowired // DI через setter (обязательна)
//    public void setUserRepository(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @PostConstruct
    void init() {
        UserEntity userEntity1 = UserEntity.builder()
                .name("Вася")
                .email("v@i.com")
                .role(Role.CLIENT)
                .passwordHash("11111")
                .phoneNumber("+49 1234 56 34 26")
                .build();

        userEntity1 = userRepository.save(userEntity1);

        UserEntity userEntity2 = UserEntity.builder()
                .name("Дуня")
                .email("d@i.com")
                .role(Role.ADMIN)
                .passwordHash("22222")
                .phoneNumber("+49 9745 56 34 26")
                .build();

        userEntity2 = userRepository.save(userEntity2);
    }

    public List<UserShortDto> getAll() {
        List<UserEntity> users = userRepository.findAll(); //вернуть все строки таблицы
        List<UserShortDto> usersShortDto = new ArrayList<>();
        for (UserEntity entity : users) {
            UserShortDto userDto = UserShortDto.builder()
                    .userId(entity.getUserId())
                    .email(entity.getEmail())
                    .name(entity.getName())
                    .build();
            usersShortDto.add(userDto);
        }
        return usersShortDto;
    }

    public UserDto create(UserDto newUserDto) {
        if(newUserDto.getUserId()!=null)
            throw new IllegalArgumentException("userId должен быть неопределен");

        UserEntity userEntity = UserEntity.builder()
                .phoneNumber(newUserDto.getPhoneNumber())
                .passwordHash(newUserDto.getPasswordHash())
                .email(newUserDto.getEmail())
                .name(newUserDto.getName())
                .role(Role.valueOf(newUserDto.getRole()))
                .build();

        userEntity = userRepository.save(userEntity);

        UserDto resultUserDto= UserDto.builder()
                .phoneNumber(userEntity.getPhoneNumber())
                .passwordHash("****")
                .email(userEntity.getEmail())
                .name(userEntity.getName())
                .role(userEntity.getRole().toString())
                .userId(userEntity.getUserId())
                .build();

        return resultUserDto;
    }

    public UserDto getById(Long id) {
        Optional<UserEntity> returnUserOptional = userRepository.findById(id);
        // returnUser == null -> new UserEntity() = NPE не произойдет
        UserEntity returnUserEntity = returnUserOptional.orElse(new UserEntity());

        // Entity -> Dto
        UserDto returnUserDto = UserDto.builder()
                .userId(returnUserEntity.getUserId())
                .email(returnUserEntity.getEmail())
                .name(returnUserEntity.getName())
                .role(returnUserEntity.getRole().toString())
                .phoneNumber("Нет доступа")
                .passwordHash("******")
                .build();

        return returnUserDto;
     }

    public UserDto getByEmail(String valueEmail) {
        //Вызываем самостоятельно описанный метод из репозитория
        UserEntity returnUserEntity = userRepository.findByEmailNativeQuery(valueEmail);

        //Трансформируем в Dto
        UserDto returnUserDto = UserDto.builder()
                .userId(returnUserEntity.getUserId())
                .email(returnUserEntity.getEmail())
                .name(returnUserEntity.getName())
                .role(returnUserEntity.getRole().toString())
                .phoneNumber(returnUserEntity.getPhoneNumber())
                .passwordHash("******")
                .build();

        return returnUserDto;
    }

    public List<UserDto> getByName(String valueName) {
        //Вызываем самостоятельно описанный метод из репозитория
        List<UserEntity> returnUsersEntity = userRepository.findByNameHql(valueName);

        //Трансформируем в List Dto
        List<UserDto> returnUsersDto =
                returnUsersEntity.stream()
                        .map(userEntity ->
                                UserDto.builder()
                                        .userId(userEntity.getUserId())
                                        .email(userEntity.getEmail())
                                        .name(userEntity.getName())
                                        .role(userEntity.getRole().toString())
                                        .phoneNumber(userEntity.getPhoneNumber())
                                        .passwordHash("******")
                                        .build())
                        .collect(Collectors.toList());

        return returnUsersDto;

    }

    public UserShortDto updatePhoneNumber(Long id, String phone) {
       int result  = userRepository.setPhoneNumber(id, phone);
       if(result > 0) {
            Optional<UserEntity> userEntityOptional = userRepository.findById(id);
           UserEntity userEntity = userEntityOptional.orElse(new UserEntity());
           return UserShortDto.builder()
                   .userId(userEntity.getUserId())
                   .name(userEntity.getName())
                   .email(userEntity.getEmail())
                   .phoneNumber(userEntity.getPhoneNumber())
                   .build();
       }
       return null;
    }

    public List<UserShortDto> getByNameAndEmail(String name, String valueEmail) {
        List<UserEntity> userEntities = userRepository.findByNameContainingAndEmail(name, valueEmail);

        return userEntities.stream()
                .map(userEntity ->
                        UserShortDto.builder()
                                .userId(userEntity.getUserId())
                                .email(userEntity.getEmail())
                                .name(userEntity.getName())
                                .phoneNumber(userEntity.getPhoneNumber())
                                .build())
                        .collect(Collectors.toUnmodifiableList());
    }
}
