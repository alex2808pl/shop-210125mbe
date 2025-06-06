package de.telran.shop210125mbe.repository;

import de.telran.shop210125mbe.model.entity.UserEntity;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest()
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepositoryTest;

    private UserEntity userEntityTemplate;

    @BeforeAll
    static void setStart() {
        System.out.println("Выполняется setStart(единоразово, перед запуском всех тестов)!");
    }

    @AfterAll
    static void setEnd() {
        System.out.println("Выполняется setEnd(единоразово, после выполнения всех тестов!");
    }


    @BeforeEach
    void setUp() {
        System.out.println("Выполняется setUp(единоразово, перед запуском всех тестов)!");
        userEntityTemplate = UserEntity.builder()
                .name("TestUser")
                .email("test@i.com")
                .build();
        userEntityTemplate = userRepositoryTest.save(userEntityTemplate);

    }

    @AfterEach
    void tearDown() {
        System.out.println("Выполняется tearDown(единоразово, после выполнения всех тестов!");
        userRepositoryTest.delete(userEntityTemplate);
    }


    @Test
    void findByEmailNativeQueryTest() {
        // Подготовка
        String emailExpected = "test@i.com";

        //Выполнение
        UserEntity userEntityReturn = userRepositoryTest.findByEmailNativeQuery(emailExpected);

        //Проверка
        assertNotNull(userEntityReturn);
        assertEquals(emailExpected, userEntityReturn.getEmail());

    }

    @Test
    void saveUpdateTest() { // стандартные методы тестировать бессмысленно (но можно)
        // Подготовка

        Long expectedId = userEntityTemplate.getUserId();
        String newNameUserExpected = "NewTestUser";

        //Выполнение
        UserEntity userEntityReturn = userRepositoryTest.findById(expectedId).orElse(null);
        assertNotNull(userEntityReturn);
        assertNotNull(userEntityReturn.getUserId());

        userEntityReturn.setName(newNameUserExpected); //меняем имя
        userEntityReturn = userRepositoryTest.save(userEntityReturn); //update

        //Проверка корректности выполнения теста
        assertNotNull(userEntityReturn);
        assertEquals(newNameUserExpected, userEntityReturn.getName());

    }

    @Test
    void findByNameHql() {
    }

    @Test
    void setPhoneNumber() {
    }

    @Test
    void findByNameContainingAndEmail() {
    }
}