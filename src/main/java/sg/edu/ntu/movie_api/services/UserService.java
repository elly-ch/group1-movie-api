package sg.edu.ntu.movie_api.services;

import java.util.ArrayList;

import sg.edu.ntu.movie_api.entities.UserMovie;
// import sg.edu.ntu.movie_api.entities.MovieRating;
import sg.edu.ntu.movie_api.entities.User;

public interface UserService {

    /*
     * FOR `USERS` ENDPOINTS:
     * <url> /users
     * <url> /users/userid
     * 
     * Author: Elly
     */

    User createUser(User user);

    User getUser(Long userid);

    ArrayList<User> getAllUsers();

    User updateUser(Long userid, User user);

    void deleteUser(Long userid);

    // MovieRating addMovieRatingToUser(Long userid, MovieRating movieRating);

    ArrayList<User> searchUsersByEmail(String userid);

    /*
     * FOR `USERMOVIE` ENDPOINTS:
     * <url> /users/userid/movies
     * <url> /users/userid/movies/movieid
     * 
     * Author: Georgiana
     */

    // code below

    UserMovie addUserMovie(Long userid, Long movieid); // POST: <url> /users/{userid}/movies/{movieid}

    UserMovie getUserMovie(Long userid, Long movieid); // GET: <url> /users/{userid}/movies/{movieid}

    void deleteUserMovie(Long userid, Long movieid); // DELETE: <url> /users/{userid}/movies/{movieid}

    ArrayList<UserMovie> getAllUserMovies(Long userid); // GET: <url> /users/{userid}/movies

    /*
     * FOR `MOVIERATING` ENDPOINTS:
     * <url> /users/userid/movies
     * <url> /users/userid/movies/movieid
     * 
     * Author: Anu
     */

    // code below
}
