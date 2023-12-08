package sg.edu.ntu.movie_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.ntu.movie_api.entities.MovieRating;

public interface MovieRatingRepository extends JpaRepository<MovieRating, Long> {

    List<MovieRating> findByRating(Integer rating);
    
}

