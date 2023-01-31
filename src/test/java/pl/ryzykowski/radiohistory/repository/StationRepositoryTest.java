package pl.ryzykowski.radiohistory.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.ryzykowski.radiohistory.entity.Station;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@DataJpaTest
class StationRepositoryTest {

    @Autowired
    StationRepository stationRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    public void init(){
        entityManager.persist(new Station("1", "RadioZet"));
        entityManager.flush();
    }

    @Test
    void should_find_one_by_url_id() {
        //when
        Station station = stationRepository.findOneByUrlId("1");
        //then
        assertThat(station.getUrlId()).isEqualTo("1");
    }

    @Test
    void should_not_find_one_by_url_id() {
        //when
        Station station = stationRepository.findOneByUrlId("2");
        //then
        assertThat(station).isNull();
    }

    @Test
    void should_find_all_stations(){
        //when
        List<Station> stations = stationRepository.findAll();
        //then
        assertThat(stations).hasSize(1);
    }
}