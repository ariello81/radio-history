package pl.ryzykowski.radiohistory.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.ryzykowski.radiohistory.entity.Authority;
import pl.ryzykowski.radiohistory.entity.Station;
import pl.ryzykowski.radiohistory.entity.User;
import pl.ryzykowski.radiohistory.repository.AuthorityRepository;
import pl.ryzykowski.radiohistory.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InitUsersAndAuthorities implements CommandLineRunner {

    private UserRepository userRepository;

    private AuthorityRepository authorityRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public InitUsersAndAuthorities(UserRepository userRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findAll().isEmpty() && authorityRepository.findAll().isEmpty()) {
            List<User> users = initUsersAndAuthorities();
            System.out.println(users.size());
        }
    }

    private List<User> initUsersAndAuthorities() {
        List<Authority> authorities = new ArrayList<>();
        authorities.add(new Authority(1L, "read"));
        authorities = authorityRepository.saveAll(authorities);
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "user", passwordEncoder.encode("12345"), new HashSet<>(authorities)));
        users.add(new User(2L, "106", passwordEncoder.encode("106"), new HashSet<>(authorities)));
        users.add(new User(3L, "154", passwordEncoder.encode("154"), new HashSet<>(authorities)));
        users.add(new User(4L, "160", passwordEncoder.encode("160"), new HashSet<>(authorities)));
        users = userRepository.saveAll(users);
        users.stream().forEach(u -> System.out.println(u.getPassword()));
        return users;
    }


}
