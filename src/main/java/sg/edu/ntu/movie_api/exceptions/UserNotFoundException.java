/*
 * UserNotFoundException.java
 * 
 * TODO: implement tests?
 * 
 */

package sg.edu.ntu.movie_api.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String id) {
        super("Could not find user with email: " + id + ".");
    }
}
