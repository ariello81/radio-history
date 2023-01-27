package pl.ryzykowski.radiohistory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.ryzykowski.radiohistory.dto.SongDTO;
import pl.ryzykowski.radiohistory.service.SongService;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {

    private SongService songServiceOdsluchane;

    @Autowired
    public SongController(SongService songServiceOdsluchane) {
        this.songServiceOdsluchane = songServiceOdsluchane;
    }


    @GetMapping("/{stationId}/{dateFrom}/{dateTo}")
    public List<SongDTO> songsStationForDateRange(@PathVariable("stationId") String stationId,
                                                  @PathVariable("dateFrom") String dateFrom,
                                                  @PathVariable("dateTo") String dateTo){
        return songServiceOdsluchane.songsStationForDateRange(stationId, dateFrom, dateTo);
    }



}
