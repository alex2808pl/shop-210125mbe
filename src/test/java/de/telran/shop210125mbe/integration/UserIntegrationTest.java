package de.telran.shop210125mbe.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.telran.shop210125mbe.model.dto.UserDto;
import de.telran.shop210125mbe.model.entity.UserEntity;
import de.telran.shop210125mbe.model.enums.Role;
import de.telran.shop210125mbe.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
public class UserIntegrationTest {
    @Autowired
    private MockMvc mockMvc; // для иммитации запросов пользователей

    @MockitoBean
    private UserRepository userRepositoryMock;

    @Autowired
    private ObjectMapper objectMapper; // для преобразования объекта в Json

    @Test
    void getAllIntegrationTestWitDB()  throws Exception {
        //подготовка, необходимо заполнить БД тестовыми данными, как при тестировании Repository

        //имитирую запрос пользователя
        this.mockMvc.perform(get("/user"))
                .andDo(print()) //печать лога вызова
                .andExpect(status().isOk());

    }

    // Имитируем работу с БД
    @Test
    void getAllIntegrationTest() throws Exception {
        when(userRepositoryMock.findAll()).thenReturn( List.of(
                UserEntity.builder()
                        .userId(1L)
                        .name("Test")
                        .email("test@i.ua")
                        .build()
                )
        );


        this.mockMvc.perform(get("/user"))
                .andDo(print()) //печать лога вызова
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..userId").exists())
                .andExpect(jsonPath("$..name").exists());
    }


    @Test
    void createPartSystemUsersTest() throws Exception {

        UserDto usersDtoInput = new UserDto(
                null,
                "Вася Пупкин",
                "vasya@i.com",
                "+491601234567",
                "Password1",
                "ADMIN",
                null,
                null
        );

        this.mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usersDtoInput))) // jackson: object -> json
                .andDo(print())
                .andExpect(status().isCreated());
    }


    // Имитируем работу с БД
    @Test
    void createIntegrationUsersTest() throws Exception {

        UserDto usersDtoInput = new UserDto(
                null,
                "Вася Пупкин",
                "vasya@i.com",
                "+491601234567",
                "Password1",
                "ADMIN",
                null,
                null
        );

        UserEntity userEntityInput = new UserEntity(
                null,
                "Вася Пупкин",
                "vasya@i.com",
                "+491601234567",
                "Password1",
                Role.ADMIN,
                null,
                null
        );

        UserEntity userEntityExpected  = userEntityInput;
        userEntityExpected.setUserId(1L);

        when(userRepositoryMock.save(userEntityInput)).thenReturn(userEntityExpected);

        this.mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usersDtoInput))) // jackson: object -> json
                .andDo(print())
                .andExpect(status().isCreated());
    }
}
