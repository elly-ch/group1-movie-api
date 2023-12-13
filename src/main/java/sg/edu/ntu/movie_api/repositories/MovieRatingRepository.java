package sg.edu.ntu.movie_api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.ntu.movie_api.entities.MovieRating;

public interface MovieRatingRepository extends JpaRepository<MovieRating, Long> {

    List<MovieRating> findByRating(Integer rating);

    Optional<MovieRating> findByUserIdAndMovieId(Long userid, Long movie_id);

    List<MovieRating> findAllByUserId(Long userid);

    Optional<MovieRating> findByMovieId(Long movie_id);
    
}

