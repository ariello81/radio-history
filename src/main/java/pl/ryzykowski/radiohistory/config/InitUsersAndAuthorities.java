package pl.ryzykowski.radiohistory.config;

import org.springframework.boot.CommandLineRunner;
import pl.ryzykowski.radiohistory.entity.Authority;
import pl.ryzykowski.radiohistory.entity.Station;
import pl.ryzykowski.radiohistory.entity.User;
import pl.ryzykowski.radiohistory.repository.AuthorityRepository;
import pl.ryzykowski.radiohistory.repository.UserRepository;

import java.util.List;

public class InitUsersAndAuthorities implements CommandLineRunner {

    private UserRepository userRepository;

    private AuthorityRepository authorityRepository;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findAll().isEmpty()) {
            List<User> users = initUsers();
            System.out.println(users.size());
        }
        if (authorityRepository.findAll().isEmpty()) {
            List<Authority> authorities = initAuthorities();
            System.out.println(authorities.size());
        }
    }

    private List<User> initUsers() {
        return null;
    }

    private List<Authority> initAuthorities() {
        return null;
    }
}
