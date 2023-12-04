package sg.edu.ntu.movie_api.serviceImpls;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import sg.edu.ntu.movie_api.entities.Interaction;
import sg.edu.ntu.movie_api.exceptions.InteractionNotFoundException;
import sg.edu.ntu.movie_api.repositories.InteractionRepository;
import sg.edu.ntu.movie_api.services.InteractionService;

@Service
public class InteractionServiceImpl implements InteractionService{

    private InteractionRepository interactionRepository;

    // @Autowired
    public InteractionServiceImpl(InteractionRepository interactionRepository) {
        this.interactionRepository = interactionRepository;
    }

    @Override
    public Interaction createInteraction(Interaction interaction) {
        return interactionRepository.save(interaction);
    }

    @Override
    public Interaction getInteraction(Long id) {
        // [Activity 1 - Refactor code]
        return interactionRepository.findById(id).orElseThrow(() -> new InteractionNotFoundException(id));
    }

    @Override
    public ArrayList<Interaction> getAllInteractions() {
        return (ArrayList<Interaction>) interactionRepository.findAll();
    }

    @Override
    public Interaction updateInteraction(Long id, Interaction interaction) {
        // find from database
        // [Activity 1 - Refactor code]
        Interaction interactionToUpdate = interactionRepository.findById(id).orElseThrow(() -> new InteractionNotFoundException(id));

        // update interactionToUpdate with details from client
        interactionToUpdate.setInteractionDate(interaction.getInteractionDate());
        interactionToUpdate.setRemarks(interaction.getRemarks());

        // save and return interaction
        return interactionRepository.save(interaction);
    }

    @Override
    public void deleteInteraction(Long id) {
        interactionRepository.deleteById(id);
    }
    
}
