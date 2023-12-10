package sg.edu.ntu.movie_api.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usermovie")
@Getter
@Setter
public class UserMovie {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "id")
    // private Long id;

    // // [Activity 2 - validation]
    // @Size(min = 3, max = 30, message = "Remarks has to be more than 3 characters
    // and less than 30 characters")
    // @Column(name = "remarks")
    // private String remarks;

    // // [Activity 2 - validation]
    // @PastOrPresent(message = "Interaction Date must be in the past or present.")
    // @Column(name = "interaction_date")
    // private LocalDate interactionDate;

    @JsonBackReference
    @ManyToOne(optional = false)
    @MapsId("userid")
    @JoinColumn(name = "userid")
    private User user;

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "movieid", referencedColumnName = "movieid")
    private Movie movie;

    private Long userMovieid;
}