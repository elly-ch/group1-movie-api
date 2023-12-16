package sg.edu.ntu.movie_api.serviceImpls;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sg.edu.ntu.movie_api.entities.Movie;
// import sg.edu.ntu.movie_api.entities.MovieRating;
import sg.edu.ntu.movie_api.entities.User;
import sg.edu.ntu.movie_api.entities.UserMovie;
import sg.edu.ntu.movie_api.exceptions.MovieNotFoundException;
import sg.edu.ntu.movie_api.exceptions.UserMovieAlreadyExistsException;
import sg.edu.ntu.movie_api.exceptions.UserMovieNotFoundException;
import sg.edu.ntu.movie_api.exceptions.UserMoviesNotFoundException;
import sg.edu.ntu.movie_api.exceptions.UserNotFoundException;
import sg.edu.ntu.movie_api.repositories.UserRepository;
// import sg.edu.ntu.movie_api.repositories.MovieRatingRepository;
import sg.edu.ntu.movie_api.repositories.MovieRepository;
import sg.edu.ntu.movie_api.repositories.UserMovieRepository;
import sg.edu.ntu.movie_api.services.UserService;

@Service
public class UserServiceImplwLogging implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImplwLogging.class);

    /*
     * FOR `USERS` ENDPOINTS:
     * <url> /users
     * <url> /users/userid
     * 
     * Author: Elly
     */

    private UserRepository userRepository;
    private MovieRepository movieRepository;
    private UserMovieRepository userMovieRepository;
    // private MovieRatingRepository movieRatingRepository;

    // @Autowired
    public UserServiceImplwLogging(UserRepository userRepository, UserMovieRepository userMovieRepository,
            MovieRepository movieRepository) {
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.userMovieRepository = userMovieRepository;
    }

    @Override
    public ArrayList<User> searchUsersByEmail(String email) {
        logger.info("⚪️ UserServiceImplwLogging.searchUsersByEmail() called");

        List<User> foundUsers = userRepository.findByEmail(email);
        return (ArrayList<User>) foundUsers;
    }

    // @Override
    public User createUser(User user) {
        logger.info("⚪️ UserServiceImplwLogging.createUser() called");

        User newUser = userRepository.save(user);
        return newUser;
    }

    @Override
    public User getUser(Long userid) {
        logger.info("⚪️ UserServiceImplwLogging.getUser() called");

        // Note to self: findById (from JPA) takes in the primary key
        return userRepository.findById(userid).orElseThrow(() -> new UserNotFoundException(userid));
    }

    @Override
    public ArrayList<User> getAllUsers() {
        logger.info("⚪️ UserServiceImplwLogging.getAllUsers() called");

        List<User> allUsers = userRepository.findAll();
        return (ArrayList<User>) allUsers;
    }

    @Override
    public User updateUser(Long userid, User user) {
        logger.info("⚪️ UserServiceImplwLogging.updateUser() called");

        // retrieve the customer from the database
        User userToUpdate = userRepository.findById(userid).orElseThrow(() -> new UserNotFoundException(userid));

        // update the customer retrieved from the database
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setName(user.getName());

        // save the updated customer back to the database
        return userRepository.save(userToUpdate);
    }

    @Override
    public void deleteUser(Long userid) {
        logger.info("⚪️ UserServiceImplwLogging.deleteUser() called");

        userRepository.deleteById(userid); // deleteById takes in the primary key
    }

    // @Override
    // public UserMovie addUserMovieToUser(Long userid, UserMovie userMovie) {
    // // TODO: DEBUG
    // // retrieve the user from the database
    // User selectedUser = userRepository.findById(userid).orElseThrow(() -> new
    // UserNotFoundException(userid));

    // // add the customer to the interaction
    // userMovie.setUser(selectedUser);
    // // save the interaction to the database
    // return userMovieRepository.save(userMovie);
    // }

    // @Override
    // public MovieRating addMovieRatingToUser(Long userid, MovieRating movieRating)
    // {
    // // TODO: DEBUG
    // // retrieve the user from the database
    // User selectedUser = userRepository.findById(userid).orElseThrow(() -> new
    // UserNotFoundException(userid));

    // // add the customer to the interaction
    // // TODO: uncomment user field in MovieRating entity for this to work
    // movieRating.setUser(selectedUser);
    // // save the interaction to the database
    // return movieRatingRepository.save(movieRating);
    // }

    /*
     * FOR `USERMOVIE` ENDPOINTS:
     * <url> /users/userid/movies
     * <url> /users/userid/movies/movieid
     * 
     * Author: Georgiana
     */

    // code below
    @Override
    public UserMovie getUserMovie(Long userid, Long movieid) {
        logger.info("⚪️ UserServiceImplwLogging.getUserMovie() called");

        // check if user and movie exist
        userRepository.findById(userid).orElseThrow(() -> new UserNotFoundException(userid));
        movieRepository.findById(movieid).orElseThrow(() -> new MovieNotFoundException(movieid));

        // check if user saved the movie
        // check if there is already an existing record
        Optional<UserMovie> searchUserMovie = userMovieRepository.findByUseridAndMovieid(userid, movieid);
        if (searchUserMovie.isEmpty()) {
            // if user didn't save the movie, nothing to delete & throw exception
            throw new UserMovieNotFoundException(userid, movieid);
        }
        UserMovie foundUserMovie = searchUserMovie.get();

        return foundUserMovie;
    }

    @Override
    public ArrayList<UserMovie> getAllUserMovies(Long userid) {
        logger.info("⚪️ UserServiceImplwLogging.getAllUserMovies() called");

        // check if user exists
        userRepository.findById(userid).orElseThrow(() -> new UserNotFoundException(userid));

        List<UserMovie> searchUserMovies = userMovieRepository.findByUserid(userid);
        if (searchUserMovies.isEmpty()) {
            // if user didn't save the movie, nothing to delete & throw exception
            throw new UserMoviesNotFoundException();
        }
        return (ArrayList<UserMovie>) searchUserMovies;
    }

    @Override
    public UserMovie addUserMovie(Long userid, Long movieid) {
        logger.info("⚪️ UserServiceImplwLogging.addUserMovie() called");

        // TODO: DEBUG
        // retrieve the user and movie from the database
        userRepository.findById(userid).orElseThrow(() -> new UserNotFoundException(userid));
        movieRepository.findById(movieid).orElseThrow(() -> new MovieNotFoundException(movieid));

        // check if there is already an existing record
        Optional<UserMovie> foundUserMovie = userMovieRepository.findByUseridAndMovieid(userid, movieid);
        if (!foundUserMovie.isEmpty()) {
            // if user didn't save the movie, nothing to delete & throw exception
            throw new UserMovieAlreadyExistsException(userid, movieid);
        }

        // add new User-Movie record
        UserMovie newUserMovie = new UserMovie(userid, movieid);
        return userMovieRepository.save(newUserMovie);
    }

    @Override
    public void deleteUserMovie(Long userid, Long movieid) {
        logger.info("⚪️ UserServiceImplwLogging.deleteUserMovie() called");

        // check if user and movie exist
        userRepository.findById(userid).orElseThrow(() -> new UserNotFoundException(userid));
        movieRepository.findById(movieid).orElseThrow(() -> new MovieNotFoundException(movieid));

        // check if user saved the movie
        Optional<UserMovie> foundUserMovie = userMovieRepository.findByUseridAndMovieid(userid, movieid);
        if (foundUserMovie.isEmpty()) {
            // if user didn't save the movie, nothing to delete & throw exception
            throw new UserMovieNotFoundException(userid, movieid);
        } else {
            // if user saved the movie, delete the record
            userMovieRepository.deleteByUseridAndMovieid(userid, movieid);
        }
    }

    /*
     * FOR `MOVIERATING` ENDPOINTS:
     * <url> /users/userid/movies
     * <url> /users/userid/movies/movieid
     * 
     * Author: Anu
     */

    // code below

}
