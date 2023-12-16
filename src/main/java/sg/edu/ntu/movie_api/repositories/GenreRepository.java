package sg.edu.ntu.movie_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.ntu.movie_api.entities.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    List<Genre> findByGenreName(String genreName);
}