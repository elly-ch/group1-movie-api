package sg.edu.ntu.movie_api.serviceImpls;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import sg.edu.ntu.movie_api.entities.User;
import sg.edu.ntu.movie_api.entities.UserMovie;
import sg.edu.ntu.movie_api.exceptions.UserNotFoundException;
import sg.edu.ntu.movie_api.repositories.UserRepository;
import sg.edu.ntu.movie_api.repositories.UserMovieRepository;
import sg.edu.ntu.movie_api.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    
    private UserRepository userRepository;
    private UserMovieRepository userMovieRepository;

    // @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMovieRepository userMovieRepository) {
        this.userRepository = userRepository;
        this.userMovieRepository = userMovieRepository;
    }

    @Override
    public ArrayList<User> searchUsers(String userId) {
        List<User> foundUsers = userRepository.findByUserId(userId);
        return (ArrayList<User>) foundUsers;
    }

    //@Override
    public User createUser(User user) {
        User newUser = userRepository.save(user);
        return newUser;
    }

    @Override
    public User getUser(String userId) {
        // Note to self: findById (from JPA) takes in the primary key
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Override
    public ArrayList<User> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        return (ArrayList<User>) allUsers;
    }

    @Override
    public User updateUser(String userId, User user) {
        // retrieve the customer from the database
        User userToUpdate = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        
        // update the customer retrieved from the database
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setName(user.getName());

        // save the updated customer back to the database
        return userRepository.save(userToUpdate);
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId); // deleteById takes in the primary key
    }

    @Override
    public UserMovie addUserMovieToUser(String userId, UserMovie userMovie) {
        // TODO: debug this method
        // retrieve the user from the database
        User selectedUser = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        // add the User to the UserMovie
        userMovie.setUserId(selectedUser); // needs to be implemented in UserMovie.java

        // save the UserMovie to the database
        return userMovieRepository.save(userMovie);
    }

    

}
