package de.telran.shop210125mbe.service;

import de.telran.shop210125mbe.mapper.Mappers;
import de.telran.shop210125mbe.model.dto.UserDto;
import de.telran.shop210125mbe.model.entity.UserEntity;
import de.telran.shop210125mbe.repository.CartRepository;
import de.telran.shop210125mbe.repository.FavoriteRepository;
import de.telran.shop210125mbe.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

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


    @Test
    void getAllTest() {

    }

    @Test
    void create() {
    }

    @Test
    void getByIdTest() {

        // подготовка
        UserEntity userEntityExpected = UserEntity.builder()
                .userId(1L)
                .name("TestUser")
                .email("test@i.com")
                .build();

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
}