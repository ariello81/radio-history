package pl.ryzykowski.radiohistory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ryzykowski.radiohistory.entity.Station;

public interface StationRepository extends JpaRepository<Station, Long> {

    Station findOneByUrlId(String urlId);

}
