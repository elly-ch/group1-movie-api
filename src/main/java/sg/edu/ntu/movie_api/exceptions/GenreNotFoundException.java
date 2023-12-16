package sg.edu.ntu.movie_api.exceptions;

public class GenreNotFoundException extends RuntimeException {
    public GenreNotFoundException(Long genreid) {
        super("Unable to find genre with id: " + genreid);
    }
}
