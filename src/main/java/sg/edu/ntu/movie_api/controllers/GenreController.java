package sg.edu.ntu.movie_api.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
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

import sg.edu.ntu.movie_api.entities.Genre;
import sg.edu.ntu.movie_api.services.GenreService;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    // CREATE (POST)
    @PostMapping("")
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genre) {
        Genre newGenre = genreService.createGenre(genre);
        return new ResponseEntity<>(newGenre, HttpStatus.CREATED);
    }

    // READ (GET ALL)
    @GetMapping("")
    public ResponseEntity<ArrayList<Genre>> getAllGenres() {
        ArrayList<Genre> allGenres = genreService.getAllGenres();
        return new ResponseEntity<>(allGenres, HttpStatus.OK);
    }

    // READ (GET ONE)
    @GetMapping("/{genreid}")
    public ResponseEntity<Genre> getGenre(@PathVariable Long genreid) {
        Genre foundGenre = genreService.getGenre(genreid);
        return new ResponseEntity<>(foundGenre, HttpStatus.OK);
    }

    // UPDATE (PUT)
    @PutMapping("/{genreid}")
    public ResponseEntity<Genre> updateGenre(@PathVariable Long genreid, @RequestBody Genre genre) {
        Genre updatedGenre = genreService.updateGenre(genreid, genre);
        return new ResponseEntity<>(updatedGenre, HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/{genreid}")
    public ResponseEntity<Genre> deleteGenre(@PathVariable Long genreid) {
        genreService.deleteGenre(genreid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
