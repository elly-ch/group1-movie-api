package sg.edu.ntu.movie_api.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.ntu.movie_api.entities.UserMovie;

@Transactional
public interface UserMovieRepository extends JpaRepository<UserMovie, Long> {
    Optional<UserMovie> findByUseridAndMovieid(Long userid, Long movieid);

    List<UserMovie> findByUserid(Long userid);

    List<UserMovie> findByMovieid(Long movieid);

    void deleteByUseridAndMovieid(Long userid, Long movieid);

}