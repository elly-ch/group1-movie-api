package sg.edu.ntu.movie_api.serviceImpls;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import sg.edu.ntu.movie_api.entities.Genre;
import sg.edu.ntu.movie_api.exceptions.GenreNotFoundException;
import sg.edu.ntu.movie_api.repositories.GenreRepository;
import sg.edu.ntu.movie_api.services.GenreService;

@Service
public class GenreServiceImpl implements GenreService {

    private GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    // // POSTGRES
    // @Override
    // public ArrayList<Genre> searchGenres(String genreName) {
    // List<Genre> foundGenres = genreRepository.findByGenreName(genreName);
    // return (ArrayList<Genre>) foundGenres;
    // }

    // POST
    @Override
    public Genre createGenre(Genre genre) {
        Genre newGenre = genreRepository.save(genre);
        return newGenre;
    }

    // GET (ONE)
    // @Override
    // public Genre getGenre(Long genreid) {
    // Optional<Genre> optionalGenre = genreRepository.findById(genreid);
    // if (optionalGenre.isPresent()) {
    // Genre foundGenre = optionalGenre.get();
    // return foundGenre;
    // }
    // throw new GenreNotFoundException(genreid);
    // }
    // @Override
    // public Genre getGenre(Long genreid) {
    // Genre foundGenre = genreRepository.findById(genreid).get();
    // return foundGenre;
    // }
    @Override
    public Genre getGenre(Long genreid) {
        return genreRepository.findById(genreid).orElseThrow(() -> new GenreNotFoundException(genreid));
    }

    // GET (ALL)
    @Override
    public ArrayList<Genre> getAllGenres() {
        List<Genre> allGenres = genreRepository.findAll();
        return (ArrayList<Genre>) allGenres;
    }

    // UPDATE
    @Override
    public Genre updateGenre(Long genreid, Genre genre) {
        // retrieve the genre from the database
        Genre genreToUpdate = genreRepository.findById(genreid).orElseThrow(() -> new GenreNotFoundException(genreid));
        // update the genre retrieved from the database
        genreToUpdate.setGenreid(genre.getGenreid());
        genreToUpdate.setGenreName(genre.getGenreName());
        // save the updated genre back to the database
        return genreRepository.save(genreToUpdate);
    }

    // DELETE
    @Override
    public void deleteGenre(Long genreid) {
        genreRepository.deleteById(genreid);
    }
}
