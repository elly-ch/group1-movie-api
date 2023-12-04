package sg.edu.ntu.movie_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.ntu.movie_api.entities.Interaction;

public interface InteractionRepository extends JpaRepository<Interaction, Long> {
    
}
