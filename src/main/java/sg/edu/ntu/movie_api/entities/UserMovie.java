package sg.edu.ntu.movie_api.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "usermovie")
public class UserMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userMovieid;

    private Long userid;
    private Long movieid;

    @JsonBackReference("user-usermovie")
    @ManyToOne(optional = false)
    @MapsId("userid")
    @JoinColumn(name = "userid", insertable = false, updatable = false)
    private User user;

    @JsonBackReference("movie-usermovie")
    @ManyToOne(optional = false)
    @JoinColumn(name = "movieid", insertable = false, updatable = false)
    private Movie movie;

    public UserMovie(Long userid, Long movieid) {
        this.userid = userid;
        this.movieid = movieid;
    }

    public UserMovie() {
    }

}