package sg.edu.ntu.movie_api.serviceImpls;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import sg.edu.ntu.movie_api.services.MovieRatingService;
import sg.edu.ntu.movie_api.entities.MovieRating;

import sg.edu.ntu.movie_api.exceptions.MovieRatingNotFoundException;

import sg.edu.ntu.movie_api.repositories.MovieRatingRepository;

@Service
public class MovieRatingServiceImplwLogging implements MovieRatingService {

 private final Logger logger = LoggerFactory.getLogger(MovieRatingServiceImplwLogging.class);

    private MovieRatingRepository movieRatingRepository;

    // @Autowired
    public MovieRatingServiceImplwLogging(MovieRatingRepository movieRatingRepository) {
        this.movieRatingRepository = movieRatingRepository;
    }

    @Override
    public ArrayList<MovieRating> searchMovieRatings(Integer rating) {
        logger.info("⚪️ MovieRatingServiceImplwLogging.searchMovieRatings() called");
        List<MovieRating> foundMovieRatings = movieRatingRepository.findByRating(rating);
        return (ArrayList<MovieRating>) foundMovieRatings;
    }

    @Override
    public ArrayList<MovieRating> searchMovieRatingsByUserid(Long userid) {
         logger.info("⚪️ MovieRatingServiceImplwLogging.searchMovieRatingsByUserid() called");
        List<MovieRating> foundMovieRatings = movieRatingRepository.findRatingByUserid(userid);
        return (ArrayList<MovieRating>) foundMovieRatings;
    }

    @Override
    public ArrayList<MovieRating> searchMovieRatingsByMovieid(Long movieid) {
         logger.info("⚪️ MovieRatingServiceImplwLogging.searchMovieRatingsByMovieid() called");
        List<MovieRating> foundMovieRatings = movieRatingRepository.findRatingByMovieid(movieid);
        return (ArrayList<MovieRating>) foundMovieRatings;
    }

    @Override
    public ArrayList<MovieRating> getAllMovieRatings() {
        logger.info("⚪️ MovieRatingServiceImplwLogging.getAllMovieRatings() called");
        List<MovieRating> allMovieRatings = movieRatingRepository.findAll();
        return (ArrayList<MovieRating>) allMovieRatings;
    }

    @Override
    public MovieRating getMovieRatingByUseridAndMovieid(Long userid, Long movieid) {
        logger.info("⚪️ MovieRatingServiceImplwLogging.getMovieRatingByUseridAndMovieid() called");
        Optional<MovieRating> optionalMovieRating = movieRatingRepository.findByUseridAndMovieid(userid, movieid);
        return optionalMovieRating.orElseThrow(() -> new MovieRatingNotFoundException(userid, movieid));
    }

    @Override
    public MovieRating updateMovieRating(Long userid, Long movieid, MovieRating movieRating) {
         logger.info("⚪️ MovieRatingServiceImplwLogging.updateMovieRating() called");
        // retrieve the movieRating from the database
        MovieRating movieRatingToUpdate = movieRatingRepository.findByUseridAndMovieid(userid, movieid)
                .orElseThrow(() -> new MovieRatingNotFoundException(userid, movieid));
    
        // update the movieRating retrieved from the database
        movieRatingToUpdate.setRating(movieRating.getRating());
    
        // save the updated customer back to the database
        return movieRatingRepository.save(movieRatingToUpdate);
    }

}
