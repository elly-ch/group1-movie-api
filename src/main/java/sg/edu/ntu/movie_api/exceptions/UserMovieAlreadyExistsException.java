package sg.edu.ntu.movie_api.exceptions;

public class UserMovieAlreadyExistsException extends RuntimeException {
    public UserMovieAlreadyExistsException(Long movieid, Long userid) {
        super("User " + userid + "already saved movie " + movieid + ".");
    }

}
