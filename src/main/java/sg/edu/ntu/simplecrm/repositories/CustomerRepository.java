package sg.edu.ntu.simplecrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.ntu.simplecrm.entities.Customer;

import java.util.List;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Custom query to find all customers with a certain first name
    List<Customer> findByFirstName(String firstName);
}
