package pl.ryzykowski.radiohistory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ryzykowski.radiohistory.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
