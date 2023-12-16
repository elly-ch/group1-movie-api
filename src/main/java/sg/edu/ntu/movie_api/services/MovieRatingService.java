package sg.edu.ntu.movie_api.services;

import java.util.ArrayList;

import sg.edu.ntu.movie_api.entities.MovieRating;


public interface MovieRatingService {
    
    MovieRating getMovieRatingByUseridAndMovieid(Long userid, Long movieid);

    ArrayList<MovieRating> getAllMovieRatings();
 
    MovieRating updateMovieRating(Long userid, Long movieid, MovieRating movieRating);

    ArrayList<MovieRating> searchMovieRatings(Integer rating);

    ArrayList<MovieRating> searchMovieRatingsByUserid(Long userid);

    ArrayList<MovieRating> searchMovieRatingsByMovieid(Long movieid);
}
