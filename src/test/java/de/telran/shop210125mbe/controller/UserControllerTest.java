package de.telran.shop210125mbe.controller;

import de.telran.shop210125mbe.model.dto.UserShortDto;
import de.telran.shop210125mbe.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc; // для иммитации запросов пользователей

    @MockBean
    private UserService userServiceTest;

    @Test
    void getAll() throws Exception {
        // Подготовка
        UserShortDto userShortExpected1 = new UserShortDto(1L, "TestName1", "test1@i.com", "+49111222222222");
        UserShortDto userShortExpected2 = new UserShortDto(2L, "TestName2", "test2@i.com", "+49111333333333");
        List<UserShortDto> expectedList = List.of(userShortExpected1, userShortExpected2);

        when(userServiceTest.getAll()).thenReturn(expectedList); //сымитировал поведение метода

        mockMvc.perform(get("/user"))
                .andDo(print()) //печать лога вызова
                .andExpect(status().isOk()) //200
                .andExpect(jsonPath("$..userId").exists()); //существует ли в JSON тег userId
    }

    @Test
    void getById() {
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
    void create() {
    }

    @Test
    void updatePartProduct() {
    }

    @Test
    void update() {
    }
}