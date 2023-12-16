package sg.edu.ntu.movie_api.serviceImpls;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import sg.edu.ntu.movie_api.entities.MovieRating;

import sg.edu.ntu.movie_api.exceptions.MovieRatingNotFoundException;
import sg.edu.ntu.movie_api.repositories.MovieRatingRepository;


@SpringBootTest
public class MovieRatingServiceImplTest {
      @Mock
    private MovieRatingRepository movieRatingRepository;

    @InjectMocks
    MovieRatingServiceImplwLogging movieRatingService; 
    
        @Test
    public void getMovieRatingTest() {
        MovieRating movieRating = MovieRating.builder().userid(1L).movieid(1L)
                .rating(5).build();

        Long movieId = 1L;
        Long userId = 1L;

        when(movieRatingRepository.findByUseridAndMovieid(userId, movieId)).thenReturn(Optional.of(movieRating));

        MovieRating retrievedMovie = movieRatingService.getMovieRatingByUseridAndMovieid(userId, movieId);

        assertEquals(movieRating, retrievedMovie);
    }

        @Test
    void testGetMovieRatingNotFound() {
        Long movieId = 1L;
        Long userId = 1L;
        when(movieRatingRepository.findByUseridAndMovieid(userId, movieId)).thenReturn(Optional.empty());

        assertThrows(MovieRatingNotFoundException.class, () -> movieRatingService.getMovieRatingByUseridAndMovieid(userId, movieId));
    }
}
