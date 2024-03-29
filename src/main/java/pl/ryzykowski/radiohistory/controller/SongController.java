package pl.ryzykowski.radiohistory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    @PreAuthorize("#stationId == authentication.name")
    public List<SongDTO> songsStationForDateRange(@PathVariable("stationId") String stationId,
                                                  @PathVariable("dateFrom") String dateFrom,
                                                  @PathVariable("dateTo") String dateTo){
        return songServiceOdsluchane.songsStationForDateRange(stationId, dateFrom, dateTo);
    }

    @GetMapping("/{dateFrom}/{dateTo}")
    public List<SongDTO> songsForDateRangeLoggedStation(
                                                  @PathVariable("dateFrom") String dateFrom,
                                                  @PathVariable("dateTo") String dateTo){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return songServiceOdsluchane.songsStationForDateRange(userDetails.getUsername(), dateFrom, dateTo);
    }

}
