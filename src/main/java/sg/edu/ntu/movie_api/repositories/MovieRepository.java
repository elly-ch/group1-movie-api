// package sg.ntu.edu.simplecrm;
package sg.edu.ntu.movie_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.ntu.movie_api.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    // Find all movies with a certain movie title (custom)
    List<Movie> findByMovieTitle(String movieTitle);
    
}
