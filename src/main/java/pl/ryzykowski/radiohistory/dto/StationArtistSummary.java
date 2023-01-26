package pl.ryzykowski.radiohistory.dto;

import java.util.LinkedHashMap;
import java.util.List;

public class StationArtistSummary {

    private StationDTO stationDTO;
    private String dateFrom;
    private String dateTo;
    private String artist;
    private LinkedHashMap<String, Long> distinctTitles;
    private List<Song> songs;

    public StationArtistSummary() {
    }

    public StationDTO getStation() {
        return stationDTO;
    }

    public void setStation(StationDTO stationDTO) {
        this.stationDTO = stationDTO;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public LinkedHashMap<String, Long> getDistinctTitles() {
        return distinctTitles;
    }

    public void setDistinctTitles(LinkedHashMap<String, Long> distinctTitles) {
        this.distinctTitles = distinctTitles;
    }
}
