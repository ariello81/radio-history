package pl.ryzykowski.radiohistory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ryzykowski.radiohistory.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
