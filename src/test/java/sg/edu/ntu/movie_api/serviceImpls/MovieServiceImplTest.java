// package sg.ntu.edu.simplecrm;
package sg.edu.ntu.movie_api.serviceImpls;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.ntu.movie_api.entities.Movie;
import sg.edu.ntu.movie_api.entities.User;
import sg.edu.ntu.movie_api.entities.UserMovie;
import sg.edu.ntu.movie_api.exceptions.MovieNotFoundException;
import sg.edu.ntu.movie_api.exceptions.UserMovieNotFoundException;
import sg.edu.ntu.movie_api.exceptions.UserMoviesNotFoundException;
import sg.edu.ntu.movie_api.repositories.MovieRepository;
import sg.edu.ntu.movie_api.repositories.UserMovieRepository;
import sg.edu.ntu.movie_api.repositories.UserRepository;

@SpringBootTest
public class MovieServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private UserMovieRepository userMovieRepository;

    @InjectMocks
    MovieServiceImplwLogging movieService;

    @Test
    public void createMovieTest() {
        Movie movie = Movie.builder().movieTitle("Test Movie").movieYear(1234)
                .movieDescription("A test movie description").build();

        when((movieRepository.save(movie))).thenReturn(movie);

        Movie savedMovie = movieService.createMovie(movie);

        assertEquals(movie, savedMovie, "The saved movie is to be same as the new movie");

        verify(movieRepository, times(1)).save(movie);
    }

    @Test
    public void getMovieTest() {
        Movie movie = Movie.builder().movieTitle("Test Movie").movieYear(1234)
                .movieDescription("A test movie description").build();

        Long movieId = 1L;

        when(movieRepository.findById(movieId)).thenReturn(Optional.of(movie));

        Movie retrievedMovie = movieService.getMovie(movieId);

        assertEquals(movie, retrievedMovie);
    }

    @Test
    void testGetMovieNotFound() {
        Long movieId = 1L;
        when(movieRepository.findById(movieId)).thenReturn(Optional.empty());

        assertThrows(MovieNotFoundException.class, () -> movieService.getMovie(movieId));
    }

    @Test
    public void getUserMovieTest() throws Exception {
        Long userid = 1L;
        Long movieid = 1L;

        User user = User.builder().userid(userid).email("user4@gmail.com").password("password4").name("name4").build();

        Movie movie = Movie.builder().movieid(movieid).movieTitle("Test Movie").movieYear(1234)
                .movieDescription("A test movie description").build();

        UserMovie userMovie = UserMovie.builder().userMovieid(1L).userid(userid).movieid(movieid).build();

        when(userRepository.findById(userid)).thenReturn(Optional.of(user));

        when(movieRepository.findById(movieid)).thenReturn(Optional.of(movie));

        when(userMovieRepository.findByUseridAndMovieid(userid, movieid)).thenReturn(Optional.of(userMovie));

        // userMovie is found

        UserMovie foundUserMovie = movieService.getUserMovie(userid, movieid);

        assertEquals(foundUserMovie, userMovie);

        // userMovie not found

        User user2 = User.builder().userid(2L).email("user4@gmail.com").password("password4").name("name4").build();

        when(userRepository.findById(2L)).thenReturn(Optional.of(user2));

        assertThrows(UserMovieNotFoundException.class, () -> movieService.getUserMovie(2L, movieid));

    }

    @Test
    public void getAllUserMoviesTest() throws Exception {
        Long userid = 1L;
        Long movieid = 1L;
        User user = User.builder().userid(userid).email("user4@gmail.com").password("password4").name("name4").build();
        Movie movie = Movie.builder().movieid(movieid).movieTitle("Test Movie").movieYear(1234)
                .movieDescription("A test movie description").build();
        UserMovie userMovie = UserMovie.builder().userMovieid(1L).userid(userid).movieid(movieid).build();
        List<UserMovie> userMovies = new ArrayList<>();
        userMovies.add(userMovie);
        when(movieRepository.findById(movieid)).thenReturn(Optional.of(movie));
        when(userMovieRepository.findByMovieid(movieid)).thenReturn(userMovies);

        // usermovies are found
        ArrayList<UserMovie> foundUserMovies = movieService.getAllUserMovies(movieid);
        assertEquals(foundUserMovies, userMovies);

        // usermovies not found
        Movie movie2 = Movie.builder().movieid(2L).movieTitle("Test Movie").movieYear(1234)
                .movieDescription("A test movie description").build();
        when(movieRepository.findById(2L)).thenReturn(Optional.of(movie2));
        assertThrows(UserMoviesNotFoundException.class, () -> movieService.getAllUserMovies(2L));

    }

}
