package pl.ryzykowski.radiohistory.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.ryzykowski.radiohistory.dto.StationDTO;
import pl.ryzykowski.radiohistory.entity.Station;
import pl.ryzykowski.radiohistory.repository.StationRepository;
import pl.ryzykowski.radiohistory.service.StationService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class StationServiceOdsluchaneTest {

    @InjectMocks
    StationServiceOdsluchane stationServiceOdsluchane;

    @Mock
    StationRepository stationRepository;

    @Test
    void getAllStationsTest() {

        //given
        List<Station> stations = new ArrayList<>();
        stations.add(new Station(1L, "1", "Radio Zet"));
        stations.add(new Station(2L, "2", "RMF FM"));
        stations.add(new Station(3L, "160", "RMF Ballady"));

        List<StationDTO> stationsDTO = new ArrayList<>();
        stationsDTO.add(new StationDTO("1", "Radio Zet"));
        stationsDTO.add(new StationDTO("2", "RMF FM"));
        stationsDTO.add(new StationDTO("160", "RMF Ballady"));

        given(stationRepository.findAll()).willReturn(stations);

        //when
        List<StationDTO> allStations = stationServiceOdsluchane.getAllStations();

        //then
        assertEquals(allStations.size(), 3);
        assertEquals(allStations, stationsDTO);
    }
}