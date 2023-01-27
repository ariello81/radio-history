package pl.ryzykowski.radiohistory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ryzykowski.radiohistory.dto.StationDTO;
import pl.ryzykowski.radiohistory.service.StationService;

import java.util.List;

@RestController
@RequestMapping("/stations")
public class StationController {

    private StationService stationServiceOdsluchane;

    @Autowired
    public StationController(StationService stationServiceOdsluchane) {
        this.stationServiceOdsluchane = stationServiceOdsluchane;
    }

    @GetMapping
    public List<StationDTO> getAllStations() {
        return stationServiceOdsluchane.getAllStations();
    }




}
