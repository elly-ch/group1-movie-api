// package sg.ntu.edu.simplecrm;
package sg.edu.ntu.movie_api.serviceImpls;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import sg.edu.ntu.movie_api.entities.Movie;
import sg.edu.ntu.movie_api.entities.User;
import sg.edu.ntu.movie_api.entities.UserMovie;
import sg.edu.ntu.movie_api.exceptions.MovieNotFoundException;
import sg.edu.ntu.movie_api.exceptions.UserMovieAlreadyExistsException;
import sg.edu.ntu.movie_api.exceptions.UserMovieNotFoundException;
import sg.edu.ntu.movie_api.exceptions.UserMoviesNotFoundException;
import sg.edu.ntu.movie_api.exceptions.UserNotFoundException;
import sg.edu.ntu.movie_api.repositories.MovieRepository;
import sg.edu.ntu.movie_api.repositories.UserMovieRepository;
import sg.edu.ntu.movie_api.repositories.UserRepository;
import sg.edu.ntu.movie_api.services.MovieService;

@Service
public class MovieServiceImplwLogging implements MovieService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImplwLogging.class);

    private MovieRepository movieRepository;
    private UserRepository userRepository;
    private UserMovieRepository userMovieRepository;
    // private GenreRepository genreRepository;

    // TODO: Interaction with genre. Hide this and enable line 20 to 30.
    public MovieServiceImplwLogging(MovieRepository movieRepository, UserRepository userRepository,
            UserMovieRepository userMovieRepository) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
        this.userMovieRepository = userMovieRepository;

    }

    // public MovieServiceImpl(MovieRepository movieRepository, GenreRepository
    // genreRepository) {
    // this.movieRepository = movieRepository;
    // this.genreRepository = genreRepository;
    // }

    // @Override
    // public Genre addInteractionToGenre(Long movieId, Genre interaction) {
    // Movie selectedMovie = movieRepository.findById(movieId).orElseThrow(() -> new
    // MovieNotFoundException(movieId));
    // interaction.setMovie(selectedMovie);
    // return genreRepository.save(interaction);
    // }

    @Override
    public ArrayList<Movie> searchMovies(String movieTitle) {
        logger.info("⚪️ MovieServiceImplwLogging.searchMovies() called");

        List<Movie> foundMovies = movieRepository.findByMovieTitle(movieTitle);
        return (ArrayList<Movie>) foundMovies;
    }

    @Override
    public Movie createMovie(Movie movie) {
        logger.info("⚪️ MovieServiceImplwLogging.createMovie() called");

        Movie newMovie = movieRepository.save(movie);
        return newMovie;
    }

    @Override
    public Movie getMovie(Long movieId) {
        logger.info("⚪️ MovieServiceImplwLogging.getMovie() called");

        return movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId));
    }

    @Override
    public ArrayList<Movie> getAllMovies() {
        logger.info("⚪️ MovieServiceImplwLogging.getAllMovies() called");

        List<Movie> allMovies = movieRepository.findAll();
        return (ArrayList<Movie>) allMovies;
    }

    @Override
    public Movie updateMovie(Long movieId, Movie movie) {
        logger.info("⚪️ MovieServiceImplwLogging.updateMovie() called");

        Movie movieToUpdate = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId));
        // Updating...
        movieToUpdate.setMovieTitle(movie.getMovieTitle());
        movieToUpdate.setMovieYear(movie.getMovieYear());
        movieToUpdate.setMovieDescription(movie.getMovieDescription());
        // Save to database...
        return movieRepository.save(movieToUpdate);
    }

    @Override
    public void deleteMovie(Long movieId) {
        logger.info("⚪️ MovieServiceImplwLogging.deleteMovie() called");

        movieRepository.deleteById(movieId);
    }

    /*
     * FOR `USERMOVIE` ENDPOINTS:
     * <url> /movies/movieid/users
     * <url> /movies/movieid/users/userid
     * 
     * Author: Georgiana
     */

    // code below
    @Override
    public UserMovie getUserMovie(Long userid, Long movieid) {
        logger.info("⚪️ MovieServiceImplwLogging.getUserMovie() called");

        // check if user and movie exist
        userRepository.findById(movieid).orElseThrow(() -> new UserNotFoundException(userid));
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
    public ArrayList<UserMovie> getAllUserMovies(Long movieid) {
        logger.info("⚪️ MovieServiceImplwLogging.getAllUserMovies() called");

        // check if movie exists
        movieRepository.findById(movieid).orElseThrow(() -> new MovieNotFoundException(movieid));

        List<UserMovie> searchUserMovies = userMovieRepository.findByMovieid(movieid);
        if (searchUserMovies.isEmpty()) {
            throw new UserMoviesNotFoundException();
        }
        return (ArrayList<UserMovie>) searchUserMovies;
    }

    @Override
    public UserMovie addUserMovie(Long userid, Long movieid) {
        logger.info("⚪️ MovieServiceImplwLogging.addUserMovie() called");

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
        logger.info("⚪️ MovieServiceImplwLogging.deleteUserMovie() called");

        // check if user and movie exist
        movieRepository.findById(userid).orElseThrow(() -> new UserNotFoundException(userid));
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

}
