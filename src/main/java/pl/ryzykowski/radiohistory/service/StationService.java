package pl.ryzykowski.radiohistory.service;

import pl.ryzykowski.radiohistory.dto.StationDTO;

import java.util.List;

public interface StationService {

    List<StationDTO> getAllStations();

}
