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
        .andExpect(jsonPath("$.movieId").value(1));
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
        .andExpect(jsonPath("$.movieId").value(3))
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


}
