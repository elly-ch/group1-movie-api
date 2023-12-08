package sg.edu.ntu.movie_api.exceptions;


public class MovieRatingNotFoundException extends RuntimeException {
    public MovieRatingNotFoundException(Long id) {
        super("Could not find movieRating with id: " + id + ".");
    }
}
