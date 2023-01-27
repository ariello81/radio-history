package pl.ryzykowski.radiohistory.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import pl.ryzykowski.radiohistory.entity.Station;
import pl.ryzykowski.radiohistory.repository.StationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InitStationServiceOdsluchane implements CommandLineRunner {

    private ConfigOdsluchane configOdsluchane;

    private StationRepository stationRepository;

    @Autowired
    public InitStationServiceOdsluchane(ConfigOdsluchane configOdsluchane, StationRepository stationRepository) {
        this.configOdsluchane = configOdsluchane;
        this.stationRepository = stationRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (stationRepository.findAll().isEmpty()) {
            List<Station> stationEntities = initStations();
            System.out.println(stationEntities.size());
        }
    }

    public List<Station> initStations() {
        List<Station> stations = configOdsluchane.getStations().stream()
                .map(station -> new Station(station.getId(), station.getName()))
                .collect(Collectors.toList());
        List<Station> stationEntities = stationRepository.saveAll(stations);
        return stationEntities;
    }
}
