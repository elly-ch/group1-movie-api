package sg.edu.ntu.movie_api.serviceImpls;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import sg.edu.ntu.movie_api.entities.Customer;
import sg.edu.ntu.movie_api.repositories.CustomerRepository;

@Component
public class DataLoader {
    private CustomerRepository customerRepository;

    // @Autowired
    public DataLoader(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostConstruct
    public void loadData() {
        // clear the database first
        customerRepository.deleteAll();

        // load data here
        // [Activity 2 - validation]
        customerRepository.save(new Customer("Bruce", "Banner", "12345678", 1972));
        customerRepository.save(new Customer("Peter","Parker", "12345677", 2005));
        customerRepository.save(new Customer("Stephen", "Strange", "12345678", 1976));
        // customerRepository.save(new Customer("Steve", "Rogers", "12345678", 1940));
    }
}
