package sg.edu.ntu.movie_api.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;

import sg.edu.ntu.movie_api.entities.Genre;

@SpringBootTest
@AutoConfigureMockMvc
public class GenreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("Get genre by id")
    @Test
    public void getGenreByIdTest() throws Exception {

        // Step 1: Build a GET request to /genres/1
        RequestBuilder request = MockMvcRequestBuilders.get("/genres/1");

        // Step 2: Perform the request, get the response and assert
        mockMvc.perform(request)
                // Assert that the status code is 200
                .andExpect(status().isOk())
                // Assert that the content type is JSON
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Assert that the id returned is 1
                .andExpect(jsonPath("$.genreid").value(1));
    }

    @Test
    public void getAllGenresTest() throws Exception {
        // Step 1: Build a GET request to /genres
        RequestBuilder request = MockMvcRequestBuilders.get("/genres");

        // Step 2: Perform the request, get the response and assert
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(3));
    }

    @Test
    public void validGenreCreationTest() throws Exception {
        // Step 1: Create a Genre object
        Genre genre = Genre.builder().genreid(1).genreName("Comedy").build();

        // Step 2: Convert the Java object to JSON using ObjectMapper
        String newGenreAsJSON = objectMapper.writeValueAsString(genre);

        // Step 3: Build the request
        RequestBuilder request = MockMvcRequestBuilders.post("/genres")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newGenreAsJSON);

        // Step 4: Perform the request and get the response and assert
        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.genreid").value(1))
                .andExpect(jsonPath("$.genreName").value("Comedy"));
    }

//     @Test
//     public void invalidGenreCreationTest() throws Exception {
//         // Step 1: Create a Genre object with invalid fields
//         Genre invalidGenre = new Genre();

//         // Step 2: Convert the Java object to JSON
//         String invalidGenreAsJSON = objectMapper.writeValueAsString(invalidGenre);

//         // Step 3: Build the request
//         RequestBuilder request = MockMvcRequestBuilders.post("/genres")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(invalidGenreAsJSON);

//         // Step 4: Perform the request and get the response
//         mockMvc.perform(request)
//                 .andExpect(status().isBadRequest())
//                 .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//     }

}
