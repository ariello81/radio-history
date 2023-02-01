package pl.ryzykowski.radiohistory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ryzykowski.radiohistory.entity.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
