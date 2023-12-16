package sg.edu.ntu.movie_api.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import sg.edu.ntu.movie_api.entities.User;

//import sg.edu.ntu.simplecrm.entities.Customer;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("Get user by userid")
    @Test
    public void getUserByUserIdTest() throws Exception {
        // Step 1: Build a GET request to /users/1
        RequestBuilder request = MockMvcRequestBuilders.get("/users/1");

        // Step 2: Perform the request, get the respinse and assert
        mockMvc.perform(request)
                // Assert that the status code is 200
                .andExpect(status().isOk())
                // Assert that the content type is JSON
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Assert that the userid returned is 1
                .andExpect(jsonPath("$.userid").value(1));
    }

    @DisplayName("Get all users")
    @Test
    public void getAllUsersTest() throws Exception {
        // Step 1: SETUP
        RequestBuilder request = MockMvcRequestBuilders.get("/users");

        // Step 2 & 3: EXECUTE and ASSERT
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // one thing to note, for Integration test, is end-to-end, so it actually writes
                // into the database
                // the value here 3 reflects the actual results return from the database, so you
                // may have to modify your DataLoader.java to make sure it has 3 records inside
                // your database.
                .andExpect(jsonPath("$.size()").value(3));
    }

    @DisplayName("Get usermovies")
    @Test
    public void getAllUserMoviesTest() throws Exception {
        // Step 1: Build a GET request to /users/1
        RequestBuilder request = MockMvcRequestBuilders.get("/users/1/movies");

        // Step 2: Perform the request, get the respinse and assert
        mockMvc.perform(request)
                // Assert that the status code is 200
                .andExpect(status().isOk())
                // Assert that the content type is JSON
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(2));
    }

    @DisplayName("Get usermovie by userid and movieid")
    @Test
    public void getUserMovieTest() throws Exception {
        // Step 1: Build a GET request to /users/1
        RequestBuilder request = MockMvcRequestBuilders.get("/users/1/movies/1");

        // Step 2: Perform the request, get the respinse and assert
        mockMvc.perform(request)
                // Assert that the status code is 200
                .andExpect(status().isOk())
                // Assert that the content type is JSON
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userid").value(1))
                .andExpect(jsonPath("$.movieid").value(1));
    }

    @Test
    public void validUserCreationTest() throws Exception {
    User user =
    User.builder().userid(4L).email("user4@gmail.com")
                .password("password4").name("name4").build();

    String newUserAsJSON = objectMapper.writeValueAsString(user);

    RequestBuilder request =
    MockMvcRequestBuilders.post("/users").contentType(MediaType.APPLICATION_JSON)
    .content(newUserAsJSON);

    mockMvc.perform(request)
    .andExpect(status().isCreated())
    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
    .andExpect(jsonPath("$.userid").value(4))
    .andExpect(jsonPath("$.email").value("user4@gmail.com"))
    .andExpect(jsonPath("$.password").value("password4"))
    .andExpect(jsonPath("$.name").value("name4"));
    }

    @Test
    public void invalidUserCreationTest() throws Exception {

    User invalidUser = new User(5L, "invalidemail", "password5", "name5");

    String invalidUserAsJSON =
    objectMapper.writeValueAsString(invalidUser);

    RequestBuilder request = MockMvcRequestBuilders.post("/users")
    .contentType(MediaType.APPLICATION_JSON)
    .content(invalidUserAsJSON);

    mockMvc.perform(request)
    .andExpect(status().isBadRequest())
    .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
