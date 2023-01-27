package pl.ryzykowski.radiohistory.service;

import pl.ryzykowski.radiohistory.dto.SongDTO;

import java.util.List;

public interface SongService {

    List<SongDTO> songsStationForDateRange(String station, String dateFrom, String dateTo);



}
