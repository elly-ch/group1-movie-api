package sg.edu.ntu.simplecrm.services;

import java.util.ArrayList;

import sg.edu.ntu.simplecrm.entities.Customer;
import sg.edu.ntu.simplecrm.entities.Interaction;

public interface CustomerService {
    
    Customer createCustomer(Customer customer);

    Customer getCustomer(Long id);

    ArrayList<Customer> getAllCustomers();

    Customer updateCustomer(Long id, Customer customer);

    void deleteCustomer(Long id);

    Interaction addInteractionToCustomer(Long id, Interaction interaction);

    ArrayList<Customer> searchCustomers(String firstName);
}
