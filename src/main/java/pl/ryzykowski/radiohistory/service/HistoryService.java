package pl.ryzykowski.radiohistory.service;

import pl.ryzykowski.radiohistory.dto.Song;
import pl.ryzykowski.radiohistory.dto.StationArtistSummary;

import java.util.List;

public interface HistoryService {

    List<Song> songsStationForDateRange(String station, String dateFrom, String dateTo);

    StationArtistSummary songsStationForDateRangeAndArtist(String station, String dateFrom, String dateTo, String artist);

    StationArtistSummary songsStationForArtistYearAndMonth(String stationId, String artist, String year, String month);

    void songsStationForArtistAndYears(String stationId, String artist, String yearStart, String yearStop);

}
