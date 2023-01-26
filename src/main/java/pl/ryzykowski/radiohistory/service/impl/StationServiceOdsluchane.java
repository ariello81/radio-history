package pl.ryzykowski.radiohistory.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ryzykowski.radiohistory.dto.StationDTO;
import pl.ryzykowski.radiohistory.repository.StationRepository;
import pl.ryzykowski.radiohistory.service.StationService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StationServiceOdsluchane implements StationService {

    private StationRepository stationRepository;

    @Autowired
    public StationServiceOdsluchane(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @Override
    public List<StationDTO> getAllStations() {
        return stationRepository.findAll().stream()
                .map(station -> new StationDTO(station.getUrlId(), station.getName()))
                .collect(Collectors.toList());
    }
}
