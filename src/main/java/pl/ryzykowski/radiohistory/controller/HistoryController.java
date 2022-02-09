package pl.ryzykowski.radiohistory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.ryzykowski.radiohistory.dto.Song;
import pl.ryzykowski.radiohistory.dto.StationArtistSummary;
import pl.ryzykowski.radiohistory.service.HistoryService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HistoryController {

    private HistoryService historyServiceOdsluchane;

    @Autowired
    public HistoryController(HistoryService historyServiceOdsluchane) {
        this.historyServiceOdsluchane = historyServiceOdsluchane;
    }


    @GetMapping("/{stationId}/{dateFrom}/{dateTo}")
    public List<Song> songsStationForDateRange(@PathVariable("stationId") String stationId,
                                               @PathVariable("dateFrom") String dateFrom,
                                               @PathVariable("dateTo") String dateTo){
        return historyServiceOdsluchane.songsStationForDateRange(stationId, dateFrom, dateTo);
    }

    @GetMapping("/{stationId}/{dateFrom}/{dateTo}/{artist}")
    public StationArtistSummary songsStationForDateRangeAndArtist(@PathVariable("stationId") String stationId,
                                                                  @PathVariable("dateFrom") String dateFrom,
                                                                  @PathVariable("dateTo") String dateTo,
                                                                  @PathVariable("artist") String artist){
        return historyServiceOdsluchane.songsStationForDateRangeAndArtist(stationId, dateFrom, dateTo, artist);
    }

    @GetMapping
    public StationArtistSummary songsStationForArtistYearAndMonth(@RequestParam("stationId") String stationId,
                                                                  @RequestParam("artist") String artist,
                                                                  @RequestParam("year") String year,
                                                                  @RequestParam("month") String month) {
        return historyServiceOdsluchane.songsStationForArtistYearAndMonth(stationId, artist, year, month);
    }

    @GetMapping("/dump")
    public void songsStationForArtistAndYears(@RequestParam("stationId") String stationId,
                                                                  @RequestParam("artist") String artist,
                                                                  @RequestParam("yearStart") String yearStart,
                                                                  @RequestParam("yearStop") String yearStop) {
        historyServiceOdsluchane.songsStationForArtistAndYears(stationId, artist, yearStart, yearStop);
    }



}
