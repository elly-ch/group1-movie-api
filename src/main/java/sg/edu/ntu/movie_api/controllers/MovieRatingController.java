package sg.edu.ntu.movie_api.controllers;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.ntu.movie_api.entities.MovieRating;
import sg.edu.ntu.movie_api.services.MovieRatingService;

@RestController
@RequestMapping("/movieRatings")
public class MovieRatingController {

    private MovieRatingService movieRatingService;

    public MovieRatingController(MovieRatingService movieRatingService) {
        this.movieRatingService = movieRatingService;
    }

    @GetMapping("/search")
    public ResponseEntity<ArrayList<MovieRating>> searchMovieRatings(@RequestParam Integer rating) {
        ArrayList<MovieRating> foundMovieRatings = movieRatingService.searchMovieRatings(rating);
        return new ResponseEntity<>(foundMovieRatings, HttpStatus.OK);
    }

    // READ (GET ALL)
    @GetMapping("")
    public ResponseEntity<ArrayList<MovieRating>> getAllMovieRatings() {
        ArrayList<MovieRating> allMovieRatings = movieRatingService.getAllMovieRatings();
        return new ResponseEntity<>(allMovieRatings, HttpStatus.OK);
    }

    // READ (GET ONE)
    @GetMapping("/{rating}")
    public ResponseEntity<MovieRating> getMovieRating(@PathVariable Long id) {
        MovieRating foundMovieRating = movieRatingService.getMovieRating(id);
        return new ResponseEntity<>(foundMovieRating, HttpStatus.OK);
    }

    // // UPDATE
    // @PutMapping("/{id}")
    // public ResponseEntity<MovieRating> updateMovieRating(@PathVariable Long id, @RequestBody MovieRating movieRating) {
    //     MovieRating updatedMovieRating = movieRatingService.updateMovieRating(id, movieRating);
    //     return new ResponseEntity<>(updatedMovieRating, HttpStatus.OK);
    // }

}
