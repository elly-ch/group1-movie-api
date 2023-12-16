package sg.edu.ntu.movie_api.exceptions;

public class MovieRatingNotFoundException extends RuntimeException {
    // public MovieRatingNotFoundException(Long id) {
    //     super("Could not find movieRating with id: " + id + ".");
    // }

    public MovieRatingNotFoundException(Long userid, Long movieid) {
        super("Could not find movieRating with id: " + userid + "and" + movieid + ".");
    }
}
