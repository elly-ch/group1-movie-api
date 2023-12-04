package sg.edu.ntu.simplecrm.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long id) {
        super("Could not find customer with id: " + id + ".");
    }
}
