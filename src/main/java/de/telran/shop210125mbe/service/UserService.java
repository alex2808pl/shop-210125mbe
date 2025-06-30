package de.telran.shop210125mbe.service;

import de.telran.shop210125mbe.configure.MapperUtil;
import de.telran.shop210125mbe.mapper.Mappers;
import de.telran.shop210125mbe.model.dto.CartDto;
import de.telran.shop210125mbe.model.dto.FavoriteDto;
import de.telran.shop210125mbe.model.dto.UserDto;
import de.telran.shop210125mbe.model.dto.UserShortDto;
import de.telran.shop210125mbe.model.entity.CartEntity;
import de.telran.shop210125mbe.model.entity.FavoriteEntity;
import de.telran.shop210125mbe.model.entity.UserEntity;
import de.telran.shop210125mbe.model.enums.Role;
import de.telran.shop210125mbe.repository.CartRepository;
import de.telran.shop210125mbe.repository.FavoriteRepository;
import de.telran.shop210125mbe.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.hibernate.StaleObjectStateException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // будет создан конструктор, аргументами которого будут переменные класса private final
@Validated
public class UserService { //имя компонента по умолчанию userService

//    @Autowired // DI через конструктор (в SpringBoot 3.0 и выше эту анотацию указывать не обязательно)
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    // @Autowired // DI через поле (переменная) (обязательно)
    private final UserRepository userRepository;
    private final FavoriteRepository favoriteRepository;
    private final CartRepository cartRepository;

    private final ModelMapper modelMapper;
    private final Mappers mappers;


//    @Autowired // DI через setter (обязательна)
//    public void setUserRepository(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @PostConstruct
    void init() {
//        UserEntity userEntity1 = UserEntity.builder()
//                .name("Вася")
//                .email("v@i.com")
//                .role(Role.CLIENT)
//                .passwordHash("11111")
//                .phoneNumber("+49 1234 56 34 26")
//                .build();
//
//        userEntity1 = userRepository.save(userEntity1);
//
//        UserEntity userEntity2 = UserEntity.builder()
//                .name("Дуня")
//                .email("d@i.com")
//                .role(Role.ADMIN)
//                .passwordHash("22222")
//                .phoneNumber("+49 9745 56 34 26")
//                .build();
//
//        userEntity2 = userRepository.save(userEntity2);
//
//        FavoriteEntity favoriteEntity = new FavoriteEntity();
//        favoriteEntity.setUser(userEntity2);
//        favoriteEntity = favoriteRepository.save(favoriteEntity);
//
//        CartEntity cartEntity = new CartEntity();
//        cartEntity.setUser(userEntity2);
//        cartEntity = cartRepository.save(cartEntity);
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

    public UserDto create(@Valid UserDto newUserDto) {
        if(newUserDto.getUserId()!=null)
            throw new IllegalArgumentException("userId должен быть неопределен");

        UserEntity userEntity = mappers.convertToUserEntity(newUserDto);
//        UserEntity userEntity = UserEntity.builder()
//                .phoneNumber(newUserDto.getPhoneNumber())
//                .passwordHash(newUserDto.getPasswordHash())
//                .email(newUserDto.getEmail())
//                .name(newUserDto.getName())
//                .role(Role.valueOf(newUserDto.getRole()))
//                .build();

        userEntity = userRepository.save(userEntity);

        UserDto resultUserDto =  mappers.convertToUserDto(userEntity);
//                UserDto resultUserDto= UserDto.builder()
//                .phoneNumber(userEntity.getPhoneNumber())
//                .passwordHash("****")
//                .email(userEntity.getEmail())
//                .name(userEntity.getName())
//                .role(userEntity.getRole().toString())
//                .userId(userEntity.getUserId())
//                .build();

        return resultUserDto;
    }

    public UserDto getById(@Min(0) Long id) {
        Optional<UserEntity> returnUserOptional = userRepository.findById(id);
        // returnUser == null -> new UserEntity() = NPE не произойдет
        UserEntity returnUserEntity = returnUserOptional.orElse(new UserEntity());

        UserDto returnUserDto = null;
        returnUserDto = mappers.convertToUserDto(returnUserEntity);

        // Entity -> Dto
//        returnUserDto = UserDto.builder()
//                .userId(returnUserEntity.getUserId())
//                .email(returnUserEntity.getEmail())
//                .name(returnUserEntity.getName())
//                .role(returnUserEntity.getRole().toString())
//                .phoneNumber("Нет доступа")
//                .passwordHash("******")
//                .build();

//        // UserDto::setPasswordHash - возьми тело этого метода и подставь как реализацию метода, опубликованного в функциональном интерфейсе
//        modelMapper.typeMap(UserEntity .class, UserDto.class)
//                //.addMappings(mapper -> mapper.skip((userDto, userEntity)-> userDto.setPasswordHash(((UserEntity)userEntity).getPasswordHash()))); // исключаем этот метод из работы
//                .addMappings(mapper -> mapper.skip(UserDto::setEmail)); // исключаем этот метод из работы
//        modelMapper.typeMap(UserEntity .class, UserDto.class)
//                //.addMappings(mapper -> mapper.skip((userDto, userEntity)-> userDto.setPasswordHash(((UserEntity)userEntity).getPasswordHash()))); // исключаем этот метод из работы
//                .addMappings(mapper -> mapper.skip(UserDto::setFavorites));
//        returnUserDto = modelMapper.map(returnUserEntity, UserDto.class); //автомат
//        returnUserDto.setPasswordHash("****"); //изменить уже созданный объект
//
//        if(returnUserEntity.getFavorites()!=null && returnUserEntity.getFavorites().size()>0) {
//            Set<FavoriteDto> favoriteDtoSet = returnUserEntity.getFavorites()
//                    .stream()
//                    .map(favoriteEntity -> modelMapper.map(favoriteEntity, FavoriteDto.class))
//                    .collect(Collectors.toSet());
//            returnUserDto.setFavorites(favoriteDtoSet);
//        }

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


    public UserDto update(UserDto newUserDto) {
        // PUT - обновляет или создает новый
        if(newUserDto.getUserId()==null)
            throw new IllegalArgumentException("userId должен быть определен!");

        UserEntity userEntity = mappers.convertToUserEntity(newUserDto);

        // Id имеет значение, поэтому на БД выполниться операция UPDATE
        try {
            userEntity = userRepository.save(userEntity);
            //ObjectOptimisticLockingFailureException | StaleObjectStateException
        } catch (Exception e) {
            // если объект с таким Id отсутствует, согласно REST API
            // мы должны создать новый объект вручную, если не противоречит ТЗ???
            userEntity.setUserId(null);
            userEntity = userRepository.save(userEntity);
        }

        UserDto resultUserDto =  mappers.convertToUserDto(userEntity);

        return resultUserDto;
    }

    public UserDto updateFind(UserDto newUserDto) {
        if (userRepository.findById(newUserDto.getUserId()).isPresent()) {
            // Если объект есть в базе данных, мы его обновляем
            UserEntity updatedUserEntity = userRepository.save(mappers.convertToUserEntity(newUserDto));
            return mappers.convertToUserDto(updatedUserEntity);
        } else {
            // Если объекта нет в базе данных, то нужно создать новый
            newUserDto.setUserId(null);
            UserEntity updatedUserEntity = userRepository.save(mappers.convertToUserEntity(newUserDto));
            return mappers.convertToUserDto(updatedUserEntity);

        }
    }
}
