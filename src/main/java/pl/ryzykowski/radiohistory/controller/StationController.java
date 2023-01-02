package pl.ryzykowski.radiohistory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ryzykowski.radiohistory.dto.Song;
import pl.ryzykowski.radiohistory.dto.Station;
import pl.ryzykowski.radiohistory.service.HistoryService;

import java.util.List;

@RestController
@RequestMapping("/stations")
public class StationController {

    private HistoryService historyServiceOdsluchane;

    @Autowired
    public StationController(HistoryService historyServiceOdsluchane) {
        this.historyServiceOdsluchane = historyServiceOdsluchane;
    }

    @GetMapping
    public List<Station> getAllStations() {
        return historyServiceOdsluchane.getAllStations();
    }

    @GetMapping("/{id}/{dateFrom}/{dateTo}")
    public List<Song> songsStationForDateRange(@PathVariable("id") String stationId,
                                               @PathVariable("dateFrom") String dateFrom,
                                               @PathVariable("dateTo") String dateTo){
        return historyServiceOdsluchane.songsStationForDateRange(stationId, dateFrom, dateTo);
    }


}
