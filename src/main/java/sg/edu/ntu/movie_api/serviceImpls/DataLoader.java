package sg.edu.ntu.movie_api.serviceImpls;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import sg.edu.ntu.movie_api.entities.User;
import sg.edu.ntu.movie_api.repositories.UserRepository;

@Component
public class DataLoader {
    private UserRepository userRepository;


    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @PostConstruct
    public void loadData() {
        // clear the database first
        userRepository.deleteAll();

        // load data here
        userRepository.save(new User(1L, "user1@gmail.com", "password1", "name1"));
        userRepository.save(new User(2L, "user2@outlook.com", "password2", "name2"));
        userRepository.save(new User(3L, "user3@yahoo.com", "password3", "name3"));
    }
}