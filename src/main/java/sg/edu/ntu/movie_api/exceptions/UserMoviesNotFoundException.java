package sg.edu.ntu.movie_api.exceptions;

public class UserMoviesNotFoundException extends RuntimeException {
    public UserMoviesNotFoundException() {
        super("No user-movie record found.");
    }
}
