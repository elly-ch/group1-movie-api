package sg.edu.ntu.movie_api.controllers;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// import sg.edu.ntu.movie_api.entities.MovieRating;
import sg.edu.ntu.movie_api.entities.User;
import sg.edu.ntu.movie_api.entities.UserMovie;
// import sg.edu.ntu.movie_api.services.MovieRatingService;
import sg.edu.ntu.movie_api.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    /*******************************************************************
     * FOR `USERS` ENDPOINTS:
     * <url> /users
     * <url> /users/userid
     * 
     * Author: Elly
     ******************************************************************/
    private UserService userService;

    // @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // @GetMapping("/search") // TODO: see if we want to add this endpoint, and add
    // search by name
    public ResponseEntity<ArrayList<User>> searchUsers(@Valid @RequestParam String email) {
        ArrayList<User> foundUsers = userService.searchUsersByEmail(email);
        return new ResponseEntity<>(foundUsers, HttpStatus.OK);
    }

    // CREATE i.e. POST /users
    @PostMapping("")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    // READ (GET ALL) i.e. GET /users
    @GetMapping("")
    public ResponseEntity<ArrayList<User>> getAllUsers() {
        ArrayList<User> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    // READ (GET ONE) i.e. GET /users/{userid}
    @GetMapping("/{userid}")
    public ResponseEntity<User> getUser(@PathVariable Long userid) {
        User foundUser = userService.getUser(userid);
        return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }

    // UPDATE
    @PutMapping("/{userid}")
    public ResponseEntity<User> updateUser(@PathVariable Long userid, @RequestBody User user) {
        User updatedUser = userService.updateUser(userid, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{userid}")
    public ResponseEntity<User> deleteUser(@PathVariable Long userid) {
        userService.deleteUser(userid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // // Nested route - add interaction to customer
    // @PostMapping("/{id}/interactions")
    // public ResponseEntity<Interaction> addInteractionToCustomer(@PathVariable
    // Long id, @Valid @RequestBody Interaction interaction) {
    // Interaction newInteraction = customerService.addInteractionToCustomer(id,
    // interaction);
    // return new ResponseEntity<>(newInteraction, HttpStatus.CREATED);
    // }

    /*******************************************************************
     * FOR `USERMOVIE` ENDPOINTS:
     * <url> /users/userid/movies
     * <url> /users/userid/movies/movieid
     * 
     * Author: Georgiana
     ******************************************************************/

    // code below
    @GetMapping("/{userid}/movies")
    public ResponseEntity<ArrayList<UserMovie>> getAllUserMovies(@PathVariable Long userid) {
        ArrayList<UserMovie> foundUserMovies = userService.getAllUserMovies(userid);
        return new ResponseEntity<>(foundUserMovies, HttpStatus.OK);
    }

    @PostMapping("/{userid}/movies/{movieid}")
    public ResponseEntity<UserMovie> addUserMovie(@PathVariable Long userid, @PathVariable Long movieid) {
        UserMovie newUserMovie = userService.addUserMovie(userid, movieid);
        return new ResponseEntity<>(newUserMovie, HttpStatus.CREATED);
    }

    @GetMapping("/{userid}/movies/{movieid}")
    public ResponseEntity<UserMovie> getUserMovie(@PathVariable Long userid, @PathVariable Long movieid) {
        UserMovie foundUserMovie = userService.getUserMovie(userid, movieid);
        return new ResponseEntity<>(foundUserMovie, HttpStatus.OK);
    }

    @DeleteMapping("/{userid}/movies/{movieid}")
    public ResponseEntity<UserMovie> deleteUserMovie(@PathVariable Long userid, @PathVariable Long movieid) {
        userService.deleteUserMovie(userid, movieid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
