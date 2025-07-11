package de.telran.shop210125mbe.mapper;

import de.telran.shop210125mbe.configure.MapperUtil;
import de.telran.shop210125mbe.model.dto.CartDto;
import de.telran.shop210125mbe.model.dto.FavoriteDto;
import de.telran.shop210125mbe.model.dto.UserDto;
import de.telran.shop210125mbe.model.dto.UserShortDto;
import de.telran.shop210125mbe.model.entity.CartEntity;
import de.telran.shop210125mbe.model.entity.FavoriteEntity;
import de.telran.shop210125mbe.model.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class Mappers {
    private final ModelMapper modelMapper;

    public UserDto convertToUserDto(UserEntity usersEntity) {
        if(usersEntity==null) return new UserDto(); // дополнительніе проверки
//        modelMapper.typeMap(UsersEntity.class, UserDto.class)
//                .addMappings(mapper -> mapper.skip(UserDto::setEmail)); // исключаем этот метод из работы
        UserDto userDto = modelMapper.map(usersEntity, UserDto.class); //автомат

        if (userDto.getPasswordHash()!=null)
            userDto.setPasswordHash("***"); // замещаем данных

        // преобразовываем вручную
        if (usersEntity.getFavorites()!=null && usersEntity.getFavorites().size() > 0) {
            Set<FavoriteDto> favoritesDtoSet = MapperUtil.convertSet(usersEntity.getFavorites(), this::convertToFavoriteDto);
            userDto.setFavorites(favoritesDtoSet);
        }

        // преобразовываем вручную
        CartDto cartDto = convertToCartDto(usersEntity.getCart()); // второй связанный объект
        userDto.setCart(cartDto);
        return userDto;
    }

    public UserShortDto convertToUserShortDto(UserEntity usersEntity) {

        UserShortDto userDto = modelMapper.map(usersEntity, UserShortDto.class); //автомат

        return userDto;
    }

    public FavoriteDto convertToFavoriteDto(FavoriteEntity favoriteEntity) {
        FavoriteDto favoriteDto = modelMapper.map(favoriteEntity, FavoriteDto.class); //автомат
        favoriteDto.setUser(null);
        return favoriteDto;
    }

    public CartDto convertToCartDto(CartEntity cartEntity) {
        CartDto cartDto = null;
        if(cartEntity!=null)
            cartDto = modelMapper.map(cartEntity, CartDto.class); //автомат
        return cartDto;
    }

    public UserEntity convertToUserEntity(UserDto userDto) {
        UserEntity usersEntity = modelMapper.map(userDto, UserEntity.class); //автомат
        return usersEntity;
    }


}
