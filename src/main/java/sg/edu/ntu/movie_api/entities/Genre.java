package sg.edu.ntu.movie_api.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.Column;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@Table(name = "Genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "genre_id")
    private long genreid;

    @Column(name = "genre_name")
    private String genreName;

    // One to Many R/s with Movie.java
    // @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL)
    // private List<Movie> Movie;
    @OneToMany(mappedBy = "movieid")
    private List<Movie> movies;

    // DataLoader
    public Genre() {
    }

    public Genre(Long genreid, String genreName) {
        this();
        this.genreid = genreid;
        this.genreName = genreName;
    }
}
