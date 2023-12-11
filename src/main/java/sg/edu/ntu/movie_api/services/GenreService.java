package sg.edu.ntu.movie_api.services;

import java.util.ArrayList;

public interface GenreService {

    Genre createGenre(Genre genre);

    Genre getGenre(Long genreid);

    ArrayList<Genre> getAllGenres();

    Genre updateGenre(Long genreid, Genre genre);

    void deleteGenre(Long genreid);

    // ArrayList<Genre> searchGenres(String genreName);
}
