package de.telran.shop210125mbe.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.telran.shop210125mbe.model.dto.UserDto;
import de.telran.shop210125mbe.model.dto.UserShortDto;
import de.telran.shop210125mbe.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc; // для иммитации запросов пользователей

    @MockitoBean
    private UserService userServiceTest;

    @Autowired
    private ObjectMapper objectMapper; // для преобразования объекта в Json

    @Test
    void getAllTest() throws Exception {
        // Подготовка
        UserShortDto userShortExpected1 = new UserShortDto(1L, "TestName1", "test1@i.com", "+49111222222222");
        UserShortDto userShortExpected2 = new UserShortDto(2L, "TestName2", "test2@i.com", "+49111333333333");
        List<UserShortDto> expectedList = List.of(userShortExpected1, userShortExpected2);

         when(userServiceTest.getAll()).thenReturn(expectedList); //сымитировал поведение метода
        // doReturn(expectedList).when(userServiceTest.getAll()); //альтернатива

        mockMvc.perform(get("/user"))
                .andDo(print()) //печать лога вызова
                .andExpect(status().isOk()) //200
                .andExpect(jsonPath("$..userId").exists()); //существует ли в JSON тег userId
    }

    @Test
    void getByIdTest() throws Exception {
        when(userServiceTest.getById(anyLong())).thenReturn(
                UserDto.builder()
                        .userId(1L)
                        .name("TestName")
                        .build()
        ); //сымитировал поведение метода

        mockMvc.perform(get("/user/{id}", 1)) // /user/1
                .andDo(print()) //печать лога вызова
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").exists())
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.name").value("TestName")
                );
    }

    @Test
    void getByEmail() {
    }

    @Test
    void getByName() {
    }

    @Test
    void getByNameAndEmail() {
    }

    @Test
    void createTest() throws Exception {
        // подготовка
        Long idExpected = 1L;
        when(userServiceTest.create(any(UserDto.class))).thenReturn(
                UserDto.builder()
                        .userId(idExpected)
                        .name("TestName")
                        .build()
        ); //сымитировал поведение метода

        //выполнение
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                        """
                          {
                              "userId": null,
                              "name": "TestName"
                          }
                          """
                ))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userId").exists())
                .andExpect(jsonPath("$.userId").value(idExpected))
                .andExpect(jsonPath("$.name").value("TestName")
                );
    }

    @Test
    void updatePartProduct() {
    }

    @Test
    void updateTest() throws Exception {
        UserDto userDtoExpected = UserDto.builder()
                .userId(1L)
                .name("NewTestName")
                .build();

        when(userServiceTest.updateFind(userDtoExpected)).thenReturn(userDtoExpected); //сымитировал поведение метода

        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDtoExpected))) //преобразую объект в Json
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.userId").exists())
                .andExpect(jsonPath("$.userId").value(1L))
                .andExpect(jsonPath("$.name").value("NewTestName")
                );
      }
}