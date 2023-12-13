package sg.edu.ntu.movie_api.services;

import java.util.ArrayList;

import sg.edu.ntu.movie_api.entities.MovieRating;

public interface MovieRatingService {
    
     MovieRating getMovieRating(Long id);
     MovieRating getMovieRatingForMovie(Long movie_id);
     MovieRating getMovieRatingForUserAndMovie(Long userid, Long movie_id);
    ArrayList<MovieRating> getAllMovieRatings();
    ArrayList<MovieRating> getAllMovieRatingsForUser(Long userid);

    MovieRating updateMovieRating(Long userid, Long movie_id, MovieRating movieRating);

    ArrayList<MovieRating> searchMovieRatings(Integer rating);


}
