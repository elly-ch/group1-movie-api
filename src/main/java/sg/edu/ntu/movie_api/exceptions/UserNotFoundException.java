/*
 * UserNotFoundException.java
 * 
 * TODO: implement tests?
 * 
 */

package sg.edu.ntu.movie_api.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userid) {
        super("Could not find user with id: " + userid + ".");
    }
}
