package sg.edu.ntu.simplecrm.services;

import java.util.ArrayList;

import sg.edu.ntu.simplecrm.entities.Interaction;

public interface InteractionService {
    
    // CREATE
    Interaction createInteraction(Interaction interaction);

    // READ GET ONE
    Interaction getInteraction(Long id);

    // READ GET ALL
    ArrayList<Interaction> getAllInteractions();

    // UPDATE
    Interaction updateInteraction(Long id, Interaction interaction);

    // DELETE
    void deleteInteraction(Long id);
}
