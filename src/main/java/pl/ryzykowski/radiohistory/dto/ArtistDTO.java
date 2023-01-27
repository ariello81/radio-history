package pl.ryzykowski.radiohistory.dto;

import java.util.LinkedHashMap;
import java.util.List;

public class ArtistDTO {

    private StationDTO stationDTO;
    private String dateFrom;
    private String dateTo;
    private String artist;
    private LinkedHashMap<String, Long> distinctTitles;
    private List<SongDTO> songDTOS;

    public ArtistDTO() {
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

    public List<SongDTO> getSongDTOS() {
        return songDTOS;
    }

    public void setSongDTOS(List<SongDTO> songDTOS) {
        this.songDTOS = songDTOS;
    }

    public LinkedHashMap<String, Long> getDistinctTitles() {
        return distinctTitles;
    }

    public void setDistinctTitles(LinkedHashMap<String, Long> distinctTitles) {
        this.distinctTitles = distinctTitles;
    }
}
