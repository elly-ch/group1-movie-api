// package sg.ntu.edu.simplecrm;
package sg.edu.ntu.movie_api.services;

import java.util.ArrayList;

public interface MovieService {

    Movie createMovie(Movie movie);

    Movie getMovie(Long movieId);

    ArrayList<Movie> getAllMovies();

    Movie updateMovie(Long movieId, Movie movie);

    void deleteMovie(Long movieId);

    // TODO: Interaction with genre
    // Genre addInteractiontoGenre(Long movieId, Genre interaction);

    ArrayList<Movie> searchMovies(String movieTitle);
    
}
