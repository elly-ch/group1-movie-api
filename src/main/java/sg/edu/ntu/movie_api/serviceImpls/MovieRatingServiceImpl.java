package sg.edu.ntu.movie_api.serviceImpls;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import sg.edu.ntu.movie_api.services.MovieRatingService;
import sg.edu.ntu.movie_api.entities.MovieRating;
import sg.edu.ntu.movie_api.entities.User;
import sg.edu.ntu.movie_api.exceptions.MovieRatingNotFoundException;
import sg.edu.ntu.movie_api.exceptions.UserNotFoundException;
import sg.edu.ntu.movie_api.repositories.MovieRatingRepository;

@Service
public class MovieRatingServiceImpl implements MovieRatingService {

    private MovieRatingRepository movieRatingRepository;

    // @Autowired
    public MovieRatingServiceImpl(MovieRatingRepository movieRatingRepository) {
        this.movieRatingRepository = movieRatingRepository;
    }

    @Override
    public ArrayList<MovieRating> searchMovieRatings(Integer rating) {
        List<MovieRating> foundMovieRatings = movieRatingRepository.findByRating(rating);
        return (ArrayList<MovieRating>) foundMovieRatings;
    }

    @Override
    public ArrayList<MovieRating> searchMovieRatingsByUserid(Long userid) {
        List<MovieRating> foundMovieRatings = movieRatingRepository.findRatingByUserid(userid);
        return (ArrayList<MovieRating>) foundMovieRatings;
    }

    @Override
    public ArrayList<MovieRating> searchMovieRatingsByMovieid(Long movieid) {
        List<MovieRating> foundMovieRatings = movieRatingRepository.findRatingByMovieid(movieid);
        return (ArrayList<MovieRating>) foundMovieRatings;
    }

    @Override
    public ArrayList<MovieRating> getAllMovieRatings() {
        List<MovieRating> allMovieRatings = movieRatingRepository.findAll();
        return (ArrayList<MovieRating>) allMovieRatings;
    }

    @Override
    public MovieRating getMovieRatingByUseridAndMovieid(Long userid, Long movieid) {
        Optional<MovieRating> optionalMovieRating = movieRatingRepository.findByUseridAndMovieid(userid, movieid);
        return optionalMovieRating.orElseThrow(() -> new MovieRatingNotFoundException(userid, movieid));
    }

    @Override
    public MovieRating updateMovieRating(Long userid, Long movieid, MovieRating movieRating) {
        // retrieve the movieRating from the database
        MovieRating movieRatingToUpdate = movieRatingRepository.findByUseridAndMovieid(userid, movieid)
                .orElseThrow(() -> new MovieRatingNotFoundException(userid, movieid));
    
        // update the movieRating retrieved from the database
        movieRatingToUpdate.setRating(movieRating.getRating());
    
        // save the updated customer back to the database
        return movieRatingRepository.save(movieRatingToUpdate);
    }

}
