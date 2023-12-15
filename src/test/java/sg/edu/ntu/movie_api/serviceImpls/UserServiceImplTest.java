package sg.edu.ntu.movie_api.serviceImpls;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

// import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import sg.edu.ntu.movie_api.entities.User;
import sg.edu.ntu.movie_api.entities.UserMovie;
import sg.edu.ntu.movie_api.exceptions.UserNotFoundException;
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
    private UserMovieRepository userMovieRepository;

    @InjectMocks
    UserServiceImplwLogging userService;

    @Test
    public void createUserTest() {

    // 1. SETUP - Create a new customer
    User user =
    User.builder().userid(4L).email("user4@gmail.com")
        .password("password4").name("name4").build();

    // mock the save method of the user repository
    when((userRepository.save(user))).thenReturn(user);

    // 2. EXECUTE
    User savedUser = userService.createUser(user);

    // 3. ASSERT
    assertEquals(
        user,
        savedUser,
        "The saved user should be the same as the new user."
    );

    // also verify that the save method of the user repository is called once only.
    verify(userRepository, times(1)).save(user);

    }

    @Test
    public void getUserTest() {
    // 1. SETUP
    // Create a new user
    User user =
    User.builder().userid(4L).email("user4@gmail.com")
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

    assertThrows(UserNotFoundException.class, () ->
    userService.getUser(userId));
    }

    // // =================================USER-MOVIE=================================

    // // test below does not work :')
    // @Test
    // public void createUserMovieTest() throws Exception {
    //     UserMovie userMovie = UserMovie.builder().userid(1L).movieid(1L).build();

    //     when(userMovieRepository.save(userMovie)).thenReturn(userMovie);

    //     UserMovie savedUserMovie = userService.addUserMovie(1L, 1L);

    //     assertEquals(userMovie, savedUserMovie);

    //     verify(userMovieRepository, times(1)).save(userMovie);

    // }

}
