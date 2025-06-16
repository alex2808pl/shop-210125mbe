package de.telran.shop210125mbe.service;

import de.telran.shop210125mbe.mapper.Mappers;
import de.telran.shop210125mbe.model.dto.UserDto;
import de.telran.shop210125mbe.model.dto.UserShortDto;
import de.telran.shop210125mbe.model.entity.CartEntity;
import de.telran.shop210125mbe.model.entity.FavoriteEntity;
import de.telran.shop210125mbe.model.entity.UserEntity;
import de.telran.shop210125mbe.model.enums.Role;
import de.telran.shop210125mbe.repository.CartRepository;
import de.telran.shop210125mbe.repository.FavoriteRepository;
import de.telran.shop210125mbe.repository.UserRepository;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@FieldDefaults(level = PRIVATE) //присваивает область видимости по умолчанию для переменных класса
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock //объект, поведение которого мы будем иммитировать
    private  UserRepository userRepositoryMock;
    @Mock
    private  FavoriteRepository favoriteRepositoryMock;
    @Mock
    private  CartRepository cartRepositoryMock;
    @Mock
    private  Mappers mappersMock;

    @InjectMocks // unit объект, который тестируется
    private UserService userService; //прямой класс, но может быть и интерфейс

    UserEntity userEntity1;
    UserEntity userEntity2;

    @BeforeEach
    void setUp() {
        userEntity1 = UserEntity.builder()
                .userId(1L)
                .name("TestUser")
                .email("test@i.com")
                .build();
        userEntity2 = UserEntity.builder()
                .userId(2L)
                .name("TestUser2")
                .email("test2@i.com")
                .build();
    }

    @DisplayName("Тест возврата всех пользователей")
    @Test
    void getAllTest() {
        //подготовка
        when(userRepositoryMock.findAll()).thenReturn(List.of(userEntity1, userEntity2));

        // запуск
        List<UserShortDto> userShortDtoListActual = userService.getAll();

        //проверки
        assertNotNull(userShortDtoListActual);
        assertTrue(userShortDtoListActual.size()==2);
        assertEquals(1, userShortDtoListActual.getFirst().getUserId());
        verify(userRepositoryMock).findAll();
    }

    @Test
    void create() {
    }

    @Test
    void getByIdTest() {

        // подготовка
        UserEntity userEntityExpected = userEntity1;

        UserDto userDtoExpected = UserDto.builder()
                .userId(userEntityExpected.getUserId())
                .name(userEntityExpected.getName())
                .email(userEntityExpected.getEmail())
                .build();
        Long idExpected = 1L;

        when(userRepositoryMock.findById(anyLong())).thenReturn(Optional.of(userEntityExpected));
        when(mappersMock.convertToUserDto(userEntityExpected)).thenReturn(userDtoExpected);

        // запуск
        UserDto userDtoActual = userService.getById(idExpected);

        // проверка
        assertNotNull(userDtoActual);
        assertEquals(userDtoExpected, userDtoActual);
        assertEquals(userEntityExpected.getUserId(), userDtoActual.getUserId());
        verify(userRepositoryMock).findById(idExpected); //был ли запущен этот метод
        verify(mappersMock, times(1)).convertToUserDto(any(UserEntity.class)); //запущен ли этот метод 1 раз

    }

    @Test
    void getByEmail() {
    }

    @Test
    void getByName() {
    }

    @Test
    void updatePhoneNumber() {
    }

    @Test
    void getByNameAndEmail() {
    }

    @Test
    void update() {
    }

    @Test
    void updateFind() {
    }

    @Test
    void initTest() {
        //подготовка
        when(favoriteRepositoryMock.save(any(FavoriteEntity.class))).thenReturn(new FavoriteEntity());
        when(cartRepositoryMock.save(any(CartEntity.class))).thenReturn(new CartEntity());
        when(userRepositoryMock.save(any(UserEntity.class))).thenReturn(new UserEntity());

        //выполнение
        userService.init();

        //проверка
        //assert-ов нет, потому что метод void init() не возвращает результат
        verify(cartRepositoryMock).save(any(CartEntity.class));
        verify(favoriteRepositoryMock).save(any(FavoriteEntity.class));
        verify(userRepositoryMock,times(2)).save(any(UserEntity.class)); //был 2 раза вызван метод
    }
}