package sg.edu.ntu.movie_api.services;

import java.util.ArrayList;

import sg.edu.ntu.movie_api.entities.UserMovie;
import sg.edu.ntu.movie_api.entities.MovieRating;
import sg.edu.ntu.movie_api.entities.User;

public interface UserService {
    
    User createUser(User user);

    User getUser(Long userid);

    ArrayList<User> getAllUsers();

    User updateUser(Long userid, User user);

    void deleteUser(Long userid); 

    UserMovie addUserMovieToUser(Long userid, UserMovie userMovie);

    MovieRating addMovieRatingToUser(Long userid, MovieRating movieRating);

    ArrayList<User> searchUsersByEmail(String userid);
}
