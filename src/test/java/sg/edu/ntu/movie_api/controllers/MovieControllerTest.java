// package sg.ntu.edu.simplecrm;
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

import sg.edu.ntu.movie_api.entities.Movie;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("Get movie by id")
    @Test
    public void getMovieByIdTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/movies/1");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.movieid").value(1));
    }

    @DisplayName("Get all movies")
    @Test
    public void getAllMoviesTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/movies");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    public void validMovieCreationTest() throws Exception {
        Movie movie = Movie.builder().movieTitle("Test Movie").movieYear(1234).movieDescription("A Test Movie").build();

        String newMovieAsJSON = objectMapper.writeValueAsString(movie);

        RequestBuilder request = MockMvcRequestBuilders.post("/movies").contentType(MediaType.APPLICATION_JSON)
                .content(newMovieAsJSON);

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.movieid").value(3))
                .andExpect(jsonPath("$.movieTitle").value("Test Movie"))
                .andExpect(jsonPath("$.movieYear").value(1234))
                .andExpect(jsonPath("$.movieDescription").value("A Test Movie"));
    }

    @Test
    public void invalidMovieCreationTest() throws Exception {
        Movie invalidMovie = new Movie(" ", 1234, " ");

        String invalidMovieAsJSON = objectMapper.writeValueAsString(invalidMovie);

        RequestBuilder request = MockMvcRequestBuilders.post("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidMovieAsJSON);

        mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @DisplayName("Get usermovies")
    @Test
    public void getAllUserMoviesTest() throws Exception {
        // Step 1: Build a GET request to /users/1
        RequestBuilder request = MockMvcRequestBuilders.get("/movies/1/users");

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
        RequestBuilder request = MockMvcRequestBuilders.get("/movies/1/users/1");

        // Step 2: Perform the request, get the respinse and assert
        mockMvc.perform(request)
                // Assert that the status code is 200
                .andExpect(status().isOk())
                // Assert that the content type is JSON
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userid").value(1))
                .andExpect(jsonPath("$.movieid").value(1));
    }

}
