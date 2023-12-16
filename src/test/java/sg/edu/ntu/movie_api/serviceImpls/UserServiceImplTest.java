package sg.edu.ntu.movie_api.serviceImpls;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import sg.edu.ntu.movie_api.entities.Movie;
import sg.edu.ntu.movie_api.entities.MovieRating;
import sg.edu.ntu.movie_api.entities.User;
import sg.edu.ntu.movie_api.entities.UserMovie;
import sg.edu.ntu.movie_api.exceptions.MovieNotFoundException;
import sg.edu.ntu.movie_api.exceptions.MovieRatingNotFoundException;
import sg.edu.ntu.movie_api.exceptions.UserMovieNotFoundException;
import sg.edu.ntu.movie_api.exceptions.UserMoviesNotFoundException;
import sg.edu.ntu.movie_api.exceptions.UserNotFoundException;
import sg.edu.ntu.movie_api.repositories.MovieRatingRepository;
import sg.edu.ntu.movie_api.repositories.MovieRepository;
import sg.edu.ntu.movie_api.repositories.UserMovieRepository;
import sg.edu.ntu.movie_api.repositories.UserRepository;
// import sg.edu.ntu.simplecrm.entities.Customer;
// import sg.edu.ntu.simplecrm.exceptions.CustomerNotFoundException;
// import sg.edu.ntu.simplecrm.repositories.CustomerRepository;
// import sg.edu.ntu.simplecrm.serviceImpls.CustomerServiceImpl;

@SpringBootTest
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private UserMovieRepository userMovieRepository;

    @InjectMocks
    UserServiceImplwLogging userService;

    @Test
    public void createUserTest() {

        // 1. SETUP - Create a new customer
        User user = User.builder().userid(4L).email("user4@gmail.com")
                .password("password4").name("name4").build();

        // mock the save method of the user repository
        when((userRepository.save(user))).thenReturn(user);

        // 2. EXECUTE
        User savedUser = userService.createUser(user);

        // 3. ASSERT
        assertEquals(
                user,
                savedUser,
                "The saved user should be the same as the new user.");

        // also verify that the save method of the user repository is called once only.
        verify(userRepository, times(1)).save(user);

    }

    @Test
    public void getUserTest() {
        // 1. SETUP
        // Create a new user
        User user = User.builder().userid(4L).email("user4@gmail.com")
                .password("password4").name("name4").build();

        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // 2.EXECUTE
        User retrievedUser = userService.getUser(userId);

        // 3. ASSERT
        assertEquals(user, retrievedUser);
    }

    @Test
    void testGetUserNotFound() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUser(userId));
    }

    // //
    // =================================USER-MOVIE=================================

    // test below does not work :')
    // @Test
    // public void addUserMovieTest() throws Exception {

    // Long userid = 1L;
    // Long movieid = 1L;

    // User user =
    // User.builder().userid(userid).email("user4@gmail.com").password("password4").name("name4").build();

    // Movie movie = Movie.builder().movieTitle("Test Movie").movieYear(1234)
    // .movieDescription("A test movie description").build();

    // UserMovie userMovie =
    // UserMovie.builder().userMovieid(1L).userid(userid).movieid(movieid).build();

    // when(userRepository.findById(userid)).thenReturn(Optional.of(user));

    // when(movieRepository.findById(movieid)).thenReturn(Optional.of(movie));

    // when(userMovieRepository.save(userMovie)).thenReturn(userMovie);

    // UserMovie savedUserMovie = userService.addUserMovie(1L, 1L);

    // assertEquals(userMovie, savedUserMovie);

    // verify(userMovieRepository, times(1)).save(userMovie);

    // }

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

        UserMovie foundUserMovie = userService.getUserMovie(userid, movieid);

        assertEquals(foundUserMovie, userMovie);

        // userMovie not found

        User user2 = User.builder().userid(2L).email("user4@gmail.com").password("password4").name("name4").build();

        when(userRepository.findById(2L)).thenReturn(Optional.of(user2));

        assertThrows(UserMovieNotFoundException.class, () -> userService.getUserMovie(2L, movieid));

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
        when(userRepository.findById(userid)).thenReturn(Optional.of(user));
        when(userMovieRepository.findByUserid(userid)).thenReturn(userMovies);

        // usermovies are found
        ArrayList<UserMovie> foundUserMovies = userService.getAllUserMovies(userid);
        assertEquals(foundUserMovies, userMovies);

        // usermovies not found
        User user2 = User.builder().userid(2L).email("user4@gmail.com").password("password4").name("name4").build();
        when(userRepository.findById(2L)).thenReturn(Optional.of(user2));
        assertThrows(UserMoviesNotFoundException.class, () -> userService.getAllUserMovies(2L));

    }

}
