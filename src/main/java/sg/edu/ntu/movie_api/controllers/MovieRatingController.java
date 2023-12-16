package sg.edu.ntu.movie_api.controllers;

import java.util.ArrayList;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import sg.edu.ntu.movie_api.repositories.MovieRatingRepository;
import sg.edu.ntu.movie_api.services.MovieRatingService;

@RestController
@RequestMapping("/movieRatings")
public class MovieRatingController {

    @Autowired
    MovieRatingRepository repo; // Added

    private MovieRatingService movieRatingService;

    public MovieRatingController(MovieRatingService movieRatingService) {
        this.movieRatingService = movieRatingService;
    }

    @GetMapping("/searchByRating")
    public ResponseEntity<ArrayList<MovieRating>> searchMovieRatings(@RequestParam Integer rating) {
        ArrayList<MovieRating> foundMovieRatings = movieRatingService.searchMovieRatings(rating);
        return new ResponseEntity<>(foundMovieRatings, HttpStatus.OK);
    }

    @GetMapping("/searchByUserId")
    public ResponseEntity<ArrayList<MovieRating>> searchMovieRatingsByUserid(@RequestParam Long userid) {
        ArrayList<MovieRating> foundMovieRatings = movieRatingService.searchMovieRatingsByUserid(userid);
        return new ResponseEntity<>(foundMovieRatings, HttpStatus.OK);
    }

    @GetMapping("/searchByMovieId")
    public ResponseEntity<ArrayList<MovieRating>> searchMovieRatingsByMovieid(@RequestParam Long movieid) {
        ArrayList<MovieRating> foundMovieRatings = movieRatingService.searchMovieRatingsByMovieid(movieid);
        return new ResponseEntity<>(foundMovieRatings, HttpStatus.OK);
    }

    // READ (GET ALL)
    @GetMapping("")
    public ResponseEntity<ArrayList<MovieRating>> getAllMovieRatings() {
        ArrayList<MovieRating> allMovieRatings = movieRatingService.getAllMovieRatings();
        return new ResponseEntity<>(allMovieRatings, HttpStatus.OK);
    }

    // READ (GET One)
    @GetMapping("/users/{userid}/movies/{movieid}")
    public ResponseEntity<MovieRating> getMovieRatingByUseridAndMovieid(@PathVariable Long userid,
            @PathVariable Long movieid) {
        MovieRating foundMovieRating = movieRatingService.getMovieRatingByUseridAndMovieid(userid, movieid);

        if (foundMovieRating != null) {
            return new ResponseEntity<>(foundMovieRating, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/users/{userid}/movies/{movieid}")
    public ResponseEntity<MovieRating> updateMovieRating(
            @PathVariable Long userid,
            @PathVariable Long movieid,
            @Valid @RequestBody Integer newRating) {
        // Fetch the existing movie rating record, assuming it's stored in a database
        Optional<MovieRating> existingRatingOptional = repo.findByUseridAndMovieid(userid, movieid);
    
        if (existingRatingOptional.isPresent()) {
            MovieRating existingRating = existingRatingOptional.get();
    
            // Update the rating
            existingRating.setRating(newRating);
    
            // Save the updated rating back to the database
            repo.save(existingRating);
    
            return new ResponseEntity<>(existingRating, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Or another appropriate status code
        }
    }
    

}
