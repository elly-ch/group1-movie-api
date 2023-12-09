package sg.edu.ntu.movie_api.services;

import java.util.ArrayList;

import sg.edu.ntu.movie_api.entities.UserMovie;
import sg.edu.ntu.movie_api.entities.MovieRating;
import sg.edu.ntu.movie_api.entities.User;
//import sg.edu.ntu.simplecrm.entities.Interaction;


public interface UserService {
    
    User createUser(User user);

    User getUser(String userId);

    ArrayList<User> getAllUsers();

    User updateUser(String id, User user);

    void deleteUser(String id); 

    UserMovie addUserMovieToUser(String userId, UserMovie userMovie);

    ArrayList<User> searchUsers(String userId);
}
