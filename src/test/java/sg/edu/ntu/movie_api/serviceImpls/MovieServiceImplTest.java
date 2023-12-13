// package sg.ntu.edu.simplecrm;
package sg.edu.ntu.movie_api.serviceImpls;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MovieServiceImplTest {

    // @Mock
    // private MovieRepository movieRepository;

    // @InjectMocks
    // MovieServiceImpl movieService;

    // @Test
    // public void createMovieTest() {
    //     Movie movie = Movie.builder().movieTitle("Test Movie").movieYear(1234).movieDescription("A test movie description").build();

    //     when((movieRepository.save(movie))).thenReturn(movie);

    //     Movie savedMovie = movieService.createMovie(movie);

    //     assertEquals(movie, savedMovie, "The saved movie is to be same as the new movie");

    //     verify(movieRepository, times(1)).save(movie);
    // }

    // @Test
    // public void getMovieTest() {
    //     Movie movie = Movie.builder().movieTitle("Test Movie").movieYear(1234).movieDescription("A test movie description").build();

    //     Long movieId = 1L;

    //     when(movieRepository.findById(movieId)).thenReturn(Optional.of(movie));

    //     Movie retrievedMovie = movieService.getMovie(movieId);

    //     assertEquals(movie, retrievedMovie);
    // }
    
    // @Test
    // void testGetMovieNotFound() {
    //     Long movieId = 1L;
    //     when(movieRepository.findById(movieId)).thenReturn(Optional.empty());

    //     assertThrows(MovieNotFoundException.class, () -> movieService.getMovie(movieId));
    // }
}
