package sg.edu.ntu.movie_api.services;

import java.util.ArrayList;

import sg.edu.ntu.movie_api.entities.MovieRating;

public interface MovieRatingService {
    
     MovieRating getMovieRating(Long id);

    ArrayList<MovieRating> getAllMovieRatings();

    MovieRating updateMovieRating(Long id, MovieRating movieRating);

    ArrayList<MovieRating> searchMovieRatings(Integer rating);

}
