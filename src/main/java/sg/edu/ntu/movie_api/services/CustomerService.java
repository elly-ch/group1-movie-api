package sg.edu.ntu.movie_api.services;

import java.util.ArrayList;

import sg.edu.ntu.movie_api.entities.Customer;
import sg.edu.ntu.movie_api.entities.Interaction;

public interface CustomerService {
    
    Customer createCustomer(Customer customer);

    Customer getCustomer(Long id);

    ArrayList<Customer> getAllCustomers();

    Customer updateCustomer(Long id, Customer customer);

    void deleteCustomer(Long id);

    Interaction addInteractionToCustomer(Long id, Interaction interaction);

    ArrayList<Customer> searchCustomers(String firstName);
}
