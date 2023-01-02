package pl.ryzykowski.radiohistory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ryzykowski.radiohistory.dto.StationArtistSummary;
import pl.ryzykowski.radiohistory.service.HistoryService;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    private HistoryService historyServiceOdsluchane;

    @Autowired
    public ArtistController(HistoryService historyServiceOdsluchane) {
        this.historyServiceOdsluchane = historyServiceOdsluchane;
    }

    @GetMapping("/{artist}/{stationId}/{year}/{month}")
    public StationArtistSummary artistSummaryForStationYearAndMonth(@PathVariable("artist") String artist,
                                                                @PathVariable("stationId") String stationId,
                                                                @PathVariable("year") String year,
                                                                @PathVariable("month") String month){
        return historyServiceOdsluchane.songsStationForArtistYearAndMonth(stationId, artist, year, month);
    }
}
