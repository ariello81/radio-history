package pl.ryzykowski.radiohistory.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import pl.ryzykowski.radiohistory.config.ConfigOdsluchane;
import pl.ryzykowski.radiohistory.entity.Station;
import pl.ryzykowski.radiohistory.repository.StationRepository;
import pl.ryzykowski.radiohistory.service.InitStationsService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InitStationServiceImpl implements InitStationsService, CommandLineRunner {

    private ConfigOdsluchane configOdsluchane;

    private StationRepository stationRepository;

    @Autowired
    public InitStationServiceImpl(ConfigOdsluchane configOdsluchane, StationRepository stationRepository) {
        this.configOdsluchane = configOdsluchane;
        this.stationRepository = stationRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("jestem");
        if (stationRepository.findAll().isEmpty()) {
            List<Station> stationEntities = initStations();
            System.out.println(stationEntities.size());
        }
    }

    @Override
    public List<Station> initStations() {
        List<Station> stations = configOdsluchane.getStations().stream()
                .map(station -> new Station(station.getId(), station.getName()))
                .collect(Collectors.toList());
        List<Station> stationEntities = stationRepository.saveAll(stations);
        return stationEntities;
    }
}
