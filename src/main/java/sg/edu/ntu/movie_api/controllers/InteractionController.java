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
import org.springframework.web.bind.annotation.RestController;

import sg.edu.ntu.movie_api.entities.Interaction;
import sg.edu.ntu.movie_api.exceptions.InteractionNotFoundException;
import sg.edu.ntu.movie_api.services.InteractionService;

@RestController
@RequestMapping("/interactions")
public class InteractionController {
    
    private InteractionService InteractionService;

    // @Autowired
    public InteractionController(InteractionService InteractionService) {
        this.InteractionService = InteractionService;
    }

    // CREATE
    @PostMapping("")
    // [Activity 2 - validation]
    public ResponseEntity<Interaction> createInteraction(@Valid @RequestBody Interaction Interaction) {
        Interaction newInteraction = InteractionService.createInteraction(Interaction);
        return new ResponseEntity<>(newInteraction, HttpStatus.CREATED);
    }

    // READ (GET ALL)
    @GetMapping("")
    public ResponseEntity<ArrayList<Interaction>> getAllInteractions() {
        ArrayList<Interaction> allInteractions = InteractionService.getAllInteractions();
        return new ResponseEntity<>(allInteractions, HttpStatus.OK);
    }

    // READ (GET ONE)
    @GetMapping("/{id}")
    public ResponseEntity<Interaction> getInteraction(@PathVariable Long id) {
        try {
            Interaction foundInteraction = InteractionService.getInteraction(id);
            return new ResponseEntity<>(foundInteraction, HttpStatus.OK);
        } catch (InteractionNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Interaction> updateInteraction(@PathVariable Long id, @RequestBody Interaction Interaction) {
        try {
            Interaction updatedInteraction = InteractionService.updateInteraction(id, Interaction);
            return new ResponseEntity<>(updatedInteraction, HttpStatus.OK);
        } catch (InteractionNotFoundException e) {
            // not found, return 404
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }                
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Interaction> deleteInteraction(@PathVariable Long id) {
        try {
            InteractionService.deleteInteraction(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (InteractionNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
