package com.aacademy.advanceacademy.controller.integration;

import com.aacademy.advanceacademy.dto.UserDto;
import com.aacademy.advanceacademy.model.User;
import com.aacademy.advanceacademy.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserControllerIntTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveUser() throws JsonProcessingException {
        UserDto userDto = UserDto.builder().email("antonivasilev15@gmail.com").build();
        String requestJson = objectMapper.writeValueAsString(userDto);

        given()
                .contentType(ContentType.JSON)
                .body(requestJson)
                .when()
                .post("http://localhost:3000/api/v1/users")
                .then()
                .statusCode(200)
                .body("email", equalTo("antonivasilev15@gmail.com"));
    }

    @Test
    public void updateUser() throws Exception {
        userRepository.save(User.builder().email("antonivasilev15@gmail.com").build());
        UserDto userDto = UserDto.builder().id(1L).email("antonivasilev15@gmail.com").build();
        String requestJson = objectMapper.writeValueAsString(userDto);

        given()
                .contentType(ContentType.JSON)
                .body(requestJson)
                .when()
                .post("http://localhost:3000/api/v1/users")
                .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("email", equalTo("antonivasilev15@gmail.com"));
    }

    @Test
    public void findByEmail() {
        userRepository.save(User.builder().email("antonivasilev15@gmail.com").build());

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:3000/api/v1/users/byEmail/antonivasilev15@gmail.com")
                .then()
                .statusCode(200);
    }
}
