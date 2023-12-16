// package sg.ntu.edu.simplecrm;
package sg.edu.ntu.movie_api.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long movieid;

    @NotBlank(message = "Movie Title is mandatory")
    @Column(name = "movie_title")
    private String movieTitle;

    @Column(name = "movie_year")
    private int movieYear;

    @Column(name = "movie_description")
    private String movieDescription;

    // TODO: Interaction to genre
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    @JsonManagedReference("movie-movierating")
    private List<MovieRating> ratings;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    @JsonManagedReference("movie-usermovie")
    private List<UserMovie> userMovies;

    public Movie() {
    }

    public Movie(String movieTitle, int movieYear, String movieDescription) {
        this();
        this.movieTitle = movieTitle;
        this.movieYear = movieYear;
        this.movieDescription = movieDescription;
        // this.genre = genre;
    }
}
