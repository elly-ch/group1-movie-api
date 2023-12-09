/*
 * UserRepository.java
 * 
 * TODO: see what queries to add
 */

package sg.edu.ntu.movie_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.ntu.movie_api.entities.User;

import java.util.List;


public interface UserRepository extends JpaRepository<User, String> {
    //TODO: check if use of String above is correct (I think it's supposed to be the primary key, which is a String)
    
    // Custom query to find all customers with a certain userId
    List<User> findByUserId(String userId);
}