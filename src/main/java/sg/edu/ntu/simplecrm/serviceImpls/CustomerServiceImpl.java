package sg.edu.ntu.simplecrm.serviceImpls;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import sg.edu.ntu.simplecrm.entities.Customer;
import sg.edu.ntu.simplecrm.entities.Interaction;
import sg.edu.ntu.simplecrm.exceptions.CustomerNotFoundException;
import sg.edu.ntu.simplecrm.repositories.CustomerRepository;
import sg.edu.ntu.simplecrm.repositories.InteractionRepository;
import sg.edu.ntu.simplecrm.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
    
    private CustomerRepository customerRepository;
    private InteractionRepository interactionRepository;

    // @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, InteractionRepository interactionRepository) {
        this.customerRepository = customerRepository;
        this.interactionRepository = interactionRepository;
    }

    @Override
    public ArrayList<Customer> searchCustomers(String firstName) {
        List<Customer> foundCustomers = customerRepository.findByFirstName(firstName);
        return (ArrayList<Customer>) foundCustomers;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        Customer newCustomer = customerRepository.save(customer);
        return newCustomer;
    }

    @Override
    public Customer getCustomer(Long id) {
        // Optional<Customer> optionalCustomer = customerRepository.findById(id);
        // if(optionalCustomer.isPresent()) {
        //     Customer foundCustomer = optionalCustomer.get();
        //     return foundCustomer;
        // }
        // throw new CustomerNotFoundException(id);
        return customerRepository.findById(id).orElseThrow(()-> new CustomerNotFoundException(id));
    }

    @Override
    public ArrayList<Customer> getAllCustomers() {
        List<Customer> allCustomers = customerRepository.findAll();
        return (ArrayList<Customer>) allCustomers;
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        // retrieve the customer from the database
        // [Activity 1 - Refactor code]
        Customer customerToUpdate = customerRepository.findById(id).orElseThrow(()-> new CustomerNotFoundException(id));
        // update the customer retrieved from the database
        customerToUpdate.setFirstName(customer.getFirstName());
        customerToUpdate.setLastName(customer.getLastName());
        customerToUpdate.setEmail(customer.getEmail());
        customerToUpdate.setContactNo(customer.getContactNo());
        customerToUpdate.setJobTitle(customer.getJobTitle());
        customerToUpdate.setYearOfBirth(customer.getYearOfBirth());
        // save the updated customer back to the database
        return customerRepository.save(customerToUpdate);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Interaction addInteractionToCustomer(Long id, Interaction interaction) {
        // retrieve the customer from the database
        // [Activity 1 - Refactor code]
        Customer selectedCustomer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));

        // add the customer to the interaction
        interaction.setCustomer(selectedCustomer);
        // save the interaction to the database
        return interactionRepository.save(interaction);
    }

    

}
