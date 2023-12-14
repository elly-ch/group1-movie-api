package sg.edu.ntu.movie_api.serviceImpls;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import sg.edu.ntu.movie_api.entities.Genre;
import sg.edu.ntu.movie_api.entities.User;
import sg.edu.ntu.movie_api.entities.UserMovie;
import sg.edu.ntu.movie_api.repositories.GenreRepository;
import sg.edu.ntu.movie_api.entities.Movie;
import sg.edu.ntu.movie_api.repositories.UserMovieRepository;
import sg.edu.ntu.movie_api.repositories.UserRepository;
import sg.edu.ntu.movie_api.repositories.MovieRepository;

@Component
public class DataLoader {
    private UserRepository userRepository;
    private UserMovieRepository userMovieRepository;
    private GenreRepository genreRepository;

    public DataLoader(UserRepository userRepository, UserMovieRepository userMovieRepository,
            GenreRepository genreRepository) {
        this.userRepository = userRepository;
        this.userMovieRepository = userMovieRepository;
        this.genreRepository = genreRepository;
    }

    private MovieRepository movieRepository;

    public DataLoader(UserRepository userRepository, UserMovieRepository userMovieRepository,
            MovieRepository movieRepository) {
        this.userRepository = userRepository;
        this.userMovieRepository = userMovieRepository;
        this.movieRepository = movieRepository;
    }

    @PostConstruct
    public void loadData() {
        // clear the database first
        userRepository.deleteAll();
        movieRepository.deleteAll();

        // load data here
        userRepository.save(new User(1L, "user1@gmail.com", "password1", "name1"));
        userRepository.save(new User(2L, "user2@outlook.com", "password2", "name2"));
        userRepository.save(new User(3L, "user3@yahoo.com", "password3", "name3"));

        // Insert genreRepo here
        genreRepository.save(new Genre(1L, "Comedy"));
        genreRepository.save(new Genre(2L, "Horror"));
        genreRepository.save(new Genre(3L, "Romance"));
        movieRepository.save(new Movie("Bettle Juice", 1909, "Halloween Specials"));
        movieRepository.save(new Movie("Forest Gump", 1978, "A Classic"));

        userMovieRepository.save(new UserMovie(1L, 1L));
        userMovieRepository.save(new UserMovie(1L, 2L));
        userMovieRepository.save(new UserMovie(2L, 3L));
        userMovieRepository.save(new UserMovie(2L, 1L));
    }
}