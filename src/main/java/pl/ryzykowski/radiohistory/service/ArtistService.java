package pl.ryzykowski.radiohistory.service;

import pl.ryzykowski.radiohistory.dto.ArtistDTO;

public interface ArtistService {

    ArtistDTO artistSongsForStationAndDateRange(String station, String dateFrom, String dateTo, String artist);

    ArtistDTO artistSongsForStationYearAndMonth(String stationId, String artist, String year, String month);

    void artistSongsForStationAndYears(String stationId, String artist, String yearStart, String yearStop);

}
