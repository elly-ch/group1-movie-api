package sg.edu.ntu.movie_api.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import sg.edu.ntu.movie_api.entities.MovieRating;
import sg.edu.ntu.movie_api.services.MovieRatingService;



import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieRatingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieRatingService movieRatingService;

    @Test
    @DisplayName("Update Movie Rating")
    public void updateMovieRatingTest() throws Exception {
        // Given
        Long userId = 1L;
        Long movieId = 1L;
        Integer newRating = 5;
    
        MovieRating existingRating = new MovieRating();
        existingRating.setUserid(userId);
        existingRating.setMovieid(movieId);
        existingRating.setRating(3);
    
        given(movieRatingService.getMovieRatingByUseridAndMovieid(userId, movieId)).willReturn(existingRating);
    
        // When
        RequestBuilder request = MockMvcRequestBuilders.put("/movieRatings/users/1/movies/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(newRating));
    
        // Then
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.userid").value(userId))
                .andExpect(jsonPath("$.movieid").value(movieId))
                .andExpect(jsonPath("$.rating").value(newRating));
    }
    
    
    
    @Test
    @DisplayName("Update Non-Existing Movie Rating - Not Found")
    public void updateNonExistingMovieRatingTest() throws Exception {
        // Given
        Long userId = 1L;
        Long movieId = 3L;
        Integer newRating = 4;
    
        given(movieRatingService.getMovieRatingByUseridAndMovieid(userId, movieId)).willReturn(null);
    
        // When
        RequestBuilder request = MockMvcRequestBuilders.put("/movieRatings/users/1/movies/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(newRating));
    
        // Then
        mockMvc.perform(request)
                .andExpect(status().isNotFound());
    }
    
}


