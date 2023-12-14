package sg.edu.ntu.movie_api.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "usermovie")
@IdClass(UserMovieId.class)
public class UserMovie {

    @Id
    private Long userid;

    @Id
    private Long movieid;

    @JsonBackReference
    @ManyToOne(optional = false)
    @MapsId("userid")
    @JoinColumn(name = "userid", insertable = false, updatable = false)
    private User user;

    @JsonBackReference
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