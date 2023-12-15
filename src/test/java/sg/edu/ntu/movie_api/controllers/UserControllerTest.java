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

    // @Test
    // public void validCustomerCreationTest() throws Exception {
    // Customer customer =
    // Customer.builder().firstName("Clint").lastName("Barton").email("clint@avengers.com")
    // .contactNo("12345678").jobTitle("Special Agent").yearOfBirth(1975).build();

    // String newCustomerAsJSON = objectMapper.writeValueAsString(customer);

    // RequestBuilder request =
    // MockMvcRequestBuilders.post("/customers").contentType(MediaType.APPLICATION_JSON)
    // .content(newCustomerAsJSON);

    // mockMvc.perform(request)
    // .andExpect(status().isCreated())
    // .andExpect(content().contentType(MediaType.APPLICATION_JSON))
    // .andExpect(jsonPath("$.id").value(4))
    // .andExpect(jsonPath("$.firstName").value("Clint"))
    // .andExpect(jsonPath("$.lastName").value("Barton"));
    // }

    // @Test
    // public void invalidCustomerCreationTest() throws Exception {

    // Customer invalidCustomer = new Customer(3L, " ", " ", "bruce@a.com",
    // "12345678", "Manager", 1990, null);

    // String invalidCustomerAsJSON =
    // objectMapper.writeValueAsString(invalidCustomer);

    // RequestBuilder request = MockMvcRequestBuilders.post("/customers")
    // .contentType(MediaType.APPLICATION_JSON)
    // .content(invalidCustomerAsJSON);

    // mockMvc.perform(request)
    // .andExpect(status().isBadRequest())
    // .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    // }
}
