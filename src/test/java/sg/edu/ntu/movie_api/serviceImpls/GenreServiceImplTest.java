package sg.edu.ntu.movie_api.serviceImpls;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import sg.edu.ntu.movie_api.entities.Genre;
import sg.edu.ntu.movie_api.repositories.GenreRepository;

@SpringBootTest
public class GenreServiceImplTest {
    
    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    GenreServiceImpl genreService;

    @Test
    public void createGenreTest() {

        // 1. SETUP
        Genre genre = Genre.builder().genreid(1).genreName("Comedy").build();

        // Mock save method of genre repo
        when((genreRepository.save(genre))).thenReturn(genre);

        // 2.EXECUTE
        Genre savedGenre = genreService.createGenre(genre);

        // 3. ASSERT
        assertEquals(genre, savedGenre, "The saved genre should be the same as the new genre");

        // VERIFICATION
        verify(genreRepository, times(1)).save(genre);
    }

    @Test
    public void getGenreTest() {

        // 1. SETUP
        Genre genre = Genre.builder().genreid(1).genreName("Comedy").build();
        Long genreid = 1L;
        when(genreRepository.findById(genreid)).thenReturn(Optional.of(genre));

        // 2.EXECUTE
        Genre retrievedGenre = genreService.getGenre(genreid);

        // 3. ASSERT
        assertEquals(genre, retrievedGenre);
    }
}