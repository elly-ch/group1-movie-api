package sg.edu.ntu.movie_api.serviceImpls;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;
import sg.edu.ntu.movie_api.services.MovieRatingService;
import sg.edu.ntu.movie_api.entities.MovieRating;

import sg.edu.ntu.movie_api.exceptions.MovieRatingNotFoundException;
import sg.edu.ntu.movie_api.repositories.MovieRatingRepository;



@Service
public class MovieRatingServiceImpl implements MovieRatingService{

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
    public MovieRating getMovieRating(Long id) {
       //  Optional<Customer> optionalCustomer = customerRepository.findById(id);
        // if(optionalCustomer.isPresent()) {
        //     Customer foundCustomer = optionalCustomer.get();
        //     return foundCustomer;
        // }
        // throw new CustomerNotFoundException(id);
       return movieRatingRepository.findById(id).orElseThrow(()-> new MovieRatingNotFoundException(id));
 }

    @Override
    public ArrayList<MovieRating> getAllMovieRatings() {
        List<MovieRating> allMovieRatings = movieRatingRepository.findAll();
        return (ArrayList<MovieRating>) allMovieRatings;
    }

    @Override
    public MovieRating updateMovieRating(Long id, MovieRating movieRating) {
        // retrieve the movieRating from the database
        // [Activity 1 - Refactor code]
        MovieRating movieRatingToUpdate = movieRatingRepository.findById(id).orElseThrow(()-> new MovieRatingNotFoundException(id));
        // update the movieRating retrieved from the database
        movieRatingToUpdate.setRating(movieRating.getRating());

        // save the updated customer back to the database
        return movieRatingRepository.save(movieRatingToUpdate);
    }



}
