// package sg.ntu.edu.simplecrm;
package sg.edu.ntu.movie_api.serviceImpls;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import sg.edu.ntu.movie_api.entities.Movie;
import sg.edu.ntu.movie_api.exceptions.MovieNotFoundException;
import sg.edu.ntu.movie_api.repositories.MovieRepository;
import sg.edu.ntu.movie_api.services.MovieService;

@Service
public class MovieServiceImpl implements MovieService{

    private MovieRepository movieRepository;
    // private GenreRepository genreRepository;

    // TODO: Interaction with genre. Hide this and enable line 20 to 30.
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // public MovieServiceImpl(MovieRepository movieRepository, GenreRepository genreRepository) {
    //     this.movieRepository = movieRepository;
    //     this.genreRepository = genreRepository;
    // }

    // @Override
    // public Genre addInteractionToGenre(Long movieId, Genre interaction) {
    //     Movie selectedMovie = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId));
    //     interaction.setMovie(selectedMovie);
    //     return genreRepository.save(interaction);
    // }

    @Override
    public ArrayList<Movie> searchMovies(String movieTitle) {
        List<Movie> foundMovies = movieRepository.findByMovieTitle(movieTitle);
        return (ArrayList<Movie>) foundMovies;
    }
    
    @Override
    public Movie createMovie(Movie movie) {
        Movie newMovie = movieRepository.save(movie);
        return newMovie;
    }

    @Override
    public Movie getMovie(Long movieId) {
        return movieRepository.findById(movieId).orElseThrow(()-> new MovieNotFoundException(movieId));
    }

    @Override
    public ArrayList<Movie> getAllMovies() {
        List<Movie> allMovies = movieRepository.findAll();
        return (ArrayList<Movie>) allMovies;
    }

    @Override
    public Movie updateMovie(Long movieId, Movie movie) {
        Movie movieToUpdate = movieRepository.findById(movieId).orElseThrow(()-> new MovieNotFoundException(movieId));
        // Updating...
        movieToUpdate.setMovieTitle(movie.getMovieTitle());
        movieToUpdate.setMovieYear(movie.getMovieYear());
        movieToUpdate.setMovieDescription(movie.getMovieDescription());
        // Save to database...
        return movieRepository.save(movieToUpdate);
    }

    @Override
    public void deleteMovie(Long movieId) {
        movieRepository.deleteById(movieId);
    }
    
}
