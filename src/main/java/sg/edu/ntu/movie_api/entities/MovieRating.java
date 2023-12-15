package sg.edu.ntu.movie_api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@Table(name = "movieRating")
public class MovieRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratingId;

    private Long userid;
    private Long movieid;

    @JsonBackReference("user-movierating")
    @ManyToOne(optional = false)
    @MapsId("userid")
    @JoinColumn(name = "userid", insertable = false, updatable = false)
    private User user;

    @JsonBackReference("movie-movierating")
    @ManyToOne(optional = false)
    @JoinColumn(name = "movieid", insertable = false, updatable = false)
    private Movie movie;

    @Min(value = 0, message = "Rating value must be greater than or equal to 0")
    @Max(value = 5, message = "Rating value must be less than or equal to 5")
    @Column(name = "rating")
    private Integer rating;

    public MovieRating() {
    }

    public MovieRating(Long userid, Long movieid, Integer rating) {
        this.userid = userid;
        this.movieid = movieid;
        this.rating = rating;
    }

    public MovieRating orElseThrow(Object object) {
        return null;
    }



}
