package sg.edu.ntu.movie_api.exceptions;

public class GenreNotFoundException extends RuntimeException {
    GenreNotFoundException(Long id) {
        super("Unable to find genre with id: " + id);
    }
}
