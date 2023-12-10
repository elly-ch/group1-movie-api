package sg.edu.ntu.movie_api.services;

import java.util.ArrayList;

public class UserMovieService {
    // CREATE
    UserMovie createUserMovie(UserMovie userMovie);

    // READ GET ONE
    UserMovie getUserMovie(Long userMovieid);

    // READ GET ALL
    ArrayList<UserMovie> getAllUserMovies();

    // UPDATE
    UserMovie updateUserMovie(Long id, UserMovie userMovie);

    // DELETE
    void deleteUserMovie(Long userMovid);
}
