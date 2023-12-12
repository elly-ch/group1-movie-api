// package sg.ntu.edu.simplecrm;
package sg.edu.ntu.movie_api.serviceImpls;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService{

    private MovieRepository movieRepository;

    // TODO: Amend by adding interaction with genre?
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

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

    // TODO:
    // Interaction addInteractiontoMovie(Long movieId, Interaction genre);
    
}
