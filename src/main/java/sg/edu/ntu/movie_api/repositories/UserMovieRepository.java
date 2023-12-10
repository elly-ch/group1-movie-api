package sg.edu.ntu.movie_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.ntu.movie_api.entities.UserMovie;

public interface UserMovieRepository extends JpaRepository<UserMovie, Long> {
}