package sg.edu.ntu.movie_api.exceptions;

public class InteractionNotFoundException extends RuntimeException  {
    public InteractionNotFoundException(Long id) {
        super("Unable to find interactiong with id: "+ id + ",");
    }
}
