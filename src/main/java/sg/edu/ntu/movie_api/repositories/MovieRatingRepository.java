package sg.edu.ntu.movie_api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import sg.edu.ntu.movie_api.entities.MovieRating;

public interface MovieRatingRepository extends JpaRepository<MovieRating, Long> {

    List<MovieRating> findByRating(Integer rating);

    Optional<MovieRating> findByUseridAndMovieid(Long userid, Long movieid);

    List<MovieRating> findRatingByUserid(Long userid);

    List<MovieRating> findRatingByMovieid(Long movieid);

}
