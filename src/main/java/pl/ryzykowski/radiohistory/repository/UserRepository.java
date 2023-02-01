package pl.ryzykowski.radiohistory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ryzykowski.radiohistory.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
}
