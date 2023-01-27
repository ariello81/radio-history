package pl.ryzykowski.radiohistory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.ryzykowski.radiohistory.dto.ArtistDTO;
import pl.ryzykowski.radiohistory.service.ArtistService;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    private ArtistService artistServiceOdsluchane;

    @Autowired
    public ArtistController(ArtistService artistServiceOdsluchane) {
        this.artistServiceOdsluchane = artistServiceOdsluchane;
    }

    @GetMapping("/{artist}/{stationId}/{year}/{month}")
    public ArtistDTO artistSongsForStationYearAndMonth(@PathVariable("artist") String artist,
                                                       @PathVariable("stationId") String stationId,
                                                       @PathVariable("year") String year,
                                                       @PathVariable("month") String month){
        return artistServiceOdsluchane.artistSongsForStationYearAndMonth(stationId, artist, year, month);
    }


    @GetMapping
    public ArtistDTO artistSongsForStationAndDateRange(@RequestParam("artist") String artist,
                                                       @RequestParam("stationId") String stationId,
                                                       @RequestParam("dateFrom") String dateFrom,
                                                       @RequestParam("dateTo") String dateTo){
        return artistServiceOdsluchane.artistSongsForStationAndDateRange(stationId, dateFrom, dateTo, artist);
    }


    @GetMapping("/dump")
    public void artistSongsForStationAndYears(@RequestParam("artist") String artist,
                                              @RequestParam("stationId") String stationId,
                                              @RequestParam("yearStart") String yearStart,
                                              @RequestParam("yearStop") String yearStop) {
        artistServiceOdsluchane.artistSongsForStationAndYears(stationId, artist, yearStart, yearStop);
    }

}
