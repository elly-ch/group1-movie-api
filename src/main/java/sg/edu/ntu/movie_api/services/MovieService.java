// package sg.ntu.edu.simplecrm;
package sg.edu.ntu.movie_api.services;

import java.util.ArrayList;

import sg.edu.ntu.movie_api.entities.Movie;
import sg.edu.ntu.movie_api.entities.UserMovie;

public interface MovieService {

    Movie createMovie(Movie movie);

    Movie getMovie(Long movieId);

    ArrayList<Movie> getAllMovies();

    Movie updateMovie(Long movieId, Movie movie);

    void deleteMovie(Long movieId);

    // TODO: Interaction with genre
    // Genre addInteractiontoGenre(Long movieId, Genre interaction);

    ArrayList<Movie> searchMovies(String movieTitle);

    /*
     * FOR `USERMOVIE` ENDPOINTS:
     * <url> /users/userid/movies
     * <url> /users/userid/movies/movieid
     * 
     * Author: Georgiana
     */

    // code below

    UserMovie addUserMovie(Long userid, Long movieid); // POST: <url> /movies/{movieid}/users/{userid}

    UserMovie getUserMovie(Long userid, Long movieid); // GET: <url> /movies/{movieid}/users/{userid}

    void deleteUserMovie(Long userid, Long movieid); // DELETE: <url> /movies/{movieid}/users/{userid}

    ArrayList<UserMovie> getAllUserMovies(Long movieid); // GET: <url> /movies/{movieid}/users/

}
