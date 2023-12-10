/*
 * UserRepository.java
 * 
 * TODO: see what queries to add
 */

package sg.edu.ntu.movie_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.ntu.movie_api.entities.User;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query to find all customers with the specified keyword
    List<User> findByEmail(String email);
    //List<User> findByName(String name);
}