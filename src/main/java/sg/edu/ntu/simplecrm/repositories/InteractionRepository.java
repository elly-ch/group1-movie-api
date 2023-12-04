package sg.edu.ntu.simplecrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.ntu.simplecrm.entities.Interaction;

public interface InteractionRepository extends JpaRepository<Interaction, Long> {
    
}
