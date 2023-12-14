package sg.edu.ntu.movie_api.entities;

import java.io.Serializable;

public class UserMovieId implements Serializable {
    // to create composite key for UserMovie

    private Long userid;
    private Long movieid;

    // constructors, equals, hashCode, getters, and setters

    public UserMovieId() {
    }

    public UserMovieId(Long userid, Long movieid) {
        this.userid = userid;
        this.movieid = movieid;
    }
}