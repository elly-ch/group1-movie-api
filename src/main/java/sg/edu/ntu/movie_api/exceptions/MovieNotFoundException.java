// package sg.ntu.edu.simplecrm;
package sg.edu.ntu.movie_api.exceptions;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(Long movieId) {
        super("Could not find movie with id: " + movieId + ".");
    }
    
}
