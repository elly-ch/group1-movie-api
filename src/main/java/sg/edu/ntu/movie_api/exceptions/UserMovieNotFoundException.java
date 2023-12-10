package sg.edu.ntu.movie_api.exceptions;

public class UserMovieNotFoundException extends RuntimeException {
    UserMovieNotFoundException(Long movieid, Long userid) {
        super("User " + userid + "did not save movie " + movieid);
    }
}
