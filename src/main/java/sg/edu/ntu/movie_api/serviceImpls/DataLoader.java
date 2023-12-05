package sg.edu.ntu.movie_api.serviceImpls;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class DataLoader {
    private UserMovieRepository userMovieRepository;

    // @Autowired
    public DataLoader(UserMovieRepository userMovieRepository) {
        this.userMovieRepository = userMovieRepository;
    }

    @PostConstruct
    public void loadData() {
        // clear the database first
        userMovieRepository.deleteAll();

        // load data here
        userMovieRepository.save(new UserMovie(1, 1));
        userMovieRepository.save(new UserMovie(1, 2));
        userMovieRepository.save(new UserMovie(2, 3));
        userMovieRepository.save(new UserMovie(2, 1));
    }
}
