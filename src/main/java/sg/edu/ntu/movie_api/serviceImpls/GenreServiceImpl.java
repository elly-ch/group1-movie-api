package sg.edu.ntu.movie_api.serviceImpls;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl implements GenreService {

    private GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    // POSTGRES
    @Override
    public ArrayList<Genre> searchGenres(String genreName) {
        List<Genre> foundGenres = genreRepository.findByGenreName(genreName);
        return (ArrayList<Genre>) foundGenres;
    }

    @Override
    public Genre createGenre(Genre genre) {
        Genre newGenre = genreRepository.save(genre);
        return newGenre;
    }

    @Override
    public Genre getGenre(Long id) {
        Optional<Genre> optionalGenre = genreRepository.findById(id);
        if (optionalGenre.isPresent()) {
            Genre foundGenre = optionalGenre.get();
            return foundGenre;
        }
        throw new GenreNotFoundException(id);
    }

    @Override
    public ArrayList<Genre> getAllGenres() {
        List<Genre> allGenres = genreRepository.findAll();
        return (ArrayList<Genre>) allGenre;
    }

    @Override
    public Genre updateGenre(Long id, Genre genre) {
        // retrieve the genre from the database
        Genre genreToUpdate = genreRepository.findById(id).get();
        // update the genre retrieved from the database
        genreToUpdate.setFirstName(genre.getFirstName());
        genreToUpdate.setLastName(genre.getLastName());
        genreToUpdate.setEmail(genre.getEmail());
        genreToUpdate.setContactNo(genre.getContactNo());
        genreToUpdate.setJobTitle(genre.getJobTitle());
        genreToUpdate.setYearOfBirth(genre.getYearOfBirth());
        // save the updated genre back to the database
        return genreRepository.save(genreToUpdate);
    }

    @Override
    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }
}
