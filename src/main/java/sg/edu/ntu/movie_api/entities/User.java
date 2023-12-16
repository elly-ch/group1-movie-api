/*
 * User.java
 * 
 * NOTE: primary key is a String `userId`, not a Long `id`
 * 
 * TODO: implement tests
 * TODO: check what other annotations can be used for each field
 * TODO: store encrypted password instead of plain text
 * TODO: check that email must be unique (i.e. no two users can have the same email)
 * 
 */

package sg.edu.ntu.movie_api.entities;

import java.util.List;

//import javax.persistence.Column;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
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
@Table(name = "\"user\"")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Long userid;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference("user-usermovie")
    private List<UserMovie> userMovies;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference("user-movierating")
    private List<MovieRating> movieRatings;

    // below is for dataloader to work:
    public User() {
    }

    public User(Long userid, String email, String password, String name) {
        this();
        this.userid = userid;
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
